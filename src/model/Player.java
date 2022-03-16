package model;

public class Player {
    private int score;
    private Dice dice;
    private String name;

    Player(String name) {
        dice = new Dice();
        this.name = name;
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
}
