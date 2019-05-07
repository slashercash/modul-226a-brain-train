package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JSlider;

//Hier werden die GUI-Elemente der Info-Seite definiert.
public class GuiInfo implements ActionListener // > Der ActionListener dient zur Erkennung von User-Imputs.
{
	// Die JLayeredPane für die Info-Seite wird definiert.
		static JLayeredPane layersInfo = new JLayeredPane();
		
		// Die Buttons für die Info-Seite werden definiert.
		JButton buttonBack = new JButton("Zurück");
		JButton buttonSpenden = new JButton("Spenden");
		
		// Die JCheckBox wird definiert.
		static JCheckBox checkBoxOverlay = new JCheckBox("Overlay");
		
		// JSlider-Objekt wird erzeugt
		JSlider spendeSlider = new JSlider();
		
		// Das Overlay wird aus den Ressourcen geholt und als JLabel definiert.
		static JLabel overlayInfo = new JLabel(new ImageIcon(GuiStart.class.getResource("/olGuiInfo.png")));
		static JLabel cashEmoji = new JLabel(new ImageIcon(GuiStart.class.getResource("/olCashEmoji.png")));
		
		// Neue Farben werden definiert
		static Color sliderBackGrey = new Color(224,224,224);
		static Color checkboxBackGrey = new Color(188,188,188);
		
		public GuiInfo()
		{		
			// Layout wird auf "null" gesetzt um die Position der Buttons, Labels usw. genau setzen zu können.
			layersInfo.setLayout(null);
			
			// Die Position und Grösse Panel-Objekte werden gesetzt (x, y, breite, höhe)
			overlayInfo.setBounds(0, 0, 594, 471);
			cashEmoji.setBounds(0, 0, 594, 471);
			buttonBack.setBounds(15, 415, 90, 40);
			buttonSpenden.setBounds(489, 415, 90, 40);
			spendeSlider.setBounds(147, 410, 300, 50);
			checkBoxOverlay.setBounds(520, 2, 170, 30);
			
			// Die Buttons werden dem ActionListener hinzugefügt. Sie erkennen nun User-Imputs.
			buttonBack.addActionListener(this);
			buttonSpenden.addActionListener(this);
			checkBoxOverlay.addActionListener(this);
			
			// Slider Einstellungen
			// Min und Max Werd des Sliders
			spendeSlider.setMinimum(10);
			spendeSlider.setMaximum(100);
			// Die Abstände zwischen den Teilmarkierungen werden festgelegt.
			spendeSlider.setMajorTickSpacing(10);
			spendeSlider.setMinorTickSpacing(5);
			// Der Slider Rastet bei den Markierungen ein.
			spendeSlider.setSnapToTicks(true);
			// Standardmarkierungen werden erzeugt 
			spendeSlider.createStandardLabels(1);
			// Zeichnen der Markierungen wird aktiviert
			spendeSlider.setPaintTicks(true);	 
			// Zeichnen der Labels wird aktiviert
			spendeSlider.setPaintLabels(true);	 
			// Schiebebalken wird auf den Wert 30 gesetzt
			spendeSlider.setValue(30);
			// Hintergrund Farbe des Sliders wird angepasst.
			spendeSlider.setBackground(sliderBackGrey);
			
			// Hintergrund Farbe der Checkbox wird angepasst.
			checkBoxOverlay.setBackground(checkboxBackGrey);
			
			// JCheckbox wird standardmässig auf "selected" gesetzt.
			checkBoxOverlay.setSelected(true);
			
			// Die Layers werden bestummen (je höher die Zahl desto weiter oben das Objekt).
			layersInfo.setLayer(overlayInfo, 1);
			layersInfo.setLayer(cashEmoji, 2);
			layersInfo.setLayer(buttonBack, 2);
			layersInfo.setLayer(buttonSpenden, 2);
			layersInfo.setLayer(spendeSlider, 2);
			layersInfo.setLayer(checkBoxOverlay, 2);
			
			// Die Panel-Objekte werden dem JLayeredPane layersInfo hinzugefügt.
			layersInfo.add(overlayInfo);
			layersInfo.add(buttonBack);
			layersInfo.add(buttonSpenden);
			layersInfo.add(spendeSlider);
			layersInfo.add(checkBoxOverlay);
		}

		@Override
		public void actionPerformed(ActionEvent event)
		{
			// Der Imput wird durch eine If Else Schleife geprüft.
			// Wird der Imput von einer Abfrage erkannt, wird der darauffolgende Code ausgeführt.
			if (event.getSource() == buttonBack)
			{
				// Der Imput war vom JButton "Zurück".
				
				// Dem Benutzer wird die GuiStart Seite angezeigt.
				GuiContainer.changePanel("GUI_START");
				// Das Cash Emoji wird entfernt.
				layersInfo.remove(cashEmoji);
			}
			else if (event.getSource() == buttonSpenden)
			{
				// Der Imput war vom JButton "Spenden".
				
				// Die Spende Meldung wird ausgegeben.
				JOptionPane.showMessageDialog(null, "Vielen Dank!\n\nDer Betrag von "  + spendeSlider.getValue() + " CHF wurde erfolgreich\nvon Ihrem Konto überwiesen.");
				// Das Cash Emoji wird angezeigt.
				layersInfo.add(cashEmoji);
			}
			
			else if (event.getSource() == checkBoxOverlay)
			{
				// Der Imput war von der Checkbox "Overlay".
				
				if (checkBoxOverlay.isSelected())
				{
					GuiStart.overlaySwitch(true);
					GuiInfo.overlaySwitch(true);
					GuiEinstellungen.overlaySwitch(true);
					GuiRechner.overlaySwitch(true);
					GuiAuswertung.overlaySwitch(true);
				}
				else
				{
					GuiStart.overlaySwitch(false);
					GuiInfo.overlaySwitch(false);
					GuiEinstellungen.overlaySwitch(false);
					GuiRechner.overlaySwitch(false);
					GuiAuswertung.overlaySwitch(false);
				}
			}
		}

		
		
		// Methode für Testzwecke (Ausblenden des Overlay)
		private static void overlaySwitch(boolean on)
		{
			if (on)
			{
				layersInfo.add(overlayInfo);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Das deaktivieren der Overlays ist nur für Testzwecke gedacht");
				layersInfo.remove(overlayInfo);
				// Infoseite wird neu geladen
				GuiContainer.changePanel("GUI_START");
				GuiContainer.changePanel("GUI_INFO");
			}
		}	
}
