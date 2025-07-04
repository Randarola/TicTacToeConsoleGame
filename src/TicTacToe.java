import java.util.Random;
import java.util.Scanner;
public class TicTacToe {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';
    private static Random random = new Random();

    public static void main(String[] args) {
        initializeBoard();
        Scanner scanner = new Scanner(System.in);
        boolean gameEnded = false;

        while (!gameEnded) {
            printBoard();

            if (currentPlayer == 'X') {
                System.out.println("Gracz " + currentPlayer + ", wybierz pole (wiersz i kolumna: 0, 1 lub 2): ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                if (row < 0 || row > 2 || col < 0 || col > 2) {
                    System.out.println("Nieprawidłowe współrzędne. Spróbuj ponownie.");
                    continue;
                }

                if (board[row][col] != ' ') {
                    System.out.println("Pole zajęte. Spróbuj ponownie.");
                    continue;
                }

                board[row][col] = currentPlayer;
            } else {
                System.out.println("Ruch AI (" + currentPlayer + ")...");
                int row, col;
                do {
                    row = random.nextInt(3);
                    col = random.nextInt(3);
                } while (board[row][col] != ' ');
                board[row][col] = currentPlayer;
            }

            if (hasWon(currentPlayer)) {
                printBoard();
                if (currentPlayer == 'X') {
                    System.out.println("Gracz wygrał!");
                } else {
                    System.out.println("AI wygrało!");
                }
                gameEnded = true;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("Remis!");
                gameEnded = true;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }

        scanner.close();


    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void printBoard() {
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("  -----");
        }
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }

    private static boolean hasWon(char player) {
        // Sprawdzenie wierszy, kolumn i przekątnych
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true;

        return false;
    }
}
