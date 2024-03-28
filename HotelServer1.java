import java.io.*;
import java.net.*;

public class HotelServer1 {
    public static void main(String[] args) {
        ServerSocket ss = null;
        Socket s = null;
        DataOutputStream dos = null;
        DataInputStream dis = null;
        BufferedReader serverReader = null;

        try {
            ss = new ServerSocket(6666);
            s = ss.accept();

            final DataOutputStream finalDos = new DataOutputStream(s.getOutputStream());
            dis = new DataInputStream(s.getInputStream());
            final BufferedReader finalServerReader = new BufferedReader(new InputStreamReader(System.in)); // Declare as
                                                                                                           // final

            Thread sendingThread = new Thread(() -> {
                try {
                    String str;
                    while (!(str = finalServerReader.readLine()).equals("exit")) { // Use the finalServerReader variable
                        finalDos.writeUTF(str);
                        finalDos.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            sendingThread.start();

            while (true) {
                String clientMessage = dis.readUTF();
                System.out.println("Client: " + clientMessage);

                if (clientMessage.equalsIgnoreCase("supplier")) {
                    finalDos.writeUTF("PRICE LIST :\n Pongal -- 50\n Dosa[1] --20\nIdly[1 Set]--20\nCOMBO --150");
                    finalDos.writeUTF(
                            "Enter operation:\n 1.pongal\n  2.Dosa\n 3.Idly\n 4.Combo[Pongal+vada+idly[2]+dosa[1]] ");
                    finalDos.flush();
                    int operationChoice = Integer.parseInt(dis.readUTF());
                    double order = 0;

                    switch (operationChoice) {
                        case 1:
                            finalDos.writeUTF("QUANTITY :");
                            finalDos.flush();
                            order = Double.parseDouble(dis.readUTF());
                            break;

                        case 2:
                            finalDos.writeUTF("QUANTITY :");
                            finalDos.flush();
                            order = Double.parseDouble(dis.readUTF());
                            break;

                        case 3:
                            finalDos.writeUTF("QUANTITY :");
                            finalDos.flush();
                            order = Double.parseDouble(dis.readUTF());
                            break;

                        case 4:
                            finalDos.writeUTF("QUANTITY :");
                            finalDos.flush();
                            order = Double.parseDouble(dis.readUTF());
                            break;

                        default:
                            finalDos.writeUTF("Invalid operation choice");
                            finalDos.flush();
                            break;
                    }

                    double bill = 0;

                    switch (operationChoice) {
                        case 1:
                            bill = order * 50;
                            break;
                        case 2:
                            bill = order * 20;
                            break;
                        case 3:
                            bill = order * 20;
                            break;
                        case 4:
                            bill = order * 150;
                            break;
                        default:
                            break;
                    }

                    finalDos.writeUTF("BILL: " + bill);
                    finalDos.flush();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (ss != null)
                    ss.close();
                if (s != null)
                    s.close();
                if (dos != null)
                    dos.close();
                if (dis != null)
                    dis.close();
                if (serverReader != null)
                    serverReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
