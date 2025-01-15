public class Main {
    public static void main(String[] args) {

        Piatto piatto = new Piatto(10); // Massimo 10 bocconi nel piatto

        // Creazione dei thread per il padre e il bambino
        Thread produttore = new Thread(new Produttore(piatto));
        Thread consumatore = new Thread(new Consumatore(piatto));

        // Avvio dei thread
        produttore.start();
        consumatore.start();
    }
}
