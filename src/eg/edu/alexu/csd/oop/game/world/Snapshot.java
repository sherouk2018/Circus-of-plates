package eg.edu.alexu.csd.oop.game.world;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


import java.util.Observable;
import java.util.Observer;

import eg.edu.alexu.csd.oop.game.GameObject;

public class Snapshot implements Observer{

	private static Snapshot instance ; 	
	private LinkedHashMap<Integer, List<GameObject>> listsSnapshot = 
			new LinkedHashMap<Integer, List<GameObject>>();
	private List<GameObject> controlSnapshot = new ArrayList<>();
	private int numOfContollers;
	
	private Snapshot(int numOfContollers, List<GameObject> control){
		for(Integer i = 0; i < numOfContollers; i++){
			listsSnapshot.put(i, new ArrayList<GameObject>());
		}
		for(GameObject object : control){
			controlSnapshot.add(object);
		}
		this.numOfContollers = controlSnapshot.size();
	}
	
	public static Snapshot getInstance(int numOfContollers, List<GameObject> control){
		if(instance == null)
			instance = new Snapshot(numOfContollers, control);
		return instance;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Object[] neededElements = (Object[]) arg;
		if(neededElements == null)
			return;
		if(neededElements.length == 2)
			updateSnapshots(neededElements);
		else
			updateAfterScore(neededElements);
	}
	
	@SuppressWarnings("unchecked")
	public void updateSnapshots(Object[] neededElements) {
		Integer numOfList = (Integer) neededElements[0];
		LinkedHashMap<Integer, List<GameObject>> listsOfShapes = (LinkedHashMap<Integer, List<GameObject>>) neededElements[1];
		//List<GameObject> control = (List<GameObject>) neededElements[3];
		List<GameObject> list = listsSnapshot.get(numOfList);
		list.clear();
		int index = numOfContollers - 1;
		for(GameObject object : listsOfShapes.get(numOfList)){
			list.add(object);
			if(controlSnapshot.contains(object)){
				index = controlSnapshot.indexOf(object);
			}else{
				controlSnapshot.add(++index, object);
			}
		}
	}
	
	public void updateAfterScore(Object[] neededElements){
		Integer listNum = (Integer) neededElements[0];
		List<GameObject> help = listsSnapshot.get(listNum);
		listsSnapshot.put(listNum, new ArrayList<GameObject>());
		for(GameObject object : help){
			listsSnapshot.get(listNum).add(object);
		}
		help = controlSnapshot;
		controlSnapshot = new ArrayList<GameObject>();
		for(GameObject object : help){
			controlSnapshot.add(object);
		}
	}
	
	public Object[] getSnapshots(Integer listNum){
		Object[] arr = new Object[2];
		arr[0] = listsSnapshot.get(listNum);
		arr[1] = controlSnapshot;
		return arr;
	}	
}