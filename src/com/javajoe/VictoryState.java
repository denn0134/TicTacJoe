public class VictoryState {
	// the prepackeged responses
	String sXWins = "X has won!";
	String sOWins = "O has won!";


	// the process of checking for victory.
	// First aray coordinate denotes columb, second denotes row.
	//Fierst it checks if X won
		switch(iCellState){
	case (iCellState[0,0] + iCellState[0,1] + iCellState[0,2] == 3):
		System.out.println( sXWins );
		break;
	case (iCellState[1,0] + iCellState[1,1] + iCellState[1,2] == 3):
		System.out.println( sXWins );
		break;
	case (iCellState[2,0] + iCellState[2,1] + iCellState[2,2] == 3):
		System.out.println( sXWins );
		break;
	case (iCellState[0,0] + iCellState[1,0] + iCellState[2,0] == 3):
		System.out.println( sXWins );
		break;
	case (iCellState[0,1] + iCellState[1,1] + iCellState[2,1] == 3):
		System.out.println( sXWins );
		break;
	case (iCellState[0,2] + iCellState[1,2] + iCellState[2,2] == 3):
		System.out.println( sXWins );
		break;
	case (iCellState[0,0] + iCellState[1,1] + iCellState[2,2] == 3):
		System.out.println( sXWins );
		break;
	case (iCellState[2,0] + iCellState[1,1] + iCellState[0,2] == 3):
		System.out.println( sXWins );
		break;
//Now it checks if O won.
	case (iCellState[0,0] + iCellState[0,1] + iCellState[0,2] == -3):
		System.out.println( sOWins );
		break;
	case (iCellState[1,0] + iCellState[1,1] + iCellState[1,2] == -3):
		System.out.println( sOWins );
		break;
	case (iCellState[2,0] + iCellState[2,1] + iCellState[2,2] == -3):
		System.out.println( sOWins );
		break;
	case (iCellState[0,0] + iCellState[1,0] + iCellState[2,0] == -3):
		System.out.println( sOWins );
		break;
	case (iCellState[0,1] + iCellState[1,1] + iCellState[2,1] == -3):
		System.out.println( sOWins );
		break;
	case (iCellState[0,2] + iCellState[1,2] + iCellState[2,2] == -3):
		System.out.println( sOWins );
		break;
	case (iCellState[0,0] + iCellState[1,1] + iCellState[2,2] == -3):
		System.out.println( sOWins );
		break;
	case (iCellState[2,0] + iCellState[1,1] + iCellState[0,2] == -3):
		System.out.println( sOWins );
		break;
	//if its a draw...
	case ( ):
		System.out.println( "Draw" );
		break;
	default:
		System.out.println("Turn" + iTurn)


	}

}