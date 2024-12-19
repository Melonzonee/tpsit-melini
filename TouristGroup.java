public class TouristGroup import java.util.concurrent.Semaphore;

public class TouristGroup implements Runnable {
    private final String groupName;
    private final Semaphore portStaff;
    private final Semaphore ferry;
    private final StringBuilder log;

    public TouristGroup(String groupName, Semaphore portStaff, Semaphore ferry, StringBuilder log) {
        this.groupName = groupName;
        this.portStaff = portStaff;
        this.ferry = ferry;
        this.log = log;
    }

    @Override
    public void run() {
        try {
            System.out.println(groupName + " group departing from lodgings.");
            Thread.sleep(1000); // Simulating travel time to port
            System.out.println(groupName + " group arrived at the port.");

            portStaff.acquire();
            System.out.println(groupName + " group paying for tickets.");
            Thread.sleep(1000); // Simulating ticket payment time
            portStaff.release();

            ferry.acquire();
            System.out.println(groupName + " group boarding the ferry.");
            synchronized (log) {
                log.append(groupName).append(" group boarded the ferry.\\n");
            }
            Thread.sleep(2000); // Simulating ferry travel time
            System.out.println(groupName + " group departing for the island.");

            Thread.sleep(2000); // Simulating ferry return time
            System.out.println("Ferry returned to the port.");
            ferry.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
{
}
