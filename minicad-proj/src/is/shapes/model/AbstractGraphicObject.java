package is.shapes.model;


import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;



public abstract class AbstractGraphicObject implements GraphicObject, Cloneable {

	private List<GraphicObjectListener> listeners = new LinkedList<>();
	

	private List<CompositeGraphicObject> parent = new LinkedList<>();
	
	public void setParent(CompositeGraphicObject go ) {
		this.parent.add(go);
	}
	
	public void notifyParent() {
		for(CompositeGraphicObject cgo : parent) {
			cgo.removeChild(this);
		}
	}

	@Override
	public void addGraphicObjectListener(GraphicObjectListener l) {
		if (listeners.contains(l))
			return;
		listeners.add(l);

	}

	@Override
	public void removeGraphicObjectListener(GraphicObjectListener l) {
		listeners.remove(l);

	}

	protected void notifyListeners(GraphicEvent e) {

		for (GraphicObjectListener gol : listeners)

			gol.graphicChanged(e);

	}

	@Override
	public void moveTo(double x, double y) {
		moveTo(new Point2D.Double(x, y));

	}

	@Override
	public GraphicObject clone() {
		try {
			AbstractGraphicObject go = (AbstractGraphicObject) super.clone();
			go.listeners = new LinkedList<>();
			return go;
		} catch (CloneNotSupportedException e) {
			throw new Error(e);
		}
	}
	

	public String getInfo() {
		StringBuilder sb = new StringBuilder();
		Dimension2D dim =this.getDimension();
		Point2D p = this.getPosition();
		sb.append("Type: "+this.getType()+"\n");
		sb.append("Dimension: "+dim.getWidth()+", "+dim.getHeight()+"\n");
		sb.append("Position: "+p.getX()+", "+p.getY());
		return sb.toString();
	}

}
