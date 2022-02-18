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
import com.cinemaeclipse.model.Genere;

/**
 * Servlet implementation class ModificaFilmServlet
 */
@WebServlet("/ModificaFilmServlet")
public class ModificaFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaFilmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_film = request.getParameter("id");
		int id= Integer.parseInt(id_film);
		Film film = DaoFactory.getFactory("jpa").getFilmDao().selectById(id);
		List<Genere> listaGeneri = DaoFactory.getFactory("jpa").getGenereDao().selectAll();
		request.setAttribute("genere", listaGeneri);
		request.setAttribute("film", film);
		System.out.println(film);
		request.getRequestDispatcher("WEB-INF/jsp/ModificaFilm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_film = request.getParameter("id");
		int id= Integer.parseInt(id_film);
		String titolo = request.getParameter("titolo");		
		int durata = Integer.parseInt(request.getParameter("durata"));
		String regista = request.getParameter("regista");
		String attori = request.getParameter("attori");
		String trama = request.getParameter("trama");
		String locandina = request.getParameter("locandina");
		
		List<Genere> listaGeneri = DaoFactory.getFactory("jpa").getGenereDao().selectAll();
		Film film = new Film(id, titolo, durata, regista, attori, trama, locandina);
		for(Genere g : listaGeneri)
			if(request.getParameter("" + g.getIdGenere()) != null)
				film.addGenere(g);
		
		try {
			request.setAttribute("id_film", id);
			DaoFactory.getFactory("jpa").getFilmDao().update(film);	
			request.setAttribute("modifica", "Film \""+film.getTitolo()+"\" modificato con successo");
			doGet(request, response);
			System.out.println("filmodificato");
		} catch (Exception e) {//controlli da definire: campi non null
			request.setAttribute("controllaTitolo", "Film già inserito");
			doGet(request, response);
			System.out.println("non modificato");
		}
	}
}