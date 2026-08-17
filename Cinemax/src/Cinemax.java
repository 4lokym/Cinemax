import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;

public class Cinemax {
	
	private static IOManager ioM = new IOManager();
	
	public static void main(String[] args) throws FileNotFoundException {
		getMenu();
		
	}
	
	private static boolean valid(byte opzione) {
		return (opzione>=1 && opzione<=3);
	}

	private static void getMenu()
    {
        byte opzione = 0;

        System.out.print(
        		"CINEMAX\n\n"+
        		"Opzioni disponibili:" + "\n"+
        		"1 Login" + "\n"+
        		"2 Registrazione" + "\n"+
        		"3 Ospite" + "\n\n");

        do{
            try{
                opzione = Byte.parseByte(ioM.getInput());
            }catch(InputMismatchException | NumberFormatException  e){
                System.out.print("Input non valido, riprova"+"\n"+
                "opzione:");
                opzione = 0;
                continue;
            }
            if(!valid(opzione)) System.out.print("\nopzione non valida! Riprova! \nopzione:");
        }while(!valid(opzione));

        switch (opzione) {
			case 1: {
				new Login().getMenu();
				break;
			}case 2: {
				new Registrazione().getMenu();
				break;
			}case 3: {
				new Guest().getMenu();
				break;
			}
        }
	
    }
}
