import java.util.Scanner;

public class PingMain {
    public static void main(String[] args) {
        String input;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Inserisci l'indirizzo IP da testare");
            input = sc.nextLine();
            Thread pingThread = new Thread(new Ping(input));
            System.out.println("Ping avviato, premi Enter per cambiare IP, inserisci Q e premi Enter per uscire");
            pingThread.start();
            input = sc.nextLine();
            pingThread.interrupt();
            try {
                pingThread.join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrotto in modo errato");
            }
        } while (!input.equals("Q"));
        sc.close();
        System.out.println("Processo terminato");
    }
}