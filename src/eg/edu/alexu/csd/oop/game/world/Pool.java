package eg.edu.alexu.csd.oop.game.world;

import java.util.ArrayList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.object.DetermineMovingState;
import eg.edu.alexu.csd.oop.game.object.MovingObjects;

public class Pool {
	private List<GameObject> available;
	private List<GameObject> inUse;
	private static Pool poolInstance = new Pool();
	private int maxNumOfObjs;
	private DetermineMovingState d;
	private Pool() {
		available = new ArrayList<>();
		inUse = new ArrayList<>();
	}

	public void intializePool(int maxObj , DetermineMovingState d){
		this.d = d;
		this.maxNumOfObjs = maxObj;
		for(int i = 0; i < maxNumOfObjs; i++){
			available.add(new MovingObjects(0, 0 , d));
		}
	}

	public static Pool getInstance(){
		return poolInstance;
	}

	public void resetInstance(){
		poolInstance = new Pool();
	}


	public GameObject requestObject(){
		if(available.size()!=0){
			inUse.add(available.get(0));
			return available.remove(0);
		}else{
			GameObject  gameObject = new MovingObjects(0,0,d);
			inUse.add(gameObject);
			return gameObject;
		}
	}

	public void releaseObject(GameObject gameObject){
		inUse.remove(gameObject);
		GameObject cleanedGameObject = clean(gameObject);
		available.add(cleanedGameObject);
	}
	private GameObject clean(GameObject go){
		((MovingObjects)go).intializeObj();
		return go;
	}
}
