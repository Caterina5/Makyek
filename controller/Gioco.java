package controller;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import model.Cell;
import model.Giocatore;
import model.Giocatore.Colore;
import model.Move;
import model.Scacchiera;
import view.Casella;

public class Gioco extends Scacchiera {
		
	
	//(riga decresce, colonna rimane)
	public static final int NORD = 1;
	//(riga rimane, colonna cresce)
	public static final int EST = 2;
	// (riga rimane, colonna decresce)
	public static final int OVEST = 3;
	// (riga cresce, colonna rimane)
	public static final int SUD = 4;
	// pedina ferma
	public static final int FERMA = 0;
	
	public Giocatore giocatoreBianco = new Giocatore(Colore.BIANCO);
	public Giocatore giocatoreNero = new Giocatore(Colore.NERO);
	public ValutaMosse 	valutaMosse = new ValutaMosse();
	public String msg = "";
	
	 public Gioco() {
		super();
	 }
	
//	  Ritorna il colore opposto a quello dato: bianco per nero e nero per bianco	
	public Colore coloreOpposto(Colore colore) {
		switch (colore) {
		case BIANCO:
			return Colore.NERO;
		default:
			return Colore.BIANCO;
		}
		
	}
	
	 // Ritorna la casella che si trova intrappolata fra due caselle. 
	public Casella mangiataFra(Casella c1, Casella c2) {
		float mezzaDistanzaRighe = ((float) (c2.riga - c1.riga)) / 2.0f;
		float mezzaDistanzaColonne = ((float) (c2.colonna - c1.colonna)) / 2.0f;
		if ((mezzaDistanzaRighe != 1.0f) && (mezzaDistanzaRighe != -1.0f))
			return null;
		if ((mezzaDistanzaColonne != 1.0f) && (mezzaDistanzaColonne != -1.0f))
			return null;
		Casella fra = new Casella(c1.riga + (int) mezzaDistanzaRighe,
				c1.colonna + (int) mezzaDistanzaColonne);
		return fra;
	}
	
		
		public boolean puoAncoraGiocare(Giocatore g) {
			if(g.pedine < 2)
				return false;
			return true;
		}
		
		public String endGame() {
			// almeno uno dei due non ha mosse, vince l'altro
			if (giocatoreBianco.pedine < 2)
				return "Ha vinto il giocatore nero";
			else
				return "Ha vinto il giocatore bianco";
			
		}
	
	// Restituisce lista di tutte le mosse possibili per il pezzo che si trova
	// nella casella specificata.
	
	public ArrayList<Casella> suggerisciMosse(Casella cas) {
		//System.out.println("Suggerisce mosse (non ric) per la casella: " + cas.riga + "," + cas.colonna);
		//questo array viene passato alla funzione suggerisciMosseRic e riempito con le mosse pssibili
		ArrayList<Casella> mossePossibili = new ArrayList<Casella>(); 
		int pezzo = contenuto(cas);
		// nessuna mossa da casella vuota selezionata
		if (pezzo == VUOTA)
			return mossePossibili;
		// se la casella selezionata contiene una pedina, esplora l-albero di tutte le possibilita'
		for(int dir = 1; dir<5; dir++)
			suggerisciMosseRick(cas, dir, mossePossibili);
		return mossePossibili;
	}
	

	// Ritorna la casella adiacente a quella data, secondo la direzione	 
	public Casella casellaAdiacente(Casella c, int direz) {
		
		Casella c2 = null;
		switch (direz) {
		case NORD:
			if(c.riga - 1 < 0)
				return null;
			c2 = new Casella(c.riga - 1, c.colonna );
			break;
		case EST:
			if(c.colonna + 1 > 7)
				return null;
			c2 = new Casella(c.riga, c.colonna + 1);
			break;
		case OVEST:
			if(c.colonna - 1 < 0)
				return null;
			c2 = new Casella(c.riga, c.colonna - 1);
			break;
		case SUD:
			if(c.riga + 1 > 7)
				return null;
			c2 = new Casella(c.riga + 1, c.colonna);
			break;
		}
//		System.out.println("coordinate r: " + c.riga + ", " + c.colonna);
		return c2;
	}

	
	 // Funzione ausiliaria, ricorsiva, trova le mosse possibili per il pezzo che
	 // si trova nella casella specificata, e le aggiunge alla lista.
	 
