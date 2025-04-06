package it.uniroma3.diadia.giocatore;
import org.junit.jupiter.api.Test;

import it.uniroma3.attrezzi.Attrezzo;

import static org.junit.jupiter.api.Assertions.*;

public class BorsaTest {

    @Test
    public void testCostruttoreDefault() {
        Borsa borsa = new Borsa();
        assertNotNull(borsa);
        assertEquals(Borsa.DEFAULT_PESO_MAX_BORSA, borsa.getPesoMax());
    }

    @Test
    public void testCostruttorePersonalizzato() {
        Borsa borsa = new Borsa(20);
        assertNotNull(borsa);
        assertEquals(20, borsa.getPesoMax());
    }

    @Test
    public void testAddAttrezzo() {
        Borsa borsa = new Borsa();
        Attrezzo attrezzo = new Attrezzo("Test", 1);
        assertTrue(borsa.addAttrezzo(attrezzo));
        assertEquals(1, borsa.getNumeroAttrezzi());
    }

    @Test
    public void testAddAttrezzoPesoEccessivo() {
        Borsa borsa = new Borsa();
        Attrezzo attrezzo = new Attrezzo("Test", 15);
        assertFalse(borsa.addAttrezzo(attrezzo));
        assertEquals(0, borsa.getNumeroAttrezzi());
    }

    @Test
    public void testAddAttrezzoCapacitaMassima() {
        Borsa borsa = new Borsa();
        for (int i = 0; i < 10; i++) {
            Attrezzo attrezzo = new Attrezzo("Test" + i, 1);
            assertTrue(borsa.addAttrezzo(attrezzo));
        }
        Attrezzo attrezzoExtra = new Attrezzo("Extra", 1);
        assertFalse(borsa.addAttrezzo(attrezzoExtra));
        assertEquals(10, borsa.getNumeroAttrezzi());
    }

    @Test
    public void testGetAttrezzo() {
        Borsa borsa = new Borsa();
        Attrezzo attrezzo = new Attrezzo("Test", 1);
        borsa.addAttrezzo(attrezzo);
        assertNotNull(borsa.getAttrezzo("Test"));
    }

    @Test
    public void testGetAttrezzoNonEsistente() {
        Borsa borsa = new Borsa();
        assertNull(borsa.getAttrezzo("NonEsistente"));
    }

    @Test
    public void testPeso() {
        Borsa borsa = new Borsa();
        Attrezzo attrezzo1 = new Attrezzo("Test1", 2);
        Attrezzo attrezzo2 = new Attrezzo("Test2", 3);
        borsa.addAttrezzo(attrezzo1);
        borsa.addAttrezzo(attrezzo2);
        assertEquals(5, borsa.getPeso());
    }

    @Test
    public void testIsEmpty() {
        Borsa borsa = new Borsa();
        assertTrue(borsa.isEmpty());
        Attrezzo attrezzo = new Attrezzo("Test", 1);
        borsa.addAttrezzo(attrezzo);
        assertFalse(borsa.isEmpty());
    }

    @Test
    public void testHasAttrezzo() {
        Borsa borsa = new Borsa();
        Attrezzo attrezzo = new Attrezzo("Test", 1);
        borsa.addAttrezzo(attrezzo);
        assertTrue(borsa.hasAttrezzo("Test"));
    }

    @Test
    public void testRemoveAttrezzo() {
        Borsa borsa = new Borsa();
        Attrezzo attrezzo = new Attrezzo("Test", 1);
        borsa.addAttrezzo(attrezzo);
        assertTrue(borsa.removeAttrezzo(attrezzo));
        assertTrue(borsa.isEmpty());
    }

    @Test
    public void testRemoveAttrezzoNonEsistente() {
        Borsa borsa = new Borsa();
        Attrezzo attrezzo = new Attrezzo("Test", 1);
        assertFalse(borsa.removeAttrezzo(attrezzo));
    }

    @Test
    public void testToString() {
        Borsa borsa = new Borsa();
        assertEquals("Borsa vuota", borsa.toString());
        Attrezzo attrezzo = new Attrezzo("Test", 1);
        borsa.addAttrezzo(attrezzo);
        assertTrue(borsa.toString().contains("Test"));
    }
}
