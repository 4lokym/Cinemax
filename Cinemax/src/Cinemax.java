import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cinemax {

	final public static String DATASET_PATH = "./data/dataset.csv";
	final public static String UTENTI_PATH = "./data/Utenti.csv";
	final public static String PROIEZIONI_PATH = "./data/Proiezioni.csv";
	final public static String PRENOTAZIONI_PATH = "./data/Prenotazioni.csv";
	final public static String FILM_PATH = "./data/Film.csv";
	final public static Scanner INPUT = new Scanner(new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		getMenu();
		
		INPUT.close();
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
                opzione = INPUT.nextByte();
                INPUT.nextLine();
            }catch(InputMismatchException e){
                INPUT.nextLine();
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
			}case 2: {
				new Registrazione().getMenu();
			}case 3: {
				new Guest().getMenu();
			}
        }
	
    }
}
