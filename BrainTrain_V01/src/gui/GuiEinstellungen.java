package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import berechnungen.AufgabenGenerator;
import berechnungen.AufgabenPruefer;
import berechnungen.AufgabenTimer;
import berechnungen.AufgabenZaehler;

//Hier werden die GUI-Elemente der Einstellungen-Seite definiert.
public class GuiEinstellungen implements ActionListener // > Der ActionListener dient zur Erkennung von User-Imputs.
{
	// Die JLayeredPane für die Rechner-Seite wird definiert.
	static JLayeredPane layersEinstellungen = new JLayeredPane();
	
	 // Das JPanel für die Einstellungen-Seite wird definiert.
	//static JPanel panelEinstellungen = new JPanel();
	
	// Die Buttons für die Einstellungen-Seite werden definiert.
	static JButton buttonGoClassic = new JButton("Start");
	static JButton buttonGoTimeAttack = new JButton("Start");
	static JButton buttonBack = new JButton("Zurück");
	
	// Das Overlay wird aus den Ressourcen geholt und als JLabel definiert.
	static JLabel overlayClassicJLabel = new JLabel(new ImageIcon(GuiStart.class.getResource("/olGuiEinstellungenClassic.png")));
	static JLabel overlayTimeAttackJLabel = new JLabel(new ImageIcon(GuiStart.class.getResource("/olGuiEinstellungenTimeAttack.png")));
	
	// JTextField "AnzahlFragen" wird mit dem Standardwert "7" definiert.
	static JTextField textFieldAnzahlFragen = new JTextField("7");
	
	// JTextField "Dauer" wird mit dem Standardwert "1.5" definiert.
	static JTextField textFieldDauer = new JTextField("1.5");
	
	// Auswahlelemente für die JCombobox wird erstellt.
	static String comboBoxListe[] = {" x", " xx", " xxx", " xxxx", " xxxxx"};
	static String comboBoxListeSpez[] = {" x", " xx", " xxx"};

	// Die JCheckBoxen werden definiert.
	static JCheckBox checkBoxPlus = new JCheckBox("Plus");
	static JCheckBox checkBoxMinus = new JCheckBox("Minus");
	static JCheckBox checkBoxMal = new JCheckBox("Mal");
	static JCheckBox checkBoxGeteilt = new JCheckBox("Geteilt");
	
	// Die JComboboxen werden definiert.
	static JComboBox<?> comboBoxPlusErsteStelle = new JComboBox<Object>(comboBoxListe);
	static JComboBox<?> comboBoxPlusZweiteStelle = new JComboBox<Object>(comboBoxListe);
	static JComboBox<?> comboBoxMinusErsteStelle = new JComboBox<Object>(comboBoxListe);
	static JComboBox<?> comboBoxMinusZweiteStelle = new JComboBox<Object>(comboBoxListe);
	static JComboBox<?> comboBoxMalErsteStelle = new JComboBox<Object>(comboBoxListeSpez);
	static JComboBox<?> comboBoxMalZweiteStelle = new JComboBox<Object>(comboBoxListeSpez);
	static JComboBox<?> comboBoxGeteiltErsteStelle = new JComboBox<Object>(comboBoxListe);
	static JComboBox<?> comboBoxGeteiltZweiteStelle = new JComboBox<Object>(comboBoxListeSpez);
	
	// Boolean zum Erkennen des aktuellen Modus.
	static boolean classicMode;
	static boolean overlayOn = true;
	
	// Array um die Einstellungen der Stellen für Auswerungszwecke aufzunehmen
	static String[][] anzahlStellenArray;
	
	// Neue Farben werden definiert
	static Color consoleFrontGreen = new Color(30,105,52);
	static Color consoleBackGreen = new Color(37,194,86);
	static Color checkBoxFrontGrey = new Color(106,106,106);
	static Color checkBoxBackGrey = new Color(224,224,224);
	
