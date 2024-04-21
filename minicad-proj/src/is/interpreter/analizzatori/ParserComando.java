package is.interpreter.analizzatori;

import java.awt.geom.Point2D;
import java.io.Reader;
import java.util.LinkedList;

import is.interpreter.*;
import is.interpreter.exception.SyntaxCommandException;

public class ParserComando {
	
	private AnalizzatoreLessicale lexer;
	private Simboli simbolo;
	private Comando comm;
	
	public ParserComando(Reader in) {
		lexer = new AnalizzatoreLessicale(in);
		comm = comando();
		atteso(Simboli.EOF);
	}//ParserComando
	
	public Comando getComando() { return this.comm; }
	
	private Comando comando() {
		simbolo = lexer.prossimoSimbolo();
		if( simbolo == Simboli.NEW ) {
			return creazione();
		}
		if( simbolo == Simboli.DEL) {
			return rimozione();
		}
		if( simbolo == Simboli.MV ) {
			return movimentoNorm();
		}
		if( simbolo == Simboli.MVOFF ) {
			return movimentoOff();
		}
		if( simbolo == Simboli.SCALE ) {
			return ridimensiona();
		}
		if( simbolo == Simboli.LS ) {
			return lista();
		}
		if( simbolo == Simboli.GRP ) {
			return raggruppa();
		}
		if( simbolo == Simboli.UNGRP ) {
			return disaggruppa();
		}
		if( simbolo == Simboli.AREA ) {
			return area();
		}
		if( simbolo == Simboli.PERIMETER ) {
			return perimetro();
		}
		if( simbolo == Simboli.UNDO ) {
			simbolo = lexer.prossimoSimbolo();
			return new Undo();
		}
		if( simbolo == Simboli.REDO ) {
			simbolo = lexer.prossimoSimbolo();
			return new Redo();
		}
		return null;
	}//comando
	
	private Creazione creazione() {
		Costruzione costruzione = costruzione();
		Point2D posizione = posizione();
		return new Creazione(costruzione, posizione);
	}//creazione

	private Costruzione costruzione() {
		simbolo = lexer.prossimoSimbolo();
		if( simbolo == Simboli.CIRCLE ) {
			double raggio = raggio(); 
			return new CostruzioneCerchio(raggio);
		}
		if( simbolo == Simboli.RECTANGLE ) {
			Point2D dimensioni = dimensioni();
			return new CostruzioneRettangolo(dimensioni);
		}
		if( simbolo == Simboli.IMG ) {
			String percorso = percorso();
			return new CostruzioneImmagine(percorso);
		}
		else throw new SyntaxCommandException();
	}//costruzione
	
	private Point2D dimensioni() {
		simbolo = lexer.prossimoSimbolo();
		atteso(Simboli.TONDA_APERTA);
		double puntoA = formaFloat();
		simbolo = lexer.prossimoSimbolo();
		atteso(Simboli.VIRGOLA); //da aggiustare per la virgola
		double puntoB = formaFloat();
		simbolo = lexer.prossimoSimbolo();
		atteso(Simboli.TONDA_CHIUSA);
		return new Point2D.Double(puntoA, puntoB);
	}
	
	private Point2D posizione() {
		atteso(Simboli.TONDA_APERTA);
		double puntoA =  formaFloat();
		simbolo = lexer.prossimoSimbolo();
		atteso(Simboli.VIRGOLA);
		double puntoB = formaFloat();
		simbolo = lexer.prossimoSimbolo();
		atteso(Simboli.TONDA_CHIUSA);
		return new Point2D.Double(puntoA, puntoB);
	}//posizione
	
	private double raggio() {
		simbolo = lexer.prossimoSimbolo();
		atteso(Simboli.TONDA_APERTA);
		double ret = formaFloat();
		simbolo = lexer.prossimoSimbolo();
		atteso(Simboli.TONDA_CHIUSA);
		return ret;
	}//raggio
	
	private String percorso() {
		simbolo = lexer.prossimoSimbolo();
		atteso(Simboli.TONDA_APERTA);
		String ret = lexer.getString();
		simbolo = lexer.prossimoSimbolo();
		atteso(Simboli.TONDA_CHIUSA);
		return ret;
	}
	
	private Rimozione rimozione() {
		simbolo = lexer.prossimoSimbolo();
		String objId = lexer.getString();
		simbolo = lexer.prossimoSimbolo();
		return new Rimozione(objId);
	}
	
