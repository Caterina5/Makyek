package engine;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import model.Scacchiera;
import view.Dama;

public class SchermataIniziale extends JFrame {
	private static final long serialVersionUID = 1L;

	public SchermataIniziale() {
		
		//Layout principale
		JPanel borderl = new JPanel();
		borderl.setLayout(new BorderLayout());
		

		JButton MakyekGG = new JButton("Giocatore vs Giocatore");
		MakyekGG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Scacchiera.giocatoreVSgiocatore=true;
				Scacchiera.giocatoreVSintelligenza=false;
				Scacchiera.intelligenzaVSintelligenza=false;
				
				Dama.avviaGioco();
				dispose();
			}
		});
		
		
		JButton MakyekGI = new JButton("Giocatore vs Intelligenza");
		MakyekGI.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Scacchiera.giocatoreVSgiocatore=false;
				Scacchiera.giocatoreVSintelligenza=true;
				Scacchiera.intelligenzaVSintelligenza=false;
				Dama.avviaGioco();
				dispose();
			}
		});
		
		
		JButton MakyekII = new JButton("Intelligenza vs Intelligenza");
		MakyekII.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Scacchiera.giocatoreVSgiocatore=false;
				Scacchiera.giocatoreVSintelligenza=false;
				Scacchiera.intelligenzaVSintelligenza=true;
				dispose();
				Dama.avviaGioco();
				
			}
		});
		
		
		
		//Layout bottoni
		JPanel zonaBottoni = new JPanel();
		zonaBottoni.setLayout(new FlowLayout(FlowLayout.CENTER));
		zonaBottoni.add(MakyekGG);
		zonaBottoni.add(MakyekGI);
		zonaBottoni.add(MakyekII);
		

		//Layout titolo
		JPanel sopra = new JPanel();
		sopra.setLayout(new BoxLayout(sopra, BoxLayout.Y_AXIS));

		//Titolo
		JTextPane presentazione = new JTextPane();
		presentazione.setText("\nseleziona la modalità di gioco\n");

		presentazione.setEditable(false);

		//Centro il testo all'interno di presentazione
		StyledDocument doc = presentazione.getStyledDocument();
		SimpleAttributeSet center1 = new SimpleAttributeSet();
		StyleConstants.setAlignment(center1, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center1, false);

		sopra.add(presentazione);

		borderl.add(sopra, BorderLayout.NORTH);
		borderl.add(zonaBottoni, BorderLayout.SOUTH);

		//Inizializzo la finestra..
		setContentPane(borderl);
		setTitle("Menù");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		pack();

		//.. e la centro
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);
	}

}
