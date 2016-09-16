public class TicTacToe{

	public CellState[][] cellArray = new CellState[3][3];
	public Line[] lineArray = new Line[8];

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
		initializeLineArrray();
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
	// Initializes the victory lines columb
	// 8 statements, each creating a line and asigning it to the correct value.
	private void initializeLineArrray(){
		lineArray[0] = new Line (0,0,0,1,0,2);
		lineArray[1] = new Line (1,0,1,1,1,2);
		lineArray[2] = new Line (2,0,2,1,2,2);
		lineArray[3] = new Line (0,0,1,0,2,0);
		lineArray[4] = new Line (0,1,1,1,2,1);
		lineArray[5] = new Line (0,2,1,2,2,2);
		lineArray[6] = new Line (0,0,1,1,2,2);
		lineArray[7] = new Line (2,0,1,1,0,2);
	}

// ToDo: VictoryState class and CellState
	//  CellState has 3 pices of data, all public. 3 connstants, X,O,[BLANK]
	//Victory condition has 4: X won, O won, DRAW, and ONGOING
	// Method on this one for ending turns


}

