import java.util.LinkedList;

public class Piatto {
    private LinkedList<Integer> bocconi = new LinkedList<>();
    private int capacita;

    public Piatto(int capacita) {
        this.capacita = capacita;
    }
    public synchronized void aggiungiBoccone(int boccone) throws InterruptedException {
        while (bocconi.size() == capacita) {
            wait(); // Aspetta finché il piatto non ha spazio
        }
        bocconi.add(boccone);
        System.out.println("Padre: aggiunto boccone " + boccone + " (Bocconi nel piatto: " + bocconi.size() + ")");
        notifyAll();
    }


    public synchronized int consumaBoccone() throws InterruptedException {
        while (bocconi.isEmpty()) {
            wait(); // Aspetta finché non ci sono bocconi nel piatto
        }
        int boccone = bocconi.removeFirst();
        System.out.println("Bambino: mangiato boccone " + boccone + " (Bocconi rimasti: " + bocconi.size() + ")");
        notifyAll(); //
        return boccone;
    }
}
