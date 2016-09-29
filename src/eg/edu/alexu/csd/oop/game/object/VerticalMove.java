package eg.edu.alexu.csd.oop.game.object;

import eg.edu.alexu.csd.oop.game.GameObject;


public class VerticalMove implements Move {
	private final int STEP = 1; // the step the shape moves by
	private final int SCREENHEIGHT;
	private DetermineMovingState determineMovingState;

	public  VerticalMove(int screenHeight , DetermineMovingState newState){
		determineMovingState = newState;
		this.SCREENHEIGHT = screenHeight;
	}
	@Override
	public GameObject move(GameObject obj , boolean isRight ) {
		obj.setY(obj.getY() + STEP);
		return obj;
	}
/*
	private boolean isOnFloor(){
		if(posY == SCREENHEIGHT)
			return true;
		return false;
	}
*/
	@Override
	public boolean intersect(GameObject obj, boolean isRight) {
		if(obj.getY() == SCREENHEIGHT)
			determineMovingState.setMovingState(determineMovingState.getOnFloorState());
		return true;
	}	
}