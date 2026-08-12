import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;




public class IOManager {
	
	

	final public static Scanner IN = new Scanner(new InputStreamReader(System.in));
	
	final public static String getInput() {
		
		String input = IN.nextLine().toLowerCase();
		return input;
	}
	
	final public static boolean just_letters(String s) {
		if(s.isEmpty()) return false;
		for(char c : s.toCharArray()) {
        	if(!Character.isLetter(c)) return false;
        }
		return true;
	}
	
	
	final public static void stampa(String s) {
		System.out.print("\n"+s+"\n");
	}
	
	final public static CSVReader getCSVReader(String path) {
		try{
			return new CSVReaderBuilder(new FileReader(path)).withSkipLines(1).build();
        }catch(IOException e) { return null;}
	}
	
	final public static boolean disponibile(String elemento, Paths.TipoElemento tipo, int colonnaInCsv) {
		String[] riga;
        CSVReader r = getCSVReader(Paths.daTipo_aPath(tipo));
        try {
            while((riga = r.readNext())!=null) {
                if(riga[colonnaInCsv].equalsIgnoreCase(elemento)) return false;
            }
            return true;
        }catch (IOException | CsvValidationException e ) {
        	return false;
		}
	}
	
	final public static boolean disponibile_Username(String username) {
		return disponibile(username, Paths.TipoElemento.UTENTI, 2);
	}
	

}
