package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GT4500Test {

    // ---------- SINGLE ----------

    @Test
    void single_firstShotFiresPrimary() {
        GT4500 ship = new GT4500(1, 0.0, 1, 0.0);
        assertTrue(ship.fireTorpedo(FiringMode.SINGLE));
    }

    @Test
    void single_alternatesToSecondary() {
        GT4500 ship = new GT4500(1, 0.0, 1, 0.0);
        assertTrue(ship.fireTorpedo(FiringMode.SINGLE)); // primary
        assertTrue(ship.fireTorpedo(FiringMode.SINGLE)); // secondary
    }

    @Test
    void single_secondaryEmpty_firesPrimaryAgain() {
        GT4500 ship = new GT4500(2, 0.0, 0, 0.0);
        assertTrue(ship.fireTorpedo(FiringMode.SINGLE)); // primary
        assertTrue(ship.fireTorpedo(FiringMode.SINGLE)); // secondary empty -> primary again
    }

    @Test
    void single_secondaryEmpty_primaryNowEmpty_fails() {
        GT4500 ship = new GT4500(1, 0.0, 0, 0.0);
        assertTrue(ship.fireTorpedo(FiringMode.SINGLE));  // primary used up
        assertFalse(ship.fireTorpedo(FiringMode.SINGLE)); // both empty
    }

    @Test
    void single_primaryEmpty_firesSecondary() {
        GT4500 ship = new GT4500(0, 0.0, 1, 0.0);
        assertTrue(ship.fireTorpedo(FiringMode.SINGLE));
    }

    @Test
    void single_bothEmpty_fails() {
        GT4500 ship = new GT4500(0, 0.0, 0, 0.0);
        assertFalse(ship.fireTorpedo(FiringMode.SINGLE));
    }

    @Test
    void single_primaryFails_returnsFalse() {
        GT4500 ship = new GT4500(1, 1.0, 1, 0.0);
        assertFalse(ship.fireTorpedo(FiringMode.SINGLE));
    }

    // ---------- ALL ----------

    @Test
    void all_bothFire() {
        GT4500 ship = new GT4500(1, 0.0, 1, 0.0);
        assertTrue(ship.fireTorpedo(FiringMode.ALL));
    }

    @Test
    void all_primaryEmpty_secondaryFires() {
        GT4500 ship = new GT4500(0, 0.0, 1, 0.0);
        assertTrue(ship.fireTorpedo(FiringMode.ALL));
    }

    @Test
    void all_secondaryEmpty_primaryFires() {
        GT4500 ship = new GT4500(1, 0.0, 0, 0.0);
        assertTrue(ship.fireTorpedo(FiringMode.ALL));
    }

    @Test
    void all_bothEmpty_fails() {
        GT4500 ship = new GT4500(0, 0.0, 0, 0.0);
        assertFalse(ship.fireTorpedo(FiringMode.ALL));
    }

    @Test
    void all_primaryFails_secondarySucceeds() {
        GT4500 ship = new GT4500(1, 1.0, 1, 0.0);
        assertTrue(ship.fireTorpedo(FiringMode.ALL));
    }

    @Test
    void all_bothFail() {
        GT4500 ship = new GT4500(1, 1.0, 1, 1.0);
        assertFalse(ship.fireTorpedo(FiringMode.ALL));
    }

    // ---------- Laser ----------

    @Test
    void fireLaser_notImplemented_returnsFalse() {
        GT4500 ship = new GT4500();
        assertFalse(ship.fireLaser(FiringMode.SINGLE));
    }
}