package FinalProject;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TicTacToeGUI {
	
	/**
	 * Constructor to construct the Tic-Tac-Toe GUI
	 */
	public TicTacToeGUI() {
		
		buttons = new JButton[3][3];
		
		//Game Button Panel: start and end button
		JPanel gameButtonPanel = new JPanel(new GridLayout(2,2));
		
		//Play Button, to play the game
		final String play = "Play";
		JButton playButton = new JButton(play);
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				boardSystem.instructionButton(play);
			}
		});
		
		//Reset Button, to reset the game
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				boardSystem.reset();
			}
		});
		
		//Easy Button, for easy game
		final String easy = "Easy";
		JButton easyButton = new JButton("Easy");
		easyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				boardSystem.instructionButton(easy);
			}
		});
		
		//Hard Button, for hard game
		final String hard = "Hard";
		JButton hardButton = new JButton("Hard");
		hardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				boardSystem.instructionButton(hard);
			}
		});
		gameButtonPanel.add(playButton);
		gameButtonPanel.add(resetButton);
		gameButtonPanel.add(easyButton);
		gameButtonPanel.add(hardButton);
		
		//Display Panel, to display instructions
		JPanel displayPanel = new JPanel();
		displayPanel.setLayout(new BorderLayout());
		displayPanel.add(new JLabel("Tic Tac Toe Instructions"), BorderLayout.NORTH);
		displayField = new JTextArea(10,20);
		displayField.setEditable(false);
		displayPanel.add(displayField, BorderLayout.CENTER);
		displayPanel.add(gameButtonPanel, BorderLayout.SOUTH);
		
		//Display History Panel, to display previous matches of Tic Tac Toe
		JPanel historyPanel = new JPanel();
		historyPanel.setLayout(new BorderLayout());
		historyPanel.add(new JLabel("History of Previous Matches"), BorderLayout.NORTH);
		
		//History Button, to show history
		historyButton = new JButton("History");
		historyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				boardSystem.history();
			}
		});
		
		historyField = new JTextArea(10,20);
		historyField.setEditable(false);
		JScrollPane hPanel = new JScrollPane(historyField);
		historyPanel.add(hPanel, BorderLayout.CENTER);
		historyPanel.add(historyButton, BorderLayout.SOUTH);

		
		//TicTacToePanel: To draw the 3 by 3 grid buttons
		JPanel TicTacToePanel = new JPanel(new GridLayout(3,3));
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				buttons[i][j] = new JButton(" ");
				TicTacToePanel.add(buttons[i][j]);
				final int row = i;
				final int column = j;
				buttons[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						boardSystem.gameButton(row, column);
					}
				});
			}	
		}
		
		//To combine the information panel with Tic-Tac-Toe Buttons as one piece 
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(TicTacToePanel, BorderLayout.CENTER);
		
		//The icon interface type for the GUI
	    JPanel icon = new JPanel();
	    TicTacToeIcon tTTIcon = new TicTacToeIcon(100);
	    JLabel iconLabel = new JLabel(tTTIcon);
	    icon.add(iconLabel);
	    icon.setSize(1000, 1000);

		//A frame to combine all panels together
		JFrame frame = new JFrame();
		frame.setTitle("Tic Tac Toe Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(displayPanel,BorderLayout.WEST);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(historyPanel,BorderLayout.EAST);
		frame.add(icon, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * To send and display instructions to player
	 * @param output is the instructions to be deliver
	 */
	public void display(String output) {
		displayField.setText(output);
	}
	
	/**
	 * To send and display instructions to player
	 * @param output is the instructions to be deliver
	 */
	public void displayHistory(String output) {
		historyField.setText(output);
	}
	
	/**
	 * To enable the 3 by 3 grid Tic-Tac-Toe buttons
	 * @param given if true, then enable all the 3 by 3 grid buttons, if false, disable all the 3 by 3 buttons.
	 */
	public void enableButtons(boolean given) {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				buttons[i][j].setEnabled(given);
				if(given) {
					buttons[i][j].setText(" ");
				}
			}
		}
	}
	
	/**
	 * To enable the history button 
	 * @param given if true, then enable the history button, if false, disable the history button
	 */
	public void enableHistory(boolean given) {
		historyButton.setEnabled(given);
	}
	
	/**
	 * To mark the specific location of 3 by 3 grid Tic-Tac-Toe buttons
	 * @param i is the row of the grid
	 * @param j is the column of the grid
	 * @param mark is X or O, to be marked on the grid given i and j 
	 */
	public void updateGameButton(int i, int j, String mark) {
		buttons[i][j].setText(mark);
	}
	
	/**
	 * To link the TicTacTacGUI to the board system
	 * @param givenBoardSystem is a board system to be given to the TicTacToeGUI 
	 */
	public void link(BoardSystem givenBoardSystem) {
		this.boardSystem = givenBoardSystem;
	}
	
	private BoardSystem boardSystem;
	private final JTextArea displayField;
	private final JTextArea historyField;
	private final JButton buttons[][];
	private final JButton historyButton;
}
