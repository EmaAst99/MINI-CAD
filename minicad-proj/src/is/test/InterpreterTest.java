package is.test;

import static org.junit.Assert.*;


import java.io.StringReader;

import org.junit.Test;

import is.interpreter.analizzatori.*;
import is.interpreter.exception.*;

public class InterpreterTest {

	/*@Test
	public void analizzatoreTest() {
		StringReader in = new StringReader("circle groups (100.5,) ");
		AnalizzatoreLessicale aL = new AnalizzatoreLessicale(in);
		Simboli simbolo = aL.prossimoSimbolo();
		assertSame(Simboli.CIRCLE,simbolo);
		simbolo = aL.prossimoSimbolo();
		assertSame(Simboli.GROUPS,simbolo);
		simbolo = aL.prossimoSimbolo();
		assertSame(Simboli.TONDA_APERTA,simbolo);
		simbolo = aL.prossimoSimbolo();
		assertEquals("100",aL.getString());
		simbolo = aL.prossimoSimbolo();
		assertSame(Simboli.PUNTO, simbolo);
		simbolo = aL.prossimoSimbolo();
		assertEquals("5",aL.getString());
		simbolo = aL.prossimoSimbolo();
		assertSame(Simboli.VIRGOLA,simbolo);
		simbolo = aL.prossimoSimbolo();
		assertSame(Simboli.TONDA_CHIUSA,simbolo);
	}
	*/
	
	@Test(expected = SyntaxCommandException.class)
	public void parserComandoTest1() {
		StringReader sr = new StringReader("new circle (-50.0) (-50.0,-50.0)");
		ParserComando pc = new ParserComando(sr);
		pc.getComando();
	}
	
	@Test(expected = SyntaxCommandException.class)
	public void parserComandoTest2() {
		StringReader sr = new StringReader("new circle (50) (50,50)");
		ParserComando pc = new ParserComando(sr);
		pc.getComando();
	}
	
	@Test(expected = IllegalPathException.class)
	public void interpreterTest() {
		StringReader sr = new StringReader("new img (\"/minicad-proj/src/is/test/nonesiste.gif\") (50.0,50.0)");
		ParserComando pc = new ParserComando(sr);
		pc.getComando().interpreta();
	}
	
	@Test
	public void test() {
		StringReader sr = new StringReader("new img (\"/minicad-proj/src/is/test/nonesiste.gif\") (50.0,50.0)");
		ParserComando pc = new ParserComando(sr);
		pc.getComando().interpreta();
	}
	
	
	
}

