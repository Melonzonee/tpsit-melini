import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MulticastServer {
    private static final String MULTICAST_GROUP = "230.0.0.0";
    private static final int PORT = 4446;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress group = InetAddress.getByName(MULTICAST_GROUP);
            String alertMessage = "⚠️ ATTENZIONE! Emergenza in corso! ⚠️";

            while (true) {
                byte[] buffer = alertMessage.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, PORT);
                socket.send(packet);
                System.out.println("Messaggio inviato: " + alertMessage);
                Thread.sleep(5000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
