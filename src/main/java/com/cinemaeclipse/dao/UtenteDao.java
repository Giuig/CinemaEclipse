package com.cinemaeclipse.dao;

import java.util.List;

import com.cinemaeclipse.model.Utente;

public interface UtenteDao {
	public List<Utente> selectAll();
	public Utente selectById(int id);
	public Utente login(String email, String password);
	public Utente add(Utente film);
	public Utente update(Utente film);
	public void delete(Utente film);
	public Utente selectByEmail(String email);
	public int numeroMembriStaff();

	
}
