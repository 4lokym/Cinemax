import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.InputMismatchException;

import javax.crypto.*;

import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;

public class Registrazione {

	public enum Ruolo {
		CLIENTE, PROIEZIONISTA, BIGLIETTAIO, GUEST
	}

	private IOManager ioM = new IOManager();
	private String nome;
	private String cognome;
	private String username;
	private DataNascita data_nascita;
	private String domicilio;
	private Ruolo ruolo;
	private Password cifratura;
	private String password;
	private String[] passwordCifrata;

	public Registrazione() {
		nome = null;
		cognome = null;
		password = null;
		data_nascita = null;
		domicilio = null;
		ruolo = Registrazione.Ruolo.GUEST;
		cifratura = new Password();
		passwordCifrata = new String[2];
	}

	public void stampaDati() {
		ioM.stampa(nome + " " + cognome + " " + username + " " + data_nascita.toString() + " " + domicilio + " "
				+ password + " " + ruolo);
	}

	public void setNome() {
		boolean conferma;
		do {
			conferma = false;
			ioM.stampa("\nInserisci il nome:\n");
			nome = ioM.getInput();

			conferma = ioM.just_letters(nome);

			if (!conferma) {
				// uso stampa perché così è più semplice cambiare la fomattazione o
				// il modo in cui vengono stampate le informazioni
				ioM.stampa("Input non valido, inserisci solo testo");
			}

		} while (!conferma);
	}

	public void setCognome() {
		boolean conferma;
		do {
			conferma = false;
			ioM.stampa("Inserisci il cognome: ");
			cognome = ioM.getInput();
			conferma = ioM.just_letters(cognome);
			if (!conferma) {
				ioM.stampa("Input non valido, inserisci solo testo");
				cognome = "";
			}
		} while (!conferma);
	}

	public void setUsername() {
		boolean conferma;
		do {
			conferma = false;
			ioM.stampa("Inserisci lo username, senza spazie e caratteri speciali:");
			username = ioM.getInput();
			conferma = ioM.just_letters(username) && ioM.disponibile_Username(username);

			if (!conferma) {
				ioM.stampa("Input non valido, riprova!");
			}
		} while (!conferma);
	}

	public void setPassword() throws NoSuchAlgorithmException, InvalidKeySpecException {
		boolean conferma;
		do {
			conferma = false;
			ioM.stampa("Inserisci la password senza spazi: ");
			password = ioM.getInput();

			if (!password.contains(" ")) {
				passwordCifrata = cifratura.creaHashESalt(password);
				conferma = false;
			} else
				conferma = true;
			if (conferma) {
				ioM.stampa("Password non valida, riprova!");
			}
		} while (conferma);
	}

	public void setDataNascita() {

		boolean conferma;
		byte giorno, mese;
		int anno;
		do {
			try {
				conferma = false;

				do {
					ioM.stampa("Inserisci il giorno");
					giorno = Byte.parseByte(ioM.getInput());
					conferma = DataNascita.inputValido(giorno, DataNascita.Tipo.GIORNO);

					if (!conferma) {
						ioM.stampa("Giorno non valido");
					}
				} while (!conferma);

				do {
					ioM.stampa("Inserisci il mese");
					mese = Byte.parseByte(ioM.getInput());
					conferma = DataNascita.inputValido(mese, DataNascita.Tipo.MESE);

					if (!conferma) {
						ioM.stampa("Mese non valido");
					}
				} while (!conferma);

				do {
					ioM.stampa("Inserisci l'anno");
					anno = Integer.parseInt(ioM.getInput());
					conferma = DataNascita.inputValido(anno, DataNascita.Tipo.ANNO);

					if (!conferma) {
						ioM.stampa("Anno non valido");
					}
				} while (!conferma);

				data_nascita = new DataNascita(giorno, mese, anno);
			} catch (NumberFormatException e) {
				conferma = false;
			}

		} while (!conferma);
	}

	public void setDomicilio() {
		boolean conferma;
		do {
			conferma = false;
			ioM.stampa("Inserisci il domicilio:");
			domicilio = ioM.getInput();
			conferma = ioM.just_letters(domicilio);
			if (!conferma) {
				ioM.stampa("Domicilio non valido!");
			}
		} while (!conferma);
	}

	public void setRuolo() {
		boolean conferma;
		byte scelta = 0;

		ioM.stampa("Scegli il tipo di profilo:" + "\n" + "1) Cliente" + "\n" + "2) Proiezionista" + "\n"
				+ "3) Bigliettaio" + "\n");

		do {
			conferma = false;
			scelta = Byte.parseByte(ioM.getInput());
			if (scelta >= 1 && scelta <= 3)
				conferma = true;
			if (!conferma) {
				ioM.stampa("Scelta non valida, riprova");
				scelta = 0;
			}
		} while (!conferma);

		switch (scelta) {
		case 1: {
			ruolo = Ruolo.CLIENTE;
		}
		case 2: {
			ruolo = Ruolo.PROIEZIONISTA;
		}
		case 3: {
			ruolo = Ruolo.BIGLIETTAIO;
		}
		}

	}

	public void getMenu() {
		Utente utente;
		byte scelta = 0;
		boolean conferma;
		boolean loop = false;

		ioM.stampa("Menu Registrazione");

		do {
			try {
				setNome();
				setCognome();
				setUsername();
				setPassword();
				setDataNascita();
				setDomicilio();
				setRuolo();

				utente = new Utente(nome, cognome, username, passwordCifrata, data_nascita, domicilio, ruolo);
				registrazioneToCSV(utente);

				switch (ruolo) {
				case Ruolo.CLIENTE: {
					// registrazioneToCSV(cliente);
				}
				case Ruolo.PROIEZIONISTA: {
					// registrazioneToCSV(proiezionista);
				}
				case Ruolo.BIGLIETTAIO: {
					// registrazioneToCSV(bigliettaio);
				}
				}

				stampaDati();

			} catch (InputMismatchException | NoSuchAlgorithmException | InvalidKeySpecException e) {
				ioM.stampa("Errore durante la registrazione!");
				loop = true;
				continue;
			}
		} while (loop);
	}

	public void registrazioneToCSV(Utente utente) {
		try {
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(Paths.UTENTI_PATH, true));
			ICSVWriter w = new CSVWriterBuilder(out).withQuoteChar('"').build();
			
			String[] clienteToCsv = { utente.getNome(), utente.getCognome(), utente.getUsername(),
					Arrays.toString(utente.getPasswordCifrata()), utente.getDataNascita().toString(), utente.getDomicilio(),
					utente.getRuolo().name() };
			w.writeNext(clienteToCsv);
			w.flush();
		} catch (IOException e) {
			ioM.stampa("Qualcosa e` andato storto :( -> file " + Paths.UTENTI_PATH + " non trovato");
		}

	}
}
