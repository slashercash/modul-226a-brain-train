package berechnungen;

import java.util.Random;

import gui.GuiEinstellungen;
import gui.GuiRechner;

// In dieser Klasse werden Rechenaufgaben nach Vorgaben des Benutzers durch Zufallsprinzip generiert und gespeichert.
public class AufgabenGenerator
{
	// Arrays, um die Einstellungen und Antworten des Benutzers strukturiert abzuspeichern.
	static boolean [] auswahlCheckboxenArrayBoolean;	
	static String[][] anzahlStellenArrayString;
	static String[][] aufgabeAntwortUndLoesungString = new String[301][3];
	static Integer[][] anzahlStellenArrayInteger = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
	
	// Integer um die eingestellte Stellenanzahl einzuhalten.
	static Integer stellenZahlEinsInteger;
	static Integer stellenZahlZweiInteger;
	static Integer stellenZahlEinsAnhebenInteger;
	static Integer stellenZahlZweiAnhebenInteger;
	
	// Weitere Integer für interne Berechnungen
	static Integer operatorInteger;
	static Integer zahlEinsInteger;
	static Integer zahlZweiInteger;
	static Integer loesungInteger;
	static Integer aufgabeNrInteger;
	
	// String um die erhaltenen Aufgaben abzuspeichern
	static String operatorString;
	static String zahlEinsString;
	static String zahlZweiString;	
	static String aufgabeString;
	static String loesungString;

	
	
	// Diese Methode liest die Benutzereinstellungen im GuiEinstellungen aus, und konvertiert sie in für die Berechnung notwendige Datentypen.
	public static void setGenerator()
	{
		// Die ausgewählten Checkboxen im GuiEinstellungen werden in ein Array aufgenommen.
		auswahlCheckboxenArrayBoolean = GuiEinstellungen.getAuswahlCheckboxenArray();
		
		// Die ausgewählten Stellenwerte im GuiEinstellungen werden in ein Array aufgenommen.
		anzahlStellenArrayString = GuiEinstellungen.getAnzahlStellenArray();
		
		// Die Platzhalter der Stellen (xxxxx) werden auf ein IntegerArray als brauchbare IntegerZahlen übertragen
		for (int i = 0; i <= 3; i+=1)
		{
			for (int j = 0; j <= 1; j+=1)
			{
				// Jeder Arrayplatz wird ausgelesen und der Inhalt wird zu einer IntegerZahl konvertiert.
				switch (anzahlStellenArrayString[i][j]) 
				{
					case " x": anzahlStellenArrayInteger[i][j] = 9;	
							  anzahlStellenArrayInteger[i][j+2] = 1;
						break;
						
					case " xx": anzahlStellenArrayInteger[i][j] = 90;	// > Der Zufallsgenerator wird später mit dieser Zahl (90) einen wert zwischen 0 und 89 generieren.
							   anzahlStellenArrayInteger[i][j+2] = 10;	// > Anschliessend wird das Ergebnis mit 10 addiert, um einen zweistelligen Wert zwischen 10 und 99 zu erhalten.
						break;
						
					case " xxx": anzahlStellenArrayInteger[i][j] = 900;	
								anzahlStellenArrayInteger[i][j+2] = 100;
						break;
						
					case " xxxx": anzahlStellenArrayInteger[i][j] = 9000;	
								 anzahlStellenArrayInteger[i][j+2] = 1000;
						break;
						
					case " xxxxx": anzahlStellenArrayInteger[i][j] = 90000;	
								  anzahlStellenArrayInteger[i][j+2] = 10000;
						break;
				}
			}
		}
	}

	
	
