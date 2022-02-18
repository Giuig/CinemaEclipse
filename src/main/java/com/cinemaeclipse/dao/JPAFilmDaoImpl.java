package com.cinemaeclipse.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.cinemaeclipse.model.Film;

public class JPAFilmDaoImpl implements FilmDao{
	
private static JPAFilmDaoImpl instance;
	
	private JPAFilmDaoImpl() {
	}
	
	public static JPAFilmDaoImpl getInstance() {
		if(instance == null) {
			instance = new JPAFilmDaoImpl();
		}
		return instance;
	}

	@Override
	public List<Film> selectAll() {
		List <Film> listaFilm = null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			listaFilm = manager.createQuery("select f from Film f", Film.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaFilm;
	}

	@Override
	public Film selectById(int id) {
		Film film = null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			Query query = manager.createQuery("select f from Film f where f.idFilm=:id");
			query.setParameter("id", id);
			film = (Film)query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
		return film;
	}

	@Override
	public Film add(Film film) {
		EntityTransaction transaction = null;
		try {
			System.out.println(film);
			EntityManager manager = JPADaoFactory.getEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(film);
			transaction.commit();
			System.out.println(film);
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return film;
	}

	@Override
	public void delete(Film film) {
		EntityTransaction transaction = null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			Film filmMerge=manager.merge(film);
			manager.remove(filmMerge);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
	}

	@Override
	public Film update(Film film) {
		EntityTransaction transaction = null;
		try {
			EntityManager manager = JPADaoFactory.getEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.merge(film);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return film;
	}

}
