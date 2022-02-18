package com.cinemaeclipse.model;


import javax.persistence.*;

/**
 * Entity implementation class for Entity: Prenotazione
 *
 */
@Entity

public class Prenotazione {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPrenotazione;
	@ManyToOne
	@JoinColumn(name = "fk_utente")
	private Utente utente;
	@ManyToOne
	@JoinColumn(name = "fk_spettacolo")
	private Spettacolo spettacolo; 
	 
	public Prenotazione() {
		super();
	}
	
	public Prenotazione(Utente utente, Spettacolo spettacolo) {
		super();
		this.utente = utente;
		this.spettacolo = spettacolo;
	}


	public int getIdPrenotazione() {
		return idPrenotazione;
	}

	public void setIdPrenotazione(int idPrenotazione) {
		this.idPrenotazione = idPrenotazione;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Spettacolo getSpettacolo() {
		return spettacolo;
	}

	public void setSpettacolo(Spettacolo spettacolo) {
		this.spettacolo = spettacolo;
	}
	
	public void addPrenotazioneToUtente(Prenotazione prenotazione) {
		prenotazione.getUtente().getListaPrenotazioni().add(this);
	}
	
	public void removePrenotazioneFromUtente(Prenotazione prenotazione) {
		prenotazione.getUtente().getListaPrenotazioni().remove(this);
	}

	@Override
	public String toString() {
		return "Prenotazione [idPrenotazione=" + idPrenotazione + ", utente=" + utente + ", spettacolo=" + spettacolo
				+ "]";
	}
	
	
   
}
