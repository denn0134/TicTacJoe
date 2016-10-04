package TicTacEngine;

public class TicTacToeTest{

	public static void main(String args[]){
		TicTacToe tester = new TicTacToe();
		tester.setCell(1,1, TicTacToe.CellState.X);
		System.out.println("done");
	}
}