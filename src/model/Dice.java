package model;

import java.util.Random;

public class Dice {

    private int outcome;
    Random rand = new Random();

    public int rollDice() {
        outcome = rand.nextInt(6) + 1;
        return outcome;
    }
}
