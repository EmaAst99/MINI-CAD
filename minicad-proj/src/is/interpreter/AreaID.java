package is.interpreter;

import is.command.CommandHandler;
import is.shapes.specificcommand.AreaIdCommand;
import is.singleton.GestoreObject;

public class AreaID extends Area {

	private String objId;
	
	public AreaID(String objId) {
		this.objId=objId;
	}
	
	@Override
	public void interpreta() {
		CommandHandler cmdH = GestoreObject.INSTANCE.getCmdH();
		cmdH.handle(new AreaIdCommand(GestoreObject.INSTANCE.getObjectByID(objId)));
	}

}
