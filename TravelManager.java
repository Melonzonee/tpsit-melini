import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class TravelManager {
    private final ExecutorService executor;
    private final Semaphore portStaff;
    private final Semaphore ferry;
    private final StringBuilder log;

    public TravelManager() {
        executor = Executors.newFixedThreadPool(3);
        portStaff = new Semaphore(3); // 3 staff members for ticket payments
        ferry = new Semaphore(1); // Only 1 ferry available
        log = new StringBuilder();
        log.append("Simulation date: ").append(new SimpleDateFormat("yyyy-MM-dd").format(new Date())).append("\\n");
    }

    public void startSimulation() throws IOException {
        executor.execute(new TouristGroup("French", portStaff, ferry, log));
        executor.execute(new TouristGroup("German", portStaff, ferry, log));
        executor.execute(new TouristGroup("Spanish", portStaff, ferry, log));

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Wait for all groups to complete
        }

        writeLogToFile();
        System.out.println("Simulation complete. Log written to travel_log.txt");
    }

    private void writeLogToFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("travel_log.txt"))) {
            writer.write(log.toString());
        }
    }
}
