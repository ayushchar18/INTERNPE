import java.util.Scanner;

public class ConnectFour {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private static final char EMPTY = '.';
    private static final char PLAYER1 = 'X';
    private static final char PLAYER2 = 'O';

    private char[][] board = new char[ROWS][COLS];

    public ConnectFour() {
        initializeBoard();
    }

    private void initializeBoard() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                board[r][c] = EMPTY;
            }
        }
    }

    private void printBoard() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println("1 2 3 4 5 6 7");
    }

    private boolean isColumnFull(int col) {
        return board[0][col] != EMPTY;
    }

    private boolean makeMove(int col, char player) {
        if (isColumnFull(col)) {
            return false;
        }
        for (int r = ROWS - 1; r >= 0; r--) {
            if (board[r][col] == EMPTY) {
                board[r][col] = player;
                return true;
            }
        }
        return false;
    }

    private boolean checkWin(char player) {
        // Check horizontal win
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c <= COLS - 4; c++) {
                if (board[r][c] == player && board[r][c + 1] == player && 
                    board[r][c + 2] == player && board[r][c + 3] == player) {
                    return true;
                }
            }
        }
        // Check vertical win
        for (int r = 0; r <= ROWS - 4; r++) {
            for (int c = 0; c < COLS; c++) {
                if (board[r][c] == player && board[r + 1][c] == player && 
                    board[r + 2][c] == player && board[r + 3][c] == player) {
                    return true;
                }
            }
        }
        // Check diagonal (bottom-left to top-right) win
        for (int r = 0; r <= ROWS - 4; r++) {
            for (int c = 0; c <= COLS - 4; c++) {
                if (board[r][c] == player && board[r + 1][c + 1] == player && 
                    board[r + 2][c + 2] == player && board[r + 3][c + 3] == player) {
                    return true;
                }
            }
        }
        // Check diagonal (top-left to bottom-right) win
        for (int r = 3; r < ROWS; r++) {
            for (int c = 0; c <= COLS - 4; c++) {
                if (board[r][c] == player && board[r - 1][c + 1] == player && 
                    board[r - 2][c + 2] == player && board[r - 3][c + 3] == player) {
                    return true;
                }
            }
        }
        return false;
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        char currentPlayer = PLAYER1;
        boolean gameWon = false;
        int totalMoves = 0;

        while (totalMoves < ROWS * COLS && !gameWon) {
            printBoard();
            System.out.println("Player " + currentPlayer + "'s turn. Enter column (1-7): ");
            int col = scanner.nextInt() - 1;
            if (col < 0 || col >= COLS || isColumnFull(col)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }
            makeMove(col, currentPlayer);
            gameWon = checkWin(currentPlayer);
            if (gameWon) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
            } else {
                currentPlayer = (currentPlayer == PLAYER1) ? PLAYER2 : PLAYER1;
                totalMoves++;
            }
        }
        if (!gameWon) {
            printBoard();
            System.out.println("The game is a draw!");
        }
        scanner.close();
    }

    public static void main(String[] args) {
        ConnectFour game = new ConnectFour();
        game.playGame();
    }
}
