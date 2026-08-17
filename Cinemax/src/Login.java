import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

import javax.crypto.*;

import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;

public class Login {

	IOManager ioM = new IOManager();
	Utente utente;

	public void getMenu() {
		Password checkPassword = new Password();
		Utente utente;
		boolean loop = true;
		String[] passCsv = new String[2];
		String username;
		String password;

		ioM.stampa("LOGIN");
		do {
			
			try {
				// prendo username e password
				ioM.stampa("Inserisci il tuo username: ");
				username = ioM.getInput();
				ioM.stampa("Inserisci la password: ");
				password = ioM.getInput();
				
				passCsv = getPassFromUsername(username);
				
				// se la password non è nulla ed è giusta allora faccio il login e creo un oggetto utente
				if (passCsv != null)
					if (checkPassword.verificaPassword(password, passCsv[0], passCsv[1])) {
						ioM.stampa("Login effettuato!");
						
						// costruisco l'utente generico e lo trasformo in un utente specifico
						utente = Utente.utenteSpecifico(getUtenteFromUsername(username));
						
						utente.getMenu();
						loop = false;
					} else
						ioM.stampa("Credenziali non valide");
				else
					ioM.stampa("Credenziali non valide");

			} catch (InputMismatchException | NoSuchAlgorithmException | CsvValidationException | IOException | InvalidKeySpecException e) {
				ioM.stampa("Non valide");
				username = "";
				password = "";
			}
		} while (loop);

	}

	private String[] getPassFromUsername(String username) {
		String[] rigaCsv;
		String[] passESalt = new String[2];

		try {
			CSVReader r = new CSVReaderBuilder(new FileReader(Paths.UTENTI_PATH)).withSkipLines(1).build();

			while ((rigaCsv = r.readNext()) != null)
				if (rigaCsv[2].equals(username)) {
					//restituisco il salt e la password
					return traduciPassDaCsv(rigaCsv[3]);
				}
			return null;
		} catch (IOException | CsvValidationException e) {
			ioM.stampa("Errore, la lettura della password non è andata a buon fine!");
		}

		return null;
	}

	private Utente getUtenteFromUsername(String username) throws CsvValidationException, IOException {
		CSVReader r = new CSVReaderBuilder(new FileReader(Paths.UTENTI_PATH)).withSkipLines(1).build();
		String[] rigaCsv;
		
		while ((rigaCsv = r.readNext()) != null) {
			if (rigaCsv[2].equals(username)) {
				String nome, cognome, domicilio;
				String[] passESalt = new String[2];
				Registrazione.Ruolo ruolo;
				DataNascita data;
				
				//ricavo ogni elemento dalla riga csv e lo inserisco in un nuovo oggetto utente
				nome = rigaCsv[0];
				cognome = rigaCsv[1];
				passESalt = traduciPassDaCsv(rigaCsv[3]);
				data = DataNascita.fromStringToObj(rigaCsv[4]);
				domicilio = rigaCsv[5];
				ruolo = Registrazione.Ruolo.valueOf(rigaCsv[6]);
				
				
				
				return new Utente(nome, cognome, username, passESalt, data, domicilio, ruolo);
			}
		}
		return null;
	}
	
	public static String[] traduciPassDaCsv(String rawPassword) {
		String[] passESalt;
		rawPassword = rawPassword.replace("[", "").replace("]", "");
		passESalt = rawPassword.split(",");
		passESalt[1] = passESalt[1].trim();
		return passESalt;
	}

	
}
