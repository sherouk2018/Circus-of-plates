package eg.edu.alexu.csd.oop.game.object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import eg.edu.alexu.csd.oop.game.GameObject;

public class ConstantObjects implements GameObject{

	private final int SPRITE_WIDTH ;
	private final int SPRITE_HEIGHT ;
	private final int MAX_MSTATE = 1;
	// an array of sprite images that are drawn sequentially
	private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
	private int x;
	private int y;
	
	public ConstantObjects(int posX, int posY, Color color, int width ,int height) {
		this.x = posX;
		this.y = posY;
		SPRITE_WIDTH = width;
		SPRITE_HEIGHT = height;
		// create a bunch of buffered images and place into an array, to be displayed sequentially
		spriteImages[0] = new BufferedImage(SPRITE_WIDTH, SPRITE_HEIGHT,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = spriteImages[0].createGraphics();		
//		if(bg!=null){
//			g2.drawImage(bg, posX, posY, null);
//			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//			g2.dispose();
//			Log4j.getInstance().getLogger().debug("*******BackGround Set******88");
//		}
	//	else{
			g2.setColor(color);
			g2.setBackground(color);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setStroke(new BasicStroke(10));
			g2.drawLine(0, 0, getWidth(), 0);
			g2.dispose();
//		}
	}
	
	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int mX) {
		this.x = mX;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int mY) {
		this.y = mY;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		return spriteImages;
	}

	@Override
	public int getWidth(){
		return SPRITE_WIDTH;
	}

	@Override
	public int getHeight() {
		return SPRITE_WIDTH;
	}

	@Override
	public boolean isVisible() {
		return true;
	}
	
}
