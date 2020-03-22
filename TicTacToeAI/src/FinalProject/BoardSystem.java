package FinalProject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;

public class BoardSystem {
	
	/**
	 * A constructor to construct the board system
	 * @param tGUI is the TicTacToe GUI
	 * @precondition isValidObject()
	 */
	public BoardSystem(TicTacToeGUI tGUI, LogFile lF) {
		
		assert !isValidObject() : "Violates precondtion isValidObject()";
		//initialize the Tic Tac Toe Board
		this.tGUI = tGUI;
		this.lF = lF;
		initializeBoard();
	}
	
	/**
	 * To validate the object
	 * @return true is object is not null, false, if object is null
	 */
	public boolean isValidObject() {
		if(tGUI != null && lF != null) {
			return true;
		}
		return false;
	} 
	
	/**
	 * BoardSystemcomparator is a comparator method used to compare the IndextoNumber method of two board system
	 * @return a new comparator with compare by index
	 */
	public static Comparator<BoardSystem> indexToNumberComparator(){
		return new 
				Comparator<BoardSystem>() {
					public int compare(BoardSystem b1, BoardSystem b2) {
						return b1.convertIndexToNumber(1, 1) - b2.convertIndexToNumber(1, 1);
					}
				};
	}
	
	/**
	 * To reset the game
	 */
	public void reset() {
		initializeBoard();
	}
	
