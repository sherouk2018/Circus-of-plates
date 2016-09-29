package eg.edu.alexu.csd.oop.game.object;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Shapes {

	private Color color;
	
	public abstract void draw (int x , int y , int width , int height , Graphics2D g2);
	
	public void setColor(Color c){
		color = c;
	}
	public Color getColor(){
		return color;
	}
	public abstract int getWidth();
	public abstract int getHeight();
	
	
}
