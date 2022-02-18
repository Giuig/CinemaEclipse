package com.cinemaeclipse.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity

public class Film {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFilm;
	
	@Column(nullable = false, unique = true)
	private String titolo;
	
	@ManyToMany
	@JoinTable(
		name = "film_genere",
		joinColumns = @JoinColumn(name = "fk_film"),
		inverseJoinColumns = @JoinColumn(name = "fk_genere")
	)
	private List<Genere> listaGeneri = new ArrayList<Genere>();
	
	@Column(nullable = false)
	private int durata;
	
	@Column(nullable = false)
	private String regista;
	
	@Column(nullable = false)
	private String attori;
	
	@Column(nullable = false)
	private String trama;
	
	@OneToMany(mappedBy = "film", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Spettacolo> listaSpettacoli;
	
	@Column(nullable = false)
	private String locandina;

	public Film() {
		super();
	}

	public Film(int idFilm, String titolo, int durata, String regista, String attori, String trama, String locandina) {
		super();
		this.idFilm = idFilm;
		this.titolo = titolo;
		this.durata = durata;
		this.regista = regista;
		this.attori = attori;
		this.trama = trama;
		this.locandina = locandina;
	}

	public Film(String titolo, List<Genere> listaGeneri, int durata, String regista, String attori, String trama,
			List<Spettacolo> listaSpettacoli, String locandina) {
		super();
		this.titolo = titolo;
		this.listaGeneri = listaGeneri;
		this.durata = durata;
		this.regista = regista;
		this.attori = attori;
		this.trama = trama;
		this.listaSpettacoli = listaSpettacoli;
		this.locandina = locandina;
	}
	

	public Film(String titolo, int durata, String regista, String attori, String trama, String locandina) {
		super();
		this.titolo = titolo;
		this.durata = durata;
		this.regista = regista;
		this.attori = attori;
		this.trama = trama;
		this.locandina = locandina;
	}

	public int getIdFilm() {
		return idFilm;
	}

	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	public String getRegista() {
		return regista;
	}

	public void setRegista(String regista) {
		this.regista = regista;
	}

	public String getAttori() {
		return attori;
	}

	public void setAttori(String attori) {
		this.attori = attori;
	}

	public String getTrama() {
		return trama;
	}

	public void setTrama(String trama) {
		this.trama = trama;
	}

	public String getLocandina() {
		return locandina;
	}

	public void setLocandina(String locandina) {
		this.locandina = locandina;
	}

	public List<Spettacolo> getListaSpettacoli() {
		return listaSpettacoli;
	}

	public void setListaSpettacoli(List<Spettacolo> listaSpettacoli) {
		this.listaSpettacoli = listaSpettacoli;
	}

	public List<Genere> getListaGeneri() {
		return listaGeneri;
	}

	public void setListaGeneri(List<Genere> listaGeneri) {
		this.listaGeneri = listaGeneri;
	}
	
	public void addGenere(Genere genere) {
		listaGeneri.add(genere);
		genere.getListaFilm().add(this);
	}

	@Override
	public String toString() {
		return "Film [idFilm=" + idFilm + ", titolo=" + titolo + ", durata=" + durata + ", regista=" + regista
				+ ", attori=" + attori + ", trama=" + trama + ", locandina=" + locandina + ", listaSpettacoli="
				+ listaSpettacoli + ", listaGeneri=" + listaGeneri + "]";
	}
	
}
