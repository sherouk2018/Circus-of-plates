package eg.edu.alexu.csd.oop.game;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import eg.edu.alexu.csd.oop.game.object.Shapes;

public class DynamicLinkageShapes {

	
	private static DynamicLinkageShapes instance = new DynamicLinkageShapes();
	
	private DynamicLinkageShapes(){}
	
	public static DynamicLinkageShapes getInstance(){
		return instance;
	}
	
	public List<String> addPlugin() throws ClassNotFoundException,IllegalAccessException,InstantiationException, IOException{
		List<String> classes = new ArrayList<String>();
		String path = System.getProperty("java.class.path");
		ClassLoader cl = DynamicLinkageShapes.class.getClassLoader();
		StringTokenizer st = new StringTokenizer(path , System.getProperty("path.separator"));
		List<File> matches = new ArrayList<File>();
		while(st.hasMoreElements()){
			String jarPath = (String) st.nextElement() ;
			File file = new File(jarPath);
			if(file.isFile()){
				if(jarPath.endsWith(".jar") && !jarPath.endsWith("log4j-1.2.17.jar" ) && !jarPath.endsWith("engine_v2.jar")){
					matches.add(file);
				}
			}else{
				File[] matche = file.listFiles(new FilenameFilter()
				{
					public boolean accept(File dir, String name)
					{
						boolean flag =name.endsWith(".jar" ) && !jarPath.endsWith("log4j-1.2.17.jar" ) && !jarPath.endsWith("engine_v2.jar"); 
						return  flag;
					}
				});

				for(File f : matche)
					matches.add(f);
			}
			if(matches.size() == 0 )
				continue;

			for(File f : matches){
				JarFile jarfile = new JarFile(f); 
				Enumeration<JarEntry> enu = jarfile.entries();
				while(enu.hasMoreElements())
				{

					JarEntry je = enu.nextElement();
					
					if(je.isDirectory() || !je.getName().endsWith(".class"))
						continue;
					if( je.getName().startsWith("eg/edu/alexu/csd/oop/game/object") && je.getName().endsWith(".class")){
						String s = je.getName();
						s = s.split( "\\.")[0];
						s = s.replaceAll("/", "\\.");
						Class<?> myObjectClass =  cl.loadClass(s); 
						if(Shapes.class.isAssignableFrom(myObjectClass)){
							classes.add( myObjectClass.getSimpleName());
						}
					}
				}
				jarfile.close();
			}

		}

		return classes;

	}

	
}
