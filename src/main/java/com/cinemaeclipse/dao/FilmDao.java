package com.cinemaeclipse.dao;

import java.util.List;

import com.cinemaeclipse.model.Film;

public interface FilmDao {

	List<Film> selectAll();

	Film selectById(int id);

	Film add(Film film);

	void delete(Film film);

	Film update(Film film);

}
