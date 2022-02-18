
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
 * Servlet implementation class CreaStaff
 */
@WebServlet("/CreaStaffServlet")
public class CreaStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreaStaffServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/jsp/CreaStaff.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String email = Utility.generaEmail(nome, cognome);
		String password = Utility.generaPassword(nome, cognome);
		String telefono= request.getParameter("telefono");
		LocalDate dataNascita=LocalDate.parse(request.getParameter("dataNascita"));
		String sesso=request.getParameter("sesso");
		Ruolo ruolo = DaoFactory.getFactory("jpa").getRuoloDao().selectById(2);
		Utente utente = new Utente(nome, cognome, password, email, dataNascita, telefono, sesso, ruolo);
	
		try {
			DaoFactory.getFactory("jpa").getUtenteDao().add(utente);
			utente.addUtenteToRuolo(utente);
			request.setAttribute("aggiunta", "Membro dello staff aggiunto con successo");
			request.setAttribute("email", email);
			request.setAttribute("password", password);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("aggiunta", "Problemi con l'aggiunta del membro dello staff");
			doGet(request, response);
		}
		doGet(request, response);
	}

}