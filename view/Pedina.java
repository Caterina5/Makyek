package view;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import model.Scacchiera;



public class Pedina extends Casella {

	private static final long serialVersionUID = 5829315144403763452L;

	public Pedina(int valore) {
		
		try {
			Image pedinaBianca = ImageIO.read(getClass().getResourceAsStream("/img/pedinaB.png"));
			Image pedinaNera = ImageIO.read(getClass().getResourceAsStream("/img/pedinaN.png"));
			Image damaBianca = ImageIO.read(getClass().getResourceAsStream("/img/damaB.png"));
			Image damaNera = ImageIO.read(getClass().getResourceAsStream("/img/damaN.png"));

			if(valore==1)
			{
				Icon icona = new ImageIcon(pedinaBianca);
				this.setBackground(Color.lightGray);
				this.setIcon(icona);
			}
			else if(valore==2){
				Icon icona = new ImageIcon(pedinaNera);
				this.setBackground(Color.lightGray);
				this.setIcon(icona);	
			}
			else if(valore==3){
				Icon icona = new ImageIcon(damaBianca);
				this.setIcon(icona);
					
			}
			else if(valore==4){
				Icon icona = new ImageIcon(damaNera);
				this.setIcon(icona);
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}




}
