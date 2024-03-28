import java.net.*;
import java.io.*;

public class Server5 {
    public static void main(String args[]) throws Exception {
        ServerSocket ss = new ServerSocket(3333);
        Socket s = ss.accept();
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        String operation = "", result = "";
        double num1;

        while (!operation.equals("stop")) {
            operation = din.readUTF();
            num1 = Double.parseDouble(din.readUTF());
            double num2 = 0;
            switch (operation.toLowerCase()) {
                case "add":
                    num2 = Double.parseDouble(din.readUTF());
                    result = String.valueOf(num1 + num2);
                    break;
                case "subtract":
                    num2 = Double.parseDouble(din.readUTF());
                    result = String.valueOf(num1 - num2);
                    break;
                case "multiply":
                    num2 = Double.parseDouble(din.readUTF());
                    result = String.valueOf(num1 * num2);
                    break;
                case "divide":
                    num2 = Double.parseDouble(din.readUTF());
                    if (num2 != 0)
                        result = String.valueOf(num1 / num2);
                    else
                        result = "Cannot divide by zero";
                    break;
                case "square root":
                    if (num1 >= 0)
                        result = String.valueOf(Math.sqrt(num1));
                    else
                        result = "Invalid input for square root";
                    break;
                case "cube root":
                    result = String.valueOf(Math.cbrt(num1));
                    break;
                case "power":
                    num2 = Double.parseDouble(din.readUTF());
                    result = String.valueOf(Math.pow(num1, num2));
                    break;
                case "natural log":
                    result = String.valueOf(Math.log(num1));
                    break;
                case "log":
                    result = String.valueOf(Math.log10(num1));
                    break;
                case "sine":
                    result = String.valueOf(Math.sin(Math.toRadians(num1)));
                    break;
                case "cosine":
                    result = String.valueOf(Math.cos(Math.toRadians(num1)));
                    break;
                case "tan":
                    result = String.valueOf(Math.tan(Math.toRadians(num1)));
                    break;
                default:
                    result = "Invalid operation";
            }
            dout.writeUTF(result);
            dout.flush();
        }
        din.close();
        s.close();
        ss.close();
    }
}
