package com.cinemaeclipse.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.cinemaeclipse.model.Prenotazione;
import com.cinemaeclipse.model.Spettacolo;
import com.cinemaeclipse.model.Utente;

public class JPAPrenotazioneDaoImpl implements PrenotazioneDao{
	
	private static JPAPrenotazioneDaoImpl instance;

	private JPAPrenotazioneDaoImpl(){

	}
	public static JPAPrenotazioneDaoImpl getInstance() {
		if (instance == null) {
			instance = new JPAPrenotazioneDaoImpl();
		}
		return instance;
	}
	@Override
	public List<Prenotazione> selectAllPrenotazioni() {
		EntityManager manager = JPADaoFactory.getEntityManager();
		//Query query = manager.createQuery("select r from Ricevuta r", Ricevuta.class);
		List<Prenotazione> list = manager.createQuery("select p from Prenotazione p", Prenotazione.class).getResultList();
		return list;
	}
	
	@Override
	public Prenotazione selectById(int id) {
		EntityManager manager = JPADaoFactory.getEntityManager();
		Query query = manager.createQuery("select p from Prenotazione p where p.idPrenotazione=:id", Prenotazione.class);
		query.setParameter("id", id);
		Prenotazione ricevuta = (Prenotazione)query.getSingleResult();
		return ricevuta;
	}
	
	@Override
	public int selectPrenotazioneBySpettacolo(Spettacolo spettacolo) {
		int numeroPrenotazioni = 0;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			Query query = manager.createQuery("select p from Prenotazione p where p.spettacolo=:spettacolo", Prenotazione.class);
			query.setParameter("spettacolo", spettacolo);
			List<Prenotazione> listaPrenotazioni = (List<Prenotazione>)query.getResultList();
			if(listaPrenotazioni!=null) {
				numeroPrenotazioni = listaPrenotazioni.size();
				}else return numeroPrenotazioni;	
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return numeroPrenotazioni;
	}
	@Override
	public List<Prenotazione> selectListaPrenotazioniBySpettacolo(Spettacolo spettacolo){
		List<Prenotazione> listaPrenotazioni =null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			Query query = manager.createQuery("select p from Prenotazione p where p.spettacolo=:spettacolo", Prenotazione.class);
			query.setParameter("spettacolo", spettacolo);
			listaPrenotazioni = (List<Prenotazione>)query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
		return listaPrenotazioni;
	}
	
	
	@Override
	public boolean add(Prenotazione prenotazione) {
		boolean prenotazioneConfermata = false;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			int numeroPrenotazioni = selectPrenotazioneBySpettacolo(prenotazione.getSpettacolo());
			if(numeroPrenotazioni < prenotazione.getSpettacolo().getSala().getNumPostiTotali()) {
				transaction.begin();
				manager.persist(prenotazione);
				transaction.commit();
				prenotazioneConfermata = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prenotazioneConfermata;
	}
	@Override
	public void delete(Prenotazione prenotazione) {
		// TODO Auto-generated method stub
		EntityManager manager = JPADaoFactory.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Prenotazione prenotazioneM = manager.merge(prenotazione);
		manager.remove(prenotazioneM);
		transaction.commit();
	}
	@Override
	public Prenotazione update(Prenotazione prenotazione) {
		EntityManager manager = JPADaoFactory.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Prenotazione nuovaPrenotazione = manager.merge(prenotazione);
		transaction.commit();
		return nuovaPrenotazione;
	}
	
	@Override
	public List<Prenotazione> selectPrenotazioniByUtente(Utente utente) {
		List<Prenotazione> listaPrenotazioni = null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			Query query = manager.createQuery("select p from Prenotazione p where p.utente=:utente", Prenotazione.class);
			query.setParameter("utente", utente);
			listaPrenotazioni = (List<Prenotazione>)query.getResultList();	
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return listaPrenotazioni;
	}

}