	// Diese Methode generiert nach den Vorgaben des  Benutzers eine Rechenaufgabe, sendet sie dem GuiRechner und speichert sie inklusive Lösung in einem Array ab.
	public static void generateAufgabe(Integer tempAufgabeNrInteger)
	{
		// Die Nr der Aufgabe (angefangen von 1) wird in dieser Statischen Variable gespeichert. Sie wird beim aufrufen dieser Methode mitgegeben.
		aufgabeNrInteger = tempAufgabeNrInteger;
		
		// Random und Boolean wird erstellt.
		Random rand = new Random();
		Boolean operatorGewaehlt = false;
		
		// Zuerst wird der Operator bestummen.
		while (!operatorGewaehlt)
		{
			// eine Nummer zwischen 0 und 3 wird generiert. Die Zahlen stehen für je einen der vier Operatoren.
			operatorInteger = rand.nextInt(4);
			
			// Es wird überprüft, ob der generierte Operator vom Benutzer ausgewählt wurde. Falls nicht springt der code zurück und generiert eine neue Nr.
			if (auswahlCheckboxenArrayBoolean[operatorInteger] == true)
			{
				// der Operator (noch in Integer Form) wird in einer SwitchCase Schlaufe analisiert.
				switch (operatorInteger)
				{
					case 0: operatorString = " + ";												// > Der Operator wird als String gespeichert.
							stellenZahlEinsInteger = anzahlStellenArrayInteger[0][0];			// > Die zum Operator eingetragenen Stellenwerte der ersten und zweiten Zahl
							stellenZahlZweiInteger = anzahlStellenArrayInteger[0][1];			//   werden aus dem Array in einen "temporären" Integer wert gespeichert.
							stellenZahlEinsAnhebenInteger = anzahlStellenArrayInteger[0][2];	// > Die vorhin erstellten "anheben" Werte werden ebenfalls ausgelesen.
							stellenZahlZweiAnhebenInteger = anzahlStellenArrayInteger[0][3];
							
						break;
					
					case 1: operatorString = " - ";
							stellenZahlEinsInteger = anzahlStellenArrayInteger[1][0];
							stellenZahlZweiInteger = anzahlStellenArrayInteger[1][1];
							stellenZahlEinsAnhebenInteger = anzahlStellenArrayInteger[1][2];
							stellenZahlZweiAnhebenInteger = anzahlStellenArrayInteger[1][3];
						break;
				
					case 2: operatorString = " * ";
							stellenZahlEinsInteger = anzahlStellenArrayInteger[2][0];
							stellenZahlZweiInteger = anzahlStellenArrayInteger[2][1];
							stellenZahlEinsAnhebenInteger = anzahlStellenArrayInteger[2][2];
							stellenZahlZweiAnhebenInteger = anzahlStellenArrayInteger[2][3];
						break;
				
					case 3: operatorString = " : ";
							stellenZahlEinsInteger = anzahlStellenArrayInteger[3][0];
							stellenZahlZweiInteger = anzahlStellenArrayInteger[3][1];
							stellenZahlEinsAnhebenInteger = anzahlStellenArrayInteger[3][2];
							stellenZahlZweiAnhebenInteger = anzahlStellenArrayInteger[3][3];
						break;
				}
				// Der Boolean wird auf true gesetzt, um die While Schleife zu verlassen.
				operatorGewaehlt = true;
			}
		}
		
		// Der Operator und die dazugehörenden Stellen sind nun klaar. Jetzt werden die zwei Zahlen und direkt auch die Lösung generiert.
		
		// Je nach Operator ist eine andere Berechnung vorgesehen.
		if (operatorString == " + ")
		{
			zahlEinsInteger = rand.nextInt(stellenZahlEinsInteger);	// > Zahl-Eins wird generiert
			zahlEinsInteger += stellenZahlEinsAnhebenInteger;		// > Zahl-Eins wird angehoben
			
			zahlZweiInteger = rand.nextInt(stellenZahlZweiInteger);	// > Zahl-Zwei wird generiert
			zahlZweiInteger += stellenZahlZweiAnhebenInteger;		// > Zahl-Zwei wird angehoben
			
			loesungInteger = zahlEinsInteger + zahlZweiInteger;		// > Zahl-Eins und Zahl-Zwei werden Addiert und das Ergebnis in einem Integer gespeichert.
		}
		
		else if (operatorString == " - ")
		{
			do
			{
				zahlEinsInteger = rand.nextInt(stellenZahlEinsInteger);
				zahlEinsInteger += stellenZahlEinsAnhebenInteger;
				
				zahlZweiInteger = rand.nextInt(stellenZahlZweiInteger);
				zahlZweiInteger += stellenZahlZweiAnhebenInteger;
				
			} while (zahlEinsInteger <= zahlZweiInteger);			// > Bei der Subtraktion wird zusätzlich sichergestellt, dass das Ergebniss nicht im Minusbereich liegt.
			
			loesungInteger = zahlEinsInteger - zahlZweiInteger;		// > Zahl-Zwei wird von Zahl-Eins Subtrahiert und das Ergebnis in einem Integer gespeichert.
		}
		
		else if (operatorString == " * ")
		{
			do
			{
				zahlEinsInteger = rand.nextInt(stellenZahlEinsInteger);
				zahlEinsInteger += stellenZahlEinsAnhebenInteger;
				
				zahlZweiInteger = rand.nextInt(stellenZahlZweiInteger);
				zahlZweiInteger += stellenZahlZweiAnhebenInteger;
				
			} while (zahlEinsInteger <= 1 || zahlZweiInteger <= 1);	// > Bei der Multiplikation wird zusätzlich sichergestellt, dass nicht mit 1 multipliziert wird.
			
			loesungInteger = zahlEinsInteger * zahlZweiInteger;		// > Zahl-Eins und Zahl-Zwei werden Multipliziert und das Ergebnis in einem Integer gespeichert.

		}
		
		else if (operatorString == " : ")
		{			
			do
			{
				zahlEinsInteger = rand.nextInt(stellenZahlEinsInteger);
				zahlEinsInteger += stellenZahlEinsAnhebenInteger;
				
				zahlZweiInteger = rand.nextInt(stellenZahlZweiInteger);
				zahlZweiInteger += stellenZahlZweiAnhebenInteger;
				
			} while(zahlEinsInteger % zahlZweiInteger != 0 || zahlZweiInteger <= 2 || zahlEinsInteger == zahlZweiInteger);	// > Bei der Division wird zusätzlich sichergestellt,
																															//   dass das Ergebnis eine Ganzzahl ist und der Divisor
																															//   grösser als 2 ist.
			
			loesungInteger = zahlEinsInteger / zahlZweiInteger;		// > Zahl-Eins wird durch die Zahl-Zwei Dividiert und das Ergebnis in einem Integer gespeichert.
		}
	
		// Die Integer Werte werden in einen String umgewandelt.
		zahlEinsString = Integer.toString(zahlEinsInteger);
		zahlZweiString = Integer.toString(zahlZweiInteger);
		loesungString = Integer.toString(loesungInteger);
		
		// Die generierten Strings werden zur fertigen Aufgabe zusammengesetzt
		aufgabeString = zahlEinsString + operatorString + zahlZweiString;
		
		// Die Aufgabe und die Lösung werden mithilfe der AufgabenNr in das Array aufgenommen.
		// In diesem Array werden alle generierten Aufgaben der Reihe nach abgespeichert.
		aufgabeAntwortUndLoesungString[aufgabeNrInteger-1][0] = aufgabeString;
		aufgabeAntwortUndLoesungString[aufgabeNrInteger-1][2] = loesungString;

		// Die Aufgabe wird dem GuiRechner übergeben.
		GuiRechner.setAufgabe(aufgabeString);	
	}

	
	
	// Mit dieser Methode wird die im GuiRechner eingegebene Antwort in den passenden Arrayplatz aufgenommen.
	public static void saveAntwort(String antwortString)
	{
		// die statische Variable aufgabeNrInteger ist noch aktuell. So wird die Antwort zur passenden Aufgabe und Lösung hinzugefügt.
		aufgabeAntwortUndLoesungString[aufgabeNrInteger-1][1] = antwortString;
	}

	
	
	// Mit dieser Methode wird das Array mit den gesammelten Aufgaben-Daten dem AufgabenPruefer übergeben.
	public static void sendAufgabenListe()
	{
		AufgabenPruefer.pruefeAufgabenListe(aufgabeAntwortUndLoesungString);
	}

	
	
	// mit dieser Methode wird das Array mit den gesammelten Aufgaben-Daten zurückgesetzt.
	public static void resetAufgabeAntwortUndLoesungString()
	{
		aufgabeAntwortUndLoesungString = new String[300][3];
	}
}
