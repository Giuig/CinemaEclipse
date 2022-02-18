package com.cinemaeclipse.controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
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

/**
 * Servlet implementation class CancellaPrenotazioneServlet
 */
@WebServlet("/EliminaPrenotazioneServlet")
public class EliminaPrenotazioneServlet extends HttpServlet {
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
    public EliminaPrenotazioneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		Spettacolo spettacolo = DaoFactory.getFactory("jpa").getSpettacoloDao().selectById(Integer.parseInt(request.getParameter("spettacolo")));
//		request.setAttribute("spettacoloSelezionato", spettacolo);
//		HttpSession sessione = request.getSession();
//		Utente utente = (Utente)sessione.getAttribute("utente loggato");
//		System.out.println(spettacolo);
//		request.getRequestDispatcher("WEB-INF/jsp/CancellaPrenotazione.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sessione = request.getSession();
		Utente utente = (Utente)sessione.getAttribute("utenteLoggato");
		
		try {
			
			String recipient = utente.getEmail();
			String subject = "Prenotazione cancellata";

			CustomerServices customerServices = new CustomerServices(request, response);

			String content = "Ciao "+utente.getNome() + ", \n"
					+ "La tua prenotazione è stata cancellata \n "
					;

			String message = "";
			Integer id = Integer.parseInt(request.getParameter("idPrenotazione"));
			Prenotazione prenotazione = DaoFactory.getFactory("jpa").getPrenotazioneDao().selectById(id);
			DaoFactory.getFactory("jpa").getPrenotazioneDao().delete(prenotazione);
			request.setAttribute("cancellazione", "Prenotazione cancellata con successo, ti arriverà una email di conferma");
			EmailUtility.sendEmail(host, port, email, name, pass,recipient, subject, content);
				
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			request.setAttribute("cancellazione", "Prenotazione non cancellata");

			e.printStackTrace();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			request.setAttribute("cancellazione", "Prenotazione non cancellata");
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			request.setAttribute("cancellazione", "Prenotazione non cancellata");
			e.printStackTrace();
		}
		
		request.setAttribute("utente", utente);
		List<Prenotazione> listaPrenotazioni = DaoFactory.getFactory("jpa").getPrenotazioneDao().selectPrenotazioniByUtente(utente);
		request.setAttribute("listaPrenotazioni", listaPrenotazioni);
		
		request.getRequestDispatcher("/WEB-INF/jsp/Utente.jsp").forward(request, response);
	}}


