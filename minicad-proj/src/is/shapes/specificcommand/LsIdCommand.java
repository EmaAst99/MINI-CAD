package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.model.GraphicObject;
import is.singleton.GestoreObject;

public class LsIdCommand implements Command {

	private GraphicObject go;
	
	public LsIdCommand(GraphicObject go) {
		this.go = go;
	}
	
	public boolean doIt() {
		GestoreObject.INSTANCE.getPs().println(go.getInfo());
		return false;
	}
	
	public boolean undoIt() {
		throw new NoSuchMethodError();
	}
}
