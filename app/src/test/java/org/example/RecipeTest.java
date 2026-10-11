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

    @Test
    void testDefaultRecipe() {

        assertEquals(0, recipe.getLemons());
        assertEquals(0, recipe.getSugar());
        assertEquals(0, recipe.getWater());
    }

    @Test
    void testSetRecipe() {

        recipe.setRecipe(5, 2, 6);

        assertEquals(5, recipe.getLemons());
        assertEquals(2, recipe.getSugar());
        assertEquals(6, recipe.getWater());
    }

    @Test
    void testChangeRecipe() {

        recipe.setRecipe(5, 2, 6);
        recipe.setRecipe(3, 4, 8);

        assertEquals(3, recipe.getLemons());
        assertEquals(4, recipe.getSugar());
        assertEquals(8, recipe.getWater());
    }

    @Test
    void testNegativeIngredients() {

        recipe.setRecipe(5, 2, 6);
        recipe.setRecipe(-3, 2, 6);

        assertEquals(5, recipe.getLemons());
        assertEquals(2, recipe.getSugar());
        assertEquals(6, recipe.getWater());
    }

    @Test
    void testZeroIngredients() {

        recipe.setRecipe(0, 0, 0);

        assertEquals(0, recipe.getLemons());
        assertEquals(0, recipe.getSugar());
        assertEquals(0, recipe.getWater());
    }
}
