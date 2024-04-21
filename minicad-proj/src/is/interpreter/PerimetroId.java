package is.interpreter;

import is.shapes.specificcommand.PerimeterIdCommand;
import is.singleton.GestoreObject;
import is.command.CommandHandler;

public class PerimetroId extends Perimetro{

	private String objId;
	
	public PerimetroId(String objId) {
		this.objId=objId;
	}
	
	@Override
	public void interpreta() {
		CommandHandler cmdH = GestoreObject.INSTANCE.getCmdH();
		cmdH.handle(new PerimeterIdCommand(GestoreObject.INSTANCE.getObjectByID(objId)));
	}

}
