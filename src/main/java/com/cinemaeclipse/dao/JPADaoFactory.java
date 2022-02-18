package com.cinemaeclipse.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPADaoFactory extends DaoFactory{

	public static EntityManager getEntityManager() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("CinemaEclipse");
		factory.getCache().evictAll();
		EntityManager manager = factory.createEntityManager();
		return manager;	
	}

	@Override
	public UtenteDao getUtenteDao() {
		return JPAUtenteDaoImpl.getInstance();
	}

	@Override
	public RuoloDao getRuoloDao() {
		return JPARuoloDaoImpl.getInstance();
	}

	@Override
	public FilmDao getFilmDao() {
		return JPAFilmDaoImpl.getInstance();
	}

	@Override
	public GenereDao getGenereDao() {
		return JPAGenereDaoImpl.getInstance();
	}

	@Override
	public SpettacoloDao getSpettacoloDao() {
		return JPASpettacoloDaoImpl.getInstance();
	}

	@Override
	public SalaDao getSalaDao() {
		return JPASalaDaoImpl.getInstance();
	}

	@Override
	public PagamentoDao getPagamentoDao() {
		return JPAPagamentoDaoImpl.getInstance();
	}

	@Override
	public PrenotazioneDao getPrenotazioneDao() {
		// TODO Auto-generated method stub
		return JPAPrenotazioneDaoImpl.getInstance();
	}
}
