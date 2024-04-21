package is.shapes.specificcommand;

import java.util.Set;

import is.command.Command;
import is.singleton.GestoreObject;

public class PerimeterTypeCommand implements Command {
	
	private String type;
	
	public PerimeterTypeCommand(String t) {
		type=t;
	}
	
	public boolean doIt() {
		Set<String> allId = GestoreObject.INSTANCE.getAllByType(type);
		double ris = 0;
		for(String s : allId) {
			ris += GestoreObject.INSTANCE.getObjectByID(s).getPerimeter();
		}
		GestoreObject.INSTANCE.getPs().printf("Somma dei perimetri dei tipi "+type+": %.2f \n",ris);
		return false;
	}
	
	public boolean undoIt() {
		throw new NoSuchMethodError();
	}

}
