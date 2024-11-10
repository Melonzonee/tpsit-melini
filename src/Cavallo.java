import java.util.Random;

public class Cavallo extends Thread {
    private String nome;
    private int distanzaDaPercorrere;
    private int distanzaPercorsa = 0;
    private int velocita; // Velocità in metri al secondo
    private boolean infortunato = false; // Flag per verificare se il cavallo si è infortunato
    private Random random;

    public Cavallo(String nome, int distanzaDaPercorrere, int velocita) {
        this.nome = nome;
        this.distanzaDaPercorrere = distanzaDaPercorrere;
        this.velocita = velocita;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (distanzaPercorsa < distanzaDaPercorrere && !infortunato) {
            // Simula una probabilità di infortunio del 5% per ogni ciclo
            if (random.nextDouble() < 0.05) {
                infortunato = true;
                System.out.println(nome + " si è infortunato e lascia la gara.");
                break;
            }

            // Avanza in base alla velocità
            distanzaPercorsa += velocita;
            if (distanzaPercorsa > distanzaDaPercorrere) {
                distanzaPercorsa = distanzaDaPercorrere; // Limita alla distanza totale
            }

            System.out.println(nome + " ha percorso " + distanzaPercorsa + " metri.");

            try {
                Thread.sleep(1000); // Simula un secondo di tempo tra gli avanzamenti
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Se non infortunato, ha completato la gara
        if (!infortunato) {
            System.out.println(nome + " ha completato la gara!");
        }
    }

    public String getNome() {
        return nome;
    }

    public int getDistanzaPercorsa() {
        return distanzaPercorsa;
    }

    public boolean isInfortunato() {
        return infortunato;
    }
}
