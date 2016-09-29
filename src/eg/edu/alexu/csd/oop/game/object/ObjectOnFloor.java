package eg.edu.alexu.csd.oop.game.object;

import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.world.Pool;

public class ObjectOnFloor implements Move{

	private DetermineMovingState determineMovingState;
	List<GameObject> moving ;
	
	public ObjectOnFloor(List<GameObject> moving , DetermineMovingState newState) {
		determineMovingState = newState;
		this.moving = moving;
	}
	
	@Override
	public GameObject move(GameObject obj, boolean isRight) {
		Pool.getInstance().releaseObject(obj);
		moving.remove(obj);
		return null;
	}

	@Override
	public boolean intersect(GameObject obj, boolean isRight) {
		determineMovingState.setMovingState(determineMovingState.getHorizentalState());
		return true;
	}

}
