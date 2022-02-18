package com.cinemaeclipse.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.cinemaeclipse.model.Ruolo;

public class JPARuoloDaoImpl implements RuoloDao{
	private static JPARuoloDaoImpl instance;

	private JPARuoloDaoImpl(){

	}
	public static JPARuoloDaoImpl getInstance() {
		if (instance == null) {
			instance = new JPARuoloDaoImpl();
		}
		return instance;
	}
	@Override
	public List<Ruolo> selectAllRuoli() {
		List<Ruolo> list=null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			Query query = manager.createQuery("select r from Ruolo r", Ruolo.class);
			list = query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public Ruolo selectById(int id) {
		Ruolo ruolo=null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			Query query = manager.createQuery("select r from Ruolo r where r.idRuolo=:id");
			query.setParameter("id", id);
			ruolo = (Ruolo)query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ruolo;
	}
	@Override
	public Ruolo add(Ruolo ruolo) {
		EntityTransaction transaction=null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(ruolo);
			transaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			transaction.rollback();

		}
		return ruolo;
	}
	@Override
	public void delete(Ruolo ruolo) {
		// TODO Auto-generated method stub
		EntityTransaction transaction=null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			Ruolo ruoloM = manager.merge(ruolo);
			manager.remove(ruoloM);
			transaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			transaction.rollback();

		}
	}
	@Override
	public Ruolo update(Ruolo ruolo) {
		EntityTransaction transaction=null;
		Ruolo nuovoRuolo=null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			nuovoRuolo = manager.merge(ruolo);
			transaction.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			transaction.rollback();

		}
		return nuovoRuolo;
	}


}
