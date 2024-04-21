package is.interpreter;

import is.command.CommandHandler;
import is.shapes.specificcommand.DelObjectCmd;
import is.singleton.GestoreObject;

public class Rimozione extends Comando {

	private String objId;
	
	public Rimozione(String objId) {
		this.objId = objId;
	}
	
	@Override
	public void interpreta() {
		CommandHandler cmdH = GestoreObject.INSTANCE.getCmdH();
		cmdH.handle(new DelObjectCmd(GestoreObject.INSTANCE.getPanel(),GestoreObject.INSTANCE.getObjectByID(objId)));
	}

}
