package eg.edu.alexu.csd.oop.game.world;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import eg.edu.alexu.csd.oop.game.DynamicLinkageShapes;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.Log4j;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.object.ConstantObjects;
import eg.edu.alexu.csd.oop.game.object.DetermineMovingState;
import eg.edu.alexu.csd.oop.game.object.ImageObject;
import eg.edu.alexu.csd.oop.game.object.MovingObjects;

public class Circus implements World {

	private final int width;
	private final int height;
	private  List<GameObject> constant = new ArrayList<GameObject>();
	private  List<GameObject> moving = new ArrayList<GameObject>();
	private  List<GameObject> control = new ArrayList<GameObject>();
	//private List<DetermineMovingState> det = new ArrayList<>();
	private DetermineMovingState determineMovingState;
	private int levels ;
	private List<String> supportedShapes ;
	private int x = 0;

	public Circus(int screenWidth, int screenHeight , int maxNumOfObjs, int levels ) {
		try {
			supportedShapes = DynamicLinkageShapes.getInstance().addPlugin();
			MovingObjects.setSupportedShapes(supportedShapes);
		} catch (ClassNotFoundException | IllegalAccessException
				| InstantiationException | IOException e) {
			Log4j.getInstance().getLogger().debug("ERROR LOADING SHAPES");
		}
		width = screenWidth;
		height = screenHeight;
		this.levels = levels;
		control.add(new ImageObject((screenWidth-1000), screenHeight - 120, "/clown.png"));
		control.add(new ImageObject((screenWidth-1200), screenHeight - 120, "/clown.png"));
		control.add(new ImageObject((screenWidth-750), screenHeight - 120, "/clown.png"));
		control.add(new ImageObject((screenWidth-350), screenHeight - 120, "/clown.png"));
		constant.add(new ImageObject(0, 0,"/background.jpg"));
		constant.add(new ConstantObjects(0, 35, Color.orange, 600, 5));
		constant.add(new ConstantObjects(width-600,35, Color.orange, 600, 5));
		constant.add(new ConstantObjects(0, 75, Color.orange,300, 5));
		constant.add(new ConstantObjects(width-300, 75, Color.orange, 300, 5));
		
		determineMovingState = DetermineMovingState.getInstance(screenHeight, moving, control, 30);
		Pool.getInstance().intializePool(maxNumOfObjs,determineMovingState);		
	}

	@Override
	public boolean refresh() {
		int levels = this.levels;
		int posY = 20;
		int i = 0;

		for(int j = 0  ; j< moving.size() ; j++){
			MovingObjects movingObj = (MovingObjects) moving.get(j);
			determineMovingState.setObjectState(movingObj);
			GameObject g = movingObj.getMoveState().move(movingObj , movingObj.isObjDirec());
			movingObj.getMoveState().intersect(movingObj, movingObj.isObjDirec());
			if(determineMovingState.isCatched(movingObj, movingObj.isObjDirec()))
				control = determineMovingState.getControllor();
			if(g == null)
				j--;

		}
		if(x == 0)
			while(levels-- > 0){
				GameObject constObject = constant.get(i+1);
				MovingObjects gameObjectRight = (MovingObjects)Pool.getInstance().requestObject();
				gameObjectRight.setObjDirec(true);
				MovingObjects gameObjectLeft = (MovingObjects) Pool.getInstance().requestObject();
				gameObjectRight.setLevelPlace(width - constObject.getWidth());
				gameObjectLeft.setLevelPlace(constObject.getWidth());
				gameObjectLeft.setObjDirec(false);
				gameObjectRight.setX(width-40);
				gameObjectLeft.setX(0);
				gameObjectRight.setY(posY);
				gameObjectLeft.setY(posY);
				moving.add(gameObjectRight);
				moving.add(gameObjectLeft);
				posY += 35;
				i+=2;
			}
		x++;
		if(x == 300)
			x = 0;
		return true ;
	}

	@Override
	public int getSpeed() {	
		return 5; 

	}
	@Override
	public int getControlSpeed() {	
		return 10;  
	}
	@Override
	public List<GameObject> getConstantObjects() {	
		return constant;	
	}
	@Override
	public List<GameObject> getMovableObjects() {	
		return moving;	
	}
	@Override
	public List<GameObject> getControlableObjects() {	
		return control;	
	}
	@Override
	public int getWidth() {	
		return width; 
	}
	@Override
	public int getHeight() { 
		return height; 
	}
	@Override
	public String getStatus() {
		return ("Score = " + Score.getInstance().getScore());	// update status
	}

}