	public GuiEinstellungen()
	{
		// Layout wird auf "null" gesetzt um die Position der Buttons, Labels usw. genau setzen zu können.
		layersEinstellungen.setLayout(null);
		
		// Die Position und Grösse Panel-Objekte werden gesetzt (x, y, breite, höhe)
		overlayClassicJLabel.setBounds(0, 0, 594, 471);
		overlayTimeAttackJLabel.setBounds(0, 0, 594, 471);
		
		checkBoxPlus.setBounds(60, 116, 120, 30);
		checkBoxMinus.setBounds(60, 186, 120, 30);
		checkBoxMal.setBounds(60, 256, 120, 30);
		checkBoxGeteilt.setBounds(60, 326, 120, 30);
		
		comboBoxPlusErsteStelle.setBounds(202, 111, 140, 40);
		comboBoxPlusZweiteStelle.setBounds(394, 111, 140, 40);
		comboBoxMinusErsteStelle.setBounds(202, 181, 140, 40);
		comboBoxMinusZweiteStelle.setBounds(394, 181, 140, 40);
		comboBoxMalErsteStelle.setBounds(202, 251, 140, 40);
		comboBoxMalZweiteStelle.setBounds(394, 251, 140, 40);
		comboBoxGeteiltErsteStelle.setBounds(202, 321, 140, 40);
		comboBoxGeteiltZweiteStelle.setBounds(394, 321, 140, 40);
		
		buttonGoClassic.setBounds(489, 415, 90, 40);
		buttonGoTimeAttack.setBounds(489, 415, 90, 40);
		buttonBack.setBounds(15, 415, 90, 40);
		
		textFieldAnzahlFragen.setBounds(360, 410, 70, 60);
		textFieldDauer.setBounds(312, 410, 120, 60);
		
		// Schriftarten werden bestummen	
		textFieldAnzahlFragen.setFont(new Font("Courier New", Font.BOLD, 30));
		textFieldAnzahlFragen.setForeground(consoleFrontGreen);
		textFieldAnzahlFragen.setBackground(consoleBackGreen);
		textFieldDauer.setFont(new Font("Courier New", Font.BOLD, 30));
		textFieldDauer.setForeground(consoleFrontGreen);
		textFieldDauer.setBackground(consoleBackGreen);
		
		checkBoxPlus.setFont(new Font("Courier New", Font.BOLD, 20));
		checkBoxPlus.setForeground(checkBoxFrontGrey);
		checkBoxPlus.setBackground(checkBoxBackGrey);
		checkBoxMinus.setFont(new Font("Courier New", Font.BOLD, 20));
		checkBoxMinus.setForeground(checkBoxFrontGrey);
		checkBoxMinus.setBackground(checkBoxBackGrey);
		checkBoxMal.setFont(new Font("Courier New", Font.BOLD, 20));
		checkBoxMal.setForeground(checkBoxFrontGrey);
		checkBoxMal.setBackground(checkBoxBackGrey);
		checkBoxGeteilt.setFont(new Font("Courier New", Font.BOLD, 20));
		checkBoxGeteilt.setForeground(checkBoxFrontGrey);
		checkBoxGeteilt.setBackground(checkBoxBackGrey);

		comboBoxPlusErsteStelle.setFont(new Font("Courier New", Font.BOLD, 30));
		comboBoxPlusErsteStelle.setForeground(consoleFrontGreen);
		comboBoxPlusErsteStelle.setBackground(consoleBackGreen);
		comboBoxPlusZweiteStelle.setFont(new Font("Courier New", Font.BOLD, 30));
		comboBoxPlusZweiteStelle.setForeground(consoleFrontGreen);
		comboBoxPlusZweiteStelle.setBackground(consoleBackGreen);
		comboBoxMinusErsteStelle.setFont(new Font("Courier New", Font.BOLD, 30));
		comboBoxMinusErsteStelle.setForeground(consoleFrontGreen);
		comboBoxMinusErsteStelle.setBackground(consoleBackGreen);
		comboBoxMinusZweiteStelle.setFont(new Font("Courier New", Font.BOLD, 30));
		comboBoxMinusZweiteStelle.setForeground(consoleFrontGreen);
		comboBoxMinusZweiteStelle.setBackground(consoleBackGreen);
		comboBoxMalErsteStelle.setFont(new Font("Courier New", Font.BOLD, 30));
		comboBoxMalErsteStelle.setForeground(consoleFrontGreen);
		comboBoxMalErsteStelle.setBackground(consoleBackGreen);
		comboBoxMalZweiteStelle.setFont(new Font("Courier New", Font.BOLD, 30));
		comboBoxMalZweiteStelle.setForeground(consoleFrontGreen);
		comboBoxMalZweiteStelle.setBackground(consoleBackGreen);
		comboBoxGeteiltErsteStelle.setFont(new Font("Courier New", Font.BOLD, 30));
		comboBoxGeteiltErsteStelle.setForeground(consoleFrontGreen);
		comboBoxGeteiltErsteStelle.setBackground(consoleBackGreen);
		comboBoxGeteiltZweiteStelle.setFont(new Font("Courier New", Font.BOLD, 30));
		comboBoxGeteiltZweiteStelle.setForeground(consoleFrontGreen);
		comboBoxGeteiltZweiteStelle.setBackground(consoleBackGreen);
		
		// Der Inhalt im JLabel wird immer Zentriert angezeigt.
		textFieldAnzahlFragen.setHorizontalAlignment(JLabel.CENTER);
		textFieldDauer.setHorizontalAlignment(JLabel.CENTER);
		
		// JCheckboxen werden standardmässig auf "selected" gesetzt.
		checkBoxPlus.setSelected(true);
		checkBoxMinus.setSelected(true);
		checkBoxMal.setSelected(true);
		checkBoxGeteilt.setSelected(true);
		
		// StandardEinstellungen der ComboBoxen werden vorgenommen.
		comboBoxPlusErsteStelle.setSelectedIndex(1);
		comboBoxPlusZweiteStelle.setSelectedIndex(1);
		comboBoxMinusErsteStelle.setSelectedIndex(1);
		comboBoxMinusZweiteStelle.setSelectedIndex(0);
		comboBoxMalErsteStelle.setSelectedIndex(1);
		comboBoxMalZweiteStelle.setSelectedIndex(0);
		comboBoxGeteiltErsteStelle.setSelectedIndex(1);
		comboBoxGeteiltZweiteStelle.setSelectedIndex(0);
		
		// Den Buttons und dem TextField werden dem ActionListener hinzugefügt. Sie erkennen nun User-Imputs.
		buttonGoClassic.addActionListener(this);
		buttonGoTimeAttack.addActionListener(this);
		buttonBack.addActionListener(this);
		textFieldAnzahlFragen.addActionListener(this);
		textFieldDauer.addActionListener(this);
		
		// Die Layers werden bestummen (je höher die Zahl desto weiter oben das Objekt).
		layersEinstellungen.setLayer(buttonBack, 2);
		layersEinstellungen.setLayer(buttonGoClassic, 2);
		layersEinstellungen.setLayer(buttonGoTimeAttack, 2);
		layersEinstellungen.setLayer(overlayClassicJLabel, 1);
		layersEinstellungen.setLayer(overlayTimeAttackJLabel, 1);
		
		layersEinstellungen.setLayer(checkBoxPlus, 2);
		layersEinstellungen.setLayer(checkBoxMinus, 2);
		layersEinstellungen.setLayer(checkBoxMal, 2);
		layersEinstellungen.setLayer(checkBoxGeteilt, 2);
		
		layersEinstellungen.setLayer(comboBoxPlusErsteStelle, 0);
		layersEinstellungen.setLayer(comboBoxPlusZweiteStelle, 0);
		layersEinstellungen.setLayer(comboBoxMinusErsteStelle, 0);
		layersEinstellungen.setLayer(comboBoxMinusZweiteStelle, 0);
		layersEinstellungen.setLayer(comboBoxMalErsteStelle, 0);
		layersEinstellungen.setLayer(comboBoxMalZweiteStelle, 0);
		layersEinstellungen.setLayer(comboBoxGeteiltErsteStelle, 0);
		layersEinstellungen.setLayer(comboBoxGeteiltZweiteStelle, 0);
		
		// Die Panel-Objekte werden dem JPanel panelClassic hinzugefügt.
		layersEinstellungen.add(buttonBack);
		
		layersEinstellungen.add(checkBoxPlus);
		layersEinstellungen.add(checkBoxMinus);
		layersEinstellungen.add(checkBoxMal);
		layersEinstellungen.add(checkBoxGeteilt);
		
		layersEinstellungen.add(comboBoxPlusErsteStelle);
		layersEinstellungen.add(comboBoxPlusZweiteStelle);
		layersEinstellungen.add(comboBoxMinusErsteStelle);
		layersEinstellungen.add(comboBoxMinusZweiteStelle);
		layersEinstellungen.add(comboBoxMalErsteStelle);
		layersEinstellungen.add(comboBoxMalZweiteStelle);
		layersEinstellungen.add(comboBoxGeteiltErsteStelle);
		layersEinstellungen.add(comboBoxGeteiltZweiteStelle);
		
	}
	
	
	
