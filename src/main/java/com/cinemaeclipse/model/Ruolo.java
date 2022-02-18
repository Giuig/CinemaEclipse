package com.cinemaeclipse.model;

import java.util.List;

import javax.persistence.*;

@Entity

public class Ruolo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRuolo;

	@Column(nullable = false, unique = true)
	private String descrizione;
	
	@OneToMany(mappedBy = "ruolo", fetch = FetchType.LAZY)
	private List<Utente> listaUtenti;
	
	public Ruolo() {}
	
	public Ruolo(String descrizione) {
		super();
		this.descrizione = descrizione;
	}

	public int getIdRuolo() {
		return idRuolo;
	}

	public void setIdRuolo(int idRuolo) {
		this.idRuolo = idRuolo;
	}

	public List<Utente> getListaUtenti() {
		return listaUtenti;
	}



	public void setListaUtenti(List<Utente> listaUtenti) {
		this.listaUtenti = listaUtenti;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "Ruolo [idRuolo=" + idRuolo + ", descrizione=" + descrizione + ", listaUtenti=" + listaUtenti + "]";
	}
	
}
