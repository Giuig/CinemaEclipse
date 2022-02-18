package com.cinemaeclipse.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cinemaeclipse.model.Utente;


/**
 * Servlet Filter implementation class AdminFilter
 */
//@WebFilter("/AdminFilter")
@WebFilter (filterName = "AdminFilter",urlPatterns = {"/CreaStaffServlet", "/ListaUtentiServlet"}, dispatcherTypes = {DispatcherType.REQUEST})
public class AdminFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		HttpSession session=req.getSession();
		Utente utente=(Utente) session.getAttribute("utenteLoggato");
		if(utente!=null) {
			int ruolo=utente.getRuolo().getIdRuolo();
			if(ruolo==3) {
					chain.doFilter(request, response);
			}
			else if(ruolo==2||ruolo==1) {
				res.sendRedirect("HomeServlet");
			}
		}
		else {
			res.sendRedirect("HomeServlet");

		}
		
	//	if(ruolo==1) {
		//	chain.doFilter(request, response);
		//}
	/*	else {
			res.sendRedirect("Programmazione");
		}*/
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
