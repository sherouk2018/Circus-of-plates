package eg.edu.alexu.csd.oop.game.object;

import eg.edu.alexu.csd.oop.game.GameObject;

public interface Move {
	
	public GameObject move(GameObject obj, boolean isRight);
	
	public boolean intersect(GameObject obj , boolean isRight);
}
