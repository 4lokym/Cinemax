import java.util.Arrays;

public class Utente {
	private String nome;
	private String cognome;
	private String username;
	private String[] passwordCifrata;
	private String domicilio;
	private DataNascita data;
	private Registrazione.Ruolo ruolo;

	public Utente(String nome, String cognome, String username, String[] passwordCifrata, DataNascita data,
			String domicilio, Registrazione.Ruolo ruolo) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.passwordCifrata = Arrays.copyOf(passwordCifrata, passwordCifrata.length);
		this.data = data;
		this.ruolo = ruolo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Registrazione.Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Registrazione.Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String[] getPasswordCifrata() {
		return passwordCifrata;
	}

	public void setPasswordCifrata(String[] passwordCifrata) {
		this.passwordCifrata = passwordCifrata;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public DataNascita getDataNascita() {
		return data;
	}

	public void setDataNascita(DataNascita data) {
		this.data = data;
	}

	public void getMenu() {
		
	}
	
	public static Utente utenteSpecifico(Utente u) {
		switch(u.getRuolo().name()) {
			case "CLIENTE": 
				u = new Cliente(u);
				break;
			case "PROIEZIONISTA": 
				u = new Proiezionista(u);
				break;
			case "BIGLIETTAIO": 
				u = new Bigliettaio(u);
				break;
		}
		return u;
	}
	
	
}
