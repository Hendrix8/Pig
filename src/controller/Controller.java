package controller;

import model.Player;

import javax.swing.*;
import java.awt.*;

public class Controller {


    private Player p1, p2;
    private CHOICE roll = CHOICE.roll;
    private CHOICE hold = CHOICE.hold;
    private int p1Roll = 0,p2Roll = 0; // for deciding who plays first
    private final int screenWidth = 800, screenHeight = 600;
    private final int diceWidth = 100, diceHeight = 100;
    private boolean gameOver = false;

    public Controller(String name1, String name2) {
        // initializing the players
        if (name1 == null || name1.equals("")) {
            p1 = new Player("Player 1");
        }
        else {
            p1 = new Player(name1);
        }
        if (name2 == null || name2.equals("")) {
            p2 = new Player("Player 2");
        }
        else {
            p2 = new Player(name2);

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
        pDice.setIcon(new ImageIcon("src/res/dice" + roll + ".png"));
        pDice.setDisabledIcon(new ImageIcon("src/res/dice" + roll + ".png"));

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
                           JLabel turnLabel,JLabel p1Score, JLabel p1TurnScore) {
        p1.setChoice(CHOICE.hold);
        p1.setScore(p1.getScore() + p1.getTurnScore());
        p1.setTurnScore(0);

        p1Score.setText("Score : " + p1.getScore());
        p1TurnScore.setText("Turn Score : " + p1.getTurnScore());

        turnChange(p1, p2,p1Dice, p2Dice, p1Hold, p2Hold, turnLabel);
    }

    public void logic(Player p, JButton pDice,JButton oppDice,
                      JButton pHold,JButton oppHold) {
        if (p.getScore() >= 4){
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
}
