package is.interpreter;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

import is.command.CommandHandler;
import is.shapes.model.GraphicObject;
import is.shapes.model.GroupObject;
import is.shapes.specificcommand.NewObjectCmd;
import is.singleton.GestoreObject;

public class Raggruppa extends Comando {

	private List<String> idList;
	
	public Raggruppa(List<String> idList) {
		this.idList = idList;
	}
	
	@Override
	public void interpreta() {
		LinkedList<GraphicObject> l = new LinkedList<>();
		for(String id : idList) {
			GraphicObject obj = GestoreObject.INSTANCE.getObjectByID(id);
			if( l.contains(obj) ) throw new IllegalArgumentException();
			if( obj instanceof GroupObject ) {
				checkChild((GroupObject)obj, idList, l);
			}
			l.add(obj);
		}
		GroupObject grp = (GroupObject)GestoreObject.INSTANCE.getPrototype(3);
		grp.setObjects(l);
		Point2D pos = grp.getPosition();
		CommandHandler cmdH = GestoreObject.INSTANCE.getCmdH();
		cmdH.handle(new NewObjectCmd(GestoreObject.INSTANCE.getPanel(),grp, pos.getX(), pos.getY()));
	}
	
	private static void checkChild(GroupObject obj, List<String> idList, List<GraphicObject> l ) {
	    for (GraphicObject child : obj.getChilds()) {
	        if (idList.contains(child.getId())) {
	            throw new IllegalArgumentException();
	        }
	        if (child instanceof GroupObject) {
	            checkChild((GroupObject)child, idList, l);
	        }
	    }
	}

}
