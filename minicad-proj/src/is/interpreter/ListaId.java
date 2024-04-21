package is.interpreter;

import is.command.CommandHandler;
import is.shapes.specificcommand.LsIdCommand;
import is.singleton.GestoreObject;

public class ListaId extends Lista {

	private String objId;
	
	public ListaId(String objId) {
		this.objId=objId;
	}
	
	@Override
	public void interpreta() {
		CommandHandler cmdH = GestoreObject.INSTANCE.getCmdH();
		cmdH.handle(new LsIdCommand(GestoreObject.INSTANCE.getObjectByID(objId)));
	}

}
