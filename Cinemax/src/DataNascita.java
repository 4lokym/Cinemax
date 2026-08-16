import java.time.*;

public class DataNascita {
	
	public enum Tipo{
		GIORNO,
		MESE,
		ANNO
	}
	
	int giorno;
	int mese;
	int anno;
	
	public DataNascita(int giorno, int mese, int anno) {
		this.anno = anno;
		this.mese = mese;
		this.giorno = giorno;
	}
	
	@Override
	public String toString(){
		return giorno +"/"+mese +"/"+anno;
	}
	
	public static DataNascita fromString(String dataS) {
		String temp[] = dataS.split("/");
		return new DataNascita(Integer.parseInt(temp[0]), Integer.parseInt(temp[0]), Integer.parseInt(temp[0]));
	}
	
	public static boolean giornoValido(int giorno) {
		if(giorno > 0 && giorno <= 31) {
			return true;
		}
		return false;
	}
	
	public static boolean meseValido(int mese) {
		if(mese > 0 && mese <= 12) {
			return true;
		}
		return false;
	}
	
	public static boolean annoValido(int anno) {
		if(anno > 1000 && anno <= Year.now().getValue()) {
			return true;
		}
		return false;
	}
	
	public static boolean inputValido(int input, Tipo tipo) {
		switch (tipo) {
			case GIORNO: {
				return giornoValido(input);
			}
			case MESE: {
				return meseValido(input);
			}
			case ANNO: {
				return annoValido(input);
			}
		}
		return false;
	}
}
