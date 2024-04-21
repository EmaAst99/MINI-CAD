package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.model.GraphicObject;
import is.shapes.model.GroupObject;
import is.singleton.GestoreObject;

public class AreaAllCommand implements Command {
	
	public boolean doIt() {
		double ris = 0;
		for( String s : GestoreObject.INSTANCE.getAll()) {
			GraphicObject obj = GestoreObject.INSTANCE.getObjectByID(s);
			if(!(obj instanceof GroupObject))
				ris += obj.getArea();
		}
		GestoreObject.INSTANCE.getPs().printf("Somma di tutte le aree: %.2f \n",ris);
		return false;
	}
	
	public boolean undoIt() {
		throw new NoSuchMethodError();
	}

}

