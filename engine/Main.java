package engine;

import controller.Gioco;
import view.Dama;

public class Main {
	
	public static void main(String[] args) {
		
		//Lancio la schermata iniziale
		new Dama(new Gioco()).setVisible(true);
	}

}
