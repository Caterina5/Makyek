//dipendenze classi
package view;

//dipendenze utilizzate di Java
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import model.Cell;
import model.Move;
import model.Scacchiera;
import controller.Gioco;



public class Dama extends JFrame {
	
	private static final long serialVersionUID = -3736871995616939993L;
	protected Point click1 = null;
	protected Point click2 = null;
	protected JButton p1 = null;
	protected JButton p2 = null;
	protected Gioco gioco;
	Timer timerBianco = null;
	Timer timerNero = null;
	boolean giocoFinito=false;


	public Dama(Gioco gioco) {
		super("Mak-Yek");
		this.gioco = gioco;
				
		// Imposto la grafica
		this.setLayout(new GridLayout(8, 8));
		
		//Centro la finestra
		setSize(720, 720);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		
		this.setLocation(x, y);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		if(Scacchiera.intelligenzaVSintelligenza==true) {			
			
			generaScacchiera();
			
			ActionListener taskPerformerBianco = new ActionListener() {
			      public void actionPerformed(ActionEvent evt) {			    			    	  
			    	  intelligenzaVSintelligenzaBianco();
			      }
			    };
			  
			 timerBianco= new Timer(2000, taskPerformerBianco);
			 timerBianco.start();
			 
			 generaScacchiera();
			 
			ActionListener taskPerformerNero = new ActionListener() {
			      public void actionPerformed(ActionEvent evt) {			    			    
			    	  intelligenzaVSintelligenzaNero();
			      }
			    };
			timerNero= new Timer(4000, taskPerformerNero);
			timerNero.start();			
		}
		else {
			showBoard(-1,-1);
		}		
		this.setVisible(true);
	}
	
	protected void intelligenzaVSintelligenzaBianco() {
		
		if(!gioco.puoAncoraGiocare(gioco.giocatoreBianco)) {
			timerBianco.stop();
			timerNero.stop();
			stampaMessaggio(gioco.endGame());
		}								
				
		if (gioco.giocatoreBianco.turno) {
			
			System.out.println("\n ++ ? il turno del giocatore Bianco");
			Move mossa= getMossaBianco();
			gioco.esegui(new Casella(mossa.x,mossa.y), new Casella(mossa.row,mossa.col));			
			generaScacchiera();
			System.out.println("ho eseguito la mossa del giocatore bianco");				
		} 									
	}
	
	protected void intelligenzaVSintelligenzaNero() {
				
		if(!gioco.puoAncoraGiocare(gioco.giocatoreNero)) {
			timerNero.stop();
			timerBianco.stop();
			stampaMessaggio(gioco.endGame());
		}									
				
		if (gioco.giocatoreNero.turno) {
			
			System.out.println("\n ++ ? il turno del giocatore nero");
			Move mossa= getMossaNero();
			gioco.esegui(new Casella(mossa.x,mossa.y), new Casella(mossa.row,mossa.col));			
			generaScacchiera();
			System.out.println("ho eseguito la mossa del giocatore nero");				
		} 									
	}		


