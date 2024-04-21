package is.interpreter;

import is.command.CommandHandler;
import is.interpreter.analizzatori.Simboli;
import is.shapes.specificcommand.LsTypeCommand;
import is.singleton.GestoreObject;

public class ListaTipo extends Lista {

	private String tipo;
	
	public ListaTipo(Simboli simbolo) {
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
		cmdH.handle(new LsTypeCommand(tipo));
	}

}
