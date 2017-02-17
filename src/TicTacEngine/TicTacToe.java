package TicTacEngine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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

	private int specialRandom(int iRange){
		return (int) (Math.random() * iRange);
	}

	public void computerMove(){
		Point p;
		//1
		//checking if it can win
		for(int i=0; i < lineArray.length; i++) {
			p = possibleVictory( currentTurn , lineArray[i]);
			if(p != null) {
				try{
					setCell(p.x,p.y);
				}catch(TicTacExeption tte){
					// supressing it, because it will probably never happen.
				}
				return;
			}
		}


		//Checking if it can block
		//2
		for(int i=0; i < lineArray.length; i++) {
			p = possibleBlock( currentTurn , lineArray[i]);
			if(p != null) {
				try{
					setCell(p.x,p.y);
				}catch(TicTacExeption tte){
					// supressing it, because it will probably never happen.
				}
				return;
			}
		}


		//3 Create a fork if you can
		p = possibleFork(currentTurn);
		if(p != null) {
			try {
				setCell(p.x, p.y);
			} catch (TicTacExeption tte) {
				//Supressing it because it will not happen.
			}
			return;
		}
		//4 block a fork if you can
		p = possibleFork((currentTurn == TurnState.XTURN) ? TurnState.OTURN : TurnState.XTURN);

		if(p != null) {
			try {
				setCell(p.x, p.y);
			} catch (TicTacExeption tte) {
				//Supressing it because it will not happen.
			}
			return;
		}
		//5 For the opening move
		if (findTurnNumber()== 1) {
		//if(false){
			try {
				setCell(0, 0);

			} catch (TicTacExeption tte) {
				JOptionPane.showMessageDialog(null, "The opening move should not have any nonblank cells, but it does. Odd", "FATAL Error ", JOptionPane.ERROR_MESSAGE);

			}
			return;
		}

		else {// Center
			try {
				setCell(1, 1);
				return;
			} catch (TicTacExeption tte) {
				//supress
			}
		}
		//6 Oposite corner if oponent picks one.
		//the computer has the center, the human has a corner, and the rest are blank, take oposite corner.
		if (cellArray[1][1]==CellState.X && findTurnNumber() == 3) {
			try {


				if (cellArray[0][0] == CellState.O) {
					setCell(2, 2);
					return;
				} else if (cellArray[2][0] == CellState.O) {
					setCell(0, 2);
					return;
				} else if (cellArray[0][2] == CellState.O) {
					setCell(2, 0);
					return;
				} else if (cellArray[2][2] == CellState.O) {
					setCell(0, 0);
					return;
				}
			} catch (TicTacExeption tte) {

			}
		}
		//7 empty corner
		if (cellArray[0][0] == CellState.BLANK){
			try{
				setCell(0,0);
				return;
			}catch(TicTacExeption tte){

			}
		}else if(cellArray[0][2] == CellState.BLANK){
			try{
				setCell(0,2);
				return;
			}catch(TicTacExeption tte){

			}
		}else if(cellArray[2][0] == CellState.BLANK){
			try{
				setCell(2,0);
				return;
			}catch(TicTacExeption tte){

			}
		}else if(cellArray[2][2] == CellState.BLANK) {
			try{
				setCell(2,2);
				return;
			}catch(TicTacExeption tte){

			}
		}




			//8 empty side

		//things gert murky from here.
		//N. Eventualy it will be gone.
		boolean bExeptionOcured = true;
		while(bExeptionOcured == true) {
			try {
				setCell(specialRandom(3), specialRandom(3));
				bExeptionOcured =false;
			} catch (TicTacExeption tte ) {
				bExeptionOcured = true;
			}
		}
	}

	public Point possibleVictory(TicTacToe.TurnState whosVictory, Line line) {
		Point result = null;

		//turns the turnState into the cell state we are looking for.
		TicTacToe.CellState whatWins;
		if(whosVictory == TicTacToe.TurnState.XTURN){
			whatWins = TicTacToe.CellState.X;
		}
		else{
			whatWins = TicTacToe.CellState.O;
		}

		//gets the cellStates for the cells in a line.
		TicTacToe.CellState cell1;
		TicTacToe.CellState cell2;
		TicTacToe.CellState cell3;
		cell1 = getCell(line.getCell1().x,line.getCell1().y);
		cell2 = getCell(line.getCell2().x,line.getCell2().y);
		cell3 = getCell(line.getCell3().x,line.getCell3().y);

		//Checks weather the line has two Os and an blank, and then tells you what cell is blank.
		if(((cell1 == CellState.BLANK) && (cell2 == whatWins) && (cell3 == whatWins))){
			result = line.getCell1();
		}
		else if(((cell1 == whatWins) && (cell2 == CellState.BLANK) && (cell3 == whatWins))){
			result = line.getCell2();
		}
		else if(((cell1 == whatWins) && (cell2 == whatWins) && (cell3 == CellState.BLANK))){
			result = line.getCell3();
		}

		return result;
	}
	public Point possibleBlock(TicTacToe.TurnState whosBlock, Line line){
		Point result = null;

		//turns the turnState into the cell state we are looking for.
		TicTacToe.CellState whatWins;
		if(whosBlock == TicTacToe.TurnState.XTURN){
			whatWins = TicTacToe.CellState.O;
		}
		else{
			whatWins = TicTacToe.CellState.X;
		}

		//gets the cellStates for the cells in a line.
		TicTacToe.CellState cell1;
		TicTacToe.CellState cell2;
		TicTacToe.CellState cell3;
		cell1 = getCell(line.getCell1().x,line.getCell1().y);
		cell2 = getCell(line.getCell2().x,line.getCell2().y);
		cell3 = getCell(line.getCell3().x,line.getCell3().y);

		//Checks weather the line has two Os and an blank, and then tells you what cell is blank.
		if(((cell1 == CellState.BLANK) && (cell2 == whatWins) && (cell3 == whatWins))){
			result = line.getCell1();
		}
		else if(((cell1 == whatWins) && (cell2 == CellState.BLANK) && (cell3 == whatWins))){
			result = line.getCell2();
		}
		else if(((cell1 == whatWins) && (cell2 == whatWins) && (cell3 == CellState.BLANK))){
			result = line.getCell3();
		}

		return result;

	}
	public Point possibleFork(TicTacToe.TurnState whosFork){
		Point result = null;

		ArrayList<Line> canidates = new ArrayList<Line>();

		for(int i = 0; i < lineArray.length ; i++){
			if(checkLineForSingleValue(lineArray[i], whosFork  ) )
				canidates.add(lineArray[i]);
		}
		for(int i= 0 ; i < canidates.size() ; i++ ){
			for(int j = i+1; j < canidates.size(); j++){
				result = canidates.get(i).getIntersection(canidates.get(j));
				if( result != null && getCell(result.x, result.y) == CellState.BLANK){
					return result;
				}
				else result = null;
			}
		}
		return result;
	}
	public boolean checkLineForSingleValue(Line line, TicTacToe.TurnState cellLookedFor){
		TicTacToe.CellState goal;
		if(cellLookedFor == TicTacToe.TurnState.XTURN){
			goal  = TicTacToe.CellState.X;
		}
		else{
			goal = TicTacToe.CellState.O;
		}

		//gets the cellStates for the cells in a line.
		TicTacToe.CellState cell1;
		TicTacToe.CellState cell2;
		TicTacToe.CellState cell3;
		cell1 = getCell(line.getCell1().x,line.getCell1().y);
		cell2 = getCell(line.getCell2().x,line.getCell2().y);
		cell3 = getCell(line.getCell3().x,line.getCell3().y);

		if(((cell1 == goal) && (cell2 == CellState.BLANK) && (cell3 == CellState.BLANK))){
			return true;
		}
		else if(((cell1 == CellState.BLANK) && (cell2 == goal) && (cell3 == CellState.BLANK))){
			return true;
		}
		else if(((cell1 == CellState.BLANK) && (cell2 == CellState.BLANK) && (cell3 == goal))){
			return true;
		}
		else{
			return false;
		}
	}
	public int findTurnNumber(){
		int iAnswer = 1;
		for(int i = 0; i < cellArray.length; i++){
			for( int j = 0; j < cellArray[0].length; j++) {
				if(cellArray[i][j] != CellState.BLANK)
					iAnswer++;
			}
		}
		return iAnswer;
	}

}

