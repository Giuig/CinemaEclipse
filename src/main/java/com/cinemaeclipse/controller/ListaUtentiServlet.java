package com.cinemaeclipse.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinemaeclipse.dao.DaoFactory;
import com.cinemaeclipse.model.Film;
import com.cinemaeclipse.model.Ruolo;
import com.cinemaeclipse.model.Utente;

/**
 * Servlet implementation class ListaUtentiServlet
 */
@WebServlet("/ListaUtentiServlet")
public class ListaUtentiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaUtentiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Utente> listaUtenti=DaoFactory.getFactory("jpa").getUtenteDao().selectAll();
		request.setAttribute("listaUtenti", listaUtenti);
		request.getRequestDispatcher("WEB-INF/jsp/ListaUtenti.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idUtente=Integer.parseInt(request.getParameter("id"));
		Utente utente= DaoFactory.getFactory("jpa").getUtenteDao().selectById(idUtente);
		if(utente.getRuolo().getIdRuolo()==1) {
			Ruolo ruolo= DaoFactory.getFactory("jpa").getRuoloDao().selectById(4);
			utente.setRuolo(ruolo);
			DaoFactory.getFactory("jpa").getUtenteDao().update(utente);
		}
		else if(utente.getRuolo().getIdRuolo()==4) {
			Ruolo ruolo= DaoFactory.getFactory("jpa").getRuoloDao().selectById(1);
			utente.setRuolo(ruolo);
			DaoFactory.getFactory("jpa").getUtenteDao().update(utente);
		}
		
		doGet(request, response);
	}

}
