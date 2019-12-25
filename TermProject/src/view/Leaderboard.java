package view;

import controller.Helpers;
import controller.KeyEventListener;
import controller.Main;
import controller.MouseEventListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Leaderboard extends JFrame {
    public MyCanvas canvas;
    private JTable table;

    public void init() {
        setSize(700, 500);
        setLocation(300, 200);

        canvas = new MyCanvas();
        var cp = getContentPane();
        // Data to be displayed in the JTable

        // Column Names
        String[] columnNames = { "Rank", "Time", "Score" };

        // Initializing the JTable
        table = new JTable(Main.leaderboardData, columnNames);
        table.setOpaque(false);
        table.setRowHeight(40);
        table.setRowMargin(4);
        table.setFillsViewportHeight(true);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(table);
        sp.setOpaque(false);
        sp.setBorder(BorderFactory.createEmptyBorder());
        sp.getViewport().setBackground(new Color(0f, 0f, 0f, 0f));

        JLabel label = new JLabel("Hall of Fame");

        JPanel fixedPanel = new JPanel(new GridBagLayout());
        fixedPanel.setPreferredSize(getSize());
        fixedPanel.setBackground(new Color(0f, 0f, 0f, 0f));

        fixedPanel.add(sp);

        canvas.add(fixedPanel);
       /// canvas.add(sp);
        cp.add(BorderLayout.CENTER, canvas);
    }

}
