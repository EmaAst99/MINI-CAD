package is.interpreter;

import is.command.CommandHandler;
import is.shapes.specificcommand.ZoomCommand;
import is.singleton.GestoreObject;

public class Ridimensione extends Comando {

	private String objId;
	private double f;
	
	public Ridimensione( String objId, double f ) {
		this.objId = objId;
		this.f=f;
	}
	
	@Override
	public void interpreta() {
		CommandHandler cmdH = GestoreObject.INSTANCE.getCmdH();
		cmdH.handle(new ZoomCommand(GestoreObject.INSTANCE.getObjectByID(objId), f));
	}

}
