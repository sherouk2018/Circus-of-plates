package eg.edu.alexu.csd.oop.game.object;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Observable;

import org.apache.log4j.Logger;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.Iterator;
import eg.edu.alexu.csd.oop.game.Log4j;
import eg.edu.alexu.csd.oop.game.object.MovingObjects;
import eg.edu.alexu.csd.oop.game.world.IteratorForList;
import eg.edu.alexu.csd.oop.game.world.Lives;
import eg.edu.alexu.csd.oop.game.world.Score;
import eg.edu.alexu.csd.oop.game.world.Snapshot;

public class ShapeCatcher extends Observable {
	private List<GameObject> control;
	private Snapshot snapshot;
	private final int NUMOFCONTROLLER;
	private final int SCREENHEIGHT;
	private final int WIDTHOFPLATES;
	private boolean extraLive;
	private Lives life;
	private int numOfLives;
	private int[] arrayOfX;
	private int[] arrayOfY;
	private LinkedHashMap<Integer, List<GameObject>> listsOfPlates = new LinkedHashMap<>();
	private Logger log = Log4j.getInstance().getLogger();

	public ShapeCatcher(int screenHieght, int widthOfPlate,
			List<GameObject> control) {
		extraLive = true;
		SCREENHEIGHT = screenHieght;
		NUMOFCONTROLLER = control.size();
		life = new Lives(NUMOFCONTROLLER);
		snapshot = Snapshot.getInstance(NUMOFCONTROLLER * 2, control);
		addObserver(Score.getInstance());
		addObserver(snapshot);
		WIDTHOFPLATES = widthOfPlate;
		this.control = control;
		numOfLives = 0;
		arrayOfX = new int[NUMOFCONTROLLER * 2];
		arrayOfY = new int[NUMOFCONTROLLER * 2];
		for (Integer i = 0; i < NUMOFCONTROLLER * 2; i++) {
			listsOfPlates.put(i, new ArrayList<GameObject>());
		}
	}

	public List<GameObject> getControl() {
		return control;
	}

	public void updateRefrences() {
		for (int i = 0; i < NUMOFCONTROLLER; i++) {
			arrayOfX[i * 2] = control.get(i).getX() + (WIDTHOFPLATES / 2);
			arrayOfX[(i * 2) + 1] = control.get(i).getX()
					+ control.get(i).getWidth() - (WIDTHOFPLATES / 2);

		}
		for (Integer i = 0; i < listsOfPlates.size(); i++) {
			if (listsOfPlates.get(i).isEmpty()) {
				arrayOfY[i] = control.get(0).getY();
			} else {
				arrayOfY[i] = listsOfPlates.get(i)
						.get(listsOfPlates.get(i).size() - 1).getY();
			}
		}
	}

	// method to test intersection
	@SuppressWarnings("unchecked")
	public boolean intersect(GameObject object, boolean isRight) {
		updateRefrences();
		for (Integer i = 0; i < arrayOfX.length; i++) {
			if (Math.abs(object.getX() + (object.getWidth() / 2) - arrayOfX[i]) < (object
					.getWidth() / 2)
					&& Math.abs(object.getY() + object.getHeight()
							- arrayOfY[i]) < (object.getHeight() / 2)) {
				object.setX(arrayOfX[i] - (object.getWidth() / 2));
				object.setY(arrayOfY[i] - object.getHeight());

				int count = 0;
				((MovingObjects) object).setControl(true);
				Iterator itr = new IteratorForList(listsOfPlates.get(i))
						.getIterator();
				for (int j = 0; j < 2; j++) {
					if (itr.hasPrev()) {
						if ((((MovingObjects) object).getColor() == ((MovingObjects) itr
								.Prev()).getColor()) && (j == count)) {
							count++;
						}
					}
				}
				if (count == 0 || count == 1) {
					if (count == 0) {
						Object[] neededElements = new Object[2];
						neededElements[0] = i;
						neededElements[1] = listsOfPlates;
						setChanged();
						notifyObservers(neededElements);
					}
					if (listsOfPlates.get(i).size() > NUMOFCONTROLLER
							&& (control.indexOf(listsOfPlates.get(i).get(
									listsOfPlates.get(i).size() - 1)) + 1) < control
									.size()) {
						control.add(
								control.indexOf(listsOfPlates.get(i).get(
										listsOfPlates.get(i).size() - 1)) + 1,
								object);
					} else {
						control.add(object);
					}
					listsOfPlates.get(i).add(object);
				} else if (count == 2) {
					setChanged();
					notifyObservers(null);
					Object[] arr = snapshot.getSnapshots(i);
					List<GameObject> newRef = (ArrayList<GameObject>) arr[0];
					listsOfPlates.put(i, newRef);
					List<GameObject> newRef2 = (ArrayList<GameObject>) arr[1];
					control = newRef2;
					if (!listsOfPlates.get(i).isEmpty()) {
						Iterator itr2 = new IteratorForList(
								listsOfPlates.get(i)).getIterator();
						MovingObjects helpObject = (MovingObjects) itr2.Prev();
						MovingObjects helpObject2 = null;
						int indexOfHelpObject = 0, indexOfHelpObject2 = 0;
						indexOfHelpObject = control.indexOf(helpObject);
						control.remove(helpObject);
						listsOfPlates.get(i).remove(helpObject);
						boolean isSame = false;
						if (itr2.hasPrev()) {
							helpObject2 = (MovingObjects) itr2.Prev();
							if (helpObject.getColor() == helpObject2.getColor()) {
								isSame = true;
								indexOfHelpObject2 = control
										.indexOf(helpObject2);
								log.debug(indexOfHelpObject2);
								control.remove(helpObject2);
								listsOfPlates.get(i).remove(helpObject2);
							}
						}
						Object[] needElements = new Object[1];
						needElements[0] = i;
						setChanged();
						notifyObservers(needElements);
						// snapshot.updateAfterScore(i);
						addMissing();
						if (helpObject2 != null && isSame) {
							control.add(indexOfHelpObject2, helpObject2);
							listsOfPlates.get(i).add(helpObject2);
						}
						control.add(indexOfHelpObject, helpObject);
						listsOfPlates.get(i).add(helpObject);
					} else {
						Object[] needElements = new Object[1];
						needElements[0] = i;
						setChanged();
						notifyObservers(needElements);
						// snapshot.updateAfterScore(i);
						addMissing();
					}
				}
				if ((object.getY() < (SCREENHEIGHT / 2)) && extraLive && (numOfLives < 3)) {
					log.debug("Take snapshot");
					extraLive = false;
					life.takeSnapshot(control, listsOfPlates);

				}
				if ((object.getY() < 50) && !extraLive && (numOfLives < 3)) {
					log.debug("Return snapshot");
					numOfLives++;
					life.update(control, listsOfPlates);
					updateRefrences();
					extraLive = true;
				}
				return true;
			}
		}
		return false;
	}

	private void addMissing() {
		for (Integer j = 0; j < listsOfPlates.size(); j++) {
			List<GameObject> list = listsOfPlates.get(j);
			if (!list.isEmpty()) {
				Iterator itr = new IteratorForList(list).getIterator();
				GameObject object2 = (MovingObjects) itr.Prev();
				log.debug(control.contains(object2));
				while (!control.contains(object2)) {
					log.debug(j);
					control.add(object2);
					if (itr.hasPrev())
						object2 = (MovingObjects) itr.Prev();
				}
			}
		}
	}
}