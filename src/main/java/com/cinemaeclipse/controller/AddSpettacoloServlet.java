package com.cinemaeclipse.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinemaeclipse.dao.DaoFactory;
import com.cinemaeclipse.model.Film;
import com.cinemaeclipse.model.Sala;
import com.cinemaeclipse.model.Spettacolo;
import com.cinemaeclipse.util.Utility;

/**
 * Servlet implementation class AddSpettacolo
 */
@WebServlet("/AddSpettacoloServlet")
public class AddSpettacoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddSpettacoloServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id_film = request.getParameter("id");
		int id= Integer.parseInt(id_film);
		Film film = DaoFactory.getFactory("jpa").getFilmDao().selectById(id);
		List<Sala> listaSale = DaoFactory.getFactory("jpa").getSalaDao().selectAll();
		request.setAttribute("film", film);
		request.setAttribute("sala",listaSale);
		request.getRequestDispatcher("WEB-INF/jsp/AddSpettacolo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int idFilm = Integer.parseInt(request.getParameter("film"));
		Film film = DaoFactory.getFactory("jpa").getFilmDao().selectById(idFilm);

		int idSala = Integer.parseInt(request.getParameter("sala"));
		Sala sala = DaoFactory.getFactory("jpa").getSalaDao().selectById(idSala);
		List<Sala> listaSala = (List<Sala>)DaoFactory.getFactory("jpa").getSalaDao().selectAll();
		System.out.println(sala);
		LocalDate dataSpettacolo = LocalDate.parse(request.getParameter("dataSpettacolo"));
		LocalTime oraSpettacolo = LocalTime.parse(request.getParameter("orario"));

		double prezzo = 8.0;

		Spettacolo spettacolo = new Spettacolo(film, sala, dataSpettacolo, oraSpettacolo, prezzo);
		
		
		
		if(DaoFactory.getFactory("jpa").getSpettacoloDao().selectBySalaEData(sala, dataSpettacolo).isEmpty()) {
			
			try {
				DaoFactory.getFactory("jpa").getSpettacoloDao().add(spettacolo);
				spettacolo.addSpettacoloToFilm(spettacolo);
				request.setAttribute("aggiunta", "Spettacolo aggiunto con successo");
				System.out.println("spettacolo aggiunto");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				request.setAttribute("aggiunta", "Problemi nell'aggiunta dello spetacolo");
				request.setAttribute("film", film);
				request.setAttribute("sala", listaSala);
				request.getRequestDispatcher("WEB-INF/jsp/AddSpettacolo.jsp").forward(request, response);
				System.out.println("Spettacolo non aggiunto");
			}
		

		}else {
			if(Utility.inserimentoSpettacolo(spettacolo)) {
				DaoFactory.getFactory("jpa").getSpettacoloDao().add(spettacolo);
				spettacolo.addSpettacoloToFilm(spettacolo);
				request.setAttribute("aggiunta", "Spettacolo aggiunto con successo");
				System.out.println("aggiunto");
			}
			else {
				request.setAttribute("aggiunta", "Questo spettacolo coincide con un altro spettacolo!");
				System.out.println("non aggiunto");
			}			
		}
		request.setAttribute("film", film);
		request.setAttribute("sala", listaSala);
		request.getRequestDispatcher("WEB-INF/jsp/AddSpettacolo.jsp").forward(request, response);
	}
}