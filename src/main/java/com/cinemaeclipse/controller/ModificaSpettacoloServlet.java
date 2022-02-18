package com.cinemaeclipse.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinemaeclipse.dao.DaoFactory;
import com.cinemaeclipse.model.Film;
import com.cinemaeclipse.model.Prenotazione;
import com.cinemaeclipse.model.Sala;
import com.cinemaeclipse.model.Spettacolo;
import com.cinemaeclipse.util.EmailUtility;
import com.cinemaeclipse.util.Utility;

/**
 * Servlet implementation class ModificaSpettacoloServlet
 */
@WebServlet("/ModificaSpettacoloServlet")
public class ModificaSpettacoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String host;
	private String port;
	private String email;
	private String name;
	private String pass;

	public void init() {
		// reads SMTP server setting from web.xml file
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		email = context.getInitParameter("email");
		name = context.getInitParameter("name");
		pass = context.getInitParameter("pass");
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaSpettacoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Sala> listaSale = DaoFactory.getFactory("jpa").getSalaDao().selectAll();

		Integer idSpettacolo = Integer.parseInt(request.getParameter("spettacolo"));
		Spettacolo spettacolo = DaoFactory.getFactory("jpa").getSpettacoloDao().selectById(idSpettacolo);
		request.setAttribute("spettacolo", spettacolo);				
		request.setAttribute("sala",listaSale);
		
		request.getRequestDispatcher("WEB-INF/jsp/ModificaSpettacolo.jsp").forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String subject = "modifica spettacolo";
		String message = "";
		List<Sala> listaSale = new ArrayList<Sala>();

		
		int idFilm = Integer.parseInt(request.getParameter("id"));
		int idSpettacolo = Integer.parseInt(request.getParameter("idSpettacolo"));
		Film film = DaoFactory.getFactory("jpa").getFilmDao().selectById(idFilm);


		int idSala = Integer.parseInt(request.getParameter("sala"));
		Sala sala = DaoFactory.getFactory("jpa").getSalaDao().selectById(idSala);
		System.out.println(sala);
		LocalDate dataSpettacolo = LocalDate.parse(request.getParameter("dataSpettacolo"));
		LocalTime oraSpettacolo = LocalTime.parse(request.getParameter("orario"));

		double prezzo = 8.0;

		Spettacolo spettacolo = new Spettacolo(idSpettacolo, film, sala, dataSpettacolo, oraSpettacolo, prezzo);
		
		
		
		if(DaoFactory.getFactory("jpa").getSpettacoloDao().selectBySalaEData(sala, dataSpettacolo).isEmpty()) {
			
			try {
				DaoFactory.getFactory("jpa").getSpettacoloDao().update(spettacolo);
//				spettacolo.addSpettacoloToFilm(spettacolo);
				request.setAttribute("modifica", "Spettacolo modificato con successo");
				List<Prenotazione> listaPrenotazioni = DaoFactory.getFactory("jpa").getPrenotazioneDao().selectListaPrenotazioniBySpettacolo(spettacolo);
				for(Prenotazione p : listaPrenotazioni) {
					String recipient = p.getUtente().getEmail();
					String content = "Ciao "+p.getUtente().getNome() + ", \n"
							+ "Ti informiamo che lo spettacolo da te prenotato per il film: "+spettacolo.getFilm().getTitolo() 
							+"è stato modificato: \n "							
							+ "Nuovi dati spettacolo: \nData:"+ spettacolo.getData() + "\n Ora: "+ spettacolo.getOra()
							+"\nSala: "+ spettacolo.getSala().getNomeSala(); 
					 EmailUtility.sendEmail(host, port, email, name, pass,
			                    recipient, subject, content);
					
					System.out.println(recipient);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				request.setAttribute("modifica", "Problemi nella modifica dello spetacolo");
				
				System.out.println("Spettacolo non modificato");
			}
			listaSale = DaoFactory.getFactory("jpa").getSalaDao().selectAll();

			request.setAttribute("spettacolo", spettacolo);				
			request.setAttribute("sala",listaSale);
			
			
			request.getRequestDispatcher("WEB-INF/jsp/ModificaSpettacolo.jsp").forward(request, response);

		}else {
			if(Utility.inserimentoSpettacolo(spettacolo)) {
				DaoFactory.getFactory("jpa").getSpettacoloDao().update(spettacolo);
				
//				spettacolo.addSpettacoloToFilm(spettacolo);
				request.setAttribute("modifica", "Spettacolo modificato con successo");
				System.out.println("modificato");
			}
			else {
				
				request.setAttribute("modifica", "Questo spettacolo coincide con un altro spettacolo!");
				System.out.println("Non modificato");
			}			
			request.setAttribute("spettacolo", spettacolo);				
			request.setAttribute("sala",listaSale);
			request.getRequestDispatcher("WEB-INF/jsp/ModificaSpettacolo.jsp").forward(request, response);
		}
			
	}

}
