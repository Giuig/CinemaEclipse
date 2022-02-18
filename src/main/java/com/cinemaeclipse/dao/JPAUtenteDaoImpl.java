package com.cinemaeclipse.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.cinemaeclipse.model.Ruolo;
import com.cinemaeclipse.model.Utente;
import com.cinemaeclipse.*;
import javax.persistence.Query;

public class JPAUtenteDaoImpl implements UtenteDao{
	
private static JPAUtenteDaoImpl instance;
	
	private JPAUtenteDaoImpl(){

	}
	public static JPAUtenteDaoImpl getInstance() {
		if (instance == null) {
			instance = new JPAUtenteDaoImpl();
		}
		return instance;
	}
	@Override
	public List<Utente> selectAll() {
		List <Utente> listaUtenti = null;
		EntityManager manager = JPADaoFactory.getEntityManager();
		listaUtenti = manager.createQuery("select u from Utente u", Utente.class).getResultList();
		return listaUtenti;
	}

	@Override
	public Utente login(String email, String password) {
		Utente utente = null;;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			Query query = manager.createQuery("select u from Utente u where u.email=:email and u.password=:password");
			query.setParameter("email", email);
			query.setParameter("password", password);
			utente = (Utente)query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return utente;
	}

	@Override
	public Utente selectById(int id) {
		Utente utente = null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			Query query = manager.createQuery("select u from Utente u where u.idUtente=:id");
			query.setParameter("id", id);
			utente = (Utente)query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return utente;
	}

	@Override
	public Utente add(Utente utente) {
		EntityTransaction transaction = null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(utente);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return utente;
	}
	
	@Override
	public void delete(Utente utente) {
		EntityTransaction transaction = null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			Utente utenteMerge = manager.merge(utente);
			manager.remove(utenteMerge);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
	}
		
	@Override
	public Utente update(Utente utente) {
		EntityTransaction transaction = null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.merge(utente);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return utente;
	}
	@Override
	public Utente selectByEmail(String email) {
		Utente utente = null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			Query query = manager.createQuery("select u from Utente u where u.email=:email");
			query.setParameter("email", email);
			utente = (Utente)query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
		return utente;
	}
	
	@Override
		public int numeroMembriStaff() {
			int membriStaff = 0;
			try {
				EntityManager manager = JPADaoFactory.getEntityManager();
				Ruolo ruolo = DaoFactory.getFactory("jpa").getRuoloDao().selectById(2);
				Query query = manager.createQuery("select u from Utente u where u.ruolo=:ruolo", Utente.class);
				query.setParameter("ruolo", ruolo);
				List<Utente> listaStaff = (List<Utente>)query.getResultList();
				membriStaff = listaStaff.size();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return membriStaff;
		}

	
}
