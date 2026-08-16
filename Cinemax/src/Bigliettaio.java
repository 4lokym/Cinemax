

public class Bigliettaio extends Utente{

	public Bigliettaio(String nome, String cognome, String username, String[] passwordCifrata, DataNascita data,
			String domicilio) {
		super(nome, cognome, username, passwordCifrata, data, domicilio, Registrazione.Ruolo.BIGLIETTAIO);
	}

}
