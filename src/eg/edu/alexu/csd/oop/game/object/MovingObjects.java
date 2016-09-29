package eg.edu.alexu.csd.oop.game.object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;



import java.security.SecureRandom;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.world.Flyweight;

public class MovingObjects implements GameObject{

	private  int spriteWidth ;
	private  int spriteHeight ;
	private static final int MAX_MSTATE = 1;
	private int levelPlace ;
	private boolean objDirec;
	private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
	private int x;
	private int y;
	private boolean visible = true;
	private Move moveState;
	//private Color colors[]={Color.GREEN,Color.BLUE,Color.RED,Color.CYAN,Color.MAGENTA,Color.YELLOW,Color.ORANGE,Color.PINK,Color.BLACK};
	private Color color;
	private Color colors[]={Color.GREEN,Color.BLUE,Color.RED,Color.CYAN};
	private static List<String> shapes;
	private boolean isControl;
	private DetermineMovingState determineMovingState;
	public MovingObjects(int posX, int posY ,  DetermineMovingState determineMovingState) {
		this.x = posX;
		this.y = posY;
		this.determineMovingState = determineMovingState;
		setMoveState(this.determineMovingState.getHorizentalState());
		intializeObj();
	}

	public static void setSupportedShapes(List<String> supportedShapes){
		shapes =  supportedShapes;
	}
	public void intializeObj(){
		isControl = false;		
		color = colors[new SecureRandom().nextInt(colors.length)];
		String str = shapes.get(new SecureRandom().nextInt(shapes.size()));
		str = "BlueBall";
		Shapes s = Flyweight.getInstance().getShape(color , str);
		spriteImages[0] = new BufferedImage(s.getWidth(), s.getHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = spriteImages[0].createGraphics();	
		spriteWidth = s.getWidth();
		spriteHeight = s.getHeight();
		s.draw(0, 0, s.getWidth(), s.getHeight(), g2);
		this.x = -100;
		this.y = -100;
	}
	public Color getColor() {
		return color;
	}

	public void setControl(boolean isControl) {
		this.isControl = isControl;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int mX) {
		this.x = mX;
	}

	public boolean isObjDirec() {
		return objDirec;
	}

	public void setObjDirec(boolean objDirec) {
		this.objDirec = objDirec;
	}
	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int mY) {
		if(!isControl)
			this.y = mY;
	}

	public Move getMoveState() {
		return moveState;
	}

	public void setMoveState(Move moveState) {
		this.moveState = moveState;
	}
	
	@Override
	public BufferedImage[] getSpriteImages() {
		return spriteImages;
	}

	
	@Override
	public int getWidth(){
		return spriteWidth;
	}

	@Override
	public int getHeight() {
		return spriteHeight;
	}

	public void setVisible(boolean visible){
		this.visible = visible;
	}
	
	@Override
	public boolean isVisible() {
		return visible;
	}
	
	
	
	public void setLevelPlace(int levelPlace){
		this.levelPlace = levelPlace;
	}
	
	public int getLevelPlace(){
		return this.levelPlace;
	}
}
