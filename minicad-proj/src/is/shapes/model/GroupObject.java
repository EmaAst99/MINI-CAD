package is.shapes.model;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;


public class GroupObject extends AbstractCompositeGraphicObject {

	List<GraphicObject> objects = new LinkedList<>();
	
	private Point2D posAttuale =  new Point2D.Double(0,0);;
	
	private String id;
	
	public GroupObject() {
	}
	
	public void removeChild(GraphicObject go) {
		objects.remove(go);
	}
	
	public void setObjects(List<GraphicObject> l) {
		int n = 0;
		for(GraphicObject o : l) {
			this.objects.add(o);
			o.setParent(this);
			posAttuale.setLocation(posAttuale.getX()+o.getPosition().getX(), posAttuale.getY()+o.getPosition().getY());
			n++;
		}
		posAttuale.setLocation(posAttuale.getX()/n, posAttuale.getY()/n);
	}
	
	public void setId(String id) { this.id=id; }
	
	public String getId() { return id; }
	
	@Override
	public void moveTo(Point2D p) {
		Point2D diff = new Point2D.Double(p.getX()-posAttuale.getX(), p.getY()-posAttuale.getY());
		for(GraphicObject o : objects) {
			o.moveTo(o.getPosition().getX()+diff.getX(),o.getPosition().getY()+diff.getY());
		}
	}//moveTo
	
	public double getArea() {
		double area = 0;
		for(GraphicObject o : objects) {
			area+=o.getArea();
		}
		return area;
	}
	
	public double getPerimeter() {
		double per = 0;
		for(GraphicObject o : objects) {
			per+=o.getPerimeter();
		}
		return per;
	}

	@Override
	public Point2D getPosition() {
		return posAttuale;
	}

	@Override
	public Dimension2D getDimension() {
		Dimension d = new Dimension(0,0);
		for(GraphicObject o : objects) {
			d.setSize(d.getWidth()+o.getDimension().getWidth(),d.getHeight()+o.getDimension().getHeight());
		}
		return d;
	}

	@Override
	public void scale(double factor) {
		for(GraphicObject o : objects) {
			o.scale(factor);
		}
	}

	@Override
	public boolean contains(Point2D p) {
		for(GraphicObject o : objects) {
			if(o.contains(p)) return true;
		}
		return false;
	}

	@Override
	public String getType() {
		return "Group";
	}
	
	@Override
	public GraphicObject getChild(int i) {
		return objects.get(i);
	}
	
	public List<GraphicObject> getChilds() {
		return objects;
	}
	
	@Override
	public GroupObject clone() {
		GroupObject cloned = (GroupObject) super.clone();
		objects = new LinkedList<>();
		return cloned;

	}
	
	@Override
	public void add(GraphicObject object) {
		objects.add(object);
	}

	public String getInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i = 0 ; i<objects.size(); i++) {
			sb.append(objects.get(i).getId());
			if(i<objects.size()-1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
