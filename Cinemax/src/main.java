import java.io.FileReader;
import java.io.IOException;

import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;
public class main {

	public static void main(String[] args) throws CsvValidationException, IOException {
		CSVReader r = new CSVReaderBuilder(new FileReader("./data/proiezioni.csv")).build();
		String[] nextLine;
		for(int i = 0; i < 10 && (nextLine = r.readNext()) != null; i++) {
			
			System.out.println(nextLine[1]);
		}
	}

}
