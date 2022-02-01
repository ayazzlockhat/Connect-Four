import java.util.Scanner;

public class HumanPlayer extends Player{
    
    public HumanPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);
    }

    public void makeMove(Board board) {
        int column;
        do {
            Scanner move = new Scanner(System.in);
            System.out.print(name + ", Enter a valid move: ");
            column = move.nextInt(); //gets input from user

        } while (((column - 1 <= board.getBoardWidth()) && (board.getColFull(column) == false)) == false);
        board.setToAddToBoard(column, symbol); //adds to the board if the input is valid.
    }

}

