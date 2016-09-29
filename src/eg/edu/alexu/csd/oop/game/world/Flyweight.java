package eg.edu.alexu.csd.oop.game.world;

import java.awt.Color;
import java.util.LinkedHashMap;
import eg.edu.alexu.csd.oop.game.object.Shapes;

public class Flyweight{
	
	private Flyweight(){}
	
	private static Flyweight flyweight = new Flyweight();
	
	public static Flyweight getInstance(){
		return flyweight;
	}
	
	public void reset(){
		flyweight = new Flyweight();
	}
	private final LinkedHashMap<String, LinkedHashMap<Color,Shapes>> shapes = new LinkedHashMap<>();
	
	public Shapes getShape(Color color, String shapeName){
		Shapes shape = null;
		LinkedHashMap<Color, Shapes> colorMap = shapes.get(shapeName);
		if(colorMap != null)
			shape = colorMap.get(color);
		else
			shapes.put(shapeName, new LinkedHashMap<Color, Shapes>());
		if(shape == null){
			shape = ShapesFactory.getInstance().createShape(shapeName);
			shape.setColor(color);
			LinkedHashMap<Color, Shapes>  subMap = shapes.get(shapeName);
			subMap.put(color, shape);
		}
		//Log4j.getInstance().getLogger().debug(shapes.get(shapeName).get(color) + "  " + shapes.get(shapeName).size());
		return shape;
	}
}
