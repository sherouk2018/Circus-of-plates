package eg.edu.alexu.csd.oop.game.world;

import java.util.Observable;
import java.util.Observer;

public class Score implements Observer{
	private int score=0;
	private Score(){}
	private static Score scoreInstance = new Score();
	public static Score getInstance(){
		return scoreInstance;
	}
	public void reset(){
		scoreInstance = new Score();
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1 == null)	
			score++;
	}
	public int getScore() {
		return score;
	}
}
