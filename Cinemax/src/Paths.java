
public class Paths {
	
	public enum TipoElemento{
		FILM,
		PRENOTAZIONI,
		PROIEZIONI,
		UTENTI,
		DATASET
	}
	
	public static final String DATASET_PATH = "./data/dataset.csv";
	public static final String UTENTI_PATH = "./data/Utenti.csv";
	public static final String PROIEZIONI_PATH = "./data/Proiezioni.csv";
	public static final String PRENOTAZIONI_PATH = "./data/Prenotazioni.csv";
	public static final String FILM_PATH = "./data/Film.csv";
	
	public static String daTipo_aPath(TipoElemento tipo) {
		switch (tipo) {
			case FILM: {
				return UTENTI_PATH;
			}
			case PRENOTAZIONI: {
				return UTENTI_PATH;
			}
			case PROIEZIONI: {
				return UTENTI_PATH;
			}
			case UTENTI: {
				return UTENTI_PATH;
			}
			case DATASET: {
				return UTENTI_PATH;
			}
		}
		return null;
	}
	
}
