public class Board {

	private final int NUM_OF_COLUMNS = 7;
	private final int NUM_OF_ROW = 6;	
	private char gameBoard [] [] = new char [NUM_OF_COLUMNS] [NUM_OF_ROW];  //creates the board and keeps it encapsulated
	
	public Board() {
		for (int i = 0; i < NUM_OF_COLUMNS; i ++) {
			for (int j = 0; j < NUM_OF_ROW; j ++) {
				gameBoard [i][j] = ' ';
			}
		}
	} // fills the board with empty characters ' '
	
	public int getBoardWidth(){
		return NUM_OF_COLUMNS;
	} // returns the width of the board

	public int getBoardHeight(){
		return NUM_OF_ROW;
	} // reutrns the height of the board

	private boolean ColFull(int column){
		for (int j = 0; j < NUM_OF_ROW; j ++){
			if (column > NUM_OF_COLUMNS){
				return true;
			} // if the user enters a number that is greater than the number of columns
			if (gameBoard[column-1][j] == ' '){
				return false;
			} // if the column has any empty spaces it will return false
		}
		return true; 
	}

	public boolean getColFull(int column){
		return ColFull(column);
	} //encapsulated

	private void addToBoard(int column, char symbol){
		for (int j = 0; j < NUM_OF_ROW; j ++){
			if (gameBoard[column - 1][j] == ' '){
				gameBoard[column - 1][j] = symbol;
				break;
			}
		} //adds symbols to the board
	}

	public void setToAddToBoard(int column, char symbol){
		addToBoard(column, symbol);
	} //encapsulated

	public void printBoard() { 
		for (int j = NUM_OF_ROW - 1; j >= 0; j --) {
			System.out.print("|");
			for (int i = 0; i < NUM_OF_COLUMNS; i ++){
				System.out.print(gameBoard [i][j] + "|");
			}
			System.out.println("");
		} //prints the board in a board looking way
	}

	public void resetChar(int column){ //this is mainly used to check if there is a winning move for ai
		for (int j = 0; j < NUM_OF_ROW; j ++){
			if ((gameBoard[column - 1][j]) == ' '){
				gameBoard[column - 1][j-1] = ' ';
				break;
			}
		} //resets the symbol back to empty
	} //not used by player classes

	private int winningMove(char symbol) {
		int winningNum = 8;
		for (int i = 0; i < NUM_OF_COLUMNS; i++){
			addToBoard(i+1, symbol);
			if (containsWin()){
				winningNum = i+1;
			}
			resetChar(i+1);
		}
		return winningNum; 
	} // checks each possibility to see if it has a chance to win. after checking each possibility it will be reset 
	  // to empty and will return the position that gives them a chance to win

	public int getWinningMove(char symbol){
		return winningMove(symbol);
	} //encapsulated

	private int oppWinMove(char symbol){
		int oppWinMove = 8;
		char oppSymbol = ' ';

		for (int i = 0; i < NUM_OF_COLUMNS; i++){
			for (int j = 0; j < NUM_OF_ROW; j++){
				if ((gameBoard[i][j] != symbol) && (gameBoard[i][j] != ' ')){ //if it isnt the ai symbol and its not empty it has to be the opp
					oppSymbol = gameBoard[i][j];
					break; //once the opp symbol is found the loop is broken
				}
			}
		}
		for (int i = 0; i < NUM_OF_COLUMNS; i++){
			addToBoard(i+1, oppSymbol);
			if (containsWin()){
				oppWinMove = i+1; //column to get a win
			}
			resetChar(i+1); // puts it back to empty
		}
		return oppWinMove; // the position to block

	} //finds the opponents winning move by checking each possibility they can go in and seeing if it contains a win

	public int getOppWinMove(char symbol){
		return oppWinMove(symbol);
	} //encapsulated

	public boolean containsWin() {

		//vertical wins
		for (int i = 0; i < NUM_OF_COLUMNS; i++) {
			for (int j = 0; j < NUM_OF_ROW - 3; j++){
				//System.out.println("Vert");       testing
				if ((gameBoard[i][j] == gameBoard[i][j+1]) && (gameBoard[i][j] == gameBoard[i][j+2]) && (gameBoard[i][j]== gameBoard[i][j+3]) && (gameBoard[i][j] != ' ')){
					return true;
				}
			}
		}

		//horizontal win
		for (int i = 0; i < NUM_OF_COLUMNS - 3; i++) {
			for (int j = 0; j < NUM_OF_ROW; j++){
				//System.out.println("Horiz");      testing
				if ((gameBoard[i][j] == gameBoard[i+1][j]) && (gameBoard[i][j]== gameBoard[i+2][j]) && (gameBoard[i][j]== gameBoard[i+3][j]) && (gameBoard[i][j] != ' ')){
					return true;
				}
			}
		}

		//Diagonal Down to Up Win (left to right)
		for (int i = 0; i < NUM_OF_COLUMNS -3; i++) {
			for (int j = 0; j < NUM_OF_ROW -3; j++){
				//System.out.println("Down2Up");    testing
				if ((gameBoard[i][j] == gameBoard[i+1][j+1]) && (gameBoard[i][j]== gameBoard[i+2][j+2]) && (gameBoard[i][j]== gameBoard[i+3][j+3]) && (gameBoard[i][j] != ' ')){
					return true;
				}
			}
		}

		//Diagonal Up to Down Win (left to right)
		for (int i = 0; i < NUM_OF_COLUMNS-3; i++) {
			for (int j = 3; j < NUM_OF_ROW; j++){
				//System.out.println("Up2Down");    testing
				if ((gameBoard[i][j] == gameBoard[i+1][j-1]) && (gameBoard[i][j]== gameBoard[i+2][j-2]) && (gameBoard[i][j]== gameBoard[i+3][j-3]) && (gameBoard[i][j] != ' ')){
					return true;
				}
			}
		}

		return false; // if there is no winning at all, returns false
	}
	
	public boolean isTie() {

		int count = 0; //number of pieces in the board
		boolean full = false;
		boolean tie = false;

		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++){
				if (gameBoard[i][j] != ' ') {
					count++; //counter goes up if the space is not empty which menas there is a symbol there
				}
			}
		}

		if (count == (NUM_OF_COLUMNS * NUM_OF_ROW)){
			full = true; // the board is full if each spot is filled
		}
		
		if (full == true){
			if (containsWin() == true){
				tie = false;
			} else {
				tie = true;
			}
		} // if the board is full and does not contain any wins, the game was a tie
		return tie;
	}
	
	public void reset() {
		for (int i = 0; i < NUM_OF_COLUMNS; i ++) {
			for (int j = 0; j < NUM_OF_ROW; j ++) {
				gameBoard [i][j] = ' ';
			}
		}
	} // fills each spot in the board back to nothing/empty
	
}