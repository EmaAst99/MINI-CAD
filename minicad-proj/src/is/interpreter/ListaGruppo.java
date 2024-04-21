package is.interpreter;

import is.command.CommandHandler;
import is.shapes.specificcommand.LsTypeCommand;
import is.singleton.GestoreObject;

public class ListaGruppo extends Lista{

	@Override
	public void interpreta() {
		CommandHandler cmdH = GestoreObject.INSTANCE.getCmdH();
		cmdH.handle(new LsTypeCommand("Group"));
	}

}
