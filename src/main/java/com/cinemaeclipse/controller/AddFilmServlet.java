package com.cinemaeclipse.controller;
import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.cinemaeclipse.dao.DaoFactory;
import com.cinemaeclipse.model.Film;
import com.cinemaeclipse.model.Genere;

import javax.servlet.annotation.*;
/* The Java file upload Servlet example */

@WebServlet("/AddFilmServlet")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10,      // 10 MB
		maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
public class AddFilmServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Genere> listaGeneri = DaoFactory.getFactory("jpa").getGenereDao().selectAll();
		request.setAttribute("genere",listaGeneri);
		request.getRequestDispatcher("WEB-INF/jsp/AddFilm.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part filePart = request.getPart("locandina");
		String fileName = filePart.getSubmittedFileName();
		for (Part part : request.getParts()) {
			//CAMBIARE IL PERCORSO CON QUELLO DELLA PROPRIA RES IN TOMCAT!!
			part.write("C:\\Programmazione\\Apache\\wtpwebapps\\CinemaEclipse\\res\\" + fileName);
		}
		String titolo = request.getParameter("titolo");		
		int durata = Integer.parseInt(request.getParameter("durata"));
		String regista = request.getParameter("regista");
		String attori = request.getParameter("attori");
		String trama = request.getParameter("trama");
		String locandina = fileName;
		
		List<Genere> listaGeneri = DaoFactory.getFactory("jpa").getGenereDao().selectAll();
		Film film = new Film(titolo, durata, regista, attori, trama, locandina);
		for(Genere g : listaGeneri)
			if(request.getParameter("" + g.getIdGenere()) != null)
				film.addGenere(g);
		
		try {
			DaoFactory.getFactory("jpa").getFilmDao().add(film);	
			request.setAttribute("aggiunta", "Film \""+film.getTitolo()+"\" aggiunto con successo");
		
			doGet(request, response);
			System.out.println("filmaggiunto");
		} catch (Exception e) {
			request.setAttribute("controllaTitolo", "Film già inserito");
			doGet(request, response);
			System.out.println("non aggiunto");
		}
	}

}