package is.shapes.specificcommand;

import java.util.Set;

import is.command.Command;
import is.singleton.GestoreObject;

public class LsAllCommand implements Command {

	
	
	public boolean doIt() {
		Set<String> allId = GestoreObject.INSTANCE.getAll();
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(String s : allId) {
			sb.append(s+",");
		}
		if(sb.length()>1)
			sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		GestoreObject.INSTANCE.getPs().println(sb.toString());
		return false;
	}
	
	public boolean undoIt() { throw new NoSuchMethodError(); }
	
}
