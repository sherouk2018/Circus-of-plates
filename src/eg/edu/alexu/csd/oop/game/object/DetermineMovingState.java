package eg.edu.alexu.csd.oop.game.object;

import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;

public class DetermineMovingState {
	private MovingObjects objectState;
	private int screenHeight ;
	private HorizontalMove horizontal;
	private VerticalMove vertical;
	private ObjectOnFloor objectOnFloor;
	private  List<GameObject> moving;
	private ShapeCatcher shapeCatched;
	private List<GameObject> newControl;
	private static DetermineMovingState instance ;
	
	public static DetermineMovingState getInstance( int screenheight,  List<GameObject> moving, List<GameObject> control, int handWidth){
		if(instance == null)
			instance = new DetermineMovingState(screenheight, moving, control, handWidth);
		return instance;
	}
	
	private DetermineMovingState( int screenheight,  List<GameObject> moving, List<GameObject> control, int handWidth){
		this.screenHeight = screenheight;
		this.moving = moving;
		this.newControl = control;
		//objectState = (MovingObjects) obj;
		shapeCatched = new ShapeCatcher(screenheight,handWidth, control);
		horizontal = new HorizontalMove(this);
		objectOnFloor = new ObjectOnFloor(moving , this);
		vertical = new VerticalMove(screenHeight , this);
		//objectState.setMoveState(horizontal);
	}

	public void setObjectState(MovingObjects objectState) {
		this.objectState = objectState;
	}

	public List<GameObject> getControllor (){
		return newControl ;
	}
	
	public boolean isCatched(GameObject obj, boolean rightDirec){
		if(shapeCatched.intersect(obj , rightDirec)){
				moving.remove(obj);
				newControl = shapeCatched.getControl();
				return true;
		}
		return false;
	}

	public GameObject move(GameObject obj, boolean isRight){
		return objectState.getMoveState().move(obj, isRight);
	}

	public boolean intersect(GameObject obj , boolean isRight){
		return objectState.getMoveState().intersect(obj, isRight);
	}
	
	public Move getHorizentalState(){
		return horizontal;
	}
	
	public Move getVerticalState(){
		return vertical;
	}
	
	public Move getOnFloorState(){
		return objectOnFloor;
	}
	
	public void setMovingState(Move newState){
		objectState.setMoveState(newState);
	}
}