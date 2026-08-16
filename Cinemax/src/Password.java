import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class Password {

	private static final int SALT_SIZE = 16;      // 16 byte di sale casuale
    private static final int ITERATIONS = 70000;  // Rallenta attacchi a forza bruta, è il numero di volte che tutte le operazioni vengono ripetute 
    private static final int HASH_LENGTH = 256;   // 256 bit di lunghezza hash
	
	
	public static String[] creaHashESalt(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		/*
		 * creo un array chiamato salt, cioè letteralmente sale, come se ci mettessimo quel qualcosa in più
		 * nel nostro caso questo sale è un elemento di casualità che nella cifratura della password, 
		 * in modo che ad esempio: se due utenti diversi usano la stessa password le due stringhe hash 
		 * saranno diverse.
		 */
        byte[] salt = new byte[SALT_SIZE];
        new SecureRandom().nextBytes(salt); // Genera il salt unico e casuale, riempie l'array salt con byte
        
        /*
         * eseguo il hashing usando la password divisa in un array di caratteri e il salt
         */
        byte[] hash = calcolaHashNativo(password.toCharArray(), salt);
        
        /*
         * ogni byte è fatto da una sequenza di numeri binari che nel file csv possono essere interpretati come
         * spazi o caratteri strani andando a creare problemi di formattazione nel csv.
         * Qui invece trasformo ogni byte in un carattere alfanumerico tra 64 possibili, in questo modo genero una
         * stringa composta da: A-Z, a-z, 0-9, +, /, =; lunga 32 caratteri (il numero di byte, ogni byte composto da
         * 8 bit quindi 32 * 8 = 256 bit esattamente la lunghezza della stringa hash)
         */
        String hashBase64 = Base64.getEncoder().encodeToString(hash);
        /*
         * salvo sia la hash che il salt perché mi servirà il salt e la stessa password per ottenere la stessa
         * stringa hash.
         * Per fare una metafora:  il salt non è come una chiave ma come se fosse l'impugnatura della chiave;
         * Solo con il salt non sono in grado di aprire una porta, mi serve ancora la password 
         * (cioè la parte anteriore della chiave) mentre senza il salt,
         * non sarò mai in grado di girare la chiave e aprire.
         */
        String saltBase64 = Base64.getEncoder().encodeToString(salt);

        return new String[]{ hashBase64, saltBase64 };
    }
	
	private static byte[] calcolaHashNativo(char[] password, byte[] salt) 
            throws NoSuchAlgorithmException, InvalidKeySpecException {
		/*
		 * spec è un oggetto che contiene la configurazione del motore di cifratura che faremo partire dopo
		 */
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, HASH_LENGTH);
        /*
         * SecretKeyFactory è una classe che fornisce algoritmi per fare hashing e criptazione in base a cosa chiediamo
         * per esempio in questo caso chiediamo un algoritmo PBKDF2 che va ripetere i calcoli 70.000 volte
         * mentre SHA256 è la funzione di hashing
         */
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        
        /*
         * una volta che abbiamo l'oggetto motore di cifratura, gli diamo la nostra configurazione spec e lui cifra
         */
        return factory.generateSecret(spec).getEncoded();
    }

	public static boolean verificaPassword(String passwordInserita, String hashSalvatoBase64, String saltSalvatoBase64) 
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        
        byte[] saltSalvato = Base64.getDecoder().decode(saltSalvatoBase64);

        // Ricalcola l'hash della password digitata usando lo STESSO salt del file
        byte[] nuovoHash = calcolaHashNativo(passwordInserita.toCharArray(), saltSalvato);
        String nuovoHashBase64 = Base64.getEncoder().encodeToString(nuovoHash);

        // Confronta se l'hash appena calcolato e quello del CSV sono identici
        return nuovoHashBase64.equals(hashSalvatoBase64);
    }
	
}
