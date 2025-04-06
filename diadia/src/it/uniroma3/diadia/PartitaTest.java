package it.uniroma3.diadia;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;

import static org.junit.jupiter.api.Assertions.*;

public class PartitaTest {

    @Test
    public void testCostruttore() {
        Partita partita = new Partita();
        assertNotNull(partita);
        assertNotNull(partita.getLabirinto());
        assertNotNull(partita.getGiocatore());
        assertNotNull(partita.getStanzaCorrente());
    }

    @Test
    public void testGetStanzaVincente() {
        Partita partita = new Partita();
        assertNotNull(partita.getStanzaVincente());
    }

    @Test
    public void testSetStanzaCorrente() {
        Partita partita = new Partita();
        Stanza stanza = new Stanza("Test");
        partita.setStanzaCorrente(stanza);
        assertEquals(stanza, partita.getStanzaCorrente());
    }

    @Test
    public void testVinta() {
        Partita partita = new Partita();
        assertFalse(partita.vinta());
        partita.setStanzaCorrente(partita.getStanzaVincente());
        assertTrue(partita.vinta());
    }

    @Test
    public void testIsFinita() {
        Partita partita = new Partita();
        assertFalse(partita.isFinita());
        partita.setFinita();
        assertTrue(partita.isFinita());
        partita.setFinita();
        partita.setCfu(0);
        assertTrue(partita.isFinita());
    }

    @Test
    public void testGetCfu() {
        Partita partita = new Partita();
        assertEquals(0, partita.getCfu());
    }

    @Test
    public void testSetCfu() {
        Partita partita = new Partita();
        partita.setCfu(10);
        assertEquals(10, partita.getCfu());
    }

    @Test
    public void testGetLabirinto() {
        Partita partita = new Partita();
        assertNotNull(partita.getLabirinto());
    }

    @Test
    public void testGetGiocatore() {
        Partita partita = new Partita();
        assertNotNull(partita.getGiocatore());
    }
}