	private MovimentoNormale movimentoNorm() {
		simbolo = lexer.prossimoSimbolo();
		String objId = lexer.getString();
		simbolo = lexer.prossimoSimbolo();
		Point2D posizione = posizione();
		return new MovimentoNormale(objId, posizione);
	}
	
	private MovimentoOff movimentoOff() { //Probabilmente con questa classe Movimento andiamo contro il principio di Liskov
		simbolo = lexer.prossimoSimbolo();
		String objId = lexer.getString();
		simbolo = lexer.prossimoSimbolo();
		Point2D posizione = posizione();
		return new MovimentoOff(objId, posizione);
	}
	
	private Ridimensione ridimensiona() {
		simbolo = lexer.prossimoSimbolo();
		String objId = lexer.getString();
		simbolo = lexer.prossimoSimbolo();
		double f = formaFloat();
		simbolo = lexer.prossimoSimbolo();
		return new Ridimensione(objId, f);
	}
	
	private Lista lista() {
		simbolo = lexer.prossimoSimbolo();
		if( simbolo == Simboli.ALL ) {
			simbolo = lexer.prossimoSimbolo();
			return new ListaTutto();
		}
		if( simbolo == Simboli.GROUPS ) {
			simbolo = lexer.prossimoSimbolo();
			return new ListaGruppo();
		}
		if( simbolo == Simboli.CIRCLE || simbolo == Simboli.RECTANGLE || simbolo == Simboli.IMG ) {
			Simboli s = simbolo;
			simbolo = lexer.prossimoSimbolo();
			return new ListaTipo(s);
		}
		String objId = lexer.getString();
		simbolo = lexer.prossimoSimbolo();
		return new ListaId(objId);
	}
	
	private Raggruppa raggruppa() {
		simbolo = lexer.prossimoSimbolo();
		String objId = lexer.getString();
		LinkedList<String> listObjId = listObjId();
		listObjId.addFirst(objId);
		return new Raggruppa(listObjId);
	}
	
	private LinkedList<String> listObjId() {
		simbolo = lexer.prossimoSimbolo();
		LinkedList<String> ret = new LinkedList<>();
		while( simbolo == Simboli.VIRGOLA ) {
			simbolo = lexer.prossimoSimbolo();
			ret.add(lexer.getString());
			simbolo = lexer.prossimoSimbolo();
		}
		return ret;
	}
	
	private Disaggruppa disaggruppa() {
		simbolo = lexer.prossimoSimbolo();
		String objId = lexer.getString();
		simbolo = lexer.prossimoSimbolo(); 
		return new Disaggruppa(objId);
	}
	
	private Area area() {
		simbolo = lexer.prossimoSimbolo();
		if( simbolo == Simboli.ALL ) {
			simbolo = lexer.prossimoSimbolo();
			return new AreaTutto();
		}
		if( simbolo == Simboli.CIRCLE || simbolo == Simboli.RECTANGLE || simbolo == Simboli.IMG ) {
			Simboli s = simbolo;
			simbolo = lexer.prossimoSimbolo();
			return new AreaTipo(s);
		}
		String objId = lexer.getString();
		simbolo = lexer.prossimoSimbolo();
		return new AreaID(objId);
	}
	
	private Perimetro perimetro() {
		simbolo = lexer.prossimoSimbolo();
		if( simbolo == Simboli.ALL ) {
			simbolo = lexer.prossimoSimbolo();
			return new PerimetroTutto();
		}
		if( simbolo == Simboli.CIRCLE || simbolo == Simboli.RECTANGLE || simbolo == Simboli.IMG ) {
			Simboli s = simbolo;
			simbolo = lexer.prossimoSimbolo();
			return new PerimetroTipo(s);
		}
		String objId = lexer.getString();
		simbolo = lexer.prossimoSimbolo();
		return new PerimetroId(objId);
	}
	
	private double formaFloat() {
		StringBuilder ris = new StringBuilder();
		ris.append(lexer.getString());
		simbolo = lexer.prossimoSimbolo();
		atteso(Simboli.PUNTO);
		ris.append("."+lexer.getString());
		return Double.parseDouble(ris.toString());
		
	}
	
	private void atteso(Simboli s) {
		if (simbolo != s) {
			String msg = "";
			if(simbolo != null) 
				 msg = " trovato " + simbolo + " mentre si attendeva " + s;
			else
				msg = "Comando non esistente";
			throw new SyntaxCommandException(msg);
		}
		simbolo = lexer.prossimoSimbolo();
	}//atteso
	
}//ParserComando
