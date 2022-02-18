package com.cinemaeclipse.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cinemaeclipse.dao.DaoFactory;
import com.cinemaeclipse.model.Utente;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/jsp/Login.jsp").forward(request, response);
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Utente utenteLoggato=DaoFactory.getFactory("jpa").getUtenteDao().login(email, password);
		if(utenteLoggato!=null && utenteLoggato.getRuolo().getIdRuolo() != 4) {
			HttpSession sessione = request.getSession();
			utenteLoggato.setPassword(null);
			sessione.setAttribute("utenteLoggato", utenteLoggato);
			response.sendRedirect("HomeServlet");
		}else if(utenteLoggato!=null && utenteLoggato.getRuolo().getIdRuolo() == 4){
			request.setAttribute("loginFallita", "Account bannato. Non puoi accedere.");
			request.getRequestDispatcher("WEB-INF/jsp/Login.jsp").forward(request, response);
		}
		else {
			request.setAttribute("loginFallita", "Username o password errati");
			request.getRequestDispatcher("WEB-INF/jsp/Login.jsp").forward(request, response);
		}

	}

}