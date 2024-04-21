package is.singleton;


import java.awt.geom.Point2D;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;

import is.command.CommandHandler;
import is.command.HistoryCommandHandler;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.CircleObject;
import is.shapes.model.GraphicObject;
import is.shapes.model.GroupObject;
import is.shapes.model.ImageObject;
import is.shapes.model.RectangleObject;
import is.shapes.view.GraphicObjectPanel;

public enum GestoreObject implements GestoreObjectIF{
	INSTANCE {
		
		private JTextArea textArea;
		PrintStream printStream;
		
		public void createTextArea() {
			textArea = new JTextArea();
			textArea.setSize(400, 100);
			textArea.setAutoscrolls(true);
			textArea.setEditable(false);
			printStream = new PrintStream(new AreaOutputStream(textArea));
		}
		
		public PrintStream getPs() {
			return printStream;
		}
		
		public JTextArea getTa() {
			return textArea;
		}
		
		private HashMap<String, GraphicObject> oggetti = new HashMap<>();
		private AbstractGraphicObject[] prototype = {new CircleObject(new Point2D.Double(0,0),1),
		                                             new RectangleObject(new Point2D.Double(0,0),1,1),
		                                             new ImageObject(new ImageIcon(""), new Point2D.Double(0,0)),                                       
		                                             new GroupObject()}; // 0 circle, 1 rectangle, 2 img, 3 group
		private final GraphicObjectPanel panel = new GraphicObjectPanel();
		private HistoryCommandHandler cmdH = new HistoryCommandHandler();;
		
	
		public CommandHandler getCmdH() { return cmdH; }
	
		
		public final GraphicObjectPanel getPanel() { return panel; }
		
		public void addPrototype(AbstractGraphicObject[] prototype) {
			this.prototype = prototype;
		}
		
		public GraphicObject getPrototype(int i) {
			return prototype[i].clone();
		}
		public Set<String> getAll() {
			return oggetti.keySet();
		}
		
		public Set<String> getAllByType(String type) {
			Set<String> ret = new HashSet<>();
			for(GraphicObject o : oggetti.values() ) {
				if(o.getType().equals(type)) {
					ret.add(o.getId());
				}
			}
			return ret;
		}
		
		public GraphicObject getObjectByID(String objId) {
			return oggetti.get(objId);
		}
		
		public void removeObjectById(String objId) {
			oggetti.remove(objId);
		}
		
		public String addObject(GraphicObject obj) {
			String id="d"+(oggetti.keySet().size()+1);
			oggetti.put(id,obj);
			return id;
		}
	}//INSTANCE
}//GestoreObject

