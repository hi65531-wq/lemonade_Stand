
package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LemonStandTest {

    LemonStand stand;

    @BeforeEach
    void setUp() {
        stand = new LemonStand();
    }

    @Test
    void testStartingCash() {
        assertEquals(10000, stand.getCash());
    }

    @Test
    void testBuyIngredients() {
        assertTrue(stand.buyIngredients(10, 5));

        assertEquals(10, stand.getLemons());
        assertEquals(5, stand.getSugar());
        assertEquals(8750, stand.getCash());
    }

    @Test
    void testNotEnoughMoney() {
        assertFalse(stand.buyIngredients(200, 10));

        assertEquals(10000, stand.getCash());
        assertEquals(0, stand.getLemons());
    }

    @Test
    void testNegativeIngredients() {
        assertFalse(stand.buyIngredients(-5, 10));
        assertEquals(10000, stand.getCash());
    }

    @Test
    void testMakePitchers() {
        stand.buyIngredients(20, 10);
        stand.getRecipe().setRecipe(5, 2, 6);

        assertTrue(stand.makePitchers(3));

        assertEquals(3, stand.getPitchers());
        assertEquals(5, stand.getLemons());
        assertEquals(4, stand.getSugar());
    }

    @Test
    void testNotEnoughIngredients() {
        stand.buyIngredients(5, 2);
        stand.getRecipe().setRecipe(5, 2, 6);

        assertFalse(stand.makePitchers(3));
        assertEquals(0, stand.getPitchers());
    }

    @Test
    void testSetPrice() {
        assertTrue(stand.setPrice(150));
        assertEquals(150, stand.getCupPrice());
    }

    @Test
    void testNegativePrice() {
        assertFalse(stand.setPrice(-50));
        assertEquals(100, stand.getCupPrice());
    }

    @Test
    void testSellLemonade() {
        stand.buyIngredients(10, 4);
        stand.getRecipe().setRecipe(5, 2, 6);
        stand.makePitchers(2);

        stand.setPrice(100);

        int sold = stand.sellLemonade(10);

        assertEquals(10, sold);
        assertEquals(0, stand.getPitchers());
        assertEquals(9800, stand.getCash());
    }

    @Test
    void testSellOut() {
        stand.buyIngredients(5, 2);
        stand.getRecipe().setRecipe(5, 2, 6);
        stand.makePitchers(1);

        int sold = stand.sellLemonade(50);

        assertEquals(8, sold);
        assertEquals(0, stand.getPitchers());
    }
}
