package FinalProject;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class TicTacToeMain {
	
	/**
	 * A main method
	 * @param args is java command line arguments
	 */
	public static void main(String[] args) {
		
		LogFile lF = new LogFile();
		TicTacToeGUI tTTGUI = new TicTacToeGUI();
		BoardSystem boardSystem = new BoardSystem(tTTGUI, lF);
		
		tTTGUI.link(boardSystem);
		
		//To test indexToNumberComparator with at least two BoardSystem
		List<BoardSystem> list = new ArrayList<BoardSystem>();
		list.add(boardSystem);
		list.add(new BoardSystem(tTTGUI,lF));
		Collections.sort(list, BoardSystem.indexToNumberComparator());
		
		//To test getPositionValueComparator with at least two TicTacToeAI
		List<TicTacToeAI> tList = new ArrayList<TicTacToeAI>();
		TicTacToeAI t1 = new TicTacToeAI();
		TicTacToeAI t2 = new TicTacToeAI();
		tList.add(t1);
		tList.add(t2);
		Collections.sort(tList, TicTacToeAI.positionValueComparator());
		
	}
}
