package it.uniroma3.diadia.ambienti;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LabirintoTest {

    @Test
    public void testCostruttore() {
        Labirinto labirinto = new Labirinto();
        assertNotNull(labirinto);
    }

    @Test
    public void testGetStanzaIniziale() {
        Labirinto labirinto = new Labirinto();
        assertNotNull(labirinto.getStanzaIniziale());
    }

    @Test
    public void testGetStanzaFinale() {
        Labirinto labirinto = new Labirinto();
        assertNotNull(labirinto.getStanzaFinale());
    }
}


