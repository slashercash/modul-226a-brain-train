package berechnungen;

import gui.GuiAuswertung;

// In dieser Klasse wird die das Array mit all den gespeicherten Aufgaben ausgewertet, und der html text dazu zur Darstellung generiert.
public class AufgabenPruefer
{
	// Integer für die Berechnung der Aufgaben Anzahl
	static Double anzahlAufgabenDouble;

	// In dieser Methode wird die das Array mit all den gespeicherten Aufgaben ausgewertet, und der html text dazu zur Darstellung generiert.
	public static void pruefeAufgabenListe(String[][] aufgabeAntwortUndLoesungString)
	{
		// Strings		
		String pruefListeString;
		String zeileString;
		String prozentAngabeString;
		
		// Integer
		Double anzahlRichtigeAntwortenDouble;
		Integer antwortInteger;
		Integer loesungInteger;
		
		// Anzahl Aufgaben und Anzahl richtige Antworten wird auf 0 zurückgesetzt.
		anzahlAufgabenDouble = 0d;
		anzahlRichtigeAntwortenDouble = 0d;
		
		// Hier wird die Anzahl der gespeicherten Aufgaben im Array berechnet. (Das Array hat insgesamt Platz für 300 Aufgaben).
		for (int i = 0; i <= 299; i++)
		{
			if (aufgabeAntwortUndLoesungString[i][1] == null)	// > Sobald der Arraplatz leer ist, wird mit i = 300 die for Schleife abgebrochen.
			{
				i = 300;
			}
			else
			{								
				anzahlAufgabenDouble++;	// > Ist der Arrayplatz belegt, wird die Aufgaben Anzahl um 1 erhöht.
			}		
		}
		
		// In die pruefListe wird der gesamte html code gespeichert. Die start-Tags werden hier direkt schon eingefügt.
		pruefListeString = "<html><font bgcolor=#25C256>" + ("*******************************************<br></font>").replace("*", "&#160;");
				
		// Für jede gespeicherte Aufgabe wird folgende Prozedur durchgeführt.
		for (int j = 0; j < (anzahlAufgabenDouble); j++)
		{
			// Antwort und Lösung werden in eine Integer Variable gespeichert.
			antwortInteger = Integer.parseInt(aufgabeAntwortUndLoesungString[j][1]);
			loesungInteger = Integer.parseInt(aufgabeAntwortUndLoesungString[j][2]);
			
			// Im zeileString wird die Aufgabe und die Benutzereingabe in einen html code umgewandelt (z.Bsp. 24 + 8 = 32). die Operatoren werden zusätzlich durch optisch schönere html Operatoren ersetzt.
			zeileString = String.format("%1$-30s", ("  " + aufgabeAntwortUndLoesungString[j][0] + " = " + aufgabeAntwortUndLoesungString[j][1])).replace(" ", "&#160;").replace("+", "&#43;").replace("-", "&#8722;").replace("*", "&#215;").replace(":", "&#247;");
			
			// Antwort und Lösung wird verglichen.
			if (antwortInteger - loesungInteger == 0)
			{
				// Wurde die Aufgabe richtig beantwortet, erhällt die Zeile eine hellere Hintergrundfarbe und ein "Häckchen".
				pruefListeString += "<font color=#1E6934 bgcolor=#25C256>" + zeileString + "&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</font><br>";
				// Zusätzlich wird die Anzahl der Richtigen Antworten um eins erhöht.
				anzahlRichtigeAntwortenDouble++;
			}
			else
			{
				// Wurde die Aufgabe falsch beantwortet, erhällt die Zeile eine dunklere Hintergrundfarbe und ein "x". Zusätzlich wird die Lösung hinten angesetzt.
				pruefListeString += "<font color=#25C256 bgcolor=#1E6934>" + zeileString + "&#215;" + String.format("%1$-1s %2$8s", "", (aufgabeAntwortUndLoesungString[j][2])).replace(" ", "&#160;") + "&#160;&#160;</font><br>";
			}
		}
		
		// Dann werden aus Darstellungsgründen noch 8 Lehrzeilen hinzugefügt.
		for (int x = 0; x < 8; x++)
		{
			pruefListeString += ("<font bgcolor=#25C256>*******************************************<br></font>").replace("*", "&#160;");
		}
		
		// Schlussendlich wird der html Code mit den abschliessenden Tags ergänzt.
		pruefListeString += "</html>";
		
		// Mit dieser if Schleife wird im Falle von keiner gelösten Aufgabe (in TimeAttack die Zeit ablaufen lassen) eine Division durch 0 verhindert.
		if (anzahlAufgabenDouble == 0)
		{
			prozentAngabeString = ("<html><font color=#1E6934 bgcolor=#25C256>****************************************<br>**0 % der Fragen richtig beantwortet****<br>****************************************</font></html>").replace("*", "&#160;");
		}
		else
		{
			// Hier wird die Prozentuale Angabe der richtig gelösten Aufgaben berechnet und als html Code gespeichert.
			prozentAngabeString = ("<html><font color=#1E6934 bgcolor=#25C256>****************************************<br>**" + Integer.toString((int)(anzahlRichtigeAntwortenDouble / anzahlAufgabenDouble * 100)) +  " % der Fragen richtig beantwortet****<br>****************************************</font></html>").replace("*", "&#160;");
		}
			
		// Die pruefListe und die prozentAngabe werden dem GuiAuswertung übergeben.
		GuiAuswertung.setAuswertung(pruefListeString, prozentAngabeString);
		
		// Da die pruefListe erstellt ist, wird das Aufgaben Array im AufgabenGenerator zurückgesetzt.
		AufgabenGenerator.resetAufgabeAntwortUndLoesungString();
	}
	
	// Diese Methode gibt die Anzahl der Aufgaben zurück.
	public static String getAnzahlAufgaben()
	{
		return Integer.toString(anzahlAufgabenDouble.intValue());
	}

}
