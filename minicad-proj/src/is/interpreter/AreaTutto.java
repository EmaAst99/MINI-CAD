package is.interpreter;

import is.command.CommandHandler;
import is.shapes.specificcommand.AreaAllCommand;
import is.singleton.GestoreObject;

public class AreaTutto extends Area {

	@Override
	public void interpreta() {
		CommandHandler cmdH = GestoreObject.INSTANCE.getCmdH();
		cmdH.handle(new AreaAllCommand());
	}

}
