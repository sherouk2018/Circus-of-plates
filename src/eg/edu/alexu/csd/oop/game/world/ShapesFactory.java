package eg.edu.alexu.csd.oop.game.world;

import eg.edu.alexu.csd.oop.game.Log4j;
import eg.edu.alexu.csd.oop.game.object.Shapes;

public class ShapesFactory {

	private ShapesFactory(){}
	
	private static ShapesFactory shapesFactory = new ShapesFactory();
	
	public static ShapesFactory getInstance(){
		return shapesFactory;
	}

	public Shapes createShape(String shapeName){
		Shapes newShape = null;
		try {
			newShape = (Shapes) Class.forName("eg.edu.alexu.csd.oop.game.object." + shapeName).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			Log4j.getInstance().getLogger().fatal("ERROR LOADING SHAPE CLASS : " + shapeName);
		}
		return newShape;
	}
}
