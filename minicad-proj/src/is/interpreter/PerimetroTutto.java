package is.interpreter;

import is.command.CommandHandler;
import is.shapes.specificcommand.PerimeterAllCommand;
import is.singleton.GestoreObject;

public class PerimetroTutto extends Perimetro{

	@Override
	public void interpreta() {
		CommandHandler cmdH = GestoreObject.INSTANCE.getCmdH();
		cmdH.handle(new PerimeterAllCommand());
	}

}
