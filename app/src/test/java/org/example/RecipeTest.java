package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RecipeTest {

    Recipe recipe;

    @BeforeEach
    void setUp() {
        recipe = new Recipe();
    }

    // Tests the starting recipe of 8 cups of water
    @Test
    void testDefaultRecipe() {

        assertEquals(0, recipe.getLemons());
        assertEquals(0, recipe.getSugar());
        assertEquals(8.0, recipe.getWater(), 0.001);
    }

    @Test
    void testSetRecipe() {

        boolean result = recipe.setRecipe(4, 2, 5);

        assertTrue(result);
        assertEquals(4, recipe.getLemons());
        assertEquals(2, recipe.getSugar());
        assertEquals(6.0, recipe.getWater(), 0.001);
    }

    @Test
    void testChangeRecipe() {

        recipe.setRecipe(4, 2, 5);
        boolean result = recipe.setRecipe(8, 4, 4);

        assertTrue(result);
        assertEquals(8, recipe.getLemons());
        assertEquals(4, recipe.getSugar());
        assertEquals(4.0, recipe.getWater(), 0.001);
    }

    @Test
    void testNegativeIngredients() {

        recipe.setRecipe(4, 2, 5);

        assertFalse(recipe.setRecipe(-3, 2, 5));
        assertFalse(recipe.setRecipe(4, -2, 5));
        assertFalse(recipe.setRecipe(4, 2, -5));
        assertEquals(4, recipe.getLemons());
        assertEquals(2, recipe.getSugar());
        assertEquals(6.0, recipe.getWater(), 0.001);
    }

    @Test
    void testZeroIngredients() {

        boolean result = recipe.setRecipe(0, 0, 0);

        assertTrue(result);
        assertEquals(0, recipe.getLemons());
        assertEquals(0, recipe.getSugar());
        assertEquals(8.0, recipe.getWater(), 0.001);
    }

    @Test
    void testRecipeTooLarge() {

        boolean result = recipe.setRecipe(4, 2, 7);

        assertFalse(result);
        assertEquals(0, recipe.getLemons());
        assertEquals(0, recipe.getSugar());
        assertEquals(8.0, recipe.getWater(), 0.001);
    }

    @Test
    void testExactlyEightCups() {

        boolean result = recipe.setRecipe(8, 4, 4);

        assertTrue(result);
        assertEquals(4.0, recipe.getWater(), 0.001);
    }

    @Test
    void testInvalidRecipeKeepsPreviousRecipe() {

        recipe.setRecipe(4, 2, 5);

        boolean result = recipe.setRecipe(4, 2, 7);

        assertFalse(result);
        assertEquals(4, recipe.getLemons());
        assertEquals(2, recipe.getSugar());
        assertEquals(6.0, recipe.getWater(), 0.001);
    }
}