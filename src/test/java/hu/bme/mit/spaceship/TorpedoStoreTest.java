package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    // ---------- constructor / getters ----------

    @Test
    void constructor_setsTorpedoCount() {
        TorpedoStore store = new TorpedoStore(5, 0.0);
        assertEquals(5, store.getTorpedoCount());
    }

    @Test
    void isEmpty_falseWhenStoreHasTorpedoes() {
        TorpedoStore store = new TorpedoStore(1, 0.0);
        assertFalse(store.isEmpty());
    }

    @Test
    void isEmpty_trueWhenStoreHasNoTorpedoes() {
        TorpedoStore store = new TorpedoStore(0, 0.0);
        assertTrue(store.isEmpty());
    }

    // ---------- successful firing (failure rate 0.0 = never fails) ----------

    @Test
    void fire_Success() {
        TorpedoStore store = new TorpedoStore(1, 0.0);

        boolean result = store.fire(1);

        assertEquals(true, result);
    }

    @Test
    void fire_success_decreasesCountByOne() {
        TorpedoStore store = new TorpedoStore(3, 0.0);

        store.fire(1);

        assertEquals(2, store.getTorpedoCount());
    }

    @Test
    void fire_multipleTorpedoes_decreasesCountByThatNumber() {
        TorpedoStore store = new TorpedoStore(5, 0.0);

        boolean result = store.fire(3);

        assertTrue(result);
        assertEquals(2, store.getTorpedoCount());
    }

    @Test
    void fire_allTorpedoes_storeBecomesEmpty() {
        TorpedoStore store = new TorpedoStore(2, 0.0);

        assertTrue(store.fire(2));

        assertEquals(0, store.getTorpedoCount());
        assertTrue(store.isEmpty());
    }

    // ---------- failed firing (failure rate 1.0 = always fails) ----------

    @Test
    void fire_failure_returnsFalse() {
        TorpedoStore store = new TorpedoStore(1, 1.0);

        assertFalse(store.fire(1));
    }

    @Test
    void fire_failure_doesNotChangeCount() {
        TorpedoStore store = new TorpedoStore(3, 1.0);

        store.fire(1);

        assertEquals(3, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }

    // ---------- invalid arguments ----------

    @Test
    void fire_zeroTorpedoes_throws() {
        TorpedoStore store = new TorpedoStore(3, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
    }

    @Test
    void fire_negativeNumber_throws() {
        TorpedoStore store = new TorpedoStore(3, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(-1));
    }

    @Test
    void fire_moreThanAvailable_throws() {
        TorpedoStore store = new TorpedoStore(3, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(4));
    }

    @Test
    void fire_fromEmptyStore_throws() {
        TorpedoStore store = new TorpedoStore(0, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(1));
    }

    @Test
    void fire_invalidArgument_doesNotChangeCount() {
        TorpedoStore store = new TorpedoStore(3, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(4));

        assertEquals(3, store.getTorpedoCount());
    }
}