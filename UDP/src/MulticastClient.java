import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastClient {
    private static final String MULTICAST_GROUP = "230.0.0.0";
    private static final int PORT = 4446;

    public static void main(String[] args) {
        try (MulticastSocket socket = new MulticastSocket(PORT)) {
            InetAddress group = InetAddress.getByName(MULTICAST_GROUP);
            socket.joinGroup(group);
            System.out.println("Client unito al gruppo multicast. In attesa di messaggi...");

            byte[] buffer = new byte[256];
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String receivedMessage = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Messaggio ricevuto: " + receivedMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
