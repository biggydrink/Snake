package com.andrew;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


/** A separate GUI that can be made visible by the user, so they can choose custom options in the game */
public class SettingsGUI extends JFrame {

    private JPanel rootPanel;

    private JLabel adjustSquareSizeLabel;
    private JComboBox<String> adjustSquareSizeComboBox;
    public int squareSize = 50;

    private JLabel adjustGameSpeedLabel;
    private JComboBox<String> adjustGameSpeedComboBox;
    public int gameSpeed;

    private JLabel adjustWallCountLabel;
    private JComboBox<String> adjustWallCountComboBox;
    public int wallCount;

    private JLabel adjustSnakeGrowthRateLabel;
    private JComboBox<String> adjustSnakeGrowthRateComboBox;
    public int snakeGrowthRate;

    private JLabel adjustLivesCountLabel;
    private JComboBox<String> adjustLivesCountComboBox;
    public int lives;

    private JLabel adjustColorSchemeLabel;
    private JComboBox<String> adjustColorSchemeComboBox;
    public String colorChosen;

    private JCheckBox wrapCheckBox;
    public boolean wrapEnabled;

    private JButton implementSettingsButton;

    private Colors colors = new Colors(""); // empty string will get default colors, but all we really need is the total list

    private HashMap<String,Integer> squareSizeMap = new HashMap<>();
    private HashMap<String,Integer> gameSpeedMap = new HashMap<>();
    private HashMap<String,Integer> wallSizeMap= new HashMap<>();
    private HashMap<String,Integer> wallCountMap = new HashMap<>();
    private HashMap<String,Integer> snakeGrowthMap = new HashMap<>();
    private HashMap<String,Integer> livesCountMap = new HashMap<>();


    public SettingsGUI() {
        super("Snake Settings");
        setContentPane(rootPanel);
        setPreferredSize(new Dimension(600,400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createSettingsHashmaps();
        populateComboBoxes();
        addListeners();


        pack();
        setVisible(false); // sets visible to true when user presses o

    }


    private void createSettingsHashmaps() {
        squareSizeMap.put("Large (Easy)",50);
        squareSizeMap.put("Medium (Average)",25);
        squareSizeMap.put("Small (Hard)",20);
        squareSizeMap.put("Tiny (Crazy)",10);

        gameSpeedMap.put("Slow (Easy)",500);
        gameSpeedMap.put("Medium (Average)",350);
        gameSpeedMap.put("Fast (Hard)",200);
        gameSpeedMap.put("Turbo (Crazy)",150);

        wallSizeMap.put("Easy",0);
        wallSizeMap.put("Average",1);
        wallSizeMap.put("Hard",2);
        wallSizeMap.put("Crazy",3);

        wallCountMap.put("Easy",0);
        wallCountMap.put("Average",1);
        wallCountMap.put("Hard",2);
        wallCountMap.put("Crazy",3);

        snakeGrowthMap.put("Easy",4);
        snakeGrowthMap.put("Average",3);
        snakeGrowthMap.put("Hard",2);
        snakeGrowthMap.put("Crazy",1);

        livesCountMap.put("Easy",10);
        livesCountMap.put("Average",6);
        livesCountMap.put("Hard",2);
        livesCountMap.put("Crazy",0);

    }

    public void populateComboBoxes() {

        // add combo box settings

        // Square sizes
        for (String squareSizeSetting : squareSizeMap.keySet()) {
            adjustSquareSizeComboBox.addItem(squareSizeSetting);
        }

        // Game speed
        for (String gameSpeedSetting : gameSpeedMap.keySet()) {
            adjustGameSpeedComboBox.addItem(gameSpeedSetting);
        }

        // Wall count
        for (String wallCountSetting : wallCountMap.keySet()) {
            adjustWallCountComboBox.addItem(wallCountSetting);
        }

        // Snake growth
        for (String snakeGrowthSetting : snakeGrowthMap.keySet()) {
            adjustSnakeGrowthRateComboBox.addItem(snakeGrowthSetting);
        }

        // Lives
        for (String livesCountSetting : livesCountMap.keySet()) {
            adjustLivesCountComboBox.addItem(livesCountSetting);
        }

        // Colors
        for (String color : colors.getColorList()) {
            adjustColorSchemeComboBox.addItem(color);
        }
    }


    public void addListeners() {

        implementSettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                implementSettings();
                SnakeGame.initializeGame();
                SnakeGame.createAndShowGUI();
            }
        });


    }

    // Runs when the implement settings button is pushed, updates game settings

    public void implementSettings() {

        SnakeGame.squareSize = squareSizeMap.get(adjustSquareSizeComboBox.getSelectedItem());
        SnakeGame.clockInterval = gameSpeedMap.get(adjustGameSpeedComboBox.getSelectedItem());
        SnakeGame.wallCount = wallCountMap.get(adjustWallCountComboBox.getSelectedItem());
        SnakeGame.snakeGrowthRate = snakeGrowthMap.get(adjustSnakeGrowthRateComboBox.getSelectedItem());
        SnakeGame.startingLife = livesCountMap.get(adjustLivesCountComboBox.getSelectedItem());
        SnakeGame.colorChosen = (String)adjustColorSchemeComboBox.getSelectedItem();
        SnakeGame.wrap = wrapCheckBox.isSelected();

        this.setVisible(false); // back to invisible after implementing settings, made visible again by pressing o (see game controls)
    }

}
