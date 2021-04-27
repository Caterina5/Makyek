package view;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JButton;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("casella")
public class Casella extends JButton{
	
	private static final long serialVersionUID = 4594089746246163139L;
	@Param(0)
	public int riga;
	@Param(1)
	public int colonna;
	@Param(2)
	public int colore;

	 
	public Casella(int r, int c) {
		riga = r; 
		colonna = c;
	}
	
	public Casella(int r, int c, int colore) {
		riga = r; 
		colonna = c;
		this.colore = colore;
	}
	 
	 // Controlla se due caselle sono la stessa, cioe' hanno uguale riga e colonna
	public static boolean stessa(Casella c1, Casella c2){
		return ((c1.riga==c2.riga) && (c1.colonna==c2.colonna));  
	}  
	  
	public Casella() {
		this.setBackground(Color.GRAY);
		this.createActionListener();
	}
	
	public Point getPosizione () {
		Rectangle r = this.getBounds();
		Point O = r.getLocation();
		int row = O.y / r.height;
		int col = O.x / r.width;
		
		return new Point(row, col);
	}
	
	public int getRiga() {
		return riga;
	}
	
	public int getColonna() {
		return colonna;
	}
	
	public int getColore() {
		return colore;
	}
}


