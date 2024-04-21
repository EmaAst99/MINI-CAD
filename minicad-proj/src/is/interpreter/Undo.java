package is.interpreter;

import is.command.CommandHandler;
import is.command.HistoryCommandHandler;
import is.singleton.GestoreObject;

public class Undo extends Comando {

	public void interpreta() {
		CommandHandler cmdH = GestoreObject.INSTANCE.getCmdH();
		cmdH.handle(HistoryCommandHandler.NonExecutableCommands.UNDO);
	}
	
}
