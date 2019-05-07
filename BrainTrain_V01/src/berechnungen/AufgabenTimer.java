package berechnungen;

import java.util.Timer;
import java.util.TimerTask;

import gui.GuiContainer;
import gui.GuiRechner;

// Diese Klasse regelt die korrekte Darstellung des Timers im TimeAttack Modus und sorgt für das saubere Beenden bei Ablauf des Timers.
public class AufgabenTimer extends TimerTask
{
	// Integer für die Sekunden Anzeige
	static Integer anzeigeWertSekundenInteger;
	// Boolean für das anhalten des Timers
	static boolean stopBoolean = true;
	// Timer wird erstellt.
	static Timer timer = new Timer();

	// Mit dieser Methode wird der Timer eingestellt.
	public static void setTimer(double dauerMinutenDouble)
	{
		// Der Boolean für das anhalten des Timers wird deaktiviert.
		stopBoolean = false;
		// Der erhaltene Wert der Dauer in Minuten wird in Sekunden umgerechnet und in eine lokale Variable gespeichert.
		anzeigeWertSekundenInteger = (int) (dauerMinutenDouble * 60);
	}
		
	
	
	// Mit dieser Methode wird der Timer gestartet.
	public static void startTimer()
	{	
		// Die Sekunden Anzeige im GuiRechner wird aktualisiert.
		GuiRechner.setStatus("Sekunden: " + anzeigeWertSekundenInteger.toString());
		// Der Timer wartet 1000 Millisekunden (1sek) und ruft dann die run Methode auf.
		timer.schedule(new AufgabenTimer(), 1000);
	}
	
	
	
	// Diese Methode wird vom Timer aufgerufen.
	@Override
	public void run()
	{
		// Der Anzeige Wert wird um eins reduziert.
		anzeigeWertSekundenInteger --;
		
		// Wenn der stopBoolean aktiviert ist wird die Schleife direkt abgebrochen.
		if (stopBoolean)
		{
			// Die If Schleife wird abgebrochen.
		}
		// Wenn der anzeigeWert noch nicht 0 erreicht hat, wird der Timer erneut gestartet.
		else if (0 < anzeigeWertSekundenInteger)
		{
			startTimer();
		}
		// Wenn der anzeigeWert 0 erreicht hat werden folgende Aktionen durchgeführt.
		else
		{
			// Der Timer wird gestoppt.
			stopTimer();
			// Der AufgabenGenerator sendet seine generierten Aufgaben weiter.
			AufgabenGenerator.sendAufgabenListe();
			// Dem Benutzer wird das GuiAuswertung angezeigt.
			GuiContainer.changePanel("GUI_AUSWERTUNG");
		}
	}

	
	
	// Diese Methode stoppt den Timer.
	public static void stopTimer()
	{
		stopBoolean = true;	
	}
}
