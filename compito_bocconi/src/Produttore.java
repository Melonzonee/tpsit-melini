public class Produttore implements Runnable {
    private Piatto piatto;

    public Produttore(Piatto piatto) {
        this.piatto = piatto;
    }

    @Override
    public void run() {
        int boccone = 1;
        try {
            while (true) {
                piatto.aggiungiBoccone(boccone);
                boccone++;
                Thread.sleep(500); // Simula il tempo per preparare un boccone
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
