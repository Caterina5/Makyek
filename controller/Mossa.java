package controller;
import java.util.*;

import view.Casella;

// la classe mossa è fatta da due liste una di pedine mangiate e l'altra di pedine toccate e 
// un booleano che è utilizzato in valuta mossa per dire se le pedine di mossa diventano Dame
public class Mossa
{
//istanze	
  // Lista delle caselle toccate. 
  public ArrayList<Casella> caselleToccate;
  // Lista delle caselle mangiate
  public ArrayList<Casella> caselleMangiate;
  // Indica se la mossa termina promuovendo una pedina a dama.
  public boolean fattaDama;
  
 // costruttore 

  public Mossa(Casella partenza){  
    caselleToccate = new ArrayList<Casella>();
    caselleMangiate = new ArrayList<Casella>();
    // la prima casella toccata è quella di partenza
    caselleToccate.add(caselleToccate.size(),partenza);
    fattaDama = false;
  }

  // Costruisce mossa copiandone un'altra.
   
  public Mossa(Mossa daCopiare)
  {  
    caselleToccate = new ArrayList<Casella>(daCopiare.caselleToccate);
    caselleMangiate = new ArrayList<Casella>(daCopiare.caselleMangiate);
    fattaDama = daCopiare.fattaDama;
  }

// stampa mossa
  public void stampaMossa()
  {
    ListIterator<Casella> iter;
    Casella c;
    System.out.print(" Caselle toccate: ");
    iter = caselleToccate.listIterator();
    while (iter.hasNext())
    {
      c = (Casella)iter.next();
      System.out.print(" (" + c.riga + "," + c.colonna + ")");
    }
//    System.out.println();
    System.out.print(" Caselle mangiate: ");
    iter = caselleMangiate.listIterator();
    while (iter.hasNext())
    {
      c = (Casella)iter.next();
      System.out.print(" (" + c.riga + "," + c.colonna + ")");
    }
    System.out.println();
    
    if (fattaDama) System.out.println("Pedina promossa a dama");
  }
}
