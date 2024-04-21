package is.interpreter;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import is.interpreter.exception.IllegalPathException;
import is.shapes.model.GraphicObject;
import is.shapes.model.ImageObject;
import is.singleton.GestoreObject;

import java.io.File;
import java.io.IOException;

public class CostruzioneImmagine extends Costruzione {

	private String percorso;
	
	public CostruzioneImmagine(String percorso) {
		this.percorso = percorso;
	}
	
	@Override
	public void interpreta() {
		GraphicObject go = GestoreObject.INSTANCE.getPrototype(2);
		try {
			System.out.println(percorso);
			ImageIO.read(new File(percorso));
			((ImageObject) go).setImage(new ImageIcon(percorso));
			(super.builder).setGo(go);
		}catch(IOException e){
			throw new IllegalPathException("Path non valido");
		}
	}
	
}
