package com.cinemaeclipse.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.cinemaeclipse.model.Film;
import com.cinemaeclipse.model.Sala;
import com.cinemaeclipse.model.Spettacolo;

public class JPASpettacoloDaoImpl implements SpettacoloDao{
	
	private static JPASpettacoloDaoImpl instance;
	
	private JPASpettacoloDaoImpl() {
	}
	
	public static JPASpettacoloDaoImpl getInstance() {
		if(instance == null) {
			instance = new JPASpettacoloDaoImpl();
		}
		return instance;
	}

	@Override
	public List<Spettacolo> selectAll() {
		List <Spettacolo> listaSpettacolo = null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			listaSpettacolo = manager.createQuery("select s from Spettacolo s", Spettacolo.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaSpettacolo;
	}

	@Override
	public Spettacolo selectById(int id) {
		Spettacolo spettacolo = null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			Query query = manager.createQuery("select s from Spettacolo s where s.idSpettacolo=:id");
			query.setParameter("id", id);
			spettacolo = (Spettacolo)query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return spettacolo;
	}
	
	@Override
	public List<Spettacolo> selectBySalaEData(Sala sala, LocalDate data) {
		List<Spettacolo> spettacolo = null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			Query query = manager.createQuery("select s from Spettacolo s where s.sala=:sala and s.data=:data");
			query.setParameter("sala", sala);
			query.setParameter("data", data);
			spettacolo = (List<Spettacolo>) query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (List<Spettacolo>) spettacolo;
	}
	@Override
	public List<Spettacolo> selectByFilm(Film film){
		List<Spettacolo> spettacolo = null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			Query query = manager.createQuery("select s from Spettacolo s where s.film=:film");
			query.setParameter("film", film);
			spettacolo = (List<Spettacolo>) query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (List<Spettacolo>) spettacolo;
	}

	@Override
	public Spettacolo add(Spettacolo spettacolo) {
		EntityTransaction transaction = null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(spettacolo);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return spettacolo;
	}

	@Override
	public Spettacolo update(Spettacolo spettacolo) {
		EntityTransaction transaction = null;
		Spettacolo spettacoloMerge = null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			spettacoloMerge = manager.merge(spettacolo);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return spettacoloMerge;
	}

	@Override
	public void delete(Spettacolo spettacolo) {
		EntityTransaction transaction = null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			transaction = manager.getTransaction();
			Spettacolo spettacoloMerge=manager.merge(spettacolo);
			transaction.begin();
			manager.remove(spettacoloMerge);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
	}
}
