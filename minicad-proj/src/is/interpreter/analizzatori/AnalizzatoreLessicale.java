package is.interpreter.analizzatori;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;


public class AnalizzatoreLessicale {

	private StreamTokenizer input;
	private Simboli simbolo;

	public AnalizzatoreLessicale(Reader in) {

		input = new StreamTokenizer(in);
		input.resetSyntax();
		input.eolIsSignificant(false);
		input.wordChars('a', 'z');
		input.wordChars('A', 'Z');
		input.wordChars('0', '9'); 
		input.whitespaceChars('\u0000', ' ');
		input.ordinaryChar('(');
		input.ordinaryChar(')');
		input.ordinaryChar(',');
		input.ordinaryChar('\\'); //usare \\ per il path
		input.wordChars('\\', '\\'); 
		input.quoteChar('"');
		input.ordinaryChar('.');
	}
	
	public String getString() {
		return input.sval;
	}
	
	public Simboli prossimoSimbolo() {
		try {
			switch( input.nextToken() ) {
			
			case StreamTokenizer.TT_EOF:
				simbolo = Simboli.EOF;
				break;
				
			case StreamTokenizer.TT_WORD:
				if( input.sval.equals("undo") )
					simbolo = Simboli.UNDO;
				if( input.sval.equals("redo") )
					simbolo = Simboli.REDO;
				if( input.sval.equals("new") )
					simbolo = Simboli.NEW;
				if( input.sval.equals("del") )
					simbolo = Simboli.DEL;
				if( input.sval.equals("mv") )
					simbolo = Simboli.MV;
				if( input.sval.equals("mvoff") )
					simbolo = Simboli.MVOFF;
				if( input.sval.equals("scale") )
					simbolo = Simboli.SCALE;
				if( input.sval.equals("ls") )
					simbolo = Simboli.LS;
				if( input.sval.equals("all") )
					simbolo = Simboli.ALL;
				if( input.sval.equals("groups") )
					simbolo = Simboli.GROUPS;
				if( input.sval.equals("grp") )
					simbolo = Simboli.GRP;
				if( input.sval.equals("ungrp") )
					simbolo = Simboli.UNGRP;
				if( input.sval.equals("area") )
					simbolo = Simboli.AREA;
				if( input.sval.equals("perimeter") )
					simbolo = Simboli.PERIMETER;
				if( input.sval.equals("circle") )
					simbolo = Simboli.CIRCLE;
				if( input.sval.equals("rectangle") )
					simbolo = Simboli.RECTANGLE;
				if( input.sval.equals("img") )
					simbolo = Simboli.IMG;
				break;
			case '(':
				simbolo = Simboli.TONDA_APERTA;
				break;
				
			case ')':
				simbolo = Simboli.TONDA_CHIUSA;
				break;
			case '.':
				simbolo = Simboli.PUNTO;
				break;
			case ',':
				simbolo = Simboli.VIRGOLA;
				break;
			default:
					simbolo = Simboli.COMANDO_INVALIDO;
			}//switch
		} catch( IOException e ) {
			simbolo = Simboli.EOF;
		}
		return simbolo;
	}//prossimoSimbolo
	
}//AnalizzatoreLessicale