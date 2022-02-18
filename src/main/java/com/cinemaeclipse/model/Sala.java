package com.cinemaeclipse.model;

import javax.persistence.*;

@Entity

public class Sala{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSala;
	
	@Column(nullable = false, unique = true)
	private String nomeSala;
	
	@Column(nullable = false)
	private int numPostiTotali;
	
	public Sala() {}

	public Sala(String nomeSala, int numPostiTotali) {
		super();
		this.nomeSala = nomeSala;
		this.numPostiTotali=numPostiTotali;
		
	}

	public int getIdSala() {
		return idSala;
	}

	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}

	public String getNomeSala() {
		return nomeSala;
	}

	public void setNomeSala(String nomeSala) {
		this.nomeSala = nomeSala;
	}

	public int getNumPostiTotali() {
		return numPostiTotali;
	}

	public void setNumPostiTotali(int numPostiTotali) {
		this.numPostiTotali = numPostiTotali;
	}

	@Override
	public String toString() {
		return "Sala [idSala=" + idSala + ", nomeSala=" + nomeSala + ", numPostiTotali=" + numPostiTotali + "]";
	}



	
   
}