	// Metodo che genera i bottoni e quindi la grafica che assumer? la dama.	 
	private void showBoard(int xiniziale, int yiniziale) {
		
		//controlla se il primo click corrisponde a una pedina del player
		boolean trovata=false;
		
		if(Scacchiera.giocatoreVSgiocatore==true) {
			
			//inizio scansione scacchiera
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				trovata=false;
				//Vuol dire che ho chiamato lo showBoard con l'intento di visuallizare le mosse suggerite
				//qui entra quando viene chiamato a riga 108 per mostrare le mosse suggerite relative a una casella selezionata
				if (xiniziale != -1) {
					trovata=false;
					//lista delle mosse della casella (xiniziale yinizliale)
					ArrayList<Casella> mossePedina = gioco.suggerisciMosse(new Casella(xiniziale, yiniziale));
//					System.out.println("Mosse trovate:" + mossePedina.size());
						for (int i = 0; i < mossePedina.size(); i++) {
//							System.out.println("Possibile mossa numero " + i + ": " + mossePedina.get(i).riga + ", " + mossePedina.get(i).colonna);
							// Se stiamo iterando su una delle caselle appartenenti alle possibili mosse, la booleana diventra true cos? che a rigo 83 la casella 
							//venga colorata di rosso
								if (mossePedina.get(i).riga == y && mossePedina.get(i).colonna == x) 
									trovata = true;
				        }
				}//Fine IF
				
			final Casella p;
			//se la casella x,y corrisponde a una casella suggerita verr? colorata altrimenti
			//gli viene assegnata la pedina corrispondente
				if(trovata){
					if(gioco.giocatoreBianco.isTurno())
						p = generaPedinaGvsG(gioco.contenuto(y, x), 0, 0, true, Scacchiera.GiocatoreBIANCO);
					else
						p = generaPedinaGvsG(gioco.contenuto(y, x), 0, 0, true, Scacchiera.GiocatoreNERO);
						
				}
				else{
					p = generaPedina(gioco.contenuto(y, x), x, y, false);
				}
				
				p.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						//primo click, se ho cliccato su una pedina del player
						if (gioco.colore(gioco.contenuto(p.getPosizione().x,p.getPosizione().y)) == Scacchiera.GiocatoreBIANCO && gioco.giocatoreBianco.isTurno()) {
							click1 = p.getPosizione();
							System.out.println("primo click giocatore bianco: " + click1);
							// Distruggo e ridisegno la grafica
							getContentPane().removeAll();
							//passo alla showBoard il primo click, richiamo questa stessa funzione e vado nel primo if
							showBoard(p.getPosizione().x,p.getPosizione().y);
							invalidate();
							validate();
						}
						
						else if(gioco.colore(gioco.contenuto(p.getPosizione().x,p.getPosizione().y)) == Scacchiera.GiocatoreNERO && gioco.giocatoreNero.isTurno()) {
							click1 = p.getPosizione();
							System.out.println("primo click giocatore nero: " + click1);
							// Distruggo e ridisegno la grafica
							getContentPane().removeAll();
							//passo alla showBoard il primo click, richiamo questa stessa funzione e vado nel primo if
							showBoard(p.getPosizione().x,p.getPosizione().y);
							invalidate();
							validate();
						}					
						else if (click1 != null && gioco.giocatoreBianco.isTurno()) {
							//Secondo click
							click2 = p.getPosizione();
							System.out.println("secondo click giocatore bianco: " + click2);
							String messaggio = gioco.provaMossaGiocatore(click1.x, click1.y, click2.x, click2.y);
						
							
							// Resetto i click
							click1 = null;
							click2 = null;

							// Distruggo e ridisegno la grafica
							getContentPane().removeAll();
							showBoard(-1,-1);
							invalidate();
							validate();

							if (messaggio != null)
								stampaMessaggio(messaggio);

						} //Fine secondo click
						else if(click1 != null && gioco.giocatoreNero.isTurno()) {
							//Secondo click
							click2 = p.getPosizione();
							System.out.println("secondo click giocatore nero: " + click2);
							String messaggio = gioco.provaMossaGiocatore(click1.x, click1.y, click2.x, click2.y);
						
							
							// Resetto i click
							click1 = null;
							click2 = null;

							// Distruggo e ridisegno la grafica
							getContentPane().removeAll();
							showBoard(-1,-1);
							invalidate();
							validate();

							if (messaggio != null)
								stampaMessaggio(messaggio);
						}
					}
				});

				// Aggiungo il bottone
				add(p);
			}
		}
		}
		
		if(Scacchiera.giocatoreVSintelligenza==true) {
			//inizio scansione scacchiera
			for (int y = 0; y < 8; y++) {
				for (int x = 0; x < 8; x++) {
					
					//Vuol dire che ho chiamato lo showBoard con l'intento di visuallizare le mosse suggerite
					if (xiniziale != -1) {
						trovata=false;
						//lista delle mosse della casella (xiniziale yinizliale)
						ArrayList<Casella> mossePedina = gioco.suggerisciMosse(new Casella(xiniziale, yiniziale));
						// Controllo se la mossa ? fattibile e memorizzo l'ultima casella raggiunta in casella
							for (int i = 0; i < mossePedina.size(); i++) {
							// Se stiamo iterando una delle caselle appartenenti a caselle toccate di pop								
								if (mossePedina.get(i).riga == y && mossePedina.get(i).colonna == x) 
								trovata = true;
								
								
							    
					        }
					}//Fine IF
					
				final Casella p;
				//se la casella x,y corrisponde a una casella suggerita verr? colorata altrimenti
				//gli viene assegnata la pedina corrispondente
					if(trovata){
						p = generaPedina(gioco.contenuto(y, x), 0, 0, true);
					}
					else{
						p = generaPedina(gioco.contenuto(y, x), x, y, false);
					}
					
					p.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {

							//primo click, se ho cliccato su una pedina del player
							if (gioco.colore(gioco.contenuto(p.getPosizione().x,p.getPosizione().y)) == Scacchiera.GiocatoreBIANCO) 
							{
								click1 = p.getPosizione();
								System.out.println("primo click: " + click1);
								// Distruggo e ridisegno la grafica
								getContentPane().removeAll();
								//passo alla showBoard il primo click
								showBoard(p.getPosizione().x,p.getPosizione().y);
								
								invalidate();
								validate();
							} 
							else if (click1 != null) {
								//Secondo click
								click2 = p.getPosizione();
								System.out.println("secondo click: " + click2);
								String messaggio = gioco.provaMossaGiocatore(click1.x, click1.y, click2.x, click2.y);
							
								
								// Resetto i click
								click1 = null;
								click2 = null;

								// Distruggo e ridisegno la grafica
								getContentPane().removeAll();
								showBoard(-1,-1);
								invalidate();
								validate();

								if (messaggio != null)
									stampaMessaggio(messaggio);

							} //Fine secondo click
						}
					});

					// Aggiungo il bottone
					add(p);
				}
			}
		}			
	}
	
	private Move getMossaBianco() {
		ArrayList<Cell> bianche = new ArrayList<Cell>();
		ArrayList<Cell> nere = new ArrayList<Cell>();
		ArrayList<Cell> vuote = new ArrayList<Cell>();
		
		for (int i = 0; i < Scacchiera.DIM_LATO; i++) {
			for (int j = 0; j < Scacchiera.DIM_LATO; j++) {

				if (gioco.colore(gioco.contenuto(i, j)) == Scacchiera.GiocatoreBIANCO) 
					nere.add(new Cell(i,j,2));
				else if (gioco.colore(gioco.contenuto(i, j)) == Scacchiera.GiocatoreNERO) 
					bianche.add(new Cell(i,j,1));
				else 
					vuote.add(new Cell(i,j,0));
			}
		}			
		return gioco.valutaMosse.makeAnswerSet(nere, bianche, vuote);
	}
	
	private Move getMossaNero() {
		ArrayList<Cell> bianche = new ArrayList<Cell>();
		ArrayList<Cell> nere = new ArrayList<Cell>();
		ArrayList<Cell> vuote = new ArrayList<Cell>();
		
		for (int i = 0; i < Scacchiera.DIM_LATO; i++) {
			for (int j = 0; j < Scacchiera.DIM_LATO; j++) {

				if (gioco.colore(gioco.contenuto(i, j)) == Scacchiera.GiocatoreNERO) 
					nere.add(new Cell(i,j,2));
				else if (gioco.colore(gioco.contenuto(i, j)) == Scacchiera.GiocatoreBIANCO) 
					bianche.add(new Cell(i,j,1));
				else 
					vuote.add(new Cell(i,j,0));
			}
		}			
		return gioco.valutaMosse.makeAnswerSet(nere, bianche, vuote);
	}

	

	void generaScacchiera() {
		
		getContentPane().removeAll();
		
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				
				final Casella p;	
				p = generaPedina(gioco.contenuto(y, x), x, y, false);
				add(p);
			}
		}
		repaint();
		invalidate();
		validate();
		setVisible(true);
	}
	
	private Casella generaPedinaGvsG(int valore, int x, int y, boolean colore, int giocatore) { //crea una pedina del colore giusto
		// Genero le pedine
		if (valore >= 1 && valore <= 4 && colore == false)
			return new Pedina(valore);
		
		else if(colore == true){
			
			Pedina pedColorata = new Pedina(valore);
			if(giocatore==2) 
				pedColorata.setBorder(new LineBorder(Color.GREEN, 4));
			else
				pedColorata.setBorder(new LineBorder(Color.BLUE, 4));
						
			return pedColorata;
		}
		
		if (valore == 0) {
			if (gioco.eNera(x, y)) {
				return new Vuota(Color.lightGray);
			}
		}

		// Penso a colorare la parte centrale
		if (y == 3)
			if (x % 2 == 1)
				return new Vuota(Color.lightGray);
		// Penso a colorare la parte centrale
		if (y == 4)
			if (x % 2 == 0)
				return new Vuota(Color.lightGray);

		// Di default tutto ? bianco
		return new Vuota();
	}

	private Casella generaPedina(int valore, int x, int y, boolean colore) { //crea una pedina del colore giusto
		// Genero le pedine
		if (valore >= 1 && valore <= 4 && colore == false)
			return new Pedina(valore);
		
		else if(colore == true){
			Pedina pedColorata = new Pedina(valore);
			pedColorata.setBorder(new LineBorder(Color.blue, 4));
			return pedColorata;
		}
		
		if (valore == 0) {
			if (gioco.eNera(x, y)) {
				return new Vuota(Color.lightGray);
			}
		}

		// Penso a colorare la parte centrale
		if (y == 3)
			if (x % 2 == 1)
				return new Vuota(Color.lightGray);
		
		// Penso a colorare la parte centrale
		if (y == 4)
			if (x % 2 == 0)
				return new Vuota(Color.lightGray);

		// Di default tutto ? bianco
		return new Vuota();
	}

	//Metodo che si preoccupa di stampare il messaggio passatogli per parametro.
	public void stampaMessaggio(String s) {
		Component frame = null;
		JOptionPane.showMessageDialog(frame, s);		
	}
	
	//Metodo voi che avvia il gioco
		public static void avviaGioco(){
			new Dama(new Gioco()).setVisible(true);
			
		}	

}
