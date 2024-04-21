package is.interpreter;

import java.awt.geom.Point2D;

import is.command.CommandHandler;
import is.shapes.specificcommand.NewObjectCmd.NewObjectCmdBuilder;
import is.singleton.GestoreObject;

public class Creazione extends Comando {
	private Costruzione costruzione;
	private Point2D posizione;
	
	public Creazione( Costruzione costruzione, Point2D posizione ) {
		this.costruzione = costruzione;
		this.posizione = posizione;
	}
	
	@Override
	public void interpreta() {
		NewObjectCmdBuilder b = new NewObjectCmdBuilder();
		b.setPanel(GestoreObject.INSTANCE.getPanel());
		b.setPosition(posizione);
		costruzione.setBuilder(b);
		costruzione.interpreta();
		CommandHandler cmdH = GestoreObject.INSTANCE.getCmdH();
		cmdH.handle(b.build());
	}

}
