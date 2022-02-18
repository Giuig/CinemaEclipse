package com.cinemaeclipse.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinemaeclipse.dao.DaoFactory;
import com.cinemaeclipse.model.Film;
import com.cinemaeclipse.model.Prenotazione;
import com.cinemaeclipse.model.Utente;

/**
 * Servlet implementation class UtenteServlet
 */
@WebServlet("/UtenteServlet")
public class UtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idUtente = request.getParameter("id");
		int id= Integer.parseInt(idUtente);
		Utente utente = DaoFactory.getFactory("jpa").getUtenteDao().selectById(id);
		request.setAttribute("utente", utente);
		List<Prenotazione> listaPrenotazioni = DaoFactory.getFactory("jpa").getPrenotazioneDao().selectPrenotazioniByUtente(utente);
		request.setAttribute("listaPrenotazioni", listaPrenotazioni);
		System.out.println(utente);
		request.getRequestDispatcher("WEB-INF/jsp/Utente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
