package org.example;

public class Recipe {

    //ingredients
    private int lemons;
    private int sugar;
    private double water;

    public Recipe() {
        lemons = 0;
        sugar = 0;
        water = 8.0;
    }

    //setting recipe
    public boolean setRecipe(int lemons, int sugar, double water) {
        
        //no negatives 
        if (lemons < 0 || sugar < 0 || water < 0) {
            return false;
        }

        //calculating the total volume
        double totalVolume = lemons * 0.25 + sugar * 0.5 + water;

        //rejects recipes larger than 8 cups
        if (totalVolume > 8.0) {
            return false;
        }

        //fills the remaining space with water
        double extraWater = 8.0 - totalVolume;

        this.lemons = lemons;
        this.sugar = sugar;
        this.water = water + extraWater;

        return true;
    }

    //returning values
    public int getLemons() {
        return lemons;
    }

    public int getSugar() {
        return sugar;
    }

    public double getWater() {
        return water;
    }
}
