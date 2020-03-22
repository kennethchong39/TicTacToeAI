package FinalProject;

import java.util.Comparator;

public class TicTacToeAI {

	/**
	 * A constructor to construct the Tic-Tac-Toe AI
	 */
	public TicTacToeAI() {
		tTTBoard = new int[3][3];
		checkComputerMove = false;
		checkComputerWin = false;
	}
	
	/**
	 * positionValueComparator is a comparator method used to compare the value of position (1,1) of TicTacToeAI
	 * @return a new comparator with compare by value of position (1, 1)
	 */
	public static Comparator<TicTacToeAI> positionValueComparator(){
		return new 
				Comparator<TicTacToeAI>() {
					public int compare(TicTacToeAI t1, TicTacToeAI t2) {
						return t1.getPositionValue(1, 1) -t2.getPositionValue(1, 1);
					}
				};
	}
	
	/**
	 * To get the value on Tic-Tac-Toe board based on the given i and j
	 * @param i is the row of the 3 by 3 grid
	 * @param j is the column of the 3 by 3 grid
	 * @return value on the position[i][j]
	 */
	public int getPositionValue(int i, int j) {
		return tTTBoard[i][j];
	}
	
	/**
	 * To set the value on Tic-Tac-Toe board based on the given i, j and player
	 * @param i is the row number
	 * @param j is the column number
	 * @param players either computer or human
	 */
	public void setPositionValue(int i, int j, int players) {
		tTTBoard[i][j] = players;
	}
	
	/**
	 * To randomly make a move as a computer on the 3 by 3 grid for difficulty level easy
	 * @param player is the number given as a computer
	 * @return a new i and j to the board system to place a move, else null if no available move
	 */
	public int[] computerMoveEasy(int player) {
		
		while(!checkComputerMove) {
			row = (int)(Math.random()*3);
			column = (int)(Math.random()*3);
			
			checkComputerMove = isValidMove(row, column);
			
			if(checkComputerMove == true) {
				setPositionValue(row, column, player);
				checkComputerMove = false;
				return new int[] {row,column};
			}
		}	
		return null;
	}
	
	/**
	 * To make a perfect move as computer on the 3 by 3 grid for difficulty level hard
	 * @param player is the number given as a computer
	 * @return i and j to place a move, else null if no available move
	 */
	public int[] computerMoveHard(int player) {
		
		//First check if the center position is empty, if so place the move (Higher chance to win)
		if(getPositionValue(1,1) == 0) {
			setPositionValue(1,1,player);
			return new int[] {1,1};
		}
		
		//There's possibility of computer to win, if so, computer should place that move
		int guaranteeWinMove[] = findWinMove(player);
		if(guaranteeWinMove != null) {
			int i = guaranteeWinMove[0];
			int j = guaranteeWinMove[1];
			setPositionValue(i,j,player);
			return new int[] {i,j};
		}
		
		//There's possibility of user winning, if so, place the user's winning move as a computer move
		int[] possibleUserWin = findUserWinMove(player);
		if(possibleUserWin != null) {
			int i = possibleUserWin[0];
			int j = possibleUserWin[1];
			setPositionValue(i,j,player);
			return new int[] {i,j};
		}
		
		//If none, then randomize the computer move	
		int[] finalMove = computerMoveEasy(player);
		if(finalMove != null) {
			int i = finalMove[0];
			int j = finalMove[1];
			setPositionValue(i,j,player);
			return new int[] {i,j};
		}
		
		//There's no move available to be placed
		return null;
		
	}
	
	/**
	 * To check if the specific location on the 3 by 3 grid is avialable
	 * @param row is the row of the 3 by 3 grid
	 * @param column is the column of the 3 by 3 grid
	 * @return true, if the given row and column is empty, false, if the given row and column is not avialable
	 */
	public boolean isValidMove(int row, int column) {
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(tTTBoard[row][column] == tTTBoard[i][j] && tTTBoard[i][j] == 0)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * To determine the winner of the Tic-Tac-Toe game
	 * @param player is the number given as to indicate as a computer or a user
	 * @return true, if any 3 X or 3 O set are available, false if there's no 3 X or 3 O in a diagonal, horizontal or vertical position 
	 */
	public boolean checkWin(int player) {
		//First row win
		if(tTTBoard[0][0] == player && tTTBoard[0][1] == player && tTTBoard[0][2] == player)
			return true;
				
		//Second row win
		else if(tTTBoard[1][0] == player && tTTBoard[1][1] == player && tTTBoard[1][2] == player)
			return true;
				
		//Third row win
		else if(tTTBoard[2][0] == player && tTTBoard[2][1] == player && tTTBoard[2][2] == player)
			return true;			
			
		//First Column win
		else if(tTTBoard[0][0] == player && tTTBoard[1][0] == player && tTTBoard[2][0] == player)
			return true;				
				
		//Second Column Win
		else if(tTTBoard[0][1] == player && tTTBoard[1][1] == player && tTTBoard[2][1] == player)
			return true;
				
		//Third Column win
		else if(tTTBoard[0][2] == player && tTTBoard[1][2] == player && tTTBoard[2][2] == player)
			return true;
				
		//Left to right diagonal win
		else if(tTTBoard[0][0] == player && tTTBoard[1][1] == player && tTTBoard[2][2] == player)
			return true;

		//Right to left diagonal win
		else if(tTTBoard[0][2] == player && tTTBoard[1][1] == player && tTTBoard[2][0] == player)
			return true;
	
		else
			return false;
	}
	
	/**
	 * To check if all the position on the 3 by 3 grid is filled
	 * @return true, if all the position on the 3 by 3 grid is filled, indicate draw, if no winner, false if there's still empty position available
	 */
	public boolean checkDraw() {
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(getPositionValue(i,j) == 0) {
					return false;
				}
			}
		}
		
		return true;

	}
	
	/**
	 * To locate a winning move
	 * @param player is the number given as to indicate as a computer or a user
	 * @return i and j that is the winning move, else null if no winning move
	 */
	private int[] findWinMove(int player) {
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(getPositionValue(i,j) == 0) {
					tTTBoard[i][j] = player;
					
					checkComputerWin = checkWin(player);
					
					tTTBoard[i][j] = 0;
					
					if(checkComputerWin == true) {
						return new int[] {i,j};
					}
				}
			}
		}
		
		return null;	
	}
	
	/**
	 * To locate the next user winning move
	 * @param player is the number given as to indicate as a computer or a user
	 * @return i and j to place a move at the position where user can win, else null if there's no user win move
	 */
	private int[] findUserWinMove(int player) {
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(getPositionValue(i,j) == 0) {
					tTTBoard[i][j] = player;

					int[] check = findWinMove(player - 1);
					if(check == null) {
						checkComputerWin = true;
					}
					else {
						checkComputerWin = false;
					}
					
					tTTBoard[i][j] = 0;
					
					if(checkComputerWin == true) {
						return new int[] {i,j};
					}
				}
			}
		}
		
		return null;
	}
	
	private final int tTTBoard[][];
	private int row;
	private int column;
	private boolean checkComputerMove;
	private boolean checkComputerWin;
}
