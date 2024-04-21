package is.shapes.view;

import java.awt.Graphics2D;

import is.shapes.model.GraphicObject;

public interface GraphicObjectView {
	void drawGraphicObject(GraphicObject go, Graphics2D g);
}