	// Diese Methode wird aufgerufen wenn der ActionListener einen User-Imput (ActionEvent) erhält.
	// Er speichert den Imput in der Variable event.
	@Override
	public void actionPerformed(ActionEvent event)
	{
		// Der Imput wird durch eine If Else Schleife geprüft.
		// Wird der Imput von einer Abfrage erkannt, wird der darauffolgende Code ausgeführt.
		if (event.getSource() == buttonGoClassic || event.getSource() == textFieldAnzahlFragen)
		{
			// (Button "Start" geklickt oder Enter im Textfeld) der Imput startet diese Schleife, um mit den RechenAufgaben zu starten.
			
			// Die eingegebenen Werte der "geteilt" und "minus" Stellenanzahlen werden für später gespeichert.
			String geteiltStellenZahlEinsString = comboBoxGeteiltErsteStelle.getSelectedItem().toString();
			String geteiltStellenZahlZweiString = comboBoxGeteiltZweiteStelle.getSelectedItem().toString();
			String minusStellenZahlEinsString = comboBoxMinusErsteStelle.getSelectedItem().toString();
			String minusStellenZahlZweiString = comboBoxMinusZweiteStelle.getSelectedItem().toString();
					
			// Hier werden die Benutzer Eingaben auf Ihre Richtigkeit überprüft.
			try
			{
				// Kann die Eingabe im Textfeld AnzahlFragen in einen Integer Wert umgewandelt werden, wird der folgende Code ausgeführt.
				Integer integerAnzahlFragen = Integer.parseInt(textFieldAnzahlFragen.getText());
				
				if (integerAnzahlFragen < 1)
				{
					// Bei ungültiger Eingabe (kleiner als 1) wird ein Fehler ausgegeben und das Eingabefeld zurückgesetzt.
					JOptionPane.showMessageDialog(null, "Wenn Sie keine Aufgabe lösen wollen zwinge ich Sie auch nicht.  ; - )\nAnsonsten geben Sie einen Wert über 0 im Feld \"Anzahl Fragen\" ein");
					textFieldAnzahlFragen.setText("1");
				}
				else if (integerAnzahlFragen > 99)
				{
					// Bei ungültiger Eingabe (grösser als 99) wird ein Fehler ausgegeben und das Eingabefeld zurückgesetzt.
					JOptionPane.showMessageDialog(null, "Sie haben heute bestimmt noch was anderes vor.  ; - )\nGeben Sie bitte einen Wert unter 100 im Feld \"Anzahl Fragen\" ein.");
					textFieldAnzahlFragen.setText("99");
				}
				else if (checkBoxPlus.isSelected() == false && checkBoxMinus.isSelected() == false && checkBoxMal.isSelected() == false && checkBoxGeteilt.isSelected() == false)
				{
					// Bei ungültiger Eingabe (keine CheckBox aktiviert) wird ein Fehler ausgegeben.
					JOptionPane.showMessageDialog(null, "Sie sollten mindestens einen Operator auswählen.");
				}
				else if (geteiltStellenZahlEinsString.length() < geteiltStellenZahlZweiString.length())
				{
					// Dann wird noch überprüft, dass die Einstellungen bei "geteilt", die Zweite Zahl nicht mehr Stellen hat als die Erste.
					JOptionPane.showMessageDialog(null, "Achten Sie darauf, dass beim \"Geteilt\" Operator die\nerste Zahl nicht weniger Stellen hat als die Zweite Zahl.");
				}
				else if (minusStellenZahlEinsString.length() < minusStellenZahlZweiString.length())
				{
					// Dann wird noch überprüft, dass die Einstellungen bei "minus", die Zweite Zahl nicht mehr Stellen hat als die Erste.
					JOptionPane.showMessageDialog(null, "Achten Sie darauf, dass beim \"Minus\" Operator die\nerste Zahl nicht weniger Stellen hat als die Zweite Zahl.");
				}
				else
				{
					// Bei gültiger Eingabe im Textfeld AnzahlFragen wird folgender Code ausgeführt:
					
					// Die gewählte Anzahl der Fragen wird dem AufgabenZaehler übergeben.
					AufgabenZaehler.setZaehlerMax(integerAnzahlFragen);
					
					// Der Aufgabengenerator liest die gewählten "Einstellungen" aus und speichert sie ab.
					AufgabenGenerator.setGenerator();
					
					// Der Zähler für die Aufgaben wird im AufgabenZaehler zurückgesetzt.
					AufgabenZaehler.resetZaehler();	
					
					// Die Rechner Seite wird dem Benutzer angezeigt.
					GuiContainer.changePanel("GUI_RECHNER");
					
					// Das Textfeld im Rechner wird fokussiert.
					GuiRechner.setFocus();
				}
			}
			catch (NumberFormatException ex)
			{
				// Wenn die Eingabe nicht in einen Integer Wert umgewandelt werden konnte, wird eine Meldung ausgegeben und das Eingabefeld wird zurückgesetzt.
				JOptionPane.showMessageDialog(null, "Die Tastatur zu treffen muss schwierig sein.  ; - )\nGeben Sie bitte einen gültigen Wert im Feld \"Anzahl Fragen\" ein");
				textFieldAnzahlFragen.setText("");
			}
		}
		
		else if (event.getSource() == buttonGoTimeAttack || event.getSource() == textFieldDauer)
		{
			// (Button "Start" geklickt oder Enter im Textfeld) der Imput startet diese Schleife, um mit den RechenAufgaben zu starten.
			
			// Die eingegebenen Werte der "geteilt" und "minus" Stellenanzahlen werden für später gespeichert.
			String geteiltStellenZahlEinsString = comboBoxGeteiltErsteStelle.getSelectedItem().toString();
			String geteiltStellenZahlZweiString = comboBoxGeteiltZweiteStelle.getSelectedItem().toString();
			String minusStellenZahlEinsString = comboBoxMinusErsteStelle.getSelectedItem().toString();
			String minusStellenZahlZweiString = comboBoxMinusZweiteStelle.getSelectedItem().toString();
						
			// Hier werden die Benutzer Eingaben auf Ihre Richtigkeit überprüft.
			try
			{
				// Kann die Eingabe im Textfeld AnzahlFragen in einen double Wert umgewandelt werden, wird der folgende Code ausgeführt.
				Double doubleDauerMinuten = Double.parseDouble(textFieldDauer.getText());
			
				if (doubleDauerMinuten < 0.1)
				{
					// Bei ungültiger Eingabe (kleiner als 1) wird ein Fehler ausgegeben und das Eingabefeld zurückgesetzt.
					JOptionPane.showMessageDialog(null, "Das ist aber seeehr wenig Zeit...  \nGeben Sie einen Wert ab 0.1 im Feld \"Dauer\" ein.");
					textFieldDauer.setText("0.1");
				}
				else if (doubleDauerMinuten >= 100)
				{
					// Bei ungültiger Eingabe (100 oder grösser) wird ein Fehler ausgegeben und das Eingabefeld zurückgesetzt.
					JOptionPane.showMessageDialog(null, "Sie haben heute bestimmt noch was anderes vor.  ; - )\nGeben Sie bitte einen Wert unter 100 im Feld \"Dauer\" ein.");
					textFieldDauer.setText("99.99");
				}
				else if (checkBoxPlus.isSelected() == false && checkBoxMinus.isSelected() == false && checkBoxMal.isSelected() == false && checkBoxGeteilt.isSelected() == false)
				{
					// Bei ungültiger Eingabe (keine CheckBox aktiviert) wird ein Fehler ausgegeben.
					JOptionPane.showMessageDialog(null, "Sie sollten mindestens einen Operator auswählen.");
				}
				else if (geteiltStellenZahlEinsString.length() < geteiltStellenZahlZweiString.length())
				{
					// Dann wird noch überprüft, dass die Einstellungen bei "geteilt", die Zweite Zahl nicht mehr Stellen hat als die Erste.
					JOptionPane.showMessageDialog(null, "Achten Sie darauf, dass beim \"Geteilt\" Operator die\nerste Zahl nicht weniger Stellen hat als die Zweite Zahl.");
				}
				else if (minusStellenZahlEinsString.length() < minusStellenZahlZweiString.length())
				{
					// Dann wird noch überprüft, dass die Einstellungen bei "minus", die Zweite Zahl nicht mehr Stellen hat als die Erste.
					JOptionPane.showMessageDialog(null, "Achten Sie darauf, dass beim \"Minus\" Operator die\nerste Zahl nicht weniger Stellen hat als die Zweite Zahl.");
				}
				else
				{
					// Bei gültiger Eingabe im Textfeld AnzahlFragen wird folgender Code ausgeführt:
					
					// Mit folgendem Code wird der "doubleDauerMinuten" auf zwei Nachkommastellen gekürzt
					int temp = (int)((double) (doubleDauerMinuten)*100.0);
					doubleDauerMinuten = ((double)temp)/100.0;
					
					// Der gekürzte "doubleDauerMinuten" wird in das Eingabefeld geschrieben.
					textFieldDauer.setText(Double.toString(doubleDauerMinuten));
					
					// Die gewählte Dauer wird dem AufgabenTimer übergeben.
					AufgabenTimer.setTimer(doubleDauerMinuten);
			
					// Die im TimeAttack höchstmögliche Anzahl von Fragen werden dem Aufgabenzähler übergeben.
					AufgabenZaehler.setZaehlerMax(300);
					
					// Der Aufgabengenerator liest die gewählten "Einstellungen" aus und speichert sie ab.
					AufgabenGenerator.setGenerator();
					
					// Der Zähler für die Aufgaben wird im AufgabenZaehler zurückgesetzt.
					AufgabenZaehler.resetZaehler();	

					// Der Timer wird gestartet.
					AufgabenTimer.startTimer();
					
					// Die Rechner Seite wird dem Benutzer angezeigt.
					GuiContainer.changePanel("GUI_RECHNER");
					
					// Das Textfeld im Rechner wird fokussiert.
					GuiRechner.setFocus();
				}					
			}
			catch (NumberFormatException ex)
			{
				// Wenn die Eingabe nicht in einen Double Wert umgewandelt werden konnte, wird eine Meldung ausgegeben und das Eingabefeld wird zurückgesetzt.
				JOptionPane.showMessageDialog(null, "Die Tastatur zu treffen muss schwierig sein.  ; - )\nGeben Sie bitte einen gültigen Wert im Feld \"Dauer\" ein.");
				textFieldDauer.setText("");
			}
		}
		
		else if (event.getSource() == buttonBack)
		{
			// Der Imput war vom JButton "Back".
			
			// Die Start Seite wird dem Benutzer angezeigt.
			GuiContainer.changePanel("GUI_START");
		
			// Die Anzeige der Einstellungen-Seite wird zurückgesetzt.
			layersEinstellungen.remove(buttonGoClassic);
			layersEinstellungen.remove(buttonGoTimeAttack);
			layersEinstellungen.remove(textFieldAnzahlFragen);
			layersEinstellungen.remove(textFieldDauer);	
			layersEinstellungen.remove(overlayClassicJLabel);
			layersEinstellungen.remove(overlayTimeAttackJLabel);

		}
	}

	
	
