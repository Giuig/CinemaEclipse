package com.cinemaeclipse.dao;

public abstract class DaoFactory {

	public static DaoFactory getFactory(String tipoFactory) {	
		switch(tipoFactory.toLowerCase()) {
		
		default:
			return new JPADaoFactory();
		
		}
	}
	public abstract UtenteDao getUtenteDao();
	public abstract RuoloDao getRuoloDao();
	public abstract FilmDao getFilmDao();
	public abstract GenereDao getGenereDao();
	public abstract SpettacoloDao getSpettacoloDao();
	public abstract SalaDao getSalaDao();
	public abstract PagamentoDao getPagamentoDao();
	public abstract PrenotazioneDao getPrenotazioneDao();



	
}
