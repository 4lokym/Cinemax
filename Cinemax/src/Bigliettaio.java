

public class Bigliettaio extends Utente{
	IOManager ioM = new IOManager();
	public Bigliettaio(Utente utente) {
		super(utente.getNome(), utente.getCognome(), utente.getUsername()
				, utente.getPasswordCifrata(), utente.getDataNascita(), 
				utente.getDomicilio(), Registrazione.Ruolo.CLIENTE);
	}

	public Bigliettaio(String nome, String cognome, String username, String[] passwordCifrata, DataNascita data,
			String domicilio) {
		super(nome, cognome, username, passwordCifrata, data, domicilio, Registrazione.Ruolo.BIGLIETTAIO);
	}

	public void getMenu() {
		ioM.stampa("FUnziona");
		
	}

}
