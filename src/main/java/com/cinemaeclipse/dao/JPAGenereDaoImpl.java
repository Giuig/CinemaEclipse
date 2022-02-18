package com.cinemaeclipse.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.cinemaeclipse.model.Genere;


public class JPAGenereDaoImpl implements GenereDao {
	
	private static JPAGenereDaoImpl instance;
	
	private JPAGenereDaoImpl() {
		
	}
	
	public static  JPAGenereDaoImpl getInstance() {
		if(instance==null) {
			instance=new JPAGenereDaoImpl();
		}
		return instance;
	}

	@Override
	public List<Genere> selectAll() {
		List<Genere> listaGeneri=null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			listaGeneri = manager.createQuery("select g from Genere g", Genere.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaGeneri;
	}

	@Override
	public Genere selectById(int id) {
		Genere genere=null;
		try {
			EntityManager manager=JPADaoFactory.getEntityManager();
			Query query=manager.createQuery("select g from Genere g where g.idGenere=:id", Genere.class);
			query.setParameter("id", id);
			genere=(Genere)query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return genere;
	}
	

	@Override
	public Genere add(Genere genere) {
		EntityTransaction transaction=null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(genere);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			
		}
		return genere;
	}

	@Override
	public void delete(Genere genere) {
		EntityTransaction transaction=null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			Genere genereMerge=manager.merge(genere);
			manager.remove(genereMerge);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		
	}

	@Override
	public Genere update(Genere genere) {
		Genere nuovoGenere=null;
		EntityTransaction transaction=null;
		try {
			EntityManager manager=JPADaoFactory.getEntityManager();
			transaction=manager.getTransaction();
			transaction.begin();
			nuovoGenere=manager.merge(genere);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return nuovoGenere;
	}	
}
