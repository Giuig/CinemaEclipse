package com.cinemaeclipse.dao;

import java.time.LocalDate;
import java.util.List;

import com.cinemaeclipse.model.Film;
import com.cinemaeclipse.model.Sala;
import com.cinemaeclipse.model.Spettacolo;

public interface SpettacoloDao {

	List<Spettacolo> selectAll();

	Spettacolo selectById(int id);

	Spettacolo add(Spettacolo spettacolo);

	void delete(Spettacolo spettacolo);

	Spettacolo update(Spettacolo spettacolo);

	List<Spettacolo> selectBySalaEData(Sala sala, LocalDate data);

	List<Spettacolo> selectByFilm(Film film);

}
