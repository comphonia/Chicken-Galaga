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
    public JButton quitButton;

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

        cp.add(BorderLayout.CENTER, canvas);
        //cp.add(BorderLayout.SOUTH, startPanel);

        // anonymous class, lambda
        startButton.addActionListener(e -> {
            if (!Main.running) {
                Main.running = true;
                canvas.remove(fixedPanel);
                canvas.remove(logo);
            }
        });
        quitButton.addActionListener(e -> {
            System.exit(0);
        });
        leaderboardButton.addActionListener(e -> {
            openLeaderboard();
        });

    }

    private static void openLeaderboard(){
        leaderboard = new Leaderboard();
        leaderboard.init();
        leaderboard.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        leaderboard.setVisible(true);
    }

}
