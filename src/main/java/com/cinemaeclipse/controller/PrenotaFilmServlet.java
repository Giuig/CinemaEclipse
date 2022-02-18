package com.cinemaeclipse.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cinemaeclipse.dao.DaoFactory;
import com.cinemaeclipse.model.Prenotazione;
import com.cinemaeclipse.model.Spettacolo;
import com.cinemaeclipse.model.Utente;
import com.cinemaeclipse.util.CustomerServices;
import com.cinemaeclipse.util.EmailUtility;
import com.cinemaeclipse.util.Utility;

/**
 * Servlet implementation class PrenotaFilmServlet
 */
@WebServlet("/PrenotaFilmServlet")
public class PrenotaFilmServlet extends HttpServlet {
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
	public PrenotaFilmServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Spettacolo spettacolo = DaoFactory.getFactory("jpa").getSpettacoloDao().selectById(Integer.parseInt(request.getParameter("spettacolo")));
		request.setAttribute("spettacoloSelezionato", spettacolo);
		HttpSession sessione = request.getSession();
		Utente utente = (Utente)sessione.getAttribute("utente loggato");
		System.out.println(spettacolo);
		request.getRequestDispatcher("WEB-INF/jsp/PrenotaFilm.jsp").forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sessione = request.getSession();
		String id = request.getParameter("spettacoloSelezionato");
		Spettacolo spettacolo=DaoFactory.getFactory("jpa").getSpettacoloDao().selectById(Integer.parseInt(id));
		System.out.println(spettacolo);
		Utente utente = (Utente)sessione.getAttribute("utenteLoggato");
		
		//INSERIRE PAGAMENTO!!!
		
		Prenotazione prenotazione = new Prenotazione(utente, spettacolo);

		try {
			if(DaoFactory.getFactory("jpa").getPrenotazioneDao().add(prenotazione)) {
				prenotazione.addPrenotazioneToUtente(prenotazione);
				request.setAttribute("aggiunta", "Prenotazione confermata");
				String recipient = utente.getEmail();
				String subject = "Prenotazione confermata";

				CustomerServices customerServices = new CustomerServices(request, response);

				String content = "Ciao "+utente.getNome() + ", \n"
						+ "Prenotazione confermata, ecco il riassunto della tua prenotazione: \n"
						+ "Titolo film: "+ spettacolo.getFilm().getTitolo() +"\n"
						+ "Spettacolo: "+ spettacolo.getData() + " ora: "+ spettacolo.getOra() +"\n"
						+ "Per la stampa dei biglietti presentare questo codice in cassa: "+ Utility.numeroRandom();
				//	        content += "\nNote: for security reason, "
				//	                + "you should change your password after logging in.";

				String message = "";
				try {
					EmailUtility.sendEmail(host, port, email, name, pass,
							recipient, subject, content);
					message = "E-mail inviata con successo, controlla la tua casella di posta";
					request.setAttribute("message", message);

				} catch (Exception ex) {
					ex.printStackTrace();
					message = "Errore nell'invio della e-mail: " + ex.getMessage();
					request.setAttribute("message", message);
					request.getRequestDispatcher("WEB-INF/jsp/PrenotaFilm.jsp").forward(request, response);
				} 
			}else {
				request.setAttribute("aggiunta", "Impossibile prenotare: posti in sala esauriti.");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("aggiunta", "Problemi con la prenotazione, riprova");
			request.getRequestDispatcher("WEB-INF/jsp/PrenotaFilm.jsp").forward(request, response);
		}

		request.getRequestDispatcher("WEB-INF/jsp/PrenotaFilm.jsp").forward(request, response);

	}
}
