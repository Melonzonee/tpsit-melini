import java.io.IOException;
import java.net.InetAddress;
import java.util.GregorianCalendar;

public class Ping implements Runnable {
    private String indirizzo;
    private final int delay = 2000;

    public Ping(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                InetAddress address = InetAddress.getByName(indirizzo);
                long finish = 0;
                long start = new GregorianCalendar().getTimeInMillis();
                if (address.isReachable(5000)) {
                    finish = new GregorianCalendar().getTimeInMillis();
                    System.out.println("Ping: " + address.getHostName() + " Time: " + (finish - start) + "ms");
                } else {
                    System.out.println("Richiesta scaduta, indirizzo " + address.getHostName() + " non raggiungibile");
                }
                Thread.sleep(delay);
            }
        } catch (InterruptedException e) {
            System.out.println("Ping interrotto");
        } catch (IOException e) {
            System.out.println("Errore di rete, controlla l'indirizzo inserito");
        }
    }
}