package view;

import controller.CHOICE;
import controller.Controller;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Objects;

public class GUI extends JFrame {

    // declaring the variables that will be used
    //private enum STATE { MENU, GAME}; // these will be useful for making the menu
    //private STATE state = STATE.MENU; // TODO: Menu
    private final int diceWidth = 100, diceHeight = 100;
    private final int screenWidth = 800, screenHeight = 600;
    private final int diceX = screenWidth / 2 - diceWidth / 2,
            dice1Y = screenHeight - 2 * diceHeight,
            dice2Y = screenHeight - 11 / 2 * diceHeight;
    private JLabel score1, score2, turnScore1, turnScore2;
    private JLabel name1, name2;
    private JLabel turn;
    private JLabel won1;
    private JLabel won2;
    private final JButton p1Dice, p2Dice;
    private final JButton hold1, hold2;
    private ImageIcon icon;
    private URL imageURL;
    private ClassLoader cldr;
    private Controller game;

    public GUI(String name1, String name2) {

        // initializing components
        p1Dice = new JButton();
        p2Dice = new JButton();
        hold1 = new JButton();
        hold2 = new JButton();
        turnScore1 = new JLabel();
        turnScore2 = new JLabel();
        turn = new JLabel();
        score1 = new JLabel();
        score2 = new JLabel();
        won1 = new JLabel();
        won2 = new JLabel();
        cldr = this.getClass().getClassLoader();
        game = new Controller(name1, name2);
        initComponents();

        // second players buttons begin as disabled
        p2Dice.setEnabled(false);
        hold2.setEnabled(false);


        this.setVisible(true);
    }

    public void initComponents() {

        // initializing the main frame
        this.setTitle("PIG");
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.darkGray);
        this.setSize(screenWidth, screenHeight);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // initializing the score labels
        initScore(score1, dice1Y);
        initScore(score2, dice2Y);
        initTurnScore(turnScore1, dice1Y - 50);
        initTurnScore(turnScore2, dice2Y - 50);

        // initializing the dice buttons
        initDice(p1Dice, diceX, dice1Y, diceWidth, diceHeight, game.getP1(), game.getP2(),
                p2Dice, hold1, hold2, turnScore1, turn);
        initDice(p2Dice, diceX, dice2Y, diceWidth, diceHeight, game.getP2(), game.getP1(),
                p1Dice, hold2, hold1, turnScore2, turn);

        // initializing hold buttons
        initHold(hold1, dice1Y + 25, game.getP1(), game.getP2(), turnScore1, score1, p1Dice, p2Dice, hold1, hold2,
                turn, won1);
        initHold(hold2, dice2Y + 25, game.getP2(), game.getP1(), turnScore2, score2, p2Dice, p1Dice, hold2, hold1,
                turn, won2);

        // initializing name labels
        initNames(name1, dice1Y + 90, game.getP1().getName());
        initNames(name2, dice2Y - 90, game.getP2().getName());

        // initializing turn label
        turn.setBounds(50, screenHeight / 2 - 50, 200, 100);
        turn.setForeground(Color.black);
        turn.setFont(new Font("Times", Font.PLAIN, 30));
        turn.setText("Turn : Player 1");
        this.add(turn);

        // initializing won labels
        won1.setBounds(screenWidth / 2 - diceWidth + 20, screenHeight / 2 - diceHeight / 2, 300, 80);
        won1.setFont(new Font("Times", Font.PLAIN, 30));
        won1.setText(game.getP1().getName() + " Won!");
        won1.setVisible(false);
        this.add(won1);

        won2.setBounds(screenWidth / 2 - diceWidth + 20, screenHeight / 2 - diceHeight / 2, 300, 80);
        won2.setFont(new Font("Times", Font.PLAIN, 30));
        won2.setText(game.getP2().getName() + " Won!");
        won2.setVisible(false);
        this.add(won2);

    }

    private void initHold(JButton hold, int y, Player p, Player opp, JLabel turnScore, JLabel score,
                          JButton pDice, JButton oppDice, JButton pHold, JButton oppHold, JLabel turnLabel, JLabel won1) {
        hold.setBounds(diceX - 100, y, 80, 50);
        hold.setText("HOLD");
        hold.setEnabled(true);
        hold.addActionListener((e) -> {
            game.holdAction(p, opp, pDice, oppDice, pHold, oppHold, turnLabel, score, turnScore);
            game.logic(p, pDice, oppDice, pHold, oppHold); //TODO: make whoWon Show
            if (game.isGameOver()) {
                turnLabel.setText("GAME OVER");
                won1.setVisible(true);
            }
        });
        this.add(hold);
    }

    private void initTurnScore(JLabel turnScore, int y) {
        turnScore.setBounds(diceX + 2 * diceWidth, y, 200, diceHeight);
        turnScore.setFont(new Font("Times", Font.PLAIN, 25));
        turnScore.setForeground(Color.black);
        turnScore.setText("Turn Score : 0");
        this.add(turnScore);
    }

    private void initNames(JLabel name, int y, String nameText) {
        name = new JLabel();
        name.setBounds(diceX, y, 200, 100);
        name.setForeground(Color.black);
        name.setFont(new Font("Times", Font.PLAIN, 40));
        name.setText(nameText);
        this.add(name);
    }

    private void initScore(JLabel score, int y) {
        score.setBounds(diceX + 2 * diceWidth, y, 200, diceHeight);
        score.setFont(new Font("Times", Font.PLAIN, 40));
        score.setForeground(Color.black);
        score.setText("Score : 0");
        this.add(score);
    }

    private void initDice(JButton dice, int x, int y, int width, int height, Player p, Player opp,
                          JButton oppDice, JButton pHold, JButton oppHold, JLabel turnScore, JLabel turn) {
        dice.setBounds(x, y, width, height);
        dice.setIcon(new ImageIcon(Objects.requireNonNull(cldr.getResource("res/dice1.png"))));
        dice.setDisabledIcon(new ImageIcon(Objects.requireNonNull(cldr.getResource("res/dice1.png"))));
        dice.setEnabled(true);
        dice.addActionListener(new RollDiceListener(p, opp, dice, pHold, oppDice, oppHold, turnScore, turn));
        this.add(dice);
    }

    private class RollDiceListener implements ActionListener {
        Player p, opp;
        JButton pDice, oppDice, pHold, oppHold;
        JLabel turnScore, turn;

        public RollDiceListener(Player p, Player opp, JButton pDice, JButton pHold,
                                JButton oppDice, JButton oppHold, JLabel turnScore, JLabel turn) {
            this.p = p;
            this.opp = opp;
            this.pDice = pDice;
            this.pHold = pHold;
            this.oppDice = oppDice;
            this.oppHold = oppHold;
            this.turnScore = turnScore;
            this.turn = turn;

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            game.rollAction(p, opp, pDice, oppDice, pHold, oppHold, turnScore, turn);
        }
    }
}
