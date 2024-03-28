import java.io.*;
import java.net.*;

public class HotelClient {
    public static void main(String[] a) {
        try {

            Socket s = new Socket("localhost", 9198);
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String menu, order;
            int item, quantity, totOrder, wish = 0;
            int[] array = new int[4];
            do {
                menu = dis.readUTF();
                System.out.println("\nMenu" + menu);
                System.out.println("Enter the No of dishes you order: ");
                totOrder = Integer.parseInt(br.readLine());
                System.out.println("\nEnter Your Order: ");
                for (int i = 0; i < totOrder; i++) {
                    order = br.readLine();
                    String[] parts = order.split(" ", 2);
                    item = (Integer.parseInt(parts[0])) - 1;
                    quantity = Integer.parseInt(parts[1]);
                    array[item] = quantity;
                }
               
                for (int i = 0; i < array.length; i++) {
                    dout.writeInt(array[i]);

                }
                for (int i = 0; i < 4; i++) {
                    array[i] = 0;

                }
                System.out.println("\nEnter 1 to order again or 2 to Get Bill: ");
                wish = Integer.parseInt(br.readLine());

                dout.writeInt(wish);

            } while (wish == 1);
            int bill = dis.readInt();
            System.out.println("Total Bill Amount: " + bill);
            
        } catch (Exception e) {
            System.out.println(e);

        }

    }

}
