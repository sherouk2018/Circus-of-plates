package eg.edu.alexu.csd.oop.game.world;

import java.util.List;
import eg.edu.alexu.csd.oop.game.Container;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.Iterator;

//// Can't be converted to singleton
public class IteratorForList implements Container{
	private List<GameObject> list;
	
	public IteratorForList(List<GameObject> list) {
		this.list = list;
		
	}

	@Override
	public Iterator getIterator() {
		// TODO Auto-generated method stub
		return new NameIterator();
	}
	public class NameIterator implements Iterator{
		//int=index=-1;
		  int index = list.size();

	      @Override
	      public boolean hasPrev() {
	      
	         if(index > 0){
	            return true;
	         }
	         return false;
	      }

	      @Override
	      public Object Prev() {
	      
	         if(this.hasPrev()){
	        	 //names[index++]
	            return list.get(--index);
	         }
	         return null;
	      }		
	   }
	}
	
	

