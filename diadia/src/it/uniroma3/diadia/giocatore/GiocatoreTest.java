package it.uniroma3.diadia.giocatore;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GiocatoreTest {

    @Test
    public void testCostruttore() {
        Giocatore giocatore = new Giocatore();
        assertNotNull(giocatore);
    }

    @Test
    public void testGetCfu() {
        Giocatore giocatore = new Giocatore();
        assertEquals(0, giocatore.getCfu());
    }

    @Test
    public void testSetCfu() {
        Giocatore giocatore = new Giocatore();
        giocatore.setCfu(10);
        assertEquals(10, giocatore.getCfu());
    }
}