	//funzione ricorsiva che permette di evidenziare tutte le mosse possibili da una certa casella selezionata
	//questa funzione viene chiamata dalla funzione sopra nel caso in cui ho selezionato effettivamente una pedina e non una casella vuota
	protected void suggerisciMosseRick(Casella c, int direzione, ArrayList<Casella> possibili) { 	
		
		if (direzione == NORD) { 
//			System.out.println("nord");			
			Casella aNord = casellaAdiacente(c, NORD);				
			if(aNord != null) { // se esiste sulla scacchiera
				int pezzo = contenuto(aNord.riga, aNord.colonna); // prendi il contenuto		
				if(!ePedina(pezzo)) { // se vuota
					possibili.add(aNord);
					suggerisciMosseRick(aNord, NORD, possibili);
				}
			}	
		}
		
		if (direzione == EST) { 
		
//			System.out.println("est");			
			Casella aEst = casellaAdiacente(c, EST);
			if(aEst != null) {
				int pezzo = contenuto(aEst.riga, aEst.colonna);
				if(!ePedina(pezzo)) {
					possibili.add(aEst);
					suggerisciMosseRick(aEst, EST, possibili);
				}	
			}										
		}
		
		if (direzione == OVEST) { 
			
//			System.out.println("ovest");			
			Casella aOvest = casellaAdiacente(c, OVEST);
			if(aOvest != null) {
				int pezzo = contenuto(aOvest.riga, aOvest.colonna);
				if(!ePedina(pezzo)) {
					possibili.add(aOvest);
					suggerisciMosseRick(aOvest, OVEST, possibili);
				}	
			}										
		}
		
		if (direzione == SUD) { 
			
//			System.out.println("sud");			
			Casella aSud = casellaAdiacente(c, SUD);
			if(aSud != null) {
				int pezzo = contenuto(aSud.riga, aSud.colonna);
				if(!ePedina(pezzo)) {
					possibili.add(aSud);
					suggerisciMosseRick(aSud, SUD, possibili);
				}	
			}										
		}
	
	}
	
	//Metodo che esegue la mossa del Computer, cercando la mossa migliore che sia a suo favore.
	public void mossaComputer() {
		
			ArrayList<Cell> bianche = new ArrayList<Cell>();
			ArrayList<Cell> nere = new ArrayList<Cell>();
			ArrayList<Cell> vuote = new ArrayList<Cell>();
			
			for (int i = 0; i < Scacchiera.DIM_LATO; i++) {
				for (int j = 0; j < Scacchiera.DIM_LATO; j++) {
	
					if (colore(contenuto(i, j)) == GiocatoreNERO) 
						nere.add(new Cell(i,j,2));
					else if (colore(contenuto(i, j)) == GiocatoreBIANCO) 
						bianche.add(new Cell(i,j,1));
					else 
						vuote.add(new Cell(i,j,0));
				}
			}
			
			Move mossa = valutaMosse.makeAnswerSet(nere, bianche, vuote);
			
		esegui(new Casella(mossa.x, mossa.y ), new Casella(mossa.row ,mossa.col));

	}
	
