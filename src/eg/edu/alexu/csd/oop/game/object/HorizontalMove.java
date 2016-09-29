package eg.edu.alexu.csd.oop.game.object;

import eg.edu.alexu.csd.oop.game.GameObject;

public class HorizontalMove implements Move {
	private final int STEP = 1; // the step the shape moves by
	private DetermineMovingState determineMovingState;
	
	public HorizontalMove(DetermineMovingState newState) {
		determineMovingState = newState;
	}
	
	@Override
	public GameObject move(GameObject obj, boolean isRight) {
		if(isRight)
			obj.setX(obj.getX() - STEP);
		else
			obj.setX(obj.getX() + STEP);
		return obj;
	}
	
	@Override
	public boolean intersect(GameObject obj, boolean isRight) {
		int convertingMovePoint = ((MovingObjects)obj).getLevelPlace();
		if ((obj.getX() > convertingMovePoint-40 && isRight) || (obj.getX() <= convertingMovePoint && !isRight))
			determineMovingState.setMovingState(determineMovingState.getHorizentalState());
		else{
			determineMovingState.setMovingState(determineMovingState.getVerticalState());
			determineMovingState.move(obj, isRight);
		}
		return true;
	}
}