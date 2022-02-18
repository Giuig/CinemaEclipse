package com.cinemaeclipse.controller;



import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinemaeclipse.dao.DaoFactory;
import com.cinemaeclipse.model.Utente;
import com.cinemaeclipse.util.CustomerServices;
import com.cinemaeclipse.util.EmailUtility;



/**
 * A Java Servlet to handle requests to reset password for customer
 *
 * @author www.codejava.net
 *
 */
@WebServlet("/reset_password")
public class ResetPasswordServlet extends HttpServlet {
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

	public ResetPasswordServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String page = "reset_password.jsp";
		request.getRequestDispatcher(page).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String recipient = request.getParameter("email");
		String subject = "La tua password è stata modificata";
		Utente utente = DaoFactory.getFactory("jpa").getUtenteDao().selectByEmail(recipient);

		
		
		

		String message = "";
		
		 if(utente!=null) {
			 
		 
		 try {
			 CustomerServices customerServices = new CustomerServices(request, response);
				String newPassword = customerServices.resetCustomerPassword(recipient);
				String content = "Ciao, questa è la tua nuova password: " + newPassword;
				content += "\nNote: per motivi di sicurezza, "
						+ "ti consigliamo di cambiare la password dopo l'accesso.";
	            EmailUtility.sendEmail(host, port, email, name, pass,
	                    recipient, subject, content);
	            message = "La tua password è stata modificata. Controlla la tua e-mail.";
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            message = "There were an error: " + ex.getMessage();
	        } finally {
	            request.setAttribute("message", message);
	            request.getRequestDispatcher("WEB-INF/jsp/Login.jsp").forward(request, response);
	        }
		 }
		 else {
			request.setAttribute("message", "Inserire mail corretta");
			request.getRequestDispatcher("WEB-INF/jsp/ForgotPassword.jsp").forward(request, response);
		 }
		
//		try{
//			
//			Utente utente = DaoFactory.getFactory("jpa").getUtenteDao().selectByEmail(recipient);
//			try {
//
//				EmailUtility.sendEmail(host, port, email, name, pass,
//						recipient, subject, content);
//				message = "La tua password è stata resettata. Controlla la tua e-mail.";
//
//
//			} catch (Exception ex) {
//				ex.printStackTrace();
//				message = "There were an error: " + ex.getMessage();
//			} finally {
//				request.setAttribute("message", message);
//				request.getRequestDispatcher("WEB-INF/jsp/Login.jsp").forward(request, response);
//			}
//
//		}
//		catch(Exception ex) {
//			message="Inserire mail corretta";
//			request.getRequestDispatcher("WEB-INF/jsp/ForgotPassword.jsp").forward(request, response);
//		}





	}

}
