package is.interpreter;


import is.shapes.model.GraphicObject;
import is.singleton.GestoreObject;

public class CostruzioneCerchio extends Costruzione {
	
	private double raggio;
	
	public CostruzioneCerchio(double r) {
		this.raggio = r;
	}
	
	@Override
	public void interpreta() {
		GraphicObject go = GestoreObject.INSTANCE.getPrototype(0);
		go.scale(raggio);
		(super.builder).setGo(go);
	}
	
}
