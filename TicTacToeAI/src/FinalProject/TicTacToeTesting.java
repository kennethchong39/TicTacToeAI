package FinalProject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TicTacToeTesting {

	/**
	 * testIsValidObject checks the validation of a board system object
	 */
	@Test
	public void testIsValidObject() {
		TicTacToeGUI tgui = new TicTacToeGUI();
		LogFile lf = new LogFile();
		lf = null;
		BoardSystem b = new BoardSystem(tgui,lf);
		assertTrue(b.isValidObject()==false);
	}
	
	/**
	 * testIsValidMove checks the validation of a move of TicTacToeAI by checking the value of indexes
	 */
	@Test
	public void testIsValidMove() {
		final int tTTBoard[][]= new int[3][3];
		tTTBoard[2][2]=0;
		int row = 2;
		int col=2;
		TicTacToeAI tA = new TicTacToeAI();
		assertTrue(tA.isValidMove(row, col)== true);
	}
}
