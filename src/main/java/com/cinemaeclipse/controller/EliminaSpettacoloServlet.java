package com.cinemaeclipse.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinemaeclipse.dao.DaoFactory;
import com.cinemaeclipse.model.Prenotazione;
import com.cinemaeclipse.model.Sala;
import com.cinemaeclipse.model.Spettacolo;
import com.cinemaeclipse.util.EmailUtility;

/**
 * Servlet implementation class EliminaSpettacoloServlet
 */
@WebServlet("/EliminaSpettacoloServlet")
public class EliminaSpettacoloServlet extends HttpServlet {
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
    public EliminaSpettacoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String subject = "cancellazione spettacolo";
		String message = "";
		Integer idSpettacolo = Integer.parseInt(request.getParameter("id"));
		Spettacolo spettacolo = (Spettacolo)DaoFactory.getFactory("jpa").getSpettacoloDao().selectById(idSpettacolo);
		List<Sala> listaSale = DaoFactory.getFactory("jpa").getSalaDao().selectAll();
		try {
			DaoFactory.getFactory("jpa").getSpettacoloDao().delete(spettacolo);
			List<Prenotazione> listaPrenotazioni = DaoFactory.getFactory("jpa").getPrenotazioneDao().selectListaPrenotazioniBySpettacolo(spettacolo);
			for(Prenotazione p : listaPrenotazioni) {
				String recipient = p.getUtente().getEmail();
				String content = "Ciao "+p.getUtente().getNome() + ", \n"
						+ "Ti informiamo che lo spettacolo da te prenotato per il film: "+spettacolo.getFilm().getTitolo() 
						+" in data: "+p.getSpettacolo().getData()
						+" è stato cancellato"; 
				 EmailUtility.sendEmail(host, port, email, name, pass, recipient, subject, content);
				
				System.out.println(recipient);
			}
			request.setAttribute("eliminato", "Spettacolo eliminato correttamente");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("eliminato", "Errore. Spettacolo non eliminato");

			e.printStackTrace();
		}
		request.setAttribute("spettacolo",spettacolo);
		request.setAttribute("sala", listaSale);
		request.getRequestDispatcher("WEB-INF/jsp/ModificaSpettacolo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}
