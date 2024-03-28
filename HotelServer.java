import java.io.*;
import java.net.*;

public class HotelServer {
    public static void main(String[] a) {
        try {
            ServerSocket ss = new ServerSocket(9198);
            Socket s = ss.accept();
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String menu = "\n1.Dosa---20\n2.Vada---10\n3.Puri---30\n4.Pongal-40", order;
            String[] dishes = { "1.Dosa----", "2.Vada----", "3.Puri----", "4.Pongal--" };
            int wish = 0, bill = 0;
            int[] prices = { 20, 10, 30, 40 };

            do {

                dout.writeUTF(menu);
                dout.flush();

                int[] orderArray = new int[4];

                for (int i = 0; i < 4; i++) {
                    orderArray[i] = dis.readInt();

                }
                System.out.println("\nCustomer Order: ");
                for (int i = 0; i < 4; i++) {
                    System.out.println(dishes[i] + orderArray[i]);
                    bill += prices[i] * orderArray[i];

                }

                wish = dis.readInt();
                System.out.println("Received wish: " + wish);

            } while (wish == 1);
            dout.writeInt(bill);
            ss.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
