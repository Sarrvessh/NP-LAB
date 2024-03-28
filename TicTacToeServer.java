import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class TicTacToeServer {
    private static final int PORT = 12345;
    private static final int BOARD_SIZE = 3;
    private static char[][] board;
    private static char[] marks = {'X', 'O'};
    private static int currentPlayer = 0;

    public static void main(String[] args) {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        initializeBoard();

        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
            System.out.println("Server is running...");
            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String clientMessage = new String(receivePacket.getData()).trim();
                String[] coordinates = clientMessage.split(",");
                int row = Integer.parseInt(coordinates[0]);
                int col = Integer.parseInt(coordinates[1]);

                if (isValidMove(row, col)) {
                    board[row][col] = marks[currentPlayer];
                    printBoard();
                    if (checkWin()) {
                        System.out.println(marks[currentPlayer] + " wins!");
                        break;
                    }
                    currentPlayer = (currentPlayer + 1) % 2;
                } else {
                    System.out.println("Invalid move!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE && board[row][col] == ' ';
    }

    private static void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j]);
                if (j < BOARD_SIZE - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < BOARD_SIZE - 1) {
                System.out.println("-----");
            }
        }
        System.out.println();
    }

    private static boolean checkWin() {
        // Check rows and columns for a win
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                return true;
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
                return true;
            }
        }
        // Check diagonals for a win
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
            return true;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
            return true;
        }
        return false;
    }
}
