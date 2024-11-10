import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Gara {
    private List<Cavallo> cavalli;
    private int lunghezzaPercorso;
    private Scanner scanner;

    public Gara(int lunghezzaPercorso) {
        this.lunghezzaPercorso = lunghezzaPercorso;
        this.cavalli = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void aggiungiCavallo(String nome) {
        System.out.print("Inserisci la velocità (metri al secondo) per il cavallo " + nome + ": ");
        int velocita = scanner.nextInt();
        scanner.nextLine();  // Pulizia del buffer

        Cavallo cavallo = new Cavallo(nome, lunghezzaPercorso, velocita);
        cavalli.add(cavallo);
    }

    public void iniziaGara() {
        // Avvia tutti i thread dei cavalli
        for (Cavallo cavallo : cavalli) {
            cavallo.start();
        }

        // Attende la fine della gara per ogni cavallo
        for (Cavallo cavallo : cavalli) {
            try {
                cavallo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("La gara è finita!");

        // Ordina i cavalli per distanza percorsa in ordine decrescente
        cavalli.sort(Comparator.comparingInt(Cavallo::getDistanzaPercorsa).reversed());

        // Stampa e salva la classifica dei primi 3 cavalli che hanno completato la gara
        System.out.println("\nClassifica dei primi 3 cavalli:");
        int pos = 1;
        for (Cavallo cavallo : cavalli) {
            if (!cavallo.isInfortunato() && pos <= 3) {
                System.out.println(pos + ". " + cavallo.getNome() + " - Distanza: " + cavallo.getDistanzaPercorsa() + " metri");
                pos++;
            }
        }

        // Chiede il file in cui salvare la classifica e salva i risultati
        salvaRisultatiSuFile();
    }

    private void salvaRisultatiSuFile() {
        System.out.print("\nInserisci il nome del file in cui salvare i risultati: ");
        String nomeFile = scanner.nextLine();

        try (FileWriter writer = new FileWriter(nomeFile, true)) {  // Modalità append
            writer.write("Classifica della gara:\n");
            int pos = 1;
            for (Cavallo cavallo : cavalli) {
                if (!cavallo.isInfortunato() && pos <= 3) {
                    writer.write(pos + ". " + cavallo.getNome() + " - Distanza: " + cavallo.getDistanzaPercorsa() + " metri\n");
                    pos++;
                }
            }
            writer.write("\n");
            System.out.println("Risultati salvati nel file " + nomeFile);
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio dei risultati: " + e.getMessage());
        }
    }
}
