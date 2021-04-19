package model;



public class Giocatore {
	
	public enum Colore{BIANCO, NERO};
	
	public Colore colore;
	public int pedine;
	public boolean turno;
	
	public Giocatore(Colore colore) {
		super();
		this.pedine = 16;
		this.colore  = colore;
		if(colore == Colore.BIANCO)
			turno = true;
		else
			turno = false;
	}
	
	public Colore getColore() {
		return colore;
	}
	
	public void setColore(Colore colore) {
		this.colore = colore;
	}
	
	public int getPunteggio() {
		return pedine;
	}
	
	public void setPunteggio(int pedineMangiate) {
		this.pedine -= pedineMangiate;
		System.out.println("-"+pedineMangiate+" giocatoreNero");
	}
	
	public boolean isTurno() {
		return turno;
	}
	
	public void setTurno(boolean turno) {
		this.turno = turno;
	}

}
