package com.cinemaeclipse.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;

import com.cinemaeclipse.dao.DaoFactory;
import com.cinemaeclipse.dao.JPAUtenteDaoImpl;
import com.cinemaeclipse.dao.UtenteDao;
import com.cinemaeclipse.model.Utente;

public class CustomerServices {

	public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated constructor stub
	}

	public String resetCustomerPassword(String email) {
	    Utente customer = DaoFactory.getFactory("jpa").getUtenteDao().selectByEmail(email);
	     
	    String randomPassword = RandomStringUtils.randomAlphanumeric(10);
	    
	    
	     
	    customer.setPassword(randomPassword);
	    DaoFactory.getFactory("jpa").getUtenteDao().update(customer);
	    
	     
	    return randomPassword;
	}
	
}
