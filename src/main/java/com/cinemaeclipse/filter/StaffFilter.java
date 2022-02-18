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
 * Servlet Filter implementation class StaffFilter
 */
@WebFilter (filterName = "StaffFilter",urlPatterns = {"/AddFilmServlet", "/AddSpettacoloServlet","/ModificaFilmServlet"}, dispatcherTypes = {DispatcherType.REQUEST})
public class StaffFilter implements Filter {

    /**
     * Default constructor. 
     */
    public StaffFilter() {
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
		HttpSession sessione=req.getSession();
		Utente utente=(Utente) sessione.getAttribute("utenteLoggato");
		if(utente!=null) {
			int ruolo=utente.getRuolo().getIdRuolo();
			if(ruolo==3||ruolo==2) {
					chain.doFilter(request, response);
			}
			else if(ruolo==1) {
				res.sendRedirect("HomeServlet");
			}
		}
		else {
			res.sendRedirect("HomeServlet");

		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
