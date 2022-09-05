import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
        openVits();
	}

    public static void openVits() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nFor ny vits, trykk \'F\'\nFor å avslutte, trykk \'X\'\nBekreft valg med enter");
        String choice = scanner.nextLine().toLowerCase();
        
        switch (choice) {
            case "f":
                fetchVits();
                break;
            case "x":
                System.out.println("Avslutter program");
                scanner.close();
                break;
            default: openVits();
        }
    }
    
    public static void fetchVits() throws IOException {
        URL url = new URL("https://icanhazdadjoke.com/");
        
        try {
            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
            urlc.setRequestProperty("Content-Type", "application/json");
            urlc.setRequestProperty("Accept", "text/plain");
            urlc.setAllowUserInteraction(false);
            urlc.connect();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
            String l = null;
            while ((l=br.readLine())!=null) {
                System.out.println(l);
            }
            br.close();
            openVits();
        }
        
        catch(Exception e) {
            System.out.println("Error occured\n"+ e.toString());
        }
    }
}
