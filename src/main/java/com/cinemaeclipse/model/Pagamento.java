package com.cinemaeclipse.model;

import java.time.LocalDate;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Pagamento
 *
 */
@Entity

public class Pagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPagamento;

	@Column(nullable = false, unique = true)
	private String numeroCarta;

	@Column(nullable = false)
	private LocalDate dataScadenza;

	@Column(nullable = false)
	private String cvv;

	@ManyToOne
	@JoinColumn(name = "fk_utente")
	private Utente utente;

	public Pagamento() {
		super();
	}

	public Pagamento(String numeroCarta, LocalDate dataScadenza, String cvv, Utente utente) {
		super();
		this.numeroCarta = numeroCarta;
		this.dataScadenza = dataScadenza;
		this.cvv = cvv;
		this.utente = utente;
	}

	public int getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(int idPagamento) {
		this.idPagamento = idPagamento;
	}

	public String getNumeroCarta() {
		return numeroCarta;
	}

	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}

	public LocalDate getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(LocalDate dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	public void addPagamentoToUtente(Pagamento pagamento) {
		pagamento.getUtente().getListaPagamenti().add(this);
	}

	@Override
	public String toString() {
		return "Pagamento [idPagamento=" + idPagamento + ", numeroCarta=" + numeroCarta + ", dataScadenza="
				+ dataScadenza + ", cvv=" + cvv + ", utente=" + utente + "]";
	}

}