	/**
	 * To read the history from log file and create a Tic-Tac-Toe to be printed 
	 */
	public void history() {

		try {
			readData = lF.readLog();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String printBoard = "";
		int count = 0;
		
		if(readData != null) {
			
			String[] matches = readData.split(",");
			
			for(int i = 0; i < matches.length; i++) {
				
				if(matches[i].equals("3")) {
					printBoard = printBoard + "\n You won!\n";
					continue;
				}
				
				if(matches[i].equals("4")) {
					printBoard = printBoard + "\n Computer won!\n";
					continue;
				}
				
				if(matches[i].equals("5")) {
					printBoard = printBoard + "\n It's a draw!\n";
					continue;
				}
				
				if(count == 3) {
					printBoard = printBoard + "\n";
					printBoard = printBoard + "----------\n";
					count= 0;
				}
				
				if(matches[i].equals("0")) {
					printBoard = printBoard + "   ";
					count++;
					
				}
				
				if(matches[i].equals("1")) {
					printBoard = printBoard + " X ";
					count++;
				}
				
				if(matches[i].equals("2")) {
					printBoard = printBoard + " O ";
					count++;
				}
				
				if(count < 3) {
					printBoard = printBoard + " | ";
				}
			}
			
			tGUI.displayHistory(printBoard);
		}
		else {
			tGUI.displayHistory("There's no previous match!");
		}		
	}
	
	/**
	 * To maintain the state of the Tic-Tac-Toe depends on the user input
	 * @param button is the input given by the user
	 */
	public void instructionButton(String button) {
		if(state == CONNECTED) {
			if(button.equals("Play")) {
				initializeGame();
			}
		} else if(state == CHOOSINGLEVEL) {
			if(button.equals("Easy")) {
				randomStart = (int)(Math.random()*2);
				int start = randomStart;
				start = 0;
				if(start == 0) {
					state = EASYLEVELHUMAN;
					tGUI.display("Difficulty Level: EASY \nLet's play! \nPlayer starts the first move. \nYour move!");
					tGUI.enableButtons(true);
					playing = true;
				}
				else if(start == 1)
				{	
					state = EASYLEVELCOMPUTER;
					tGUI.display("Difficulty Level: EASY \nLet's play! \nComputer starts the first move. \nYour move!");
					tGUI.enableButtons(true);
					playing = true;
					if(totalMoves == 0) {
						computerMove();
						totalMoves += 1;
						
					}
					
				}
			}

			if(button.equals("Hard")) {
				
				randomStart = (int)(Math.random()*2);
				int start = randomStart;
				if(start == 0) {
					state = HARDLEVELHUMAN;
					tGUI.display("Difficulty: HARD \nLet's play! \nPlayer starts the first move. \nYour move!");
					tGUI.enableButtons(true);
					playing = true;
				}
				else if(start == 1) {
					state = HARDLEVELCOMPUTER;
					tGUI.display("Difficulty: HARD \nLet's play! \nComputer starts the first move. \nYour move!");
					tGUI.enableButtons(true);
					playing = true;
					if(totalMoves == 0) {
						computerHardMove();
						totalMoves += 1;
					}
				}
			}
		}
		else if(state == GAMEDONE) {
			if(button.equals("Play")) {
				reset();
				initializeGame();
			}
		}
	}
	
	/**
	 * To control, validate and place the move of the player and computer in the game depends on the state of the application
	 * @param i is the row of the 3 by 3 grid
	 * @param j is the column of the 3 by 3 grid
	 */
	public void gameButton(int i, int j) {		
		if(state == EASYLEVELHUMAN) {
			if(aI.isValidMove(i, j) && totalMoves < 9) {
				humanMove(i,j);
				totalMoves++;
				checkWinner();
				
				if(totalMoves < 8) {
					computerMove();
					totalMoves++;
					checkWinner();
				}
			}
		}
		else if(state == EASYLEVELCOMPUTER) {
			if(aI.isValidMove(i, j) && totalMoves < 9) {
				
				if(totalMoves % 2 == 1) {
					humanMove(i,j);
					totalMoves++;
					checkWinner();
				}
				
				if(totalMoves % 2 == 0) {
					computerMove();
					totalMoves++;
					checkWinner();
				}

			}
		}
		else if(state == HARDLEVELHUMAN) {
			if(aI.isValidMove(i, j) && totalMoves < 9) {
				humanMove(i,j);
				totalMoves++;
				checkWinner();
				
				if(totalMoves < 8) {
					computerHardMove();
					totalMoves++;
					checkWinner();
				}
			}

		}
		else if(state == HARDLEVELCOMPUTER) {
			if(aI.isValidMove(i, j) && totalMoves < 9) {
				
				if(totalMoves % 2 == 1) {
					humanMove(i,j);
					totalMoves++;
					checkWinner();
				}
				
				if(totalMoves % 2 == 0) {
					computerHardMove();
					totalMoves++;
					checkWinner();
				}
			}
		}
	}
	
	/**
	 * To initialize the board
	 */
	private void initializeBoard() {
		state = CONNECTED;
		tGUI.display(welcomePrompt);
		tGUI.enableButtons(false);
		tGUI.enableHistory(false);
		playing = false;
		totalMoves = 0;
		recordMove = new int[9];
	}
	
	/**
	 * To start the game
	 */
	private void initializeGame() {
		state = CHOOSINGLEVEL;
		aI = new TicTacToeAI();
		tGUI.enableHistory(true);
		tGUI.display(chooseLevelPrompt);
	}
	
	/**
	 * To make a move based on the given user's input
	 * @param i is the row of the 3 by 3 grid
	 * @param j is the column of the 3 by 3 grid
	 */
	private void humanMove(int i, int j) {
		if(!playing) return;
		
		if(aI.isValidMove(i,j) == true) {
			int getPosition = convertIndexToNumber(i,j);
			recordMove[getPosition] = HUMAN;
			tGUI.updateGameButton(i, j, humanIsX);
			aI.setPositionValue(i, j, HUMAN);
		}
	}
	
	/**
	 * To make a move as a computer given difficulty level is easy
	 */
	private void computerMove() {
		if(!playing) return;
			
		int computerPlacement[] = aI.computerMoveEasy(COMPUTER);
			
		if(computerPlacement != null) {
			int i = computerPlacement[0];
			int j = computerPlacement[1];
			int getPosition = convertIndexToNumber(i,j);
			recordMove[getPosition] = COMPUTER;
			tGUI.updateGameButton(i, j, computerIsO);
		}
	}
	
	/**
	 * To make a move as computer given difficulty level is hard
	 */
	private void computerHardMove() {
		if(!playing) return;
		
		int computerPlacement[] = aI.computerMoveHard(COMPUTER);
		
		if(computerPlacement != null) {
			int i = computerPlacement[0];
			int j = computerPlacement[1];
			int getPosition = convertIndexToNumber(i,j);
			recordMove[getPosition] = COMPUTER;
			tGUI.updateGameButton(i, j, computerIsO);
		}
	}
	
	/**
	 * To check for a winner or a draw in a given match
	 */
	private void checkWinner() {
		 
		if(aI.checkWin(HUMAN) == true) 
		{
			endGame(humanWon, HUMANWINS);	
		} 
		
		if(aI.checkWin(COMPUTER) == true) 
		{
			endGame(computerWon, COMPUTERWINS);	
		} 
		
		if(aI.checkDraw() == true && aI.checkWin(HUMAN) == false && aI.checkWin(COMPUTER) == false)
		{
			endGame(drawMatch, DRAWS);
		}
	}
	
	/**
	 * To end the game once identify the winner
	 * @param result is the result to be deliver to the user
	 */
	private void endGame(String result, int winResult) {
		state = GAMEDONE;
		tGUI.display(result + "\n" + furtherInstructions);
		saveToFile(winResult);
		tGUI.enableButtons(false);
		playing = false;
	}
	
	/**
	 * To save the match to the log file
	 * @param winResult is the winning result of the match
	 */
	private void saveToFile(int winResult) {
		String toBeSave = "";

		for(int i = 0; i < recordMove.length; i++) {
			toBeSave = toBeSave + recordMove[i] + ",";
		}	
		
		toBeSave = toBeSave + winResult + ","; 
		
		
		try {
			lF.writeLog(toBeSave);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * To convert the index i and j to a number to reference the position on the recordMove array
	 * @param i is the row of the 3 by 3 grid of the Tic-Tac-Toe
	 * @param j is the column of the 3 by 3 grid of the Tic-Tac-Toe
	 * @return position number of the array it should be placed on the array
	 */
	private int convertIndexToNumber(int i, int j) {
		if(i == 0 && j == 0) {
			return 0;
		}
		else if(i == 0 && j == 1) {
			return 1;
		}
		else if(i == 0 && j == 2) {
			return 2;
		}
		else if(i == 1 && j == 0) {
			return 3;
		}
		else if(i == 1 && j == 1) {
			return 4;
		}
		else if(i == 1 && j == 2) {
			return 5;
		}
		else if(i == 2 && j == 0) {
			return 6;
		}
		else if(i == 2 && j == 1) {
			return 7;
		}
		else if(i == 2 && j == 2) {
			return 8;
		}
		
		return -1;
	}
	
	private final TicTacToeGUI tGUI;
	private final LogFile lF;
	
	private TicTacToeAI aI;
	private int state;
	private int totalMoves;
	private int randomStart;
	private int[] recordMove;
	private String readData;
	private boolean playing;
	
	private static final int HUMAN = 1;
	private static final int COMPUTER = 2;
	private static final int HUMANWINS = 3;
	private static final int COMPUTERWINS = 4;
	private static final int DRAWS = 5;
	private static final int CONNECTED = 6;
	private static final int CHOOSINGLEVEL = 7;
	private static final int EASYLEVELHUMAN = 8;
	private static final int EASYLEVELCOMPUTER = 9;
	private static final int HARDLEVELHUMAN = 10;
	private static final int HARDLEVELCOMPUTER = 11;
	private static final int GAMEDONE = 12;
	
	private static final String welcomePrompt = "Welcome to The Tic Tac Toe Game!\nPress play to start!";
	private static final String chooseLevelPrompt = "Pick your difficulty level!";
	private static final String humanIsX = "X";
	private static final String computerIsO = "O";
	private static final String furtherInstructions = "To play again, press Play!";
	private static final String humanWon = "Congratulations! You have won!";
	private static final String computerWon = "Sorry, you lost! Try again!";
	private static final String drawMatch = "It's a draw!";
	
}