	// Durch diese Methode holt sich der AufgabenZaehler die Werte der Checkboxen.
	public static boolean[] getAuswahlCheckboxenArray()
	{
		boolean AuswahlCheckboxenArray[] = {checkBoxPlus.isSelected(), checkBoxMinus.isSelected(), checkBoxMal.isSelected(), checkBoxGeteilt.isSelected()};
					
		return AuswahlCheckboxenArray;
	}
	
	
	
	// Durch diese Methode holt sich der AufgabenZaehler die Werte der Stellen Auswahl.
	public static String[][] getAnzahlStellenArray()
	{
		// Die verschiedenen Auswahlen werden in ein 2 Dimensionales Array übertragen.
		anzahlStellenArray = new String[][]
		{
				{comboBoxPlusErsteStelle.getSelectedItem().toString(), comboBoxPlusZweiteStelle.getSelectedItem().toString()},
				{comboBoxMinusErsteStelle.getSelectedItem().toString(), comboBoxMinusZweiteStelle.getSelectedItem().toString()},
				{comboBoxMalErsteStelle.getSelectedItem().toString(), comboBoxMalZweiteStelle.getSelectedItem().toString()},
				{comboBoxGeteiltErsteStelle.getSelectedItem().toString(), comboBoxGeteiltZweiteStelle.getSelectedItem().toString()},
		};
		return anzahlStellenArray;
	}

	
	
