package com.cinemaeclipse.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cinemaeclipse.dao.DaoFactory;
import com.cinemaeclipse.model.Sala;
import com.cinemaeclipse.model.Spettacolo;


public class Utility {

	private static final String EMAIL_PATTERN =
			"^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
					+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

	private static final String PASSWORD_PATTERN= "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

	private static final Pattern patternEmail = Pattern.compile(EMAIL_PATTERN);

	private static final Pattern patternPassword = Pattern.compile(PASSWORD_PATTERN);



	public static boolean checkEmail(final String email) {
		if(email != null && !email.isEmpty()) {
			Matcher matcher = patternEmail.matcher(email);
			return matcher.matches();
		}
		return false;

	}

	public static boolean checkPassword(String password) {
		if(password != null && !password.isEmpty()) {
			Matcher matcher = patternPassword.matcher(password);
			return matcher.matches();
		}
		return false;
	}

	public static boolean checkNome(String nome) {
		return nome != null && !nome.isEmpty();
	}

	public static boolean checkCognome(String cognome) {
		return cognome != null && !cognome.isEmpty();
	}
	public static boolean checkTelefono(String telefono) {
		return telefono != null && !telefono.isEmpty();
	}
	public static boolean checkSesso(String sesso) {
		return sesso != null && !sesso.isEmpty();
	}

	public static String generaEmail(String nome, String cognome) {
		int numeroMembriStaff = DaoFactory.getFactory("jpa").getUtenteDao().numeroMembriStaff()+1;
		String email = nome.toLowerCase()+cognome.toLowerCase()+numeroMembriStaff+"@cinemaeclipse.com";
		return email;
	}

	public static String generaPassword(String nome, String cognome) {
		int numeroMembriStaff = DaoFactory.getFactory("jpa").getUtenteDao().numeroMembriStaff();
		String numeroMatricola = "";
		if(numeroMembriStaff < 10) {
			numeroMembriStaff += 1;
			numeroMatricola = "000"+numeroMembriStaff;
		}else if(numeroMembriStaff >= 10 && numeroMembriStaff < 100) {
			numeroMembriStaff += 1;
			numeroMatricola = "00"+numeroMembriStaff;
		}else {
			numeroMembriStaff += 1;
			numeroMatricola = "0"+numeroMembriStaff;
		}	
		String password = String.valueOf(nome.charAt(0)).toLowerCase()+"."+cognome.toLowerCase()+"#"+numeroMatricola;	
		return password;
	}

	public static boolean inserimentoSpettacolo(Spettacolo spettacolo) {
		Sala sala = spettacolo.getSala();
		LocalDate data = spettacolo.getData();
		LocalTime oraInizioFilmDaInserire = spettacolo.getOra();
		Integer durataFilmDaInserire = spettacolo.getFilm().getDurata();
		LocalTime oraFineFilmDaInserire = oraInizioFilmDaInserire.plus(Duration.ofMinutes(durataFilmDaInserire));		
		boolean flag = true;
		List<Spettacolo> listaSpettacoli = DaoFactory.getFactory("jpa").getSpettacoloDao().selectBySalaEData(sala, data);
		
		for(Spettacolo s:listaSpettacoli) {
			LocalTime oraInizio = s.getOra();
			Integer durata = s.getFilm().getDurata();
			LocalTime oraFine = oraInizio.plus(Duration.ofMinutes(durata));
			
			if((oraInizioFilmDaInserire.isAfter(oraInizio)&&oraInizioFilmDaInserire.isBefore(oraFine))|| ((oraFineFilmDaInserire.isAfter(oraInizio)&&oraFineFilmDaInserire.isBefore(oraFine)))||(oraInizioFilmDaInserire.equals(oraInizio)||oraInizioFilmDaInserire.equals(oraFine))) {
				flag = false;	
				break;
			}
		}		
		return flag;
	}
	
	public static String numeroRandom() {
		int min = 0;
		int max = 9;
		long range = max - min + 1;
		String rand = "#";
		for (int i = 0; i <= max; i++) {
			int nRand = (int)(Math.random() * range) + min;
            rand += nRand;
        }return rand;
	}



}
