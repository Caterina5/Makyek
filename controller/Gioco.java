package controller;

import java.util.*;

import model.Giocatore;
import model.Giocatore.Colore;
import model.Scacchiera;

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

//	//turno
//	public int aChiTocca = GiocatoreBIANCO;
	// Mossa in preparazione per essere poi eseguita. 
	//public Mossa mossaInFormazione = null;

	
	 // Messaggio che commenta l'esito dell'ultima chiamata a una delle due
	 // funzioni puoAndare.
	 public String msg = "";

	 //costruttore
	 public Gioco() {
		super();
	 }

//	// controlla la direzione
//	public boolean eVersoAvanti(int direz, int colore) {
//		switch (colore) {
//		case Scacchiera.GiocatoreBIANCO:
//			return (direz == NORD || direz == EST || direz == OVEST || direz == SUD);
//		case Scacchiera.GiocatoreNERO:
//			return (direz == NORD || direz == EST || direz == OVEST || direz == SUD);
//		}
//		return false;
//	}

//	//controlla la direzione date due caselle
//	public boolean eVersoAvanti(Casella c1, Casella c2, int colore) {
////		switch (colore) {
////		case BIANCO:
////			return (c2.riga < c1.riga);
////		case NERO:
////			return (c2.riga > c1.riga);
////		}
////		return false;
//		return true;
//	}

	
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
	
	
//	 // Esegue la mossa assegnata, cambia anche il giocatore a cui tocca.
//		public void esegui(Mossa m) {
//		
//			Casella c0 = (Casella) m.caselleToccate.getFirst();
//			int pezzo = contenuto(c0.riga, c0.colonna);
//			metti(c0, VUOTA);
//			Casella c1 = (Casella) m.caselleToccate.getLast();
//			//if (m.fattaDama)
//				//pezzo = promossaDama(pezzo);
//			metti(c1, pezzo);
//			ListIterator<Casella> iter = m.caselleMangiate.listIterator();
//			while (iter.hasNext()) {
//				Casella c = (Casella) iter.next();
//				metti(c, VUOTA);
//			}
//			if(giocatoreBianco.turno) {
//				giocatoreBianco.setTurno(false);
//				giocatoreNero.setTurno(true);
//			}
//			else {
//				giocatoreNero.setTurno(false);
//				giocatoreBianco.setTurno(true);		
//			}
//		}
		
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


	//  Ritorna se le due caselle sono direttamente adiacenti, cioe' si toccano
	 
//	public boolean adiacenteDiretta(Casella c1, Casella c2) {
//		int distanzaRighe = c1.riga - c2.riga;
//		if (distanzaRighe < 0)
//			distanzaRighe = -distanzaRighe;
//		int distanzaColonne = c1.colonna - c2.colonna;
//		if (distanzaColonne < 0)
//			distanzaColonne = -distanzaColonne;
//		return ((distanzaRighe == 1) && (distanzaColonne == 1));
//	}
	
//	// ritorna verso dove devo andare
//	public int direzGiacenza(Casella c1, Casella c2) {
//		int distanzaRighe = c1.riga - c2.riga;
//		int distanzaColonne = c1.colonna - c2.colonna;
//		if (distanzaRighe > 0) {
//			if (distanzaColonne > 0)
//				return SUD;
//			else if (distanzaColonne < 0)
//				return OVEST;
//		} else if (distanzaRighe < 0) {
//			if (distanzaColonne > 0)
//				return EST;
//			else if (distanzaColonne < 0)
//				return NORD;
//		}
//		return FERMA;
//	}

	


	
	 // Controlla se il pezzo che si trova sull'ultima casella della mossa m puo'
	 // andare ancora nella direzione direz
	 