	// Prova la mossa del giocatore, ovvero verifica se è valida e in tal caso
	// la esegue.
	public String provaMossaGiocatore(int x1, int y1, int x2, int y2) {

		boolean trovata = false;
		// //Suggerisce tutte le mosse della pedina inizialmente selezionata
		ArrayList<Casella> mossePedina = suggerisciMosse(new Casella(x1, y1));
		//Controllo se la mossa è fattibile
		for (int i = 0; i < mossePedina.size(); i++) {
		//Corrispondenza trovata tra la mossa che voglrrei fare e quelle suggerite (possibili)
			if (mossePedina.get(i).riga == x2 && mossePedina.get(i).colonna == y2) {
				trovata = true;
				
				esegui(new Casella(x1,y1), new Casella(x2,y2));
				System.out.println("ho eseguito la mossa del giocatore bianco");
			
				if(giocatoreNero.isTurno()) {					
					if(!puoAncoraGiocare(giocatoreNero)) 
						return endGame();						
					else if(puoAncoraGiocare(giocatoreNero) && Scacchiera.giocatoreVSintelligenza) 
						mossaComputer();				
				}				
				if(giocatoreBianco.isTurno() ){
					if(!puoAncoraGiocare(giocatoreBianco)) 
						return endGame();								
				}
			}
		}

	//Mossa non valida
	if(!trovata)
	return "La mossa non è valida, riprova";

	return msg = null;
	}

	
	public void esegui(Casella partenza, Casella destinazione) {
		
		int pezzo = contenuto(partenza.riga, partenza.colonna);
		metti(partenza, VUOTA);
//		System.out.println("Contenuto partenza:" + pezzo);
		metti(destinazione, pezzo);	
		pezzo = contenuto(destinazione.riga, destinazione.colonna);
//		System.out.println("Contenuto destinazione: " + pezzo);
		
		
		
		if(giocatoreBianco.turno){
			
			//cattura
			if(casellaAdiacente(destinazione, EST)!=null && casellaAdiacente(destinazione, OVEST)!=null) {
				Casella e= casellaAdiacente(destinazione, EST);					
				int est=contenuto(e.riga, e.colonna);
				Casella o= casellaAdiacente(destinazione, OVEST);					
				int ovest=contenuto(o.riga, o.colonna);
				if(GiocatoreNERO==colore(est) && GiocatoreNERO==colore(ovest)) {
					metti(e, VUOTA);
					metti(o,VUOTA);
					giocatoreNero.setPunteggio(2);								
				}								
			}
			if(casellaAdiacente(destinazione, NORD)!=null && casellaAdiacente(destinazione, SUD)!=null) {
				Casella n= casellaAdiacente(destinazione, NORD);					
				int nord=contenuto(n.riga, n.colonna);
				Casella s= casellaAdiacente(destinazione, SUD);					
				int sud=contenuto(s.riga, s.colonna);
				if(GiocatoreNERO==colore(nord) && GiocatoreNERO==colore(sud)) {
					metti(n, VUOTA);
					metti(s,VUOTA);
					giocatoreNero.setPunteggio(2);								
				}								
			}
			// custodia
			for(int dir=1; dir<=4; dir++) 
				if(casellaAdiacente(destinazione, dir) != null) { // c'è una casella vicina in direzione dir
					Casella c= casellaAdiacente(destinazione, dir);					
					int nemico=contenuto(c.riga,c.colonna);	 // mi prendo il contenuto						
					if(GiocatoreNERO==colore(nemico)) {  // controllo che sia un nemico						
						if(casellaAdiacente(c, dir)!=null) { // controllo se nella stessa direzione del nemico c'è un mio alleato
							Casella c2= casellaAdiacente(c, dir);
							int alleato= contenuto(c2);
							if(GiocatoreBIANCO==colore(alleato)) {							
								metti(c, VUOTA); // mangia 					
								giocatoreNero.setPunteggio(1); // scala punteggio avversario
							}	
						}							
					}					
				}
									
		}
		
		
		if(giocatoreNero.turno){
			
			//cattura
			if(casellaAdiacente(destinazione, EST)!=null && casellaAdiacente(destinazione, OVEST)!=null) {
				Casella e= casellaAdiacente(destinazione, EST);					
				int est=contenuto(e.riga, e.colonna);
				Casella o= casellaAdiacente(destinazione, OVEST);					
				int ovest=contenuto(o.riga, o.colonna);
				if(GiocatoreBIANCO==colore(est) && GiocatoreBIANCO==colore(ovest)) {
					metti(e, VUOTA);
					metti(o,VUOTA);
					giocatoreBianco.setPunteggio(2);								
				}								
			}
			if(casellaAdiacente(destinazione, NORD)!=null && casellaAdiacente(destinazione, SUD)!=null) {
				Casella n= casellaAdiacente(destinazione, NORD);					
				int nord=contenuto(n.riga, n.colonna);
				Casella s= casellaAdiacente(destinazione, SUD);					
				int sud=contenuto(s.riga, s.colonna);
				if(GiocatoreBIANCO==colore(nord) && GiocatoreBIANCO==colore(sud)) {
					metti(n, VUOTA);
					metti(s,VUOTA);
					giocatoreBianco.setPunteggio(2);								
				}								
			}
			//custodia
			for(int dir=1; dir<=4; dir++) 
				if(casellaAdiacente(destinazione, dir) != null) { // c'è una casella vicina in direzione dir
					Casella c= casellaAdiacente(destinazione, dir);					
					int nemico=contenuto(c.riga,c.colonna);	 // mi prendo il contenuto						
					if(GiocatoreBIANCO==colore(nemico)) {  // controllo che sia un nemico						
						if(casellaAdiacente(c, dir)!=null) { // controllo se nella stessa direzione del nemico c'è un mio alleato
							Casella c2= casellaAdiacente(c, dir);
							int alleato= contenuto(c2);
							if(GiocatoreNERO==colore(alleato)) {							
								metti(c, VUOTA); // mangia 					
								giocatoreBianco.setPunteggio(1); // scala punteggio avversario
							}	
						}							
					}					
				}	
		}				
		
		System.out.println("punteggio giocatoreBianco: "+giocatoreBianco.getPunteggio());
		System.out.println("punteggio giocatoreNero: "+giocatoreNero.getPunteggio());	
		
		
		if(giocatoreBianco.turno) {
			giocatoreBianco.setTurno(false);
			giocatoreNero.setTurno(true);
		}
		else {
			giocatoreNero.setTurno(false);
			giocatoreBianco.setTurno(true);		
		}
	}

}
