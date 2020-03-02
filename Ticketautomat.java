import java.text.DecimalFormat;
/**
 * Beschreiben Sie hier die Klasse Ticketautomat.
 * 
 * @author Lukas Breuer 
 * @version 16.11.2019
 */
public class Ticketautomat
{   
    double preis;
    double preisProTicket;
    int reichweite;
    int anzahl;
    boolean erwachsen;
    double eingeworfen;
    double reichweiteMultiplier;
    double gezahlt;
    double rueckgeld;
    
    DecimalFormat format = new DecimalFormat("#0.00");

    /**
     * Konstruktor für Objekte der Klasse Ticketautomat
     */
    public Ticketautomat()
    {
        preis = 1;
        reichweite = 3;
        anzahl = 1;
        erwachsen = true;
        eingeworfen = 0;
        reichweiteMultiplier = 1;
        gezahlt = 0;
        rueckgeld = 0;
        abfrage();
        preisBerechnung();
        bezahlen();
    }
    
    private void reichweite()
    {   
        reichweite = EinfachEingabe.getInt("Welche Reichweite soll ihnen Ihr Ticket bieten? (1-5)");
        if(reichweite < 1 || reichweite > 5)
        {
            reichweite();
        }
    }
    
    private void erwachsen()
    {
        erwachsen = EinfachEingabe.getBoolean("Sind Sie volljährig?", "Erwachsenenticket", "Kinderticket");
    }
    
    private void anzahl()
    {
        anzahl = EinfachEingabe.getInt("Wie viele Tickets möchten Sie kaufen? (1-99)");
        if(anzahl < 1 || anzahl > 99)
        {
            anzahl();
        }
    }
    
    private void abfrage()
    {
        reichweite();
        erwachsen();
        anzahl();
    }
    
    private double preisBerechnung()
    {
      switch (reichweite)
      {
          case 1: reichweiteMultiplier = 1;
                    break;
          case 2: reichweiteMultiplier = 1.75;
                    break;
          case 3: reichweiteMultiplier = 2.25;
                    break;
          case 4: reichweiteMultiplier = 2.5;
                    break;
          case 5: reichweiteMultiplier = 3;
                    break;
          default:  break;
      }
      
      preisProTicket = reichweiteMultiplier * preis;
      
      if(erwachsen)
      {
          preisProTicket = preisProTicket * 2;
      }
      
      preis = preisProTicket * anzahl;
      
      return preis;
    }
    
    public void bezahlen()
    {
        if(preis > gezahlt)
        {
            gezahlt = gezahlt + EinfachEingabe.getDouble("Sie müssen noch " + (format.format(preis - gezahlt)) + "€ zahlen.");
            bezahlen();
            if(preis < gezahlt)
            {
                rueckgeld = gezahlt - preis;
                System.out.println("Sie erhalten " + (format.format(rueckgeld)) + "€ Rückgeld.");
            }
        }

    }
}
