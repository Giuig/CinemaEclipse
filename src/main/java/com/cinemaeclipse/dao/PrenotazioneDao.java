package com.cinemaeclipse.dao;

import java.util.List;

import com.cinemaeclipse.model.Prenotazione;
import com.cinemaeclipse.model.Spettacolo;
import com.cinemaeclipse.model.Utente;

public interface PrenotazioneDao {
	
	public List<Prenotazione> selectAllPrenotazioni();
	public Prenotazione selectById(int id);
	public boolean add(Prenotazione prenotazione);
	public void delete(Prenotazione prenotazione);
	public Prenotazione update(Prenotazione prenotazione);
	public int selectPrenotazioneBySpettacolo(Spettacolo spettacolo);
	public List<Prenotazione> selectPrenotazioniByUtente(Utente utente);
	List<Prenotazione> selectListaPrenotazioniBySpettacolo(Spettacolo spettacolo);
}
