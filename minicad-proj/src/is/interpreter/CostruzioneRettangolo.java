package is.interpreter;

import java.awt.geom.Point2D;

import is.shapes.model.GraphicObject;
import is.shapes.model.RectangleObject;
import is.singleton.GestoreObject;

public class CostruzioneRettangolo extends Costruzione  {

	private Point2D dimensioni;
	
	public CostruzioneRettangolo( Point2D dimensioni ) {
		this.dimensioni=dimensioni;
	}
	
	@Override
	public void interpreta(){
		GraphicObject go = GestoreObject.INSTANCE.getPrototype(1);
		((RectangleObject)go).scale(dimensioni);
		(super.builder).setGo(go);
	}
	
}
