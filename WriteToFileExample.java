import java.io.*;

public class WriteToFileExample {
    public static void main(String[] args) {
        String filename = "output.txt";

        try (
                BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter writer = new PrintWriter(new FileWriter(filename))
        ) {
            System.out.println("Inserisci delle righe di testo (premi Enter su una riga vuota per terminare):");

            String line = consoleInput.readLine();
            while (line != null && !line.equals("")) {
                writer.println(line);
                line = consoleInput.readLine();
            }

            System.out.println("Scrittura completata nel file " + filename);
        } catch (IOException e) {
            System.err.println("Errore durante l'accesso al file: " + e.getMessage());
        }
    }
}
