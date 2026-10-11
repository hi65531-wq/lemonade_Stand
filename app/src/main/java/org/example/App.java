package org.example;

import java.util.Scanner;
import java.util.Random;

public class App {
    public static void main(String[] args_) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        LemonStand stand = new LemonStand();
        int day = 1;
        boolean playing = true;

        System.out.println("Welcome to My Lemonde Stand!");

        //game loop for each day
        while (playing) {
            System.out.println("Day " + day + ": Preparation Phase");

            boolean preparing = true;

            while (preparing) {

                displayMenu(stand);

                int choice = getInt(input,"What would you like to do? ");

                //menu options
                switch (choice) {

                    case 1:
                        purchaseIngredients(input, stand);
                        break;

                    case 2:
                        changeRecipe(input, stand);
                        break;

                    case 3:
                        setPrice(input, stand);
                        break;

                    case 4:
                        makeLemonade(input, stand);
                        break;

                    case 5:
                        preparing = false;
                    break;

                    default:
                        System.out.println("Invalid choice. " + "Please enter 1-5.");
                }
            }

            //selling phase
            System.out.println("Day " + day + ": Selling Phase");

            // generating num of customers
            int customers = random.nextInt(500) + 1;
            long availableCups = (long) stand.getPitchers() * 8;
            int sold = stand.sellLemonade(customers);

            // calcs how many cups were not sold
            long leftover = availableCups - sold;

            System.out.println("The customers are coming!");
            System.out.println(customers + " customers visited your stand today!");

            //how much money was earned
            System.out.println("You sold " + sold + " cups of lemonade for " 
            + formatMoney((long) sold * stand.getCupPrice()) + ".");

            //leftover lemonade is thrown away
            if (leftover > 0) {
                System.out.println("Unfortunately, " + leftover + 
                " cups of lemonade " + "were thrown away.");
            }

            //player's remaining cash
            System.out.println("You now have " + formatMoney(stand.getCash()) + ".");
            System.out.println("Let's get some sleep " + "and try again tomorrow!");

            //continuing or ending the game
            day++;

            System.out.println("Would you like to " + "continue playing?");
            System.out.println("1. Yes");
            System.out.println("2. No");

            int answer = getInt(input, "Your choice: ");

            while (answer != 1 && answer != 2) {
                System.out.println("Please enter 1 or 2.");
                answer = getInt(input, "Your choice: ");
            }
            if (answer == 2) {
                playing = false;
            }
        }
        System.out.println("Thanks for playing!");
        //game ends
        input.close();
    }

    //menu display
    public static void displayMenu(LemonStand stand) {

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        System.out.println("Lemons: " + stand.getLemons());
        System.out.println("Sugar (Cups): " + stand.getSugar());
        System.out.println("Water (Cups): Infinity");
        System.out.println("Current Pitchers: " + stand.getPitchers());
        System.out.println("Cash: " + formatMoney(stand.getCash()));

        //options
        System.out.println("1. Purchase Ingredients");
        System.out.println("2. Tweak the Recipe");
        System.out.println("3. Set the Price");
        System.out.println("4. Make Pitchers of Lemonade");
        System.out.println("5. Move Onto the Selling Phase");
    }

    //purchasing ingredients
    public static void purchaseIngredients(Scanner input, LemonStand stand) {

        System.out.println("Lemons are $1.00 each.");
        System.out.println("Sugar is $0.50 per cup.");

        //gets the amount of ingredients to purchase
        int lemons = getNonNegativeInt(input,"How many lemons do you want to buy? ");
        int sugar = getNonNegativeInt(input,"How many cups of sugar do you want to buy? ");

        //checking if purchase was successful
        if (stand.buyIngredients(lemons, sugar)) {
            System.out.println("Great! Transaction complete!");
        } 
        else {
            System.out.println("Unable to purchase ingredients. " + "Check your cash and quantities.");
        }
    }

    //change recipe
    public static void changeRecipe(Scanner input, LemonStand stand) {

        //gets the ingredients needed for one pitcher
        int lemons = getNonNegativeInt(input,"How many lemons per pitcher? ");
        int sugar = getNonNegativeInt(input,"How many cups of sugar per pitcher? ");
        int water = getNonNegativeInt(input,"How many cups of water per pitcher? ");

        //preventing a recipe with no ingredients
        if (lemons == 0 && sugar == 0 && water == 0) {
            System.out.println("Your recipe must contain " + "at least one ingredient.");
        } 
        else {
            //updates the recipe
            stand.getRecipe().setRecipe(lemons, sugar, water);
            System.out.println("Great! Your recipe is set!");
        }
    }

    //setting price
    public static void setPrice(Scanner input, LemonStand stand) {

        //stores price in cents
        int price = getPriceInCents(input);
        stand.setPrice(price);
        System.out.println("Awesome! Each cup costs " + formatMoney(stand.getCupPrice()));

        //possible earnings from one pitcher
        System.out.println("Each pitcher could earn " + formatMoney((long) price * 8) + ".");
    }

    //making lemonade
     public static void makeLemonade(Scanner input, LemonStand stand) {

        //number of pitchers the player wants
        int amount = getNonNegativeInt(input,"How many pitchers do you want to make? ");

        //checking
        if (stand.makePitchers(amount)) {
            System.out.println("Fantastic! You made " + amount + " pitchers of lemonade!");
        } 
        else {
            System.out.println("Unable to make pitchers. " + "Check your recipe and ingredients.");
        }
    }

    //input validation
     public static int getInt(Scanner input, String prompt) {

        while (true) {
            System.out.print(prompt);

            //checking if input is an integer
            if (input.hasNextInt()) {
                return input.nextInt();
            } 
            else {
                System.out.println("Invalid input. " + "Please enter a whole number.");

                //removes invalid input
                input.next();
            }
        }
    }

    //validation for positive integers
    public static int getNonNegativeInt(Scanner input, String prompt) {

        int number = getInt(input, prompt);

        //keeps asking if the number is negative
        while (number < 0) {
            System.out.println("Please enter 0 or greater.");
            number = getInt(input, prompt);
        }

        return number;
    }

    //price input
    public static int getPriceInCents(Scanner input) {

        while (true) {

            //getting the dollar amount
            int dollars = getNonNegativeInt(input,"Enter the dollar amount: ");

            //getting the cents amount
            int cents = getNonNegativeInt(input,"Enter the cents (0-99): ");
            while (cents > 99) {
                System.out.println("Cents must be between 0 and 99.");

                cents = getNonNegativeInt(input,"Enter the cents (0-99): ");
            }

            //preventing price from being too large
            if (dollars > (Integer.MAX_VALUE - cents) / 100) {
                System.out.println("That price is too large. " + "Please try again.");
            } 
            else {

                //converting everything into cents
                return dollars * 100 + cents;
            }
        }
    }

    //format money
    public static String formatMoney(long cents) {

        //seperating dollars from cents
        long dollars = cents / 100;
        long remainingCents = cents % 100;

        //formats the money to have two decimal places
        if (remainingCents < 10) {
            return "$" + dollars + ".0" + remainingCents;
        } 
        else {
            return "$" + dollars + "." + remainingCents;
        }
    }
}

    