
public class Proiezionista extends Utente{
	IOManager ioM = new IOManager();
	
	public Proiezionista(Utente utente) {
		super(utente.getNome(), utente.getCognome(), utente.getUsername()
				, utente.getPasswordCifrata(), utente.getDataNascita(), 
				utente.getDomicilio(), Registrazione.Ruolo.CLIENTE);
	}
	
	public Proiezionista(String nome, String cognome, String username, String[] passwordCifrata, DataNascita data,
			String domicilio) {
		super(nome, cognome, username, passwordCifrata, data, domicilio, Registrazione.Ruolo.PROIEZIONISTA);
	}
	@Override
	public void getMenu() {
		ioM.stampa("FUnziona");
		
	}

}
