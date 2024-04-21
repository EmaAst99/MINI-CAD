package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.model.GraphicObject;
import is.singleton.GestoreObject;

public class PerimeterIdCommand implements Command {
	
	GraphicObject go;
	
	public PerimeterIdCommand(GraphicObject go) {
		this.go=go;
	}
	
	public boolean doIt() {
		GestoreObject.INSTANCE.getPs().printf("Perimetro di "+go.getId()+": %.2f \n",go.getPerimeter());
		return false;
	}
	
	public boolean undoIt() {
		throw new NoSuchMethodError();
	}

}

