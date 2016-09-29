package eg.edu.alexu.csd.oop.game;

import eg.edu.alexu.csd.oop.game.world.Circus;
import eg.edu.alexu.csd.oop.game.GameEngine;

public class Main {

	public static void main(String[] args) {

		ReadConfig rg = new ReadConfig();
		World w = new Circus(rg.getWidth(), rg.getHeight(), rg.getNumOfObjs() , rg.getLevels());
		GameEngine.start("Circus of Plates" , w);
	}
	
}
