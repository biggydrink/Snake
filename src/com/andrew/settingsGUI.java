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

public class settingsGUI extends JFrame {

    private JPanel rootPanel;
    private JComboBox adjustSquareSizeComboBox;
    private JComboBox adjustGameSpeedComboBox;
    private JLabel adjustSquareSizeLabel;
    private JLabel adjustGameSpeedLabel;
    private JComboBox adjustWallSizeComboBox;
    private JLabel adjustWallSizeLabel;
    private JComboBox adjustWallCountComboBox;
    private JLabel adjustWallCountLabel;
    private JComboBox adjustSnakeGrowthRateComboBox;
    private JLabel adjustSnakeGrowthRateLabel;
    private JComboBox adjustLivesCountComboBox;
    private JLabel adjustLivesCountLabel;
    private JCheckBox warpCheckBox;
    private JComboBox adjustColorSchemeComboBox;
    private JLabel adjustColorSchemeLabel;

    public settingsGUI() {
        super("Snake Settings");
        setContentPane(rootPanel);
        setPreferredSize(new Dimension(600,400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //addListeners();
        //addWindowListener(new WindowClosingListener());

        pack();
        setVisible(true);

        //populateModel(TicketManager.openTickets);
    }
}
