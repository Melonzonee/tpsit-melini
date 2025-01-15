public class Consumatore implements Runnable {
    private Piatto piatto;

    public Consumatore(Piatto piatto) {
        this.piatto = piatto;
    }

    @Override
    public void run() {
        try {
            while (true) {
                piatto.consumaBoccone();
                Thread.sleep(1000); // Simula il tempo per mangiare un boccone
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
