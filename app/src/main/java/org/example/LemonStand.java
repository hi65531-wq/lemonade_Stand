package org.example;

public class LemonStand {
    //variables
    private Recipe recipe;
    private int cash;
    private int lemons;
    private int sugar;
    private int pitchers;
    private int cupPrice;

    //setting prices
    public LemonStand() {
        recipe = new Recipe();
        cash = 10000;
        lemons = 0;
        sugar = 0;
        pitchers = 0;
        cupPrice = 0;
    }

    //returining values 
    public Recipe getRecipe() {
        return recipe;
    }
    
    public int getCash() {
        return cash;
    }

    public int getLemons() {
        return lemons;
    }

    public int getSugar() {
        return sugar;
    }

    public int getPitchers() {
        return pitchers;
    }

    public int getCupPrice() {
        return cupPrice;
    }

    //buying ingredients and checking if there is enough money to buy them
    public boolean buyIngredients(int lemonsToBuy, int sugarToBuy) {

        if (lemonsToBuy < 0 || sugarToBuy < 0) {
            return false;
        }

        //cacls total cost in cents 
        long cost = (long) lemonsToBuy * 100
                  + (long) sugarToBuy * 50;

        if (cost > cash) {
            return false;
        }

        //making sure inventory doesnt get too large 
        if ((long) lemons + lemonsToBuy > Integer.MAX_VALUE
                || (long) sugar + sugarToBuy > Integer.MAX_VALUE) {
            return false;
        }

        //subtracting cost from cash
        cash -= (int) cost;

        //adding ingredients to inventory
        lemons += lemonsToBuy;
        sugar += sugarToBuy;

        return true;
    }

    //creating the lemonade with available ingredinets 
    public boolean makePitchers(int amount) {

        if (amount <= 0) {
            return false;
        }

        int lemonsNeeded = recipe.getLemons();
        int sugarNeeded = recipe.getSugar();
        int waterNeeded = recipe.getWater();

        //checking to see if there is enough ingredients to make the lemonade
        if (lemonsNeeded == 0 && sugarNeeded == 0
                && waterNeeded == 0) {
            return false;
        }

        long totalLemons = (long) lemonsNeeded * amount;
        long totalSugar = (long) sugarNeeded * amount;

        if (totalLemons > lemons || totalSugar > sugar) {
            return false;
        }

        if ((long) pitchers + amount > Integer.MAX_VALUE) {
            return false;
        }

        //removes used ingredients
        lemons -= (int) totalLemons;
        sugar -= (int) totalSugar;
        pitchers += amount;

        return true;
    }

    //setting price
    public boolean setPrice(int price) {

        if (price < 0) {
            return false;
        }

        cupPrice = price;
        return true;
    }

    //selling the lemonade
    public int sellLemonade(int customers) {

        if (customers < 0) {
            return 0;
        }

        long availableCups = (long) pitchers * 8;
        int cupsSold = (int) Math.min(availableCups, customers);

        long earnings = (long) cupsSold * cupPrice;

        //preventing cash overflow
        if (earnings > Integer.MAX_VALUE - (long) cash) {
            return 0;
        }

        //adding earnings 
        cash += (int) earnings;

        pitchers = 0;

        return cupsSold;
    }
}
