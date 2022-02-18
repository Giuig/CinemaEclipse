package com.cinemaeclipse.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinemaeclipse.dao.DaoFactory;
import com.cinemaeclipse.model.Ruolo;
import com.cinemaeclipse.model.Utente;
import com.cinemaeclipse.util.Utility;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/jsp/Signup.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String telefono=request.getParameter("telefono");
		LocalDate dataNascita=LocalDate.parse(request.getParameter("dataNascita"));
		String sesso=request.getParameter("sesso");
		boolean controllo=true;
		
		if(!Utility.checkPassword(password)) {
			request.setAttribute("passwordErrata", "La password deve avere almeno 8 caratteri, almeno 1 numero, almeno 1 carattere speciale, almeno 1 maiuscola");
			controllo=false;
		}
		
		if(!controllo) {
			request.getRequestDispatcher("WEB-INF/jsp/Signup.jsp").forward(request, response);				
		}
		
		if(Utility.checkEmail(email)&&Utility.checkNome(nome)&&Utility.checkCognome(cognome)&&Utility.checkPassword(password)&&Utility.checkTelefono(telefono)&&Utility.checkSesso(sesso)) {
			if(DaoFactory.getFactory("jpa").getUtenteDao().selectByEmail(email)==null) {
				Ruolo ruolo=DaoFactory.getFactory("jpa").getRuoloDao().selectById(1);
				Utente utente=new Utente(nome, cognome, password, email, dataNascita, telefono, sesso, ruolo);
				DaoFactory.getFactory("jpa").getUtenteDao().add(utente);
				request.setAttribute("utenteRegistrato", "Registrazione avvenuta con successo!");
				request.getRequestDispatcher("WEB-INF/jsp/Signup.jsp").forward(request, response);				
			}
			
			
			else {
				request.setAttribute("utenteDuplicato", "Utente già esistente");
				request.getRequestDispatcher("WEB-INF/jsp/Signup.jsp").forward(request, response);
			}		
		}	
	}
}