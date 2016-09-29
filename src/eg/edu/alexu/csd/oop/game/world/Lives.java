package eg.edu.alexu.csd.oop.game.world;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.Log4j;

public class Lives {
	private List<GameObject> snapshot = new ArrayList<>();
	private LinkedHashMap<Integer, List<GameObject>> listsOfPlates = new LinkedHashMap<Integer, List<GameObject>>();
	private final int NUMOFCONTROLLER;

	public Lives(int NUMOFCONTROLLER) {
		this.NUMOFCONTROLLER = NUMOFCONTROLLER;
		for (Integer i = 0; i < (NUMOFCONTROLLER * 2); i++) {
			listsOfPlates.put(i, new ArrayList<GameObject>());
		}
	}

	public void takeSnapshot(List<GameObject> control,
			LinkedHashMap<Integer, List<GameObject>> listsOfShapes) {
		snapshot.clear();
		for (GameObject object : control) {
			snapshot.add(object);
		}
		for (Integer i = 0; i < NUMOFCONTROLLER * 2; i++) {
			listsOfPlates.get(i).clear();
			for (GameObject object : listsOfShapes.get(i)) {	
				listsOfPlates.get(i).add(object);
			}

		}
	}

	public void update(List<GameObject> arg1,
			LinkedHashMap<Integer, List<GameObject>> args2) {
		arg1.clear();
		for (GameObject object : snapshot) {
			arg1.add(object);
		}
		for (Integer i = 0; i < NUMOFCONTROLLER * 2; i++) {
			args2.get(i).clear();
			for (GameObject object : listsOfPlates.get(i)) {		
				args2.get(i).add(object);
			}

		}

	}

}
