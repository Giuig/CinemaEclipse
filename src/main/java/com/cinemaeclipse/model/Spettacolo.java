package com.cinemaeclipse.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.cinemaeclipse.dao.DaoFactory;

@Entity

public class Spettacolo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSpettacolo;
	
	@ManyToOne
	@JoinColumn(name = "fk_film")
	private Film film;
	
	@ManyToOne
	@JoinColumn(name = "fk_sala")
	private Sala sala;
	
	@Column(nullable = false)
	private LocalDate data;
	
	@Column(nullable = false)
	private LocalTime ora;
	
	@Column(nullable = false)
	private  double prezzo;
	
	@OneToMany(mappedBy = "spettacolo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Prenotazione> listaPrenotazioni=new ArrayList<Prenotazione>();

	public Spettacolo() {
		super();
	}
	
	

	public Spettacolo(int idSpettacolo, Film film, Sala sala, LocalDate data, LocalTime ora, double prezzo) {
		super();
		this.idSpettacolo = idSpettacolo;
		this.film = film;
		this.sala = sala;
		this.data = data;
		this.ora = ora;
		this.prezzo = prezzo;
	}



	public Spettacolo(Film film, Sala sala, LocalDate data, LocalTime ora, double prezzo) {
		super();
		this.film = film;
		this.sala = sala;
		this.data = data;
		this.ora = ora;
		this.prezzo = prezzo;
	}

	public int getIdSpettacolo() {
		return idSpettacolo;
	}

	public void setIdSpettacolo(int idSpettacolo) {
		this.idSpettacolo = idSpettacolo;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getOra() {
		return ora;
	}

	public void setOra(LocalTime ora) {
		this.ora = ora;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	public void addSpettacoloToFilm(Spettacolo spettacolo) {
		spettacolo.getFilm().getListaSpettacoli().add(this);
	}
	public void removeSpettacoloToFilm(Spettacolo spettacolo) {
		spettacolo.getFilm().getListaSpettacoli().remove(this);
	}
	public int getPostiDisponibili(Spettacolo spettacolo) {
		int postiDisponibili = (sala.getNumPostiTotali())-(DaoFactory.getFactory("jpa").getPrenotazioneDao().selectPrenotazioneBySpettacolo(spettacolo));
		return postiDisponibili;
	}

	@Override
	public String toString() {
		return "Spettacolo [idSpettacolo=" + idSpettacolo + ", film=" + film + ", sala=" + sala + ", data=" + data
				+ ", ora=" + ora + ", prezzo=" + prezzo + "]";
	}

}