import java.io.*;
import java.net.*;

public class MyClient {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 6666);
            // Input stream to receive data from server
            DataInputStream dis = new DataInputStream(s.getInputStream());

            String serverMessage = "";
            // Loop to continuously listen for messages from server
            while (!serverMessage.equals("exit")) {
                // Receive message from server
                serverMessage = dis.readUTF();
                System.out.println("Server: " + serverMessage);
            }
            dis.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
