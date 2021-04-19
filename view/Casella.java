package view;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JButton;

public class Casella extends JButton{
	
	private static final long serialVersionUID = 4594089746246163139L;
	public int riga;
	public int colonna;

	 
	public Casella(int r, int c) {
		riga = r; 
		colonna = c;
	}
	 
	 // Controlla se due caselle sono la stessa, cioe' hanno uguale riga e colonna
	public static boolean stessa(Casella c1, Casella c2){
		return ((c1.riga==c2.riga) && (c1.colonna==c2.colonna));  
	}  
	  
	protected Casella() {
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
}


