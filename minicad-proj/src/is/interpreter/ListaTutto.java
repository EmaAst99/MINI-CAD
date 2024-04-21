package is.interpreter;

import is.command.CommandHandler;
import is.shapes.specificcommand.LsAllCommand;
import is.singleton.GestoreObject;

public class ListaTutto extends Lista{

	@Override
	public void interpreta() {
		CommandHandler cmdH = GestoreObject.INSTANCE.getCmdH();
		cmdH.handle(new LsAllCommand());
	}

}
