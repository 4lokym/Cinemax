
public class Proiezionista extends Utente{

	public Proiezionista(String nome, String cognome, String username, String[] passwordCifrata, DataNascita data,
			String domicilio) {
		super(nome, cognome, username, passwordCifrata, data, domicilio, Registrazione.Ruolo.PROIEZIONISTA);
	}

}
