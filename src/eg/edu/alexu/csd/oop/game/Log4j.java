package eg.edu.alexu.csd.oop.game;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4j {
	private Logger logger = Logger.getRootLogger();
	
	private Log4j(){
		PropertyConfigurator.configure("log4j.properties");
	}
	
	private static Log4j log4j = new Log4j();
	
	public static Log4j getInstance(){
		return log4j;
	}
	
	public Logger getLogger(){
		return logger;
	}
}
