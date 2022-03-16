package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class GUI extends JFrame{

    // declaring the variables that will be used
    private final int diceWidth = 100, diceHeight = 100;
    private final int screenWidth = 800, screenHeight = 600;
    private final int diceX = screenWidth / 2 - diceWidth / 2,
            dice1Y = screenHeight - 2 * diceHeight,
            dice2Y = screenHeight - 11/2 * diceHeight;
    private JLabel score1, score2;
    private JLabel name1, name2;
    private JLabel turn;
    private final JButton p1Dice, p2Dice;
    private ImageIcon icon;
    private URL imageURL;
    private ClassLoader cldr;

    public GUI() {

        // initializing components
        p1Dice = new JButton();
        p2Dice = new JButton();
        cldr = this.getClass().getClassLoader();

        initComponents();
        this.setVisible(true);
    }

    public void initComponents() {

        // initializing the main frame
        this.setTitle("PIG");
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.darkGray);
        this.setSize(screenWidth,screenHeight);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // initializing the dice buttons
        initDice(p1Dice, diceX, dice1Y, diceWidth, diceHeight);
        initDice(p2Dice,diceX, dice2Y, diceWidth,diceHeight);

        // initializing the score labels
        initScore(score1, dice1Y);
        initScore(score2, dice2Y);

        // initializing name labels
        initNames(name1,dice1Y + 90 , "Player 1");
        initNames(name2, dice2Y - 90, "Player 2");

        // initializing turn label
        turn = new JLabel();
        turn.setBounds(50, screenHeight / 2 - 50, 200,100);
        turn.setForeground(Color.black);
        turn.setFont(new Font("Times", Font.PLAIN, 30));
        turn.setText("Turn : Player 1");
        this.add(turn);


    }

    private void initNames(JLabel name, int y,String nameText) {
        name = new JLabel();
        name.setBounds(diceX, y, 200,100);
        name.setForeground(Color.black);
        name.setFont(new Font("Times", Font.PLAIN, 40));
        name.setText(nameText);
        this.add(name);
    }

    private void initScore(JLabel score, int y) {
        score = new JLabel();
        score.setBounds(diceX + 2 * diceWidth, y, 200, diceHeight);
        score.setFont(new Font("Times", Font.PLAIN, 40));
        score.setForeground(Color.black);
        score.setText("Score : 0");
        this.add(score);
    }

    private void initDice(JButton dice, int x, int y , int width, int height) {
        dice.setBounds(x, y, width, height);
        dice.setIcon(new ImageIcon(Objects.requireNonNull(cldr.getResource("res/dice1.png"))));
        dice.setDisabledIcon(new ImageIcon(Objects.requireNonNull(cldr.getResource("res/dice1.png"))));
        dice.setEnabled(true);
        this.add(dice);
    }

    public static void resize(String path, String newPath, int width, int height) throws IOException {
        File file = new File(path);
        BufferedImage image = ImageIO.read(file);

        BufferedImage newImage = new BufferedImage(width, height, image.getType());

        Graphics2D g2d = newImage.createGraphics();
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();

        String name = newPath.substring(newPath.lastIndexOf(".") + 1);
        ImageIO.write(newImage, name, new File(newPath));
    }


}
