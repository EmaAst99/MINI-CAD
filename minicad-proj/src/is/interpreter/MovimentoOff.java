package is.interpreter;

import java.awt.geom.Point2D;

import is.command.CommandHandler;
import is.shapes.specificcommand.MoveOffCommand;
import is.singleton.GestoreObject;

public class MovimentoOff extends Comando {

	private String objId;
	private Point2D posizione;
	
	public MovimentoOff(String objId, Point2D posizione) {
		this.objId = objId;
		this.posizione = posizione;
	}
	
	@Override
	public void interpreta() {
		CommandHandler cmdH = GestoreObject.INSTANCE.getCmdH();
		cmdH.handle(new MoveOffCommand(GestoreObject.INSTANCE.getObjectByID(objId), posizione));
	}
}
