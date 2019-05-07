package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import berechnungen.AufgabenZaehler;

//Hier werden die GUI-Elemente der Start-Seite definiert.
public class GuiStart implements ActionListener // > Der ActionListener dient zur Erkennung von User-Imputs.
{
	// Die JLayeredPane für die Start-Seite wird definiert.
	static JLayeredPane layersStart = new JLayeredPane();
	
	// Die Buttons für die Start-Seite werden definiert.
	JButton buttonClassic = new JButton("Classic");
	JButton buttonTimeAttack = new JButton("Time Attack");
	JButton buttonInfo = new JButton("Info");
	
	// Das Overlay wird aus den Ressourcen geholt und als JLabel definiert.
	static JLabel overlayStartJLabel = new JLabel(new ImageIcon(GuiStart.class.getResource("/olGuiStart.png")));
	
	public GuiStart()
	{		
		// Layout wird auf "null" gesetzt um die Position der Buttons, Labels usw. genau setzen zu können.
		layersStart.setLayout(null);
		
		// Die Position und Grösse der Buttons und dem Label werden gesetzt (x, y, breite, höhe)
		overlayStartJLabel.setBounds(0, 0, 594, 471);
		buttonClassic.setBounds(25, 150, 140, 40);
		buttonTimeAttack.setBounds(430, 150, 140, 40);
		buttonInfo.setBounds(515, 437, 70, 28);

		// Den Buttons werden dem ActionListener hinzugefügt. Sie erkennen nun User-Imputs.
		buttonClassic.addActionListener(this);
		buttonTimeAttack.addActionListener(this);
		buttonInfo.addActionListener(this);
		
		// Die Layers werden bestummen (je höher die Zahl desto weiter oben das Objekt).
		layersStart.setLayer(overlayStartJLabel, 1);
		layersStart.setLayer(buttonClassic, 2);
		layersStart.setLayer(buttonTimeAttack, 2);
		layersStart.setLayer(buttonInfo, 2);
		
		// Die JPanel Objekte werden hinzugefügt.
		layersStart.add(overlayStartJLabel);
		layersStart.add(buttonClassic);
		layersStart.add(buttonTimeAttack);	
		layersStart.add(buttonInfo);
	}
	
	
	
	// Diese Methode wird aufgerufen wenn der ActionListener einen User-Imput (ActionEvent) erhält.
	// Sie speichert den Imput in der Variable event.	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		// Der Imput wird durch eine If Else Schleife geprüft.
		// Wird der Imput von einer Abfrage erkannt, wird der darauffolgende Code ausgeführt.
		if (event.getSource() == buttonClassic)
		{
			// Der Imput war vom JButton "Classic".
			
			// Die GuiEinstellungen und der AufgabenZaehler wird in den Classic Modus gesetzt.
			GuiEinstellungen.setClassic();
			AufgabenZaehler.setClassic();
			
			// Dem Benutzer wird das GuiEinstellungen angezeigt.
			GuiContainer.changePanel("GUI_EINSTELLUNGEN");
		}
		
		else if (event.getSource() == buttonTimeAttack)
		{
			// Der Imput war vom JButton "Time Attack".
			
			// Die GuiEinstellungen und der AufgabenZaehler wird in den TimeAttack Modus gesetzt.
			GuiEinstellungen.setTimeAttack();
			AufgabenZaehler.setTimeAttack();
			
			// Dem Benutzer wird das GuiEinstellungen angezeigt.
			GuiContainer.changePanel("GUI_EINSTELLUNGEN");
		}
		else if (event.getSource() == buttonInfo)
		{
			// Der Imput war vom JButton "Info".
			
			// Dem Benutzer wird das GuiInfo angezeigt.
			GuiContainer.changePanel("GUI_INFO");
		}
	}



	
	// Methode für Testzwecke (Ausblenden des Overlay)
	public static void overlaySwitch(boolean on)
	{
		if (on)
		{
			layersStart.add(overlayStartJLabel);
		}
		else
		{
			layersStart.remove(overlayStartJLabel);
		}
	}
}
