package is.singleton;

import java.io.PrintStream;
import java.util.Set;

import javax.swing.JTextArea;

import is.command.CommandHandler;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

public interface GestoreObjectIF {

	public void createTextArea();
	public JTextArea getTa();
	public PrintStream getPs();
	public Set<String> getAll();
	public Set<String> getAllByType(String type);
	public String addObject(GraphicObject obj);
	public GraphicObjectPanel getPanel();
	public CommandHandler getCmdH();
	public GraphicObject getPrototype(int i);
	public void addPrototype(AbstractGraphicObject[] prototype);
	public GraphicObject getObjectByID(String objId);
	public void removeObjectById(String objId);
}
