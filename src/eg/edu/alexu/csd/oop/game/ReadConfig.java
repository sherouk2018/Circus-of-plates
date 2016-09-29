package eg.edu.alexu.csd.oop.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadConfig {
	private final int WIDTH;
	private final int HEIGHT;
	private final int NUMOFOBJS;
	private final int LEVELS;
	
	public ReadConfig() {
		File config = new File("config.txt");
		List<Integer> arr = null ;
		try {
			BufferedReader br = new BufferedReader(new FileReader(config));
			String line;
			arr = new ArrayList<Integer>();
			while ((line = br.readLine()) != null) {
				arr.add(Integer.parseInt(line));
			}

			br.close();
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			Log4j.getInstance().getLogger()
					.fatal("Cofiguration File not found");
		}
		WIDTH = arr.get(0);
		HEIGHT = arr.get(1);
		NUMOFOBJS = arr.get(2);
		LEVELS = arr.get(3);
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public int getNumOfObjs() {
		return NUMOFOBJS;
	}
	
	public int getLevels() {
		return LEVELS;
	}
	
}
