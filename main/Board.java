package main;


// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
public class Board
{
    private Mark[][] board;

    // Ne pas changer la signature de cette méthode
    public Board() {
    board = new Mark[3][3];
    for (int r = 0; r < 3; r++) {
        for (int c = 0; c < 3; c++) {
            board[r][c] = Mark.EMPTY;
            }
        }
    }

    public Board(Mark[][] initialBoard) {
        board = new Mark[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = initialBoard[r][c];
            }
        }
    }


    // Place la pièce 'mark' sur le plateau, à la
    // position spécifiée dans Move
    //
    // Ne pas changer la signature de cette méthode
    public void play(Move m, Mark mark){
        if(board[m.getRow()][m.getCol()] == Mark.EMPTY) {
            board[m.getRow()][m.getCol()] = mark;
        }else{
            throw new IllegalArgumentException("Invalid move");
        }
    }


    // retourne  100 pour une victoire
    //          -100 pour une défaite
    //           0   pour un match nul
    // Ne pas changer la signature de cette méthode
    public int evaluate(Mark mark) {
        // Check each row for a win
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != Mark.EMPTY && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0] == mark ? 100 : -100;
            }
        }

        // Check each column for a win
        for (int i = 0; i < 3; i++) {
            if (board[0][i] != Mark.EMPTY && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i] == mark ? 100 : -100;
            }
        }

        // Check diagonals
        if (board[0][0] != Mark.EMPTY && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0] == mark ? 100 : -100;
        }
        if (board[0][2] != Mark.EMPTY && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2] == mark ? 100 : -100;
        }

        return 0;
    }


    public void printBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] == Mark.EMPTY ? "-" : board[row][col]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }


    public boolean isValidMove(int row, int col) {
        Mark[][] boardArray = getBoardArray();

        return row >= 0 && row < 3 && col >= 0 && col < 3 && boardArray[row][col] == Mark.EMPTY;
    }

    public boolean isMovesLeft() {
        Mark[][] boardArray = getBoardArray();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (boardArray[row][col] == Mark.EMPTY) {
                    return true;
                }
            }
        }
        return false;
    }




    public Mark getMarkAt(int row, int col) {
        return board[row][col];
    }

    public Mark[][] getBoardArray() {
        return board;
    }


}
