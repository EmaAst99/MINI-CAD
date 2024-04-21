package is.shapes.model;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;



public interface GraphicObject {

	void addGraphicObjectListener(GraphicObjectListener l);

	void removeGraphicObjectListener(GraphicObjectListener l);

	void moveTo(Point2D p);

	void moveTo(double x, double y);

	Point2D getPosition();

	Dimension2D getDimension();

	void scale(double factor);

	boolean contains(Point2D p);

	String getType();
	
	String getInfo();
	
	String getId();
	
	void setId(String id);
	
	double getPerimeter();
	
	double getArea();
	
	void notifyParent();
	
	void setParent(CompositeGraphicObject go);
	
}
