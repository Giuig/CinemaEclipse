package com.cinemaeclipse.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.cinemaeclipse.model.Film;
import com.cinemaeclipse.model.Sala;

public class JPASalaDaoImpl implements SalaDao{
private static JPASalaDaoImpl instance;
	
	private JPASalaDaoImpl() {
	}
	
	public static JPASalaDaoImpl getInstance() {
		if(instance == null) {
			instance = new JPASalaDaoImpl();
		}
		return instance;
	}

	@Override
	public List<Sala> selectAll() {
		List <Sala> listaSala = null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			listaSala = manager.createQuery("select s from Sala s", Sala.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaSala;
	}

	@Override
	public Sala selectById(int id) {
		Sala sala = null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			Query query = manager.createQuery("select s from Sala s where s.idSala=:id");
			query.setParameter("id", id);
			sala = (Sala)query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sala;
	}

	@Override
	public Sala add(Sala sala) {
		EntityTransaction transaction = null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(sala);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return sala;
	}

	@Override
	public Sala update(Sala sala) {
		EntityTransaction transaction = null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.merge(sala);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return sala;
	}

	@Override
	public void delete(Sala sala) {
		EntityTransaction transaction = null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			transaction = manager.getTransaction();
			Sala salaMerge=manager.merge(sala);
			transaction.begin();
			manager.remove(sala);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
	}
}
