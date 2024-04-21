package is.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.StringReader;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import is.interpreter.exception.IllegalPathException;
import is.interpreter.analizzatori.ParserComando;
import is.interpreter.exception.SyntaxCommandException;
import is.shapes.model.CircleObject;
import is.shapes.model.ImageObject;
import is.shapes.model.RectangleObject;
import is.shapes.view.CircleObjectView;
import is.shapes.view.GraphicObjectPanel;
import is.shapes.view.ImageObjectView;
import is.shapes.view.RectangleObjectView;
import is.singleton.GestoreObject;

public class Shell {

	public static void main(String[] args) throws IOException {
		JFrame f = new JFrame();
		GestoreObject instance = GestoreObject.INSTANCE;
		GraphicObjectPanel gpanel = instance.getPanel();
		gpanel.setPreferredSize(new Dimension(400, 400));

		gpanel.installView(RectangleObject.class, new RectangleObjectView());
		gpanel.installView(CircleObject.class, new CircleObjectView());
		gpanel.installView(ImageObject.class, new ImageObjectView());

		f.add(new JScrollPane(gpanel), BorderLayout.CENTER);
		
		GestoreObject.INSTANCE.createTextArea();

		JTextField textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						String s = textField.getText();
						StringReader sr = new StringReader(s);
						if(!s.trim().isEmpty()) {
							ParserComando p = new ParserComando(sr);
							p.getComando().interpreta();
						} 
						textField.setText("");
					} catch (SyntaxCommandException ex) {
						textField.setText("");
						GestoreObject.INSTANCE.getPs().println(ex);
					} catch(IllegalPathException ex){
						textField.setText("");
						GestoreObject.INSTANCE.getPs().println(ex);
					} catch (Exception ex) {
						textField.setText("");
						GestoreObject.INSTANCE.getPs().println("Comando non valido");
					}
					
				}
			}//keyPressed
		});
		
		JScrollPane panelArea = new JScrollPane(GestoreObject.INSTANCE.getTa());
		panelArea.setPreferredSize(new Dimension(400,100));
		f.add(textField, BorderLayout.NORTH);
		f.add(panelArea, BorderLayout.SOUTH);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}// main
	
	
}//Shell
