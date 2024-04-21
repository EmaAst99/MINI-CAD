package is.interpreter;

import is.command.CommandHandler;
import is.interpreter.analizzatori.Simboli;
import is.shapes.specificcommand.AreaTypeCommand;
import is.singleton.GestoreObject;

public class AreaTipo extends Area{

	private String tipo;
	
	public AreaTipo(Simboli simbolo) {
		if(simbolo==Simboli.CIRCLE) {
			tipo = "Circle";
		}
		else if(simbolo==Simboli.RECTANGLE) {
			tipo = "Rectangle";
		}
		else {
			tipo = "Image";
		}
	}
	
	@Override
	public void interpreta() {
		CommandHandler cmdH = GestoreObject.INSTANCE.getCmdH();
		cmdH.handle(new AreaTypeCommand(tipo));
	}

}
