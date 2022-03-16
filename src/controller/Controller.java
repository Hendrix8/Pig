package controller;

import model.Player;

import javax.swing.*;

public class Controller {


    private Player p1, p2;
    private CHOICE roll = CHOICE.roll;
    private CHOICE hold = CHOICE.hold;


    public Controller() {
        // initializing the players
        p1 = new Player("Player 1");
        p2 = new Player("Player 2");

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
}
