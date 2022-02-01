import java.util.Random;

public class AIPlayer extends Player{

    public AIPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);
    }

    public void makeMove(Board board) {
        Random rand = new Random();
        int randomMove = rand.nextInt(7); //random number between 0 and t

        System.out.println("I choose to go in column " + randomMove); //states the AI PLayer's move

        if (board.getWinningMove(symbol) < 8){ //the number would be less than 8 if there was a possible winning move
            board.setToAddToBoard(board.getWinningMove(symbol), symbol);
        }    
        else if (board.getOppWinMove(symbol) < 8){ //the number would be less than 8 if there was a possible winning move
            board.setToAddToBoard(board.getOppWinMove(symbol), symbol);

        }
        else {
            board.setToAddToBoard(randomMove+1, symbol); //if theres no winning moves or blocking moves it will do something random
        }
    }
}