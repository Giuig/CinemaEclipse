package com.cinemaeclipse.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity

public class Genere {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idGenere;
	
	@Column(nullable = false, unique = true)
	private String descrizione;
	
	@ManyToMany(mappedBy = "listaGeneri")
	private List<Film> listaFilm = new ArrayList<Film>();

	public Genere() {
		super();
	}

	public Genere(String descrizione) {
		super();
		this.descrizione = descrizione;
	}

	public int getIdGenere() {
		return idGenere;
	}

	public void setIdGenere(int idGenere) {
		this.idGenere = idGenere;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Film> getListaFilm() {
		return listaFilm;
	}

	public void setListaFilm(List<Film> listaFilm) {
		this.listaFilm = listaFilm;
	}
	
	public void addFilm(Film film) {
		listaFilm.add(film);
		film.getListaGeneri().add(this);
	}

	@Override
	public String toString() {
		return "Genere [idGenere=" + idGenere + ", descrizione=" + descrizione +  "]";
	}
	
   
}
