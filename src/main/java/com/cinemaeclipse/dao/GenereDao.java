package com.cinemaeclipse.dao;

import java.util.List;

import com.cinemaeclipse.model.Genere;

public interface GenereDao {

	public List<Genere> selectAll();
	public Genere selectById (int id);
	public Genere add(Genere genere);
	public void delete(Genere genere);
	public Genere update(Genere genere);
}
