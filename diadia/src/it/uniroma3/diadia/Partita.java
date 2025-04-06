package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

public class Partita {

    private Labirinto labirinto;
    private boolean finita;
    private Giocatore giocatore;
    private Stanza stanzaCorrente;

    public Partita() {
        creaStanze();
        this.finita = false;
        this.giocatore = new Giocatore();
        this.stanzaCorrente = this.labirinto.getStanzaIniziale();
    }

    private void creaStanze() {
        this.labirinto = new Labirinto();
    }

    public Stanza getStanzaVincente() {
        return this.labirinto.getStanzaFinale();
    }

    public void setStanzaCorrente(Stanza stanzaCorrente) {
        this.stanzaCorrente = stanzaCorrente;
    }

    public Stanza getStanzaCorrente() {
        return this.stanzaCorrente;
    }

    public boolean vinta() {
        return this.stanzaCorrente == this.labirinto.getStanzaFinale();
    }

    public boolean isFinita() {
        return finita || vinta() || (giocatore.getCfu() == 0);
    }

    public void setFinita() {
        this.finita = true;
    }

    public int getCfu() {
        return this.giocatore.getCfu();
    }

    public void setCfu(int cfu) {
        this.giocatore.setCfu(cfu);
    }

    public Labirinto getLabirinto() {
        return this.labirinto;
    }

    public Giocatore getGiocatore() {
        return this.giocatore;
    }
}

