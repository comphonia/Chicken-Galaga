package view;

import controller.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class MyWindow extends JFrame {

    public MyCanvas canvas;
    public JButton startButton;
    public static Leaderboard leaderboard;
    public JButton leaderboardButton;
    public JButton inGameLeaderboardButton = new JButton("leaderboard");
    public JButton quitButton;
    public JButton inGameQuitButton = new JButton("quit");
    static JLabel scoreLabel = new JLabel("Score:"+0);
    static JLabel playTimeLabel = new JLabel("Time:"+0);
    static JLabel playerLifeLabel = new JLabel("Lives:"+3);




    public void init() {
        setSize(700, 500);
        setLocation(300, 200);
        setTitle("Chicken Galaga");

        canvas = new MyCanvas();

        MouseEventListener listener = new MouseEventListener();
        canvas.addMouseListener(listener);
        canvas.addMouseMotionListener(listener);

        KeyEventListener keyEventListener = new KeyEventListener();
        canvas.addKeyListener(keyEventListener);
        canvas.setFocusable(true);

        var cp = getContentPane();

        JLabel logo = new JLabel();
        // read bg image
        BufferedImage logoImg = Helpers.ImageLoader.LoadImage("./assets/graphics/logo.png");
        logo.setHorizontalAlignment(JLabel.CENTER);
        logo.setIcon(new ImageIcon(logoImg));


        startButton = new JButton("START");
        startButton.setPreferredSize(new Dimension(130, 30));
        startButton.setFocusable(false);

        leaderboardButton = new JButton("LEADERBOARD");
        leaderboardButton.setPreferredSize(new Dimension(130, 30));
        leaderboardButton.setFocusable(false);

        inGameLeaderboardButton.setVisible(false);

        quitButton = new JButton("QUIT");
        quitButton.setPreferredSize(new Dimension(130, 30));
        quitButton.setFocusable(false);


        JPanel fixedPanel = new JPanel(new GridBagLayout());
        fixedPanel.setPreferredSize(getSize());
        fixedPanel.setBackground(new Color(0f, 0f, 0f, 0f));


        JPanel startPanel = new JPanel();
        startPanel.setBackground(new Color(0f, 0f, 0f, 0f));
        startPanel.setLayout(new GridLayout(3, 1, 5, 15));

        startPanel.add(startButton);
        startPanel.add(leaderboardButton);
        startPanel.add(quitButton);


        // canvas.setLayout(new GridLayout(2,1));
        fixedPanel.add(startPanel);
        canvas.setLayout(new GridLayout(2, 1, 0, 5));
        canvas.add(logo);
        canvas.add(fixedPanel);

        JPanel infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(100, 30));
        infoPanel.setBackground(Color.BLACK);
        infoPanel.setLayout(new GridLayout(1, 3, 5, 0));
        infoPanel.setAlignmentX(500);

        infoPanel.add(playerLifeLabel);
        infoPanel.add(playTimeLabel);
        infoPanel.add(scoreLabel);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        scoreLabel.setForeground(Color.GREEN);
        playTimeLabel.setHorizontalAlignment(JLabel.CENTER);
        playTimeLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        playTimeLabel.setForeground(Color.ORANGE);
        playerLifeLabel.setHorizontalAlignment(JLabel.CENTER);
        playerLifeLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        playerLifeLabel.setForeground(Color.CYAN);

        cp.add(BorderLayout.CENTER, canvas);

        // anonymous class, lambda
        startButton.addActionListener(e -> {
            if (!Main.running) {
                canvas.remove(fixedPanel);
                canvas.remove(logo);
                cp.add(BorderLayout.NORTH, infoPanel);
                JPanel buttonPanel = new JPanel();
                buttonPanel.add(inGameQuitButton);
                buttonPanel.add(inGameLeaderboardButton);
                cp.add(BorderLayout.SOUTH, buttonPanel);
                Main.running = true;
            }
        });
        quitButton.addActionListener(e -> {
            System.exit(0);
        });
        inGameQuitButton.addActionListener(e -> {
            System.exit(0);
        });
        leaderboardButton.addActionListener(e -> {
            openLeaderboard();
        });
        inGameLeaderboardButton.addActionListener(e -> {
            openLeaderboard();
        });

    }

    private static void openLeaderboard(){
        leaderboard = new Leaderboard();
        leaderboard.init();
        leaderboard.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        leaderboard.setVisible(true);
    }

    public static void updateScoreLabel(int score){
       scoreLabel.setText("Score:"+ score);
    }
    public static void updatePlayedTimeLabel(int playedTime) {playTimeLabel.setText("Time:"+ playedTime); }
    public static void updatePlayerLifeLabel(int playerLifeCount) {playerLifeLabel.setText("Lives:"+playerLifeCount);}

    public void showLeaderboardButton() {
        inGameLeaderboardButton.setVisible(true);
    }
}