	// Mit dieser Methode wird das GuiEinstellungen für den Classic Modus voreingestellt.
	public static void setClassic()
	{
		layersEinstellungen.add(buttonGoClassic);
		layersEinstellungen.add(textFieldAnzahlFragen);
		if (overlayOn)
		{
			layersEinstellungen.add(overlayClassicJLabel);
		}
		classicMode = true;
	}

	
	
	// Mit dieser Methode wird das GuiEinstellungen für den TimeAttack Modus voreingestellt.
	public static void setTimeAttack()
	{
		layersEinstellungen.add(buttonGoTimeAttack);
		layersEinstellungen.add(textFieldDauer);
		if (overlayOn)
		{
			layersEinstellungen.add(overlayTimeAttackJLabel);
		}
		classicMode = false;
	}

	
	
	// Mit dieser Methode holt sich das GuiAuswertung die Benutzereinstellungen als html Code.
	public static String getEinstellungen()
	{
		// Die Start-Tags werden eingefügt.
		String einstellungenString = "<html><font color=#1E6934 bgcolor=#25C256>";
		// Je nach Einstellungen müssen am Schluss des html Codes noch zusätzliche leere Zeilen hinzugefügt werden. Wie viele das sind regelt der zusatzZeilenInteger.
		Integer zusatzZeilenInteger = 0;
		
		// Je nach Modus wird der Anfang des html Codes angepasst.
		if (classicMode)
		{
			einstellungenString += ("**Anzahl Fragen: " + textFieldAnzahlFragen.getText() + "****************************<br>**********************************************<br>").replace("*", "&#160;");
		}
		else
		{
			einstellungenString += 
			(
				String.format
					("%1$-23s", ("**Anzahl Fragen:*" + (AufgabenPruefer.getAnzahlAufgaben()))).replace(" ", "*")
					
				+ "***Zeit: "
				+ textFieldDauer.getText() + "*Minuten**"
				+ "<br>**********************************************<br>"
					
			).replace("*", "&#160;");    
		}
		
		einstellungenString += "<br><br><br><br><br><br><br><br><br><br>";
			
		// Um die Anzeige der Stellen zu generieren, wird zuerst geprüft, ob der jeweilige Operator aktiv war.
		if (checkBoxPlus.isSelected())
		{
			einstellungenString += (String.format("%1$-1s %2$15s", "", (anzahlStellenArray[0][0]))).replace(" ", "&#160;") + " &#43; " + anzahlStellenArray[0][1] + ("******<br>").replace("*", "&#160;");
		}
		else
		{
			// Ansonsten wird der zusatzZeilenInteger um 1 erhöht.
			zusatzZeilenInteger++;
		}
		if (checkBoxMinus.isSelected())
		{
			einstellungenString += (String.format("%1$-1s %2$15s", "", (anzahlStellenArray[1][0]))).replace(" ", "&#160;") + " &#8722; " + anzahlStellenArray[1][1] + ("******<br>").replace("*", "&#160;");
		}
		else
		{
			zusatzZeilenInteger++; 
		}
		if (checkBoxMal.isSelected())
		{
			einstellungenString += (String.format("%1$-1s %2$15s", "", (anzahlStellenArray[2][0]))).replace(" ", "&#160;") + " &#215; " + anzahlStellenArray[2][1] + ("******<br>").replace("*", "&#160;");
		}
		else
		{
			zusatzZeilenInteger++; 
		}
		if (checkBoxGeteilt.isSelected())
		{
			einstellungenString += (String.format("%1$-1s %2$15s", "", (anzahlStellenArray[3][0]))).replace(" ", "&#160;") + " &#247; " + anzahlStellenArray[3][1] + ("******<br>").replace("*", "&#160;");
		}
		else
		{
			zusatzZeilenInteger++; 
		}
		
		// Mit dieser for Schleife werden die berechneten Zusatzzeilen erstellt.
		for (int i = 0; i < zusatzZeilenInteger; i++)
		{
			einstellungenString += ("*******************************<br>").replace("*", "&#160;");
		}
		
		// Der html Code wird mit den Schluss-Tags ergänzt.
		einstellungenString += "</font></html>";
		
		return einstellungenString;		
	}


	// Methode für Testzwecke (Ausblenden des Overlay)
	public static void overlaySwitch(boolean on)
	{
		if (on)
		{
			overlayOn = true;
		}
		else
		{
			overlayOn = false;
		}
	}
}
