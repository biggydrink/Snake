package com.andrew;


/*
Customization options for Settings GUI

Difficulty-related:
    -Game board size: how many squares will be used
	    -could either change max pixels or change square size (probably square size)
		SnakeGame.squareSize (clara default 50)
		50 Large
		25 Medium
		20 Small
		10 Crazy
		combo list

	-adjust game speed
		-give predetermined ratings, i.e. 1-5 or something
		SnakeGame.clockInterval (clara default 500)
		combo list
	-adjust wall size
		-consecutive walls
		TBD
	-adjust number of random walls
		TBD
	-adjust snake growth rate (slower growth = harder? but also massive growth = harder)
		Snake.growthIncrement
	-Somehow give a number of lives and allow player to start over at same size
		TBD

Other
    -turn on or off wall clipping
		SnakeGame.wrap
		check box
	-Choose your color scheme (predetermined schemas)
		TBD
		DrawSnakeGamePanel new Colors(color name);
		combo list

 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class SettingsGUI extends JFrame {

    private JPanel rootPanel;

    public boolean ready;

    private JLabel adjustSquareSizeLabel;
    private JComboBox<String> adjustSquareSizeComboBox;
    public int squareSize = 50;

    private JLabel adjustGameSpeedLabel;
    private JComboBox<String> adjustGameSpeedComboBox;
    public int gameSpeed;

    private JLabel adjustWallSizeLabel;
    private JComboBox<String> adjustWallSizeComboBox;
    public int wallSize;

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
        setVisible(true);

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

        /*adjustSquareSizeComboBox.addItem("Large (Easy)");
        adjustSquareSizeComboBox.addItem("Medium (Average)");
        adjustSquareSizeComboBox.addItem("Small (Hard)");
        adjustSquareSizeComboBox.addItem("Tiny (Crazy)");*/

        for (String gameSpeedSetting : gameSpeedMap.keySet()) {
            adjustGameSpeedComboBox.addItem(gameSpeedSetting);
        }

        /*adjustGameSpeedComboBox.addItem("Medium (Average)");
        adjustGameSpeedComboBox.addItem("Slow (Easy)");
        adjustGameSpeedComboBox.addItem("Fast (Hard)");
        adjustGameSpeedComboBox.addItem("Turbo (Crazy)");*/

        for (String wallSizeSetting : wallSizeMap.keySet()) {
            adjustWallSizeComboBox.addItem(wallSizeSetting);
        }

        /*adjustWallSizeComboBox.addItem("0 (Easy)");
        adjustWallSizeComboBox.addItem("1 (Average)");
        adjustWallSizeComboBox.addItem("2 (Hard)");
        adjustWallSizeComboBox.addItem("3 (Crazy)");*/

        for (String wallCountSetting : wallCountMap.keySet()) {
            adjustWallCountComboBox.addItem(wallCountSetting);
        }

        /*adjustWallCountComboBox.addItem("0 (Easy)");
        adjustWallCountComboBox.addItem("1 (Average)");
        adjustWallCountComboBox.addItem("2 (Hard)");
        adjustWallCountComboBox.addItem("3 (Crazy)");*/


        for (String snakeGrowthSetting : snakeGrowthMap.keySet()) {
            adjustSnakeGrowthRateComboBox.addItem(snakeGrowthSetting);
        }

        /*adjustSnakeGrowthRateComboBox.addItem("4 (Easy)");
        adjustSnakeGrowthRateComboBox.addItem("3 (Average)");
        adjustSnakeGrowthRateComboBox.addItem("2 (Hard)");
        adjustSnakeGrowthRateComboBox.addItem("1 (Crazy)");*/

        for (String livesCountSetting : livesCountMap.keySet()) {
            adjustLivesCountComboBox.addItem(livesCountSetting);
        }
        /*
        adjustLivesCountComboBox.addItem("10 (Easy)");
        adjustLivesCountComboBox.addItem("6 (Average)");
        adjustLivesCountComboBox.addItem("2 (Hard)");
        adjustLivesCountComboBox.addItem("0 (Crazy)");*/

        //wrapCheckBox;

        for (String color : colors.colorList) {
            adjustColorSchemeComboBox.addItem(color);
        }



    }


    public void addListeners() {

        implementSettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                implementSettings();
            }
        });


    }


    public void implementSettings() {

        // run when button is pushed
        // push all selected settings
        // should make sure settings are pushed, OR give default settings to all combo boxes


        SnakeGame.

        squareSize = squareSizeMap.get(adjustSquareSizeComboBox.getSelectedItem());
        gameSpeed = gameSpeedMap.get(adjustGameSpeedComboBox.getSelectedItem());
        wallSize = wallSizeMap.get(adjustWallSizeComboBox.getSelectedItem());
        wallCount = wallCountMap.get(adjustWallCountComboBox.getSelectedItem());
        snakeGrowthRate = snakeGrowthMap.get(adjustSnakeGrowthRateComboBox.getSelectedItem());
        lives = livesCountMap.get(adjustLivesCountComboBox.getSelectedItem());
        colorChosen = (String)adjustColorSchemeComboBox.getSelectedItem();
        wrapEnabled = wrapCheckBox.isSelected();

        ready = true;

    }



}
