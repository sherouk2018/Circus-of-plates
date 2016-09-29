package eg.edu.alexu.csd.oop.game.object;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;

public class BluePlate extends Shapes{
	private final int WIDTH = 40 ;
	private final int HEIGHT = 25;
	@Override
	public void draw(int x, int y, int width, int height, Graphics2D g2) {
		g2.setColor(super.getColor());
		g2.setBackground(super.getColor());
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(10));
		Image img = new ImageIcon(this.getClass().getResource("/blueplate.png")).getImage(); 
		//g2.drawImage(img, x, y, null);
		g2.drawImage(img, x, y,  WIDTH , HEIGHT , getColor(), null);
		g2.dispose();
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return WIDTH;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return HEIGHT;
	}
}
