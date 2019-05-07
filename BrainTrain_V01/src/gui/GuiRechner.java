package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

import berechnungen.AufgabenGenerator;
import berechnungen.AufgabenTimer;
import berechnungen.AufgabenZaehler;

//Hier werden die GUI-Elemente der Rechner-Seite definiert.
public class GuiRechner implements ActionListener // > Der ActionListener dient zur Erkennung von User-Imputs.
{
	// Die JLayeredPane für die Rechner-Seite wird definiert.
	static JLayeredPane layersRechner = new JLayeredPane();
	
	// Die Buttons für die Classic-Seite werden definiert.
	static JButton buttonBack = new JButton("Zurück");
	
	// Die neuen Label werden definiert.
	static JLabel labelStatus = new JLabel();
	static JLabel labelAufgabe = new JLabel();
	
	// Das Overlay wird aus den Ressourcen geholt und als JLabel definiert.
	static JLabel overlayRechnerJLabel = new JLabel(new ImageIcon(GuiStart.class.getResource("/olGuiRechner.png")));
	
	// TextField wird definiert.
	static JTextField textAreaLoesung = new JTextField();
	
	// Neue Farben werden definiert
	static Color consoleFrontGreen = new Color(30,105,52);
	static Color consoleBackGreen = new Color(37,194,86);
	
	public GuiRechner()
	{
		// Layout wird auf "null" gesetzt um die Position der Buttons, Labels usw. genau setzen zu können.
		layersRechner.setLayout(null);
				
		// Die Position und Grösse Panel-Objekte werden gesetzt (x, y, breite, höhe)
		overlayRechnerJLabel.setBounds(0, 0, 594, 471);
		buttonBack.setBounds(15, 415, 90, 40);	
		labelStatus.setBounds(0, 75, 600, 50);
		labelAufgabe.setBounds(-200, 100, 1000, 220);
		textAreaLoesung.setBounds(182, 280, 230, 70);
			
		// Schriftart wird bestummen.
		labelAufgabe.setFont(new Font("Courier New", Font.BOLD, 60));
		labelStatus.setFont(new Font("Courier New", Font.BOLD, 20));
		textAreaLoesung.setFont(new Font("Courier New", Font.BOLD, 40));
		textAreaLoesung.setForeground(consoleFrontGreen);
		textAreaLoesung.setBackground(consoleBackGreen);
		
		// Der Inhalt im JLabel wird immer Zentriert angezeigt.
		labelAufgabe.setHorizontalAlignment(JLabel.CENTER);
		labelStatus.setHorizontalAlignment(JLabel.CENTER);
		textAreaLoesung.setHorizontalAlignment(JLabel.CENTER);

			
		// Die Buttons werden dem ActionListener hinzugefügt. Sie erkennen nun User-Imputs.
		buttonBack.addActionListener(this);
		textAreaLoesung.addActionListener(this);
		
		// Die Layers werden bestummen (je höher die Zahl desto weiter oben das Objekt).
		layersRechner.setLayer(buttonBack, 2);
		layersRechner.setLayer(labelStatus, 0);
		layersRechner.setLayer(labelAufgabe, -1);
		layersRechner.setLayer(textAreaLoesung, 0);
		layersRechner.setLayer(overlayRechnerJLabel, 1);
		
		// Die Panel-Objekte werden dem JLayeredPane layersRechner hinzugefügt.
		layersRechner.add(buttonBack);
		layersRechner.add(labelStatus);
		layersRechner.add(labelAufgabe);
		layersRechner.add(textAreaLoesung);
	
		layersRechner.add(overlayRechnerJLabel);
	}
	
	
	
	// Diese Methode wird aufgerufen wenn der ActionListener einen User-Imput (ActionEvent) erhält.
	// Er speichert den Imput in der Variable event.	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		// Der Imput wird durch eine If Else Schleife geprüft.
		// Wird der Imput von einer Abfrage erkannt, wird der darauffolgende Code ausgeführt.¨
		if (event.getSource() == textAreaLoesung)
		{
			// Der Imput war ein "Enter" im textAreaLoesung.
			
			// Die Antwort wird in einen String gespeichert.
			String antwortString = textAreaLoesung.getText();
			Integer antwortInteger;
		
			try
			{
				// Es wird versucht, die Antwort in einen Integer umzuwandeln.
				antwortInteger = Integer.parseInt(antwortString);
				if (antwortInteger > 0 && antwortInteger <= 999999)
				{
					// Wenn die Antwort zwischen 1 und 999999 ist, gilt sie als gültig und wird dem AufgabenGenerator übergeben.
					AufgabenGenerator.saveAntwort(antwortString);
				}
				else
				{
					// Ansonsten wird dem AufgabenGenerator die Zahl 0 übergeben.
					AufgabenGenerator.saveAntwort("0");
				}
			}
			catch (Exception e)
			{
				// konnte die Antwort nicht in einen Integer Wert übertragen werden, wird dem AufgabenGenerator die Zahl 0 übergeben.
				AufgabenGenerator.saveAntwort("0");
			}

			// Anschliessend wird das Textfeld zurückgesetzt und die nächste Aufgabe angefordert.
			textAreaLoesung.setText("");
			AufgabenZaehler.setNextQuestion();
		}
		
		else if (event.getSource() == buttonBack)
		{
			// Der Imput war vom JButton "Back".
			
			// Der TImer wird gestoppt.
			AufgabenTimer.stopTimer();
			
			// Dem Benutzer wird die GuiEinstellungen Seite angezeigt.
			GuiContainer.changePanel("GUI_EINSTELLUNGEN");
		}
	}

	
	
	// Diese Methode setzt den aktuellen Status in das labelStatus.
	public static void setStatus(String statusString)
	{
		labelStatus.setText(("<html><center><font color=#1E6934 bgcolor=#25C256>$$$$$$$$$$$$$$$$$$$$$$$$$<br>$$$" + statusString + "$$$$$</font></center></html>").replace("$", "&#160;"));
	}
	
	
	
	// Diese Methode setzt die aktuelle Aufgabe in das labelAufgabe.
	public static void setAufgabe(String aufgabe)
	{
		labelAufgabe.setText(("<html><center><font color=#1E6934 bgcolor=#25C256>" + ("$$$$$$$$$$$$$$$$$<br>$$$$$$" + aufgabe + "$$$$$$<br>$$$$$$$$$$$$$$$$$").replace("$", "&#160;") + "</font></center></html>").replace("+", "&#43;").replace("-", "&#8722;").replace("*", "&#215;").replace(":", "&#247;"));
	}
	
	// Diese Methode setzt den Fokus auf das Textfeld.
	public static void setFocus()
	{
		textAreaLoesung.requestFocus();
	}



	
	// Methode für Testzwecke (Ausblenden des Overlay)
	public static void overlaySwitch(boolean on)
	{
		if (on)
		{
			layersRechner.add(overlayRechnerJLabel);
		}
		else
		{
			layersRechner.remove(overlayRechnerJLabel);
		}
	}
}
