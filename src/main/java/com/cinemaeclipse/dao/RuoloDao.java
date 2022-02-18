package com.cinemaeclipse.dao;

import java.util.List;

import com.cinemaeclipse.model.Ruolo;

public interface RuoloDao {

	public List<Ruolo> selectAllRuoli();
	public Ruolo selectById(int id);
	public Ruolo add(Ruolo ruolo);
	public void delete(Ruolo ruolo);
	public Ruolo update(Ruolo ruolo);

}
