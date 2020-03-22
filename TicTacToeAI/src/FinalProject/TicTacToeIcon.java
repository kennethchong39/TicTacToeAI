package FinalProject;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class TicTacToeIcon implements Icon {
	
	/**
	 * A constructor to construct the Tic-Tac-Toe icon of the application
	 * @param width is the dimension of the icon
	 * @precondition width > 0
	 */
	public TicTacToeIcon(int width) {
		
		assert width > 0 : "Width has to be greater than 0";
		this.width = width;
		stroke = new BasicStroke(5);
	}
	
	/**
	 * To get the icon width, inherited from the abstract class
	 * @return the width of icon
	 */
	public int getIconWidth() {
		return width;
	}
	
	/**
	 * To get the height of the icon, inherited from the abstract class
	 * @return the height of the icon
	 */
	public int getIconHeight() {
		return width;
	}
	
	/**
	 * To paint the Tic-Tac-Toe Icon
	 * @param C is the component of the icon
	 * @param g is the graphic 2d of the icon
	 * @param x is the horizontal dimension of the icon
	 * @param y is the vertical dimension of the icon
	 */
	public void paintIcon(Component C, Graphics g, int x, int y) {
		doIconDrawing(g, x, y);
	}
	
	/**
	 * To draw out the icon with the given graphics, x ,and y
	 * @param g is the graphic 2d parameter
	 * @param x is the horizontal dimension of the icon
	 * @param y is the vertical dimension of the icon
	 */
	public void doIconDrawing(Graphics g, int x, int y) {
		
		Graphics2D g2d = (Graphics2D) g.create();
		
		/**
		 * Set the color background of the rectangle
		 */
		g2d.setColor(Color.white);
		g2d.fillRect(x - 2, y - 20, width + 18, width + 20);
		
		/**
		 * Set the color and dimension of the border
		 */
		g2d.setColor(Color.darkGray);
		g2d.drawRect(x - 2, y - 20, width + 18, width + 20);
		
		/**
		 * Draw the X icon in red
		 */
		g2d.setColor(Color.red);

		g2d.setStroke(stroke);
		g2d.drawLine(x + width -50, y - 12, x + width + 10, y + width - 25);
		g2d.drawLine(x + width - 50, y + width - 23, x + width+5 , y - 15);
		
		/**
		 * Draw the circle icon in blue
		 */
		g2d.setColor(Color.BLUE);
		Ellipse2D.Double circle = new Ellipse2D.Double(x+3, y+35, width-40, width-40);
		g2d.fill(circle);
		
		g2d.dispose();
	}
	
	private final int width;
	private final BasicStroke stroke;
}
