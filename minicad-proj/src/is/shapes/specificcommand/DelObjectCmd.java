package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.model.GraphicObject;
import is.shapes.model.GroupObject;
import is.shapes.view.GraphicObjectPanel;
import is.singleton.GestoreObject;

public class DelObjectCmd implements Command {

	private GraphicObjectPanel panel;
	private GraphicObject go;
	
	public DelObjectCmd(GraphicObjectPanel panel, GraphicObject go) {
		this.panel = panel;
		this.go = go;
	}
	
	public boolean doIt() {
		if(!(go instanceof GroupObject))
			panel.remove(go);
		go.notifyParent();
		String objId = go.getId();
		GestoreObject.INSTANCE.removeObjectById(objId);
		return true;
	}
	
	public boolean undoIt() {
		GestoreObject.INSTANCE.addObject(go);
		panel.add(go);
		return true;
	}
	
	
	
}//DelObjectCmd
