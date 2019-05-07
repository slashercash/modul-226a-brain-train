package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;

//Hier werden die GUI-Elemente der Auswertung-Seite definiert.
public class GuiAuswertung implements ActionListener // > Der ActionListener dient zur Erkennung von User-Imputs.
{
	// Die JLayeredPane für die Auswertung-Seite wird definiert.
	static JLayeredPane layersAuswertung = new JLayeredPane();
	
	// Die Anzuzeigenden JLabel werden definiert.
	static JLabel auswertungProzentJLabel = new JLabel();
	static JLabel einstellungenJLabel = new JLabel();
	static JLabel auswertungListeLabel = new JLabel();
	
	// Das Overlay wird aus den Ressourcen geholt und als JLabel definiert.
	static JLabel overlayAuswertungJLabel = new JLabel(new ImageIcon(GuiStart.class.getResource("/olGuiAuswertung.png")));

	// Die Auswertungsliste wird einer JScrollPane zugeordnet, damit sie die Scrollmöglichkeit erhält.
	static JScrollPane scrollListe = new JScrollPane(auswertungListeLabel);
	
	// Die Buttons für die Auswertung-Seite werden definiert.
	static JButton buttonBack = new JButton("zurück");
	
	
	
	public GuiAuswertung()
	{		
		// Layout wird auf "null" gesetzt um die Position der Buttons, Labels usw. genau setzen zu können.
		layersAuswertung.setLayout(null);
		// setOpaque(false) bewirkt ein Repaint des Overlays, wenn die scrollListe gescrollt wird.
		scrollListe.setOpaque(false);
		
		// Die Position und Grösse Panel-Objekte werden gesetzt (x, y, breite, höhe)
		overlayAuswertungJLabel.setBounds(0, 0, 594, 471);
		auswertungProzentJLabel.setBounds(15, 10, 650, 80);
		einstellungenJLabel.setBounds(20, 70, 650, 400);
		scrollListe.setBounds(40, 165, 650, 200);
		buttonBack.setBounds(15, 415, 90, 40);
		
		// Schriftart der Label wird bestummen.
		auswertungProzentJLabel.setFont(new Font("Courier New", Font.BOLD, 24));
		auswertungListeLabel.setFont(new Font("Courier New", Font.BOLD, 20));
		einstellungenJLabel.setFont(new Font("Courier New", Font.BOLD, 20));

		// Die Buttons werden dem ActionListener hinzugefügt. Sie erkennen nun User-Imputs.
		buttonBack.addActionListener(this);
		
		// Die Layers werden bestummen (je höher die Zahl desto weiter oben das Objekt).
		layersAuswertung.setLayer(overlayAuswertungJLabel, 1);
		layersAuswertung.setLayer(auswertungProzentJLabel, 0);
		layersAuswertung.setLayer(einstellungenJLabel, -1);
		layersAuswertung.setLayer(scrollListe, 0);
		layersAuswertung.setLayer(buttonBack, 2);
		
		// Die Panel-Objekte werden dem JLayeredPane layersAuswertung hinzugefügt.	
		layersAuswertung.add(auswertungProzentJLabel);
		layersAuswertung.add(einstellungenJLabel);
		layersAuswertung.add(scrollListe);
		layersAuswertung.add(buttonBack);
		
		layersAuswertung.add(overlayAuswertungJLabel);
	}

	
	
	// Diese Methode wird aufgerufen wenn der ActionListener einen User-Imput (ActionEvent) erhält.
	// Er speichert den Imput in der Variable event.
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == buttonBack)
		{
			// Der Imput war vom JButton "Back".
			// Die Start-Seite wird dem Benutzer angezeigt.
			GuiContainer.changePanel("GUI_EINSTELLUNGEN");
		}
	}

	
	
	// Diese Methode wird vom AufgabenPruefer aufgerufen, um seine Berechnungen im GuiAuswertung anzuzeigen.
	public static void setAuswertung(String listeGeprueftString, String prozentAngabeString)
	{
		// Die Prozentangabe der richtig gelösten Aufgaben wird gesetzt.
		auswertungProzentJLabel.setText(prozentAngabeString);
		// Die Liste der geprüften Aufgaben wird gesetzt.
		auswertungListeLabel.setText(listeGeprueftString);
		// Die Einstellungen werden vom GuiEinstellungen abgefragt und gesetzt.
		einstellungenJLabel.setText(GuiEinstellungen.getEinstellungen());	
	}



	
	// Methode für Testzwecke (Ausblenden des Overlay)
	public static void overlaySwitch(boolean on)
	{
		if (on)
		{
			layersAuswertung.add(overlayAuswertungJLabel);
		}
		else
		{
			layersAuswertung.remove(overlayAuswertungJLabel);
		}
	}
}
