package it.uniroma3.diadia.ambienti;
import org.junit.jupiter.api.Test;

import it.uniroma3.attrezzi.Attrezzo;

import static org.junit.jupiter.api.Assertions.*;

public class StanzaTest {

    @Test
    public void testCostruttore() {
        Stanza stanza = new Stanza("Test");
        assertNotNull(stanza);
        assertEquals("Test", stanza.getNome());
    }

    @Test
    public void testImpostaStanzaAdiacente() {
        Stanza stanza1 = new Stanza("Stanza1");
        Stanza stanza2 = new Stanza("Stanza2");
        stanza1.impostaStanzaAdiacente("nord", stanza2);
        assertNotNull(stanza1.getStanzaAdiacente("nord"));
        assertEquals(stanza2, stanza1.getStanzaAdiacente("nord"));
    }

    @Test
    public void testGetStanzaAdiacente() {
        Stanza stanza1 = new Stanza("Stanza1");
        Stanza stanza2 = new Stanza("Stanza2");
        stanza1.impostaStanzaAdiacente("nord", stanza2);
        assertNotNull(stanza1.getStanzaAdiacente("nord"));
        assertNull(stanza1.getStanzaAdiacente("sud"));
    }

    @Test
    public void testAddAttrezzo() {
        Stanza stanza = new Stanza("Test");
        Attrezzo attrezzo = new Attrezzo("TestAttrezzo", 1);
        assertTrue(stanza.addAttrezzo(attrezzo));
        assertEquals(1, stanza.getAttrezzi().length);
    }

    @Test
    public void testAddAttrezzoCapacitaMassima() {
        Stanza stanza = new Stanza("Test");
        for (int i = 0; i < Stanza.NUMERO_MASSIMO_ATTREZZI; i++) {
            Attrezzo attrezzo = new Attrezzo("Attrezzo" + i, 1);
            assertTrue(stanza.addAttrezzo(attrezzo));
        }
        Attrezzo attrezzoExtra = new Attrezzo("Extra", 1);
        assertFalse(stanza.addAttrezzo(attrezzoExtra));
    }

    @Test
    public void testHasAttrezzo() {
        Stanza stanza = new Stanza("Test");
        Attrezzo attrezzo = new Attrezzo("TestAttrezzo", 1);
        stanza.addAttrezzo(attrezzo);
        assertTrue(stanza.hasAttrezzo("TestAttrezzo"));
    }

    @Test
    public void testGetAttrezzo() {
        Stanza stanza = new Stanza("Test");
        Attrezzo attrezzo = new Attrezzo("TestAttrezzo", 1);
        stanza.addAttrezzo(attrezzo);
        assertNotNull(stanza.getAttrezzo("TestAttrezzo"));
    }

    @Test
    public void testRemoveAttrezzo() {
        Stanza stanza = new Stanza("Test");
        Attrezzo attrezzo = new Attrezzo("TestAttrezzo", 1);
        stanza.addAttrezzo(attrezzo);
        assertTrue(stanza.removeAttrezzo(attrezzo));
        assertFalse(stanza.hasAttrezzo("TestAttrezzo"));
    }

    @Test
    public void testGetDirezioni() {
        Stanza stanza = new Stanza("Test");
        Stanza stanza2 = new Stanza("Stanza2");
        stanza.impostaStanzaAdiacente("nord", stanza2);
        String[] direzioni = stanza.getDirezioni();
        assertEquals(1, direzioni.length);
        assertEquals("nord", direzioni[0]);
    }
}
