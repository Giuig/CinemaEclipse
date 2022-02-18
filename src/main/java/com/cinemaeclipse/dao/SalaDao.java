package com.cinemaeclipse.dao;

import java.util.List;

import com.cinemaeclipse.model.Sala;

public interface SalaDao {

	List<Sala> selectAll();

	Sala selectById(int id);

	Sala add(Sala sala);

	Sala update(Sala sala);

	void delete(Sala sala);

}