//	   public boolean puoAndare(Mossa m, int direz) {
//		// se era pedina e si e' appena trasformata in dama no
////		if (m.fattaDama) {
////			msg = "No: appena promossa dama";
////			return false;
////		}
//		Casella c0 = (Casella) m.caselleToccate.get(0);
//		Casella c1 = (Casella) m.caselleToccate.get(m.caselleToccate.size());
//		int pezzo = contenuto(c0.riga, c0.colonna);
//		int col = colore(pezzo);
//		// se non tocca a questo colore no
//		// if (col!=aChiTocca)
//		// { msg = "No: non tocca a questo colore"; return false; }
//		// se e' pedina e la direz non e' verso avanti no
////		if (ePedina(pezzo) && (!eVersoAvanti(direz, col))) {
////			msg = "No: pedina va solo verso avanti";
////			return false;
////		}
//		// se uscisse dai limiti della scacchiera no
//		Casella c2 = casellaAdiacente(c1, direz);
//		if (c2 == null) {
//			msg = "No: adiacente non esiste";
//			return false;
//		}
//		// se la casella adiacente in direzione direz e' libera...
//		if (contenuto(c2) == VUOTA) {
//			// se il pezzo e' ancora al punto di partenza allora si
//			if (m.caselleToccate.size() == 1) {
//				m.caselleToccate.get(m.caselleToccate.size());
////				if (ePedina(pezzo) && bordoOpposto(c2, col))
////					m.fattaDama = true;
////				{
//					msg = "Si: adiacente esiste e vuota";
//					return true;
////				}
//			}
//			// altrimenti no
////			else {
//				msg = "No: mossa terminata";
//				return false;
////			}
//		}
//		// la casella adiacente in direzione direz e' occupata da
//		// un pezzo, controllo se lo puo' mangiare...
//		// se ha gia' fatto una mossa semplice allora no
//		if ((m.caselleToccate.size() > 1) && (m.caselleMangiate.size() == 0)) {
//			msg = "No: ha gia fatto mossa semplice";
//			return false;
//		}
//		int pezzoDaMangiare = contenuto(c2.riga, c2.colonna);
//		// se nella casella da mangiare c'e' colore opposto...
//		if (colore(pezzoDaMangiare) == coloreOpposto(col)) {
//			// se questo pezzo e' pedina e quello da mangiare dama allora no
//			if (ePedina(pezzo) && !ePedina(pezzoDaMangiare)) {
//				msg = "No: pedina non mangia dama";
//				return false;
//			}
//			Casella c3 = casellaAdiacente(c2, direz);
//			// se mangiando il pezzo finirebbe fuori da scacchiera allora no
//			if (c3 == null) {
//				msg = "No: seconda adiacente non esiste";
//				return false;
//			}
//			// se il pezzo l'ha gia' mangiato (e' dama e sta facendo un ciclo)
//			// allora no
//			if (!ePedina(pezzo)) {
//				ListIterator<Casella> iter = m.caselleMangiate.listIterator();
//				while (iter.hasNext()) {
//					Casella cm = (Casella) iter.next();
//					if (Casella.stessa(cm, c2)) {
//						msg = "No: ciclo";
//						return false;
//					}
//				}
//			}
//			// se mangiando il pezzo finisce su casella vuota allora si
//			if (contenuto(c3) == VUOTA) {
//				m.caselleToccate.addLast(c3);
//				m.caselleMangiate.addLast(c2);
//				if (ePedina(pezzo) && bordoOpposto(c3, col))
//					m.fattaDama = true;
//				{
//					msg = "Si: mangia";
//					return true;
//				}
//			}
//			// se mangiando finisce su casella occupata allora no
//			else {
//				msg = "No: seconda adiacente non vuota";
//				return false;
//			}
//		}
//		// se nella casella da mangiare c'e' stesso colore allora no
//		else {
//			msg = "No: non mangia stesso colore";
//			return false;
//		}
//	}
//
//	/**
//	 * Controlla se il pezzo che si trova sull'ultima casella della mossa m puo'
//	 * andare nella casella cas, in caso affermativo estende la mossa m.
//	 * 
//	 * @param m
//	 *            la mossa che provo a estendere
//	 * @param cas
//	 *            la casella in cui provo a estenderla
//	 */
//	public boolean puoAndare(Mossa m, Casella cas) {
//		if (m.fattaDama) {
//			msg = "No: appena promossa dama";
//			return false;
//		}
//		if (contenuto(cas) != VUOTA) {
//			msg = "No: casella destinazione non vuota";
//			return false;
//		}
//		Casella c0 = (Casella) m.caselleToccate.getFirst();
//		Casella c1 = (Casella) m.caselleToccate.getLast();
//		int pezzo = contenuto(c0.riga, c0.colonna);
//		int col = colore(pezzo);
//		// if (col!=aChiTocca)
//		// { msg = "No: non tocca a questo colore"; return false; }
////		if (ePedina(pezzo) && (!eVersoAvanti(c1, cas, col))) {
////			msg = "No: pedina muove solo verso avanti";
////			return false;
////		}
//		if (adiacenteDiretta(c1, cas)) {
//			// se il pezzo e' ancora al punto di partenza allora si
//			if (m.caselleToccate.size() == 1) {
//				m.caselleToccate.addLast(cas);
//				if (ePedina(pezzo) && bordoOpposto(cas, col))
//					m.fattaDama = true;
//				{
//					msg = "Si: adiacente diretta vuota";
//					return true;
//				}
//			}
//			// altrimenti no
//			else {
//				msg = "No: mossa terminata";
//				return false;
//			}
//		}
//		// la casella destinazione non e' l'adiacente diretta,
//		// sta cercando di mangiare un pezzo, controllo se puo'...
//		// se ha gia' fatto una mossa semplice allora no
//		if ((m.caselleToccate.size() > 1) && (m.caselleMangiate.size() == 0)) {
//			msg = "No: ha gia fatto mossa semplice";
//			return false;
//		}
//		Casella c2 = mangiataFra(c1, cas);
//		if (c2 == null) {
//			msg = "No: non esiste casella frapposta";
//			return false;
//		}
//		int pezzoDaMangiare = contenuto(c2);
//		// se nella casella da mangiare c'e colore opposto...
//		if (colore(pezzoDaMangiare) == coloreOpposto(col)) {
//			// se questo pezzo e' pedina e quello da mangiare dama allora no
//			if (ePedina(pezzo) && !ePedina(pezzoDaMangiare)) {
//				msg = "No: pedina non mangia dama";
//				return false;
//			}
//			// se il pezzo l'ha gia' mangiato (e' dama e sta facendo un ciclo)
//			// allora no
//			if (!ePedina(pezzo)) {
//				ListIterator<Casella> iter = m.caselleMangiate.listIterator();
//				while (iter.hasNext()) {
//					Casella cm = (Casella) iter.next();
//					if (Casella.stessa(cm, c2)) {
//						msg = "No: ciclo";
//						return false;
//					}
//				}
//			}
//			// ho gia' controllato che casella destinazione vuota, quind si
//			m.caselleToccate.addLast(cas);
//			m.caselleMangiate.addLast(c2);
//			if (ePedina(pezzo) && bordoOpposto(cas, col))
//				m.fattaDama = true;
//			{
//				msg = "Si: mangia pezzo";
//				return true;
//			}
//		} else // stesso colore o vuota
//		{
//			msg = "No: casella frapposta stesso colore o vuota";
//			return false;
//		}
//	}


	// Se uno dei due giocatori ha vinto, ritorna il colore del vincitore,
	 
	

	
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
		System.out.println("coordinate r: " + c.riga + ", " + c.colonna);
		return c2;
	}

	
	 // Funzione ausiliaria, ricorsiva, trova le mosse possibili per il pezzo che
	 // si trova nella casella specificata, e le aggiunge alla lista.
	 
	//funzione ricorsiva che permette di evidenziare tutte le mosse possibili da una certa casella selezionata
	//questa funzione viene chiamata dalla funzione sopra nel caso in cui ho selezionato effettivamente una pedina e non una casella vuota
	protected void suggerisciMosseRick(Casella c, int direzione, ArrayList<Casella> possibili) { 	
		
		if (direzione == NORD) { 
			System.out.println("nord");			
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
		
			System.out.println("est");			
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
			
			System.out.println("ovest");			
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
			
			System.out.println("ovest");			
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
//		System.out.println("Rimango bloccato nella mossa del pc");
		Gioco gioco = this;
//		int r1, c1, max = 0;
//		Mossa mossamax = null;
//
//		for (r1 = 0; r1 < Scacchiera.DIM_LATO; r1++) {
//			for (c1 = 0; c1 < Scacchiera.DIM_LATO; c1++) {
//
//				//Controllo se il computer deve mangiare
//				if (colore(contenuto(r1, c1)) == GiocatoreNERO) {
//					// System.out.println(r1+" "+c1);
//					ArrayList<Mossa> prova = gioco.suggerisciMosse(new Casella(r1, c1));
//
//					for (int i = 0; i < prova.size(); i++) {
//						Mossa provamossa = (Mossa) prova.get(i);
//						if (provamossa.caselleMangiate.size() > max) {
//							max = provamossa.caselleMangiate.size();
//							mossamax = provamossa;
//						}
//
//					}
//
//				}
//
//			}
//		}
//
//	//Non deve mangiare, allora valuto la mossa migliore che mi restituisce l'intelligenza artificiale
//		if (max == 0) {
//			ValutaMosse prova = new ValutaMosse(this);
//			Mossa pop = prova.mossaMigliore();
//			//E la eseguo
//			this.esegui(pop);
//		} else
//		//Posso mangiare e quindi sono vincolato a mangiare la pedina nemica. Eseguo quindi la mangiata.
//			gioco.esegui(mossamax);
//	}
//
//	
//	//Controlla la mangiata obbligatoria
//	public boolean mangiataObbligatoria(int x1, int y1, int x2, int y2) {
//		int r1, c1, max = 0;
//		
//		//primo ciclo per trovare la casella che mangia pi� pedine
//		for (r1 = 0; r1 < Scacchiera.DIM_LATO; r1++) {
//			for (c1 = 0; c1 < Scacchiera.DIM_LATO; c1++) {
//
//				if (colore(contenuto(r1, c1)) == GiocatoreBIANCO) {
//					// System.out.println(r1+" "+c1);
//					ArrayList<Mossa> prova = this.suggerisciMosse(new Casella(r1, c1));
//
//					for (int i = 0; i < prova.size(); i++) {
//						Mossa provamossa = (Mossa) prova.get(i);
//						if (provamossa.caselleMangiate.size() > max) {
//							max = provamossa.caselleMangiate.size();
//						}
//
//					}
//
//				}
//
//			}
//		}
//		//controllo che la casella cliccata corrisponda all'ultima casella toccata
//		for (r1 = 0; r1 < Scacchiera.DIM_LATO; r1++) {
//			for (c1 = 0; c1 < Scacchiera.DIM_LATO; c1++) {
//
//				if (colore(contenuto(r1, c1)) == GiocatoreBIANCO) {
//					// System.out.println(r1+" "+c1);
//					ArrayList<Mossa> prova = this.suggerisciMosse(new Casella(r1, c1));
//
//					for (int i = 0; i < prova.size(); i++) {
//						Mossa provamossa = (Mossa) prova.get(i);
//						if (provamossa.caselleMangiate.size() == max) {
//							
//								Casella casellafinale = (Casella) provamossa.caselleToccate.getLast();
//								Casella casellainiziale = (Casella) provamossa.caselleToccate.getFirst();
//								if (casellafinale.riga == x2
//										&& casellafinale.colonna == y2
//										&& casellainiziale.riga == x1
//										&& casellainiziale.colonna == y1) {
//									return true;
//								}
//							}
//						}
//
//					}
//
//				}
//
//			}
//		
//		//Nessuna mangiata obbligatoria
//		return false;

	}

	// Prova la mossa del giocatore, ovvero verifica se � valida e in tal caso
	// la esegue.
	public String provaMossaGiocatore(int x1, int y1, int x2, int y2) {
		
		boolean trovata = false;
//		//Suggerisce tutte le mosse della pedina inizialmente selezionata
		ArrayList<Casella> mossePedina = suggerisciMosse(new Casella(x1, y1));
		//Controllo se la mossa � fattibile
		for (int i = 0; i < mossePedina.size(); i++) {
			//Corrispondenza trovata tra la mossa che voglrrei fare e quelle suggerite (possibili)
			if (mossePedina.get(i).riga == x2 && mossePedina.get(i).colonna == y2) {
				trovata = true;
				esegui(new Casella(x1,y1), new Casella(x2,y2));
				if(puoAncoraGiocare(giocatoreNero)) {
					//mossaComputer();
					if(!puoAncoraGiocare(giocatoreBianco))
						return endGame();
				}else {
					return endGame();
				}
			}
		}
		
		//Mossa non valida
		if(!trovata)
			return "La mossa non � valida, riprova";
		
		return msg = null;
	}
	
	public void esegui(Casella partenza, Casella destinazione) {
		int pezzo = contenuto(partenza.riga, partenza.colonna);
		metti(partenza, VUOTA);
		System.out.println("Contenuto partenza:" + pezzo);
		metti(destinazione, pezzo);	
		pezzo = contenuto(destinazione.riga, destinazione.colonna);
		System.out.println("Contenuto destinazione: " + pezzo);
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
