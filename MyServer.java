import java.io.*;
import java.net.*;

public class MyServer {

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(6666);
            Socket s = ss.accept();

            // Output stream to send data to client
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            // Reader to read input from server's console
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(System.in));

            String str = "";
            // Loop to continuously read input from server's console and send it to client
            while (!str.equals("exit")) {
                // Read input from server's console
                str = serverReader.readLine();
                // Send input to client
                dos.writeUTF(str);
                dos.flush();
            }
            dos.close();
            s.close();
            ss.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}