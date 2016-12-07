package TicTacEngine;

import com.sun.media.jfxmedia.events.PlayerStateEvent;

public class TicTacToe{


	public CellState[][] cellArray = new CellState[3][3];
	public Line[] lineArray = new Line[8];
	private TurnState currentTurn;
	public TurnState getCurrentTurn(){
		return currentTurn;
	}
	public GameMode gameMode;

	public enum CellState{
		X,
		O,
		BLANK
	}//end CellState
	public enum TurnState{
		XTURN,
		OTURN
	}
	public enum VictoryState{
		XWINS,
		OWINS,
		DRAW,
		ONGOING
	}
	public enum GameMode{
		ONEPLAYER,
		TWOPLAYER
	}
	//constructor.
	public TicTacToe(){
		initializeCellArray();
		initializeLineArrray();
		currentTurn = TurnState.XTURN;
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
	public void setCell (int iCol, int iRow) throws TicTacExeption {
		if ( checkVictory() != VictoryState.ONGOING ) {
			throw new GameAlreadyOverException("The game is over");

		} else if (cellArray[iCol][iRow] == CellState.BLANK) {

			if (currentTurn == TurnState.XTURN)
				cellArray[iCol][iRow] = CellState.X;
			else
				cellArray[iCol][iRow] = CellState.O;
			if (currentTurn == TurnState.XTURN)
				currentTurn = TurnState.OTURN;
			else
				currentTurn = TurnState.XTURN;
		}
		else{
			throw new ButtonAlreadySetException("you must pick a blank square");
		}

	}

	//3
	public CellState getCell(int iCol, int iRow){

		return cellArray[iCol][iRow];
	}

	//4
	public void resetGame (){
		//returns game to initial state state.
		initializeCellArray();
		currentTurn = TurnState.XTURN;
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
	public VictoryState checkLineState(Line line){

		int iOne = cellStateToInt(cellArray [line.getCell1().x][line.getCell1().y]);
		int iTwo = cellStateToInt(cellArray [line.getCell2().x][line.getCell2().y]);
		int iThree = cellStateToInt(cellArray [line.getCell3().x][line.getCell3().y]);

		if ( Math.abs(iOne) +  Math.abs(iTwo) +  Math.abs(iThree) != Math.abs( iOne + iTwo + iThree)){
			return VictoryState.DRAW;
		}
		else {
			if (iOne + iTwo + iThree == 3){
				return VictoryState.XWINS;
			}
			else if (iOne + iTwo + iThree == -3){
				return VictoryState.OWINS;
			}
			else {
				return VictoryState.ONGOING;
			}
		}
	}

	public VictoryState checkVictory (){
		VictoryState result = VictoryState.ONGOING;
		int iDrawCount = 0;
		for(int i = 0; i < lineArray.length; i++){
			switch (checkLineState(lineArray[i])) {
				case XWINS:
					result = VictoryState.XWINS;
					break;
				case OWINS:
					result =VictoryState.OWINS;
					break;
				case DRAW:
					iDrawCount++;
					break;
			}
			if ((result == VictoryState.XWINS) || (result == VictoryState.OWINS))
				break;

		}
		if (iDrawCount == lineArray.length)
			result = VictoryState.DRAW;
		return result;
	}
	public void computerMove{
		//First, check if we can win.

		//Now, check if we can block.

		//things gert murky from here.
			//Just chose a random space.
	}

// ToDo: VictoryState class and CellState
	//  CellState has 3 pices of data, all public. 3 connstants, X,O,[BLANK]
	//Victory condition has 4: X won, O won, DRAW, and ONGOING
	// Method on this one for ending turns


}

