package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.model.GraphicObject;
import is.singleton.GestoreObject;

public class AreaIdCommand implements Command {
	
	GraphicObject go;
	
	public AreaIdCommand(GraphicObject go) {
		this.go=go;
	}
	
	public boolean doIt() {
		GestoreObject.INSTANCE.getPs().printf("Area di "+go.getId()+": %.2f \n",go.getArea());
		return false;
	}
	
	public boolean undoIt() {
		throw new NoSuchMethodError();
	}

}

