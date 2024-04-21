package is.interpreter;

import is.command.CommandHandler;
import is.shapes.specificcommand.DelObjectCmd;
import is.singleton.GestoreObject;

public class Disaggruppa extends Comando{

	private String objId;
	
	public Disaggruppa(String objId) {
		this.objId=objId;
	}
	
	@Override
	public void interpreta() {
		CommandHandler cmdH = GestoreObject.INSTANCE.getCmdH();
		cmdH.handle(new DelObjectCmd(GestoreObject.INSTANCE.getPanel(),GestoreObject.INSTANCE.getObjectByID(objId)));
	}

}
