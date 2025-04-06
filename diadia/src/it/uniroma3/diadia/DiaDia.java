package it.uniroma3.diadia;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai 'direzione' ", "aiuto", "fine", "prendi 'attrezzo' ", "posa 'attrezzo' "};

	private Partita partita;
	private IOConsole ioconsole;

	public DiaDia() {
		this.partita = new Partita();
		this.ioconsole = new IOConsole();
	}

	public void gioca() {
		String istruzione; 
		
		ioconsole.mostraMessaggio(MESSAGGIO_BENVENUTO);
		ioconsole.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		ioconsole.mostraMessaggio(partita.getGiocatore().toString());
		
		do		
			istruzione = ioconsole.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			ioconsole.mostraMessaggio("Comando non corretto");
		if (this.partita.vinta()) {
			ioconsole.mostraMessaggio("Hai vinto, congratulazioni!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		ioconsole.mostraMessaggio("Di seguito l'elenco dei comandi a disposizione: ");
		for(int i=0; i< elencoComandi.length; i++) 
			ioconsole.mostraMessaggio(elencoComandi[i]+ "\n");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			ioconsole.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			ioconsole.mostraMessaggio("La direzione non esiste. ");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getCfu();
			this.partita.setCfu(cfu--);
		}
		ioconsole.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		ioconsole.mostraMessaggio(partita.getGiocatore().toString());
	}

	//prendi attrezzo
	
	private void prendi(String nomeAttrezzo) {
		if(nomeAttrezzo == null)
			ioconsole.mostraMessaggio("Quale attrezzo vuoi utilizzare?");
		
			Attrezzo attrezzo = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			if (attrezzo == null) {
				ioconsole.mostraMessaggio("L'attrezzo inserito non esiste nella stanza");
			return;
			}	
			
			this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
			this.partita.getLabirinto().getStanzaFinale().removeAttrezzo(attrezzo);
			ioconsole.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}
	
	//posa attrezzo
	
	private void posa(String nomeAttrezzo) {
		if(nomeAttrezzo == null)
			ioconsole.mostraMessaggio("Non hai dichiarato l'attrezzo da posare");
		Attrezzo attrezzo = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		if(attrezzo == null) {
			ioconsole.mostraMessaggio("L'attrezzo non esiste!");
			return;
		}
		this.partita.getLabirinto().getStanzaFinale().addAttrezzo(attrezzo);
		this.partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo);
		ioconsole.mostraMessaggio(partita.getLabirinto().getStanzaFinale().getDescrizione());
	}
	
	/**
	 * Comando "Fine".
	 */
	private void fine() {
		ioconsole.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}
	
	
	

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}