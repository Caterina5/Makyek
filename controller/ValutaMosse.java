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
import model.Move;

public class ValutaMosse {
	
	private static String encodingResource="encodings/makyek";
	private static Handler handler;

	
	public ValutaMosse() {

	handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2.exe"));	
			
		try {
			// registrazione delle classi per gli input/output dei fatti
			ASPMapper.getInstance().registerClass(Move.class);
			ASPMapper.getInstance().registerClass(Cell.class);		
				
		} catch (ObjectNotValidException | IllegalAnnotationException e1) {
			e1.printStackTrace();
		}
		
		//Specifichiamo il programma logico tramite file
		InputProgram encoding= new ASPInputProgram();
		encoding.addFilesPath(encodingResource);
		
		//Aggiungiamo all'handler il programma logico
		handler.addProgram(encoding);
		
	}
	
		
	public Move makeAnswerSet(ArrayList<Cell> nere, ArrayList<Cell> bianche, ArrayList<Cell> vuote) {					
	
		InputProgram facts= new ASPInputProgram();
		
			try {
				System.out.println("inizio  a passare i fatti");
				for(int i=0;i<nere.size();i++){
					facts.addObjectInput(new Cell(nere.get(i).row, nere.get(i).column, 2));					
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
			
		
//		//Aggiungiamo all'handler i fatti 
		int id = handler.addProgram(facts);
		
	
		//stampa fatti
		System.out.println("\n");
		System.out.println(facts.getPrograms());
		System.out.println("\n");
		
		//L'handler invoca DLV2 in modo SINCRONO dando come input il programma logico e i fatti
		Output o =  handler.startSync();	
		
		
		//ArrayList per salvare
		ArrayList<Cell> answersCell = new ArrayList<Cell>();
		ArrayList<Move> answersMove = new ArrayList<Move>();	

		
		//Analizziamo l'answer set che in quest caso è unico e che rappresenta la soluzione
		//del Sudoku e aggiorniamo la matrice
		AnswerSets answersets = (AnswerSets) o;

		for(AnswerSet a:answersets.getOptimalAnswerSets()){
			try {
				for(Object obj:a.getAtoms()){
										
					if((obj instanceof Cell)) {
						Cell cell = (Cell) obj;							
						answersCell.add(cell);
					}					
					else if(obj instanceof Move) {	
						Move move = (Move) obj;	
						move.getMove();
						answersMove.add(move);						
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
//		System.out.println("size Celle: "+answersCell.size());
//		for(int i = 0; i<answersCell.size(); i++) {
//			System.out.println("cell(" + answersCell.get(i).colore+ "," + answersCell.get(i).row + "," + answersCell.get(i).column+").");
//		}
		System.out.println("\n\n");
		System.out.println("size Move: "+answersMove.size());
		for(int i = 0; i<answersMove.size(); i++) {			
			System.out.println("move: "+"("+ answersMove.get(i).x + "," + answersMove.get(i).y + ") in (" + answersMove.get(i).row + "," + answersMove.get(i).col+").");
		}
		System.out.println("\n\n");
		
		handler.removeProgram(id);			
		
		return answersMove.get(0);
	}
  
}
