package controller;

import java.util.ArrayList;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.IllegalAnnotationException;
import it.unical.mat.embasp.languages.ObjectNotValidException;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;
import model.Cell;


public class ValutaMosse {
	
	private static String encodingResource="encodings/makyekTest.txt";
	private static Handler handler;
	//private static ValutaMosse instance = null;
	
//	private ValutaMosse() {
//		instance = new ValutaMosse();
//		handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2.exe"));
//		try {
//			ASPMapper.getInstance().registerClass(Cell.class);
//		} catch (ObjectNotValidException | IllegalAnnotationException e1) {
//			e1.printStackTrace();
//		}
//	}
//	
//	public ValutaMosse getInstance() {
//		if(instance == null)
//			return instance = new ValutaMosse();
//		return instance;
//	}
	
		
	public void makeAnswerSet(ArrayList<Cell> nere, ArrayList<Cell> bianche, ArrayList<Cell> vuote) {
		handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2.exe"));
		try {
			ASPMapper.getInstance().registerClass(Cell.class);
		} catch (ObjectNotValidException | IllegalAnnotationException e1) {
			e1.printStackTrace();
		}
		
		InputProgram facts= new ASPInputProgram();
		
			try {
				for(int i=0;i<nere.size();i++){
					facts.addObjectInput(new Cell(nere.get(i).row, nere.get(i).column, 2));
					//facts.addObjectInput(new Cell(nere.get(i).riga, nere.get(i).colonna, "nera"));
				}
				for(int i=0;i<bianche.size();i++){
					facts.addObjectInput(new Cell(bianche.get(i).row, bianche.get(i).column, 1));	
				}
				for(int i=0;i<vuote.size();i++){
					facts.addObjectInput(new Cell(vuote.get(i).row, vuote.get(i).column, 0));	
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}			
		
		
		//Aggiungiamo all'handler i fatti 
		handler.addProgram(facts);
		
		//Specifichiamo il programma logico tramite file
		InputProgram encoding= new ASPInputProgram();
		encoding.addFilesPath(encodingResource);
		
		//Aggiungiamo all'handler il programma logico
		handler.addProgram(encoding);
		
		//L'handler invoca DLV2 in modo SINCRONO dando come input il programma logico e i fatti
		Output o =  handler.startSync();
		
		//ArrayList per salvare
		ArrayList<Move> answers = new ArrayList<Move>();
		
		//Analizziamo l'answer set che in quest caso è unico e che rappresenta la soluzione
		//del Sudoku e aggiorniamo la matrice
		AnswerSets answersets = (AnswerSets) o;
		
		for(AnswerSet a:answersets.getAnswersets()){
			try {
				for(Object obj:a.getAtoms()){
					//Scartiamo tutto ciò che non è un oggetto della classe Cell
					if(!(obj instanceof Move)) continue;
					//Convertiamo in un oggetto della classe Cell e impostiamo il valore di ogni cella 
					//nella matrice rappresentante la griglia del Sudoku
					Move move = (Move) obj;	
					//System.out.println("cell(" + cell.row + "," + cell.column + "," + cell.colore+").");
					System.out.println("ciao");
					answers.add(move);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		}
		
		for(int i = 0; i<answers.size(); i++) {
			System.out.println("move(" + answers.get(i).id + "," + answers.get(i).row + "," + answers.get(i).col+").");
		}
	
	}
  
}
