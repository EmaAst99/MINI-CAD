package is.shapes.model;


public interface CompositeGraphicObject extends GraphicObject {
	GraphicObject getChild(int i);
	void add(GraphicObject object);
	void removeChild(GraphicObject object);
}
