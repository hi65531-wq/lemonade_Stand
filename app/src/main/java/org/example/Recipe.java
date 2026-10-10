package org.example;

public class Recipe {

    //ingredients
    private int lemons;
    private int sugar;
    private int water;

    public Recipe() {
        lemons = 0;
        sugar = 0;
        water = 0;
    }

    //setting recipe
    public void setRecipe(int lemons, int sugar, int water) {
        if (lemons >= 0 && sugar >= 0 && water >= 0) {
            this.lemons = lemons;
            this.sugar = sugar;
            this.water = water;
        }
    }

    //returning values
    public int getLemons() {
        return lemons;
    }

    public int getSugar() {
        return sugar;
    }

    public int getWater() {
        return water;
    }
}
