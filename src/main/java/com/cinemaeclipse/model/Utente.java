package com.cinemaeclipse.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;




@Entity

public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUtente;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String cognome;

	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private LocalDate dataNascita;
	
	@Column(nullable = false)
	private String telefono;
	
	@Column(nullable = false)
	private String sesso;
	
	@Column(unique = true)
	private static int numeroDipendente = 0;

	@ManyToOne
	@JoinColumn(name = "fk_ruolo")
	private Ruolo ruolo;
	
	@OneToMany(mappedBy = "utente", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Pagamento> listaPagamenti=new ArrayList<Pagamento>();
	
	@OneToMany(mappedBy = "utente", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Prenotazione> listaPrenotazioni=new ArrayList<Prenotazione>();

	public Utente() {}
	
	

	public Utente(String password, String email) {
		super();
		this.password = password;
		this.email = email;
	}



	public Utente(String nome, String cognome, String password, String email, LocalDate dataNascita, String telefono,
			String sesso, Ruolo ruolo) {
		super();
		if(ruolo.getDescrizione().equalsIgnoreCase("staff")) {
			numeroDipendente ++;
		}
		this.nome = nome;
		this.cognome = cognome;
		this.password = password;
		this.email = email;
		this.dataNascita = dataNascita;
		this.telefono = telefono;
		this.sesso = sesso;
		this.ruolo=ruolo;
	}

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public static int getNumeroDipendente() {
		return numeroDipendente;
	}

	public static void setNumeroDipendente(int numeroDipendente) {
		Utente.numeroDipendente = numeroDipendente;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}
	
	

	public List<Pagamento> getListaPagamenti() {
		return listaPagamenti;
	}

	public void setListaPagamenti(List<Pagamento> listaPagamenti) {
		this.listaPagamenti = listaPagamenti;
	}

	public List<Prenotazione> getListaPrenotazioni() {
		return listaPrenotazioni;
	}



	public void setListaPrenotazioni(List<Prenotazione> listaPrenotazioni) {
		this.listaPrenotazioni = listaPrenotazioni;
	}

	public void addUtenteToRuolo(Utente utente) {
		utente.getRuolo().getListaUtenti().add(this);
	}



	@Override
	public String toString() {
		return "Utente [idUtente=" + idUtente + ", nome=" + nome + ", cognome=" + cognome + ", password=" + password
				+ ", email=" + email + ", dataNascita=" + dataNascita + ", telefono=" + telefono + ", sesso=" + sesso
				+ ", ruolo=" + ruolo + ", listaPagamenti=" + listaPagamenti + ", listaPrenotazioni=" + listaPrenotazioni
				+ "]";
	}


	
	
}
