package model;

import controller.CHOICE;

public class Player {
    private int score, turnScore;
    private Dice dice;
    private String name;
    private CHOICE choice;
    private boolean turn;
    private boolean firstRoll = true;

    public Player(String name) {
        this.dice = new Dice();
        this.name = name;
        this.score = 0;
        this.turnScore = 0;
    }

    public int roll() {
        return dice.rollDice();
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setChoice(CHOICE choice) {
        this.choice = choice;
    }

    public CHOICE getChoice() {
        return choice;
    }

    public void setTurnScore(int turnScore) {
        this.turnScore = turnScore;
    }

    public int getTurnScore() {
        return turnScore;
    }

    public boolean hasTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public void setFirstRoll(boolean firstRoll) {
        this.firstRoll = firstRoll;
    }

    public boolean isFirstRoll() {
        return firstRoll;
    }

}
