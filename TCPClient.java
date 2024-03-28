import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        final String SERVER_IP = "127.0.0.1"; // Server IP address
        final int SERVER_PORT = 12345; // Server port number

        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String clientMessage;
            String serverMessage;

            while (true) {
                // Send message to server
                System.out.print("Enter message to send to server: ");
                clientMessage = userInputReader.readLine();
                writer.println(clientMessage);

                // Check if client wants to terminate
                if (clientMessage.equalsIgnoreCase("exit")) {
                    break;
                }

                // Receive response from server
                serverMessage = reader.readLine();
                System.out.println("Message from server: " + serverMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
