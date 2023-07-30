import java.util.Scanner;

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static final String[][] board = new String[ROW][COL];

    public static void main(String[] args) {
        //Define Variables
        Scanner scanner = new Scanner(System.in);
        int moveNumber;
        int userRow, userCol;
        boolean gameOver;
        boolean Move;
        String currentPlayer;
        //Start Loops for determining what move they would like to make
        do {
            clearBoard();
            currentPlayer = "|X|";
            moveNumber = 0;
            gameOver = false;
            do {
                display();
                do {
                    userRow = SafeInput.getRangedInt(scanner, "Please enter the row for your move (1-3)", 1, 3) - 1;
                    userCol = SafeInput.getRangedInt(scanner, "Please enter the column for your move (1-3)", 1, 3) - 1;
                    Move = ValidMove(userRow, userCol);
                    if(!Move) {
                        System.out.println("That was not a valid move, try again:");
                    }
                } while(!Move);
                board[userRow][userCol] = currentPlayer;
                moveNumber++;
                //Loops to Determine if there is a win or a tie
                if(moveNumber >= 5) {
                    if(Win(currentPlayer)) {
                        gameOver = true;
                        display();
                        System.out.println("\n");
                        System.out.println(currentPlayer + " is the winning player!");

                    }
                }
                if(moveNumber >= 7 && !gameOver) {
                    if(Tie()) {
                        gameOver = true;
                        System.out.println("The game is a tie!");
                    }
                }
                //if statement for switching players
                if(currentPlayer == "|X|"){
                    currentPlayer = "|O|";
                } else {
                    currentPlayer = "|X|";
                }
            }while(!gameOver);
        }while(SafeInput.getYNConfirm(scanner, "Play again (Y or N)?"));

    }
    private static void display() {
        //display command that will be added to the main code to allow for displaying of the game each time
        for(int i = 0; i < board.length; i++) {
            for(int k = 0; k < board[i].length; k++) {
                System.out.print(board[i][k]);
                System.out.print(' ');
            }
            System.out.print("\n");
        }
    }
    private static void clearBoard() {
        //for loop to clear board
        for(int i = 0; i < board.length; i++) {
            for(int k = 0; k <board[i].length; k++) {
                board[i][k] = " ";
            }
        }
    }
    private static boolean ValidMove(int row, int col) {
        //Checks to see if the move is valid
        boolean validMove = false;
        if(board[row][col] == " ") {
            validMove = true;
        }
        return validMove;
    }

    private static boolean Tie() {
        boolean Tie = false;
        //Checks for tie by using a set of IFS to determine all the ways there can be a tie
        for(String[] row : board) {
            if(row[0] == "|X|" || row[1] == "|X|" || row[2] == "|X|") {
                if(row[0] == "|O|" || row[1] == "|O|" || row[2] == "|O|") {
                    Tie = true;
                }
            }
        }
        for(int i = 0; i < board.length && !Tie; i++) {
            if((board[0][i] == "|X|")|| (board[1][i] == "|X|") || (board[2][i] == "|X|")) {
                if((board[0][i] == "|O|")|| (board[1][i] == "|O|") || (board[2][i] == "|O|")) {
                    Tie = true;
                }
            }
        }
        if(!Tie) {
            if(board[0][0] == "|X|" || board[1][1] == "|X|" || board[2][2] == "|X|") {
                if(board[0][0] == "|O|" || board[1][1] == "|O|" || board[2][2] == "|O|") {
                    Tie = true;
                }
            }
        }
        if(!Tie) {
            if(board[0][2] == "|X|" || board[1][1] == "|X|" || board[2][0] == "|X|") {
                if(board[0][2] == "|O|" || board[1][1] == "|O|" || board[2][0] == "|O|") {
                    Tie = true;
                }
            }
        }
        return Tie;
    }
    private static boolean ColWin(String player) {
        //checks for column wins
        boolean Won = false;
        for(int i = 0; i < board.length && !Won; i++) {
            if((board[0][i] == player) && (board[1][i] == player) && (board[2][i] == player)) {
                Won = true;
            }
        }
        return Won;
    }

    private static boolean RowWin(String player) {
        //checks for row wins using the exact code as before exept swapping the iterations so it runs each row instead of each column
        boolean Won = false;
        for(int i = 0; i < board.length && !Won; i++) {
            if((board[i][0] == player) && (board[i][1] == player) && (board[i][2] == player)) {
                Won = true;
            }
        }

        return Won;
    }
    private static boolean DiagonalWin(String player) {
        //checks for diagonal win
        boolean Won = false;
        if((board[0][0] == player) && (board[1][1] == player) && (board[2][2] == player)) {
            Won = true;
        }
        if((board[0][2] == player) && (board[1][1] == player) && (board[2][0] == player)) {
            Won = true;
        }
        return Won;
    }
    private static boolean Win(String player) {
        //Checks to see if there is a win in any of the following scenarios
        return ColWin(player) || RowWin(player) || DiagonalWin(player);
    }
}