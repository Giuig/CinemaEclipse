package com.cinemaeclipse.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinemaeclipse.dao.DaoFactory;
import com.cinemaeclipse.model.Film;
import com.cinemaeclipse.model.Prenotazione;
import com.cinemaeclipse.model.Spettacolo;
import com.cinemaeclipse.util.EmailUtility;

/**
 * Servlet implementation class EliminaFilmServlet
 */
@WebServlet("/EliminaFilmServlet")
public class EliminaFilmServlet extends HttpServlet {
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
    public EliminaFilmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String subject = "cancellazione spettacolo";
		Film filmDaEliminare = DaoFactory.getFactory("jpa").getFilmDao().selectById(Integer.parseInt(request.getParameter("id")));
		List<Spettacolo> listaSpettacoli = DaoFactory.getFactory("jpa").getSpettacoloDao().selectByFilm(filmDaEliminare);
		
		if(DaoFactory.getFactory("jpa").getFilmDao().selectById(Integer.parseInt(request.getParameter("id")))!=null) {		
			try {
				for(Spettacolo s: listaSpettacoli) {
					List<Prenotazione> listaPrenotazioni = DaoFactory.getFactory("jpa").getPrenotazioneDao().selectListaPrenotazioniBySpettacolo(s);
					
					for(Prenotazione p : listaPrenotazioni) {
						String recipient = p.getUtente().getEmail();
						String content = "Ciao "+p.getUtente().getNome() + ", \n"
								+ "Ti informiamo che lo spettacolo da te prenotato per il film: "+s.getFilm().getTitolo() 
								+" in data: "+p.getSpettacolo().getData()
								+" è stato cancellato"; 
						 EmailUtility.sendEmail(host, port, email, name, pass, recipient, subject, content);
						System.out.println(recipient);
					}
				}
				DaoFactory.getFactory("jpa").getFilmDao().delete(filmDaEliminare);
				
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		if(DaoFactory.getFactory("jpa").getFilmDao().selectById(Integer.parseInt(request.getParameter("id")))==null) {	
			request.setAttribute("filmEliminato", "Film eliminato correttamente");
			System.out.println("eliminato");
			request.getRequestDispatcher("HomeServlet").forward(request, response);
		}
		else {
			request.setAttribute("filmEliminato", "Film non eliminato");
			System.out.println("non eliminato");
			request.getRequestDispatcher("WEB-INF/jsp/Film.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}
