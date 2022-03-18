package view;

import controller.Controller;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GUI extends JFrame {

    // declaring the variables that will be used
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
    private JLabel menu;
    private final JButton p1Dice, p2Dice;
    private final JButton hold1, hold2;
    private final JButton newGame;
    private JButton playFriend, cpu;
    private ClassLoader cldr;
    private Controller game;
    private ImageIcon logo;

    public GUI() {

        // initializing components
        p1Dice = new JButton();
        p2Dice = new JButton();
        hold1 = new JButton();
        hold2 = new JButton();
        newGame = new JButton();
        turnScore1 = new JLabel();
        turnScore2 = new JLabel();
        turn = new JLabel();
        score1 = new JLabel();
        score2 = new JLabel();
        name1 = new JLabel();
        name2 = new JLabel();
        won1 = new JLabel();
        won2 = new JLabel();
        cldr = this.getClass().getClassLoader();
        game = new Controller();
        logo = new ImageIcon(Objects.requireNonNull(cldr.getResource("res/pig.jpg"))); // TODO: fix this so that it can be put through cldr (for jar export)
        menu = new JLabel();
        cpu = new JButton();
        playFriend = new JButton();

        // initializing the main frame
        this.setTitle("PIG");
        this.setLayout(null);
        this.setIconImage(logo.getImage());
        this.setResizable(false);
        this.getContentPane().setBackground(Color.darkGray);
        this.setSize(screenWidth, screenHeight);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);


        menu();
        startGame();
        this.setVisible(true);
    }

    public void menu() {
        // initializing menu
        menu.setText("MENU");
        menu.setFont(new Font("Times", Font.PLAIN, 50));
        menu.setBounds(screenWidth / 2 - 80, screenHeight / 2 - 300, 200, 200);
        this.add(menu);

        playFriend.setText("Play with a Friend");
        playFriend.setBounds(screenWidth / 2 - 200, screenHeight / 2 - 160, 400, 50);
        playFriend.setFont(new Font("Times", Font.PLAIN, 35));
        playFriend.setOpaque(false);
        playFriend.setContentAreaFilled(false);
        playFriend.setBorderPainted(false);
        playFriend.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        playFriend.addActionListener((e -> {
            game.setCpuON(false);
            String p1Name = JOptionPane.showInputDialog(this, "Player 1 name : ");
            String p2Name = JOptionPane.showInputDialog(this, "Player 2 name : ");
            game.getP1().setName(p1Name);
            game.getP2().setName(p2Name);
            game.initPlayers(game.getP1().getName(), game.getP2().getName());
            name1.setText(game.getP1().getName());
            name2.setText(game.getP2().getName());

            // initializing name labels
            initNames(name1, dice1Y + 90, game.getP1().getName());
            initNames(name2, dice2Y - 90, game.getP2().getName());
            // giving won labels the names
            won1.setText(game.getP1().getName() + " Won!");
            won2.setText(game.getP2().getName() + " Won!");

            menu.setVisible(false);
            cpu.setVisible(false);
            cpu.setEnabled(false);
            playFriend.setVisible(false);
            playFriend.setEnabled(false);
            setVisibleComponents(true);
        }));
        this.add(playFriend);

        cpu.setText("Play vs CPU");
        cpu.setBounds(screenWidth / 2 - 200, screenHeight / 2 - 100, 400, 50);
        cpu.setFont(new Font("Times", Font.PLAIN, 35));
        cpu.setOpaque(false);
        cpu.setContentAreaFilled(false);
        cpu.setBorderPainted(false);
        cpu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cpu.addActionListener((e -> {
            game.setCpuON(true);
            String p1Name = JOptionPane.showInputDialog(this, "What is your name ? : ");
            game.getP1().setName(p1Name);
            game.getP2().setName("CPU");
            game.getP2().setCPU(true);
            game.initPlayers(game.getP1().getName(), game.getP2().getName());
            name1.setText(game.getP1().getName());
            name2.setText("CPU");

            // initializing name labels
            initNames(name1, dice1Y + 90, game.getP1().getName());
            initNames(name2, dice2Y - 90, game.getP2().getName());
            // giving won labels the names
            won1.setText(game.getP1().getName() + " Won!");
            won2.setText(game.getP2().getName() + " Won!");

            menu.setVisible(false);
            cpu.setVisible(false);
            cpu.setEnabled(false);
            playFriend.setVisible(false);
            playFriend.setEnabled(false);
            setVisibleComponents(true);
            hold2.setEnabled(false);
            hold2.setVisible(false);
            p2Dice.setEnabled(false);

        }));
        this.add(cpu);
    }

    public void startGame() {
        initComponents();
        // second players buttons begin as disabled
        p2Dice.setEnabled(false);
        hold2.setEnabled(false);

        // setting everything not visible for the menu to initialize
        setVisibleComponents(false);
    }

    public void setVisibleComponents(boolean visible) {
        if (visible) {
            turn.setVisible(true);
            turnScore1.setVisible(true);
            turnScore2.setVisible(true);
            score1.setVisible(true);
            score2.setVisible(true);
            p1Dice.setVisible(true);
            p2Dice.setVisible(true);
            hold1.setVisible(true);
            hold2.setVisible(true);
            name1.setVisible(true);
            name2.setVisible(true);
            newGame.setVisible(true);

        } else {
            turn.setVisible(false);
            turnScore1.setVisible(false);
            turnScore2.setVisible(false);
            score1.setVisible(false);
            score2.setVisible(false);
            p1Dice.setVisible(false);
            p2Dice.setVisible(false);
            hold1.setVisible(false);
            hold2.setVisible(false);
            name1.setVisible(false);
            name2.setVisible(false);
            newGame.setVisible(false);
        }
    }

    public void initComponents() {


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
        initHold(hold1, dice1Y + 25, game.getP1(), game.getP2(), turnScore1,turnScore2 ,score1,score2, p1Dice, p2Dice, hold1, hold2,
                turn, won1);
        initHold(hold2, dice2Y + 25, game.getP2(), game.getP1(), turnScore2,turnScore1, score2,score1, p2Dice, p1Dice, hold2, hold1,
                turn, won2);

        // initializing turn label
        turn.setBounds(50, screenHeight / 2 - 50, 200, 100);
        turn.setForeground(Color.black);
        turn.setFont(new Font("Times", Font.PLAIN, 30));
        turn.setText("Turn : Player 1");
        this.add(turn);

        // initializing won labels
        won1.setBounds(screenWidth / 2 - diceWidth + 20, screenHeight / 2 - diceHeight / 2, 300, 80);
        won1.setFont(new Font("Times", Font.PLAIN, 30));
        won1.setVisible(false);
        this.add(won1);

        won2.setBounds(screenWidth / 2 - diceWidth + 20, screenHeight / 2 - diceHeight / 2, 300, 80);
        won2.setFont(new Font("Times", Font.PLAIN, 30));
        won2.setVisible(false);
        this.add(won2);

        newGame.setBounds(35, dice2Y + 130, 200, 30);
        newGame.setFont(new Font("Times", Font.PLAIN, 25));
        newGame.setText("NEW GAME");
        newGame.setOpaque(false); // Making the
        newGame.setContentAreaFilled(false); // button have a
        newGame.setBorderPainted(false); // transparent bg
        newGame.addActionListener((e -> {
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(cldr.getResource("res/pig.jpg")));
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to start a new game ?",
                    "New Game", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, icon);
            if (option == 0) {

                if (game.getP1().hasWon()) {
                    p2Dice.setEnabled(false);
                    hold2.setEnabled(false);
                    p1Dice.setEnabled(true);
                    hold1.setEnabled(true);
                    game.getP1().setWon(false);
                    game.getP1().setTurn(true);
                    game.getP2().setTurn(false);
                    turn.setText("Turn : " + game.getP1().getName());
                } else if (game.getP2().hasWon()) {
                    p1Dice.setEnabled(false);
                    hold1.setEnabled(false);
                    p2Dice.setEnabled(true);
                    hold2.setEnabled(true);
                    game.getP2().setWon(false);
                    game.getP2().setTurn(true);
                    game.getP1().setTurn(false);
                    turn.setText("Turn : " + game.getP2().getName());
                }

                game.getP1().setScore(0);
                game.getP2().setScore(0);
                game.getP1().setTurnScore(0);
                game.getP2().setTurnScore(0);
                turnScore1.setText("Turn Score : 0");
                turnScore2.setText("Turn Score : 0");
                score1.setText("Score : 0");
                score2.setText("Score : 0");
                won1.setVisible(false);
                won2.setVisible(false);
                p1Dice.setIcon(new ImageIcon(Objects.requireNonNull(cldr.getResource("res/dice1.png"))));
                p1Dice.setDisabledIcon(new ImageIcon(Objects.requireNonNull(cldr.getResource("res/dice1.png"))));
                p2Dice.setIcon(new ImageIcon(Objects.requireNonNull(cldr.getResource("res/dice1.png"))));
                p2Dice.setDisabledIcon(new ImageIcon(Objects.requireNonNull(cldr.getResource("res/dice1.png"))));
                game.setGameOver(false);
            }
        }));
        this.add(newGame);

    }

    private void initHold(JButton hold, int y, Player p, Player opp, JLabel turnScore,JLabel cpuTscore, JLabel score,
                          JLabel cpuScore,JButton pDice, JButton oppDice, JButton pHold,
                          JButton oppHold, JLabel turnLabel, JLabel won1) {
        hold.setBounds(diceX - 100, y, 80, 50);
        hold.setText("HOLD");
        hold.setEnabled(true);
        hold.addActionListener((e) -> {
            game.holdAction(p, opp, pDice, oppDice, pHold, oppHold, turnLabel, score,cpuScore, turnScore,cpuTscore, game.isCpuON());
            game.logic(p, pDice, oppDice, pHold, oppHold);
            if (game.isGameOver()) {
                turnLabel.setText("GAME OVER");
                won1.setVisible(true);
                p.setWon(true);
                newGame.setEnabled(true);
                newGame.setVisible(true);
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
