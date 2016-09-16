public class TicTacToe{

	public CellState[][] cellArray = new CellState[3][3];

	public enum CellState{
		X,
		O,
		BLANK
	}//end CellState
	public enum VictoryState{
		XWINS,
		OWINS,
		DRAW,
		ONGOING
	}

	public TicTacToe(){
		initializeCellArray();
	}// end constructer

	private int cellStateToInt(CellState value){
		switch(value){
			case X:
				return 1;

			case O:
				return -1;

			case BLANK:
				return 0;
			default:
				return -666;
		}//end switch
	}//end cellStateToInt

	private CellState intToCellState(int iInput){
		if (iInput == 1)
			return CellState.X;
		else if (iInput == -1)
			return CellState.O;
		else if (iInput == 0)
			return CellState.BLANK;
		else
			return null;



	}// End of intToCellState

	// NOTE: I have no illusions as to all of thei being correct.
	//1
	//public VictoryState checkForVictor(){
	// tells who won, and if the game has been won.
	//	return null;
	//}

	//2
	public void setCell (int iCol, int iRow, CellState newState){
		cellArray[iCol][iRow] = newState;
	}

	//3
	public CellState getCell(int iCol, int iRow){

		return cellArray[iCol][iRow];
	}

	//4
	public void resetGame (){
		//returns game to initial state state.
		initializeCellArray();
	}

	//5
	public void endTurn (){
	//Ends the turn. Somehow.
	}
	//Restores the game to initial state.
	private void initializeCellArray(){
		for(int iCol = 0; iCol < 3; iCol++){
			for(int iRow = 0; iRow < 3; iRow++){
				cellArray[iCol][iRow] = CellState.BLANK;

			}
		}

	}

// ToDo: VictoryState class and CellState
	//  CellState has 3 pices of data, all public. 3 connstants, X,O,[BLANK]
	//Victory condition has 4: X won, O won, DRAW, and ONGOING
	// Method on this one for ending turns


}

