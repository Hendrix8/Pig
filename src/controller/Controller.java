package controller;

import model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Controller {


    private Player p1, p2;
    private CHOICE roll = CHOICE.roll;
    private CHOICE hold = CHOICE.hold;
    private int p1Roll = 0,p2Roll = 0; // for deciding who plays first
    private final int screenWidth = 800, screenHeight = 600;
    private final int diceWidth = 100, diceHeight = 100;
    private boolean gameOver = false;
    private boolean cpuON = false;
    private ClassLoader cldr = this.getClass().getClassLoader();


    public Controller() {
        p1 = new Player("Player 1");
        p2 = new Player("Player 2");
    }

    public void initPlayers(String name1, String name2) {
        // initializing the players
        if (name1 == null || name1.equals("")) {
            p1.setName("Player 1");
        }
        else {
            p1.setName(name1);
        }
        if (name2 == null || name2.equals("")) {
            p2.setName("Player 2");
        }
        else {
            p2.setName(name2);

        }
        p1.setTurn(true);
        p2.setTurn(false);
        p1.setFirstRoll(true);
        p2.setFirstRoll(false);
    }

    public void rollAction(Player p,Player opp, JButton pDice,JButton oppDice,
                           JButton pHold,JButton oppHold, JLabel turnScoreLbl, JLabel turn){

        p.setChoice(CHOICE.roll); // if player presses button for dices then it is implied that the choice is to roll
        int roll = p.roll();
        pDice.setIcon(new ImageIcon(Objects.requireNonNull(cldr.getResource("res/dice" + roll + ".png"))));
        pDice.setDisabledIcon(new ImageIcon(Objects.requireNonNull(cldr.getResource("res/dice" + roll + ".png"))));

        if (roll == 1) {
            p.setTurnScore(0);
            turnScoreLbl.setText("Turn Score : "+ p.getTurnScore());
            turnChange(p, opp,pDice,oppDice,pHold,oppHold, turn);
        }
        else {
            p.setTurnScore(p.getTurnScore() + roll);
            turnScoreLbl.setText("Turn Score : "+ p.getTurnScore());

        }


    }

    public void turnChange(Player p1, Player p2, JButton p1Dice, JButton p2Dice, JButton p1Hold, JButton p2Hold,
                           JLabel turnLabel) {
        if (p1.hasTurn()) {
            p1.setTurn(false);
            p1Dice.setEnabled(false);
            p1Hold.setEnabled(false);

            p2.setTurn(true);
            p2Dice.setEnabled(true);
            p2Hold.setEnabled(true);
            turnLabel.setText("Turn : " + p2.getName());

        }
        else {
            p2.setTurn(false);
            p2Dice.setEnabled(false);
            p2Hold.setEnabled(false);

            p1.setTurn(true);
            p1Dice.setEnabled(true);
            p1Hold.setEnabled(true);
            turnLabel.setText("Turn : " + p1.getName());

        }
    }

    public void holdAction(Player p1, Player p2, JButton p1Dice, JButton p2Dice, JButton p1Hold, JButton p2Hold,
                           JLabel turnLabel,JLabel p1Score,JLabel p2Score,
                           JLabel p1TurnScore,JLabel p2TurnScore, boolean cpuON) {
        if (cpuON) {
            if (p2.isCPU()) {
                System.out.println("p2 is cpu");
                boolean playing = true;

                //The first time always roll
                double randomChoice = 0.5;

                while (playing) {

                    if (randomChoice <= 0.3) { // chooses to hold 30 % of the time and roll 70% of the time
                        System.out.println("p2 holds");
                        p2.setChoice(CHOICE.hold);
                        p2.setScore(p2.getScore() + p2.getTurnScore());
                        p2.setTurnScore(0);

                        p2Score.setText("Score : " + p2.getScore());
                        p2TurnScore.setText("Turn Score : " + p2.getTurnScore());

                        turnChange(p1, p2,p1Dice, p2Dice, p1Hold, p2Hold, turnLabel);

                        playing = false;
                    }
                    else {
                        try {
                            System.out.println("p2 rolls");
                            p2.setChoice(CHOICE.roll);
                            //TODO: fix here
                            int p2Roll = p2.roll();
                            p2Dice.setIcon(new ImageIcon(Objects.requireNonNull(cldr.getResource("res/dice" + p2Roll + ".png"))));
                            p2Dice.setDisabledIcon(new ImageIcon(Objects.requireNonNull(cldr.getResource("res/dice" + p2Roll + ".png"))));
                            p2.setTurnScore(p2.getTurnScore() + p2Roll);
                            p2TurnScore.setText("Turn Score : " + p2.getTurnScore());
                            Thread.sleep(500);

                            randomChoice = Math.random(); // updating choice

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }

            }
        }
        else {
            p1.setChoice(CHOICE.hold);
            p1.setScore(p1.getScore() + p1.getTurnScore());
            p1.setTurnScore(0);

            p1Score.setText("Score : " + p1.getScore());
            p1TurnScore.setText("Turn Score : " + p1.getTurnScore());

            turnChange(p1, p2,p1Dice, p2Dice, p1Hold, p2Hold, turnLabel);
        }

    }

    public void logic(Player p, JButton pDice,JButton oppDice,
                      JButton pHold,JButton oppHold) {
        if (p.getScore() >= 100){
            // disable buttons after game ended
            pDice.setEnabled(false);
            oppDice.setEnabled(false);
            pHold.setEnabled(false);
            oppHold.setEnabled(false);

            this.gameOver = true;


        }

    }


    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public Player getP1() {
        return this.p1;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }

    public Player getP2() {
        return p2;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setCpuON(boolean cpuON) {
        this.cpuON = cpuON;
    }

    public boolean isCpuON() {
        return cpuON;
    }
}
