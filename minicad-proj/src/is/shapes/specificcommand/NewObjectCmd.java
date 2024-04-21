package is.shapes.specificcommand;

import java.awt.geom.Point2D;

import is.command.Command;
import is.shapes.model.GraphicObject;
import is.shapes.model.GroupObject;
import is.shapes.view.GraphicObjectPanel;
import is.singleton.GestoreObject;

public class NewObjectCmd implements Command {

	private GraphicObjectPanel panel;
	private GraphicObject go;
	private Point2D position;

	public NewObjectCmd(GraphicObjectPanel panel, GraphicObject go, double x, double y) {
		
		this.panel = panel;
		this.go = go;
		position = new Point2D.Double(x,y);
		
	}

	@Override
	public boolean doIt() {
		if(!(go instanceof GroupObject)) {
			go.moveTo(position);
			panel.add(go);
		}
		String objId = GestoreObject.INSTANCE.addObject(go);
		go.setId(objId);
		GestoreObject.INSTANCE.getPs().println("Creato "+objId);
		return true;
	}

	@Override
	public boolean undoIt() {
		panel.remove(go);
		GestoreObject.INSTANCE.removeObjectById(go.getId());
		return true;
	}
	
	public static class NewObjectCmdBuilder {
		private GraphicObjectPanel panel;
		private GraphicObject go;
		private Point2D position;
		
		public NewObjectCmdBuilder setPanel(GraphicObjectPanel panel) {
			this.panel = panel;
			return this;
		}
		
		public NewObjectCmdBuilder setGo(GraphicObject go) {
			this.go=go;
			return this;
		}
		
		public NewObjectCmdBuilder setPosition(Point2D position) {
			this.position = position;
			return this;
		}
		
		public NewObjectCmd build() {
			return new NewObjectCmd(panel, go, position.getX(), position.getY());
		}
		
	}//NewObjectCmdBuilder

}//NewObjectCmd
