package TicTacEngine;

public class TicTacToeTest{

	public static void main(String args[]){
		TicTacToe tester = new TicTacToe();
		System.out.println(tester.getCell(1,1));
		try {
			tester.setCell(1, 1);
		}
		catch(TicTacExeption tte){
			System.out.println(tte.getMessage());
		}
		System.out.println(tester.getCell(1,1));
		try {
			tester.setCell(1, 1);
		}
		catch(TicTacExeption tte){
			System.out.println(tte.getMessage());
		}
		System.out.println(tester.getCell(1,1));
	}
}