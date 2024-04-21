package is.interpreter;

import is.shapes.specificcommand.NewObjectCmd.NewObjectCmdBuilder;

public abstract class Costruzione implements ComandoIF {
	
	protected NewObjectCmdBuilder builder;
	
	public void setBuilder(NewObjectCmdBuilder b) {
		builder=b;
	}
	
}
