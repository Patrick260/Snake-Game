package de.patrick260.snake.menu;

import de.patrick260.snake.game.Game;
import de.patrick260.snake.gui.FixedStateButtonModel;
import de.patrick260.snake.gui.GUI;

import javax.swing.*;
import java.awt.*;

public class PauseMenu extends JPanel {

    private static final int WIDTH = GUI.WIDTH;
    private static final int HEIGHT = GUI.HEIGHT;

    private static final Color BACKGROUND_COLOR = GUI.BACKGROUND_COLOR;

    private static final String PAUSE_TEXT = "Pause";

    private static final int PAUSE_TEXT_WIDTH = 600;
    private static final int PAUSE_TEXT_HEIGHT = 150;

    private static final int PAUSE_TEXT_Y = 100;

    private static final String PAUSE_TEXT_FONT = null;
    private static final int PAUSE_TEXT_STYLE = Font.BOLD;
    private static final int PAUSE_TEXT_SIZE = 100;

    private static final Color PAUSE_TEXT_COLOR = Color.BLACK;

    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 75;

    private static final int GAP_BETWEEN_BUTTONS = 10;

    private static final String BUTTON_FONT = null;
    private static final int BUTTON_STYLE = Font.BOLD;
    private static final int BUTTON_TEXT_SIZE = 25;

    private static final Color BUTTON_TEXT_COLOR = Color.BLACK;

    private static final Color BUTTON_COLOR = Color.DARK_GRAY;


    public PauseMenu() {

        setLayout(null);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        setBackground(BACKGROUND_COLOR);

        JTextField pause_text = new JTextField(PAUSE_TEXT);

        pause_text.setEditable(false);
        pause_text.setForeground(PAUSE_TEXT_COLOR);
        pause_text.setBackground(BACKGROUND_COLOR);
        pause_text.setBorder(null);
        pause_text.setFont(new Font(PAUSE_TEXT_FONT, PAUSE_TEXT_STYLE, PAUSE_TEXT_SIZE));
        pause_text.setHorizontalAlignment(JTextField.CENTER);
        pause_text.setBounds((WIDTH - PAUSE_TEXT_WIDTH) / 2, PAUSE_TEXT_Y, PAUSE_TEXT_WIDTH, PAUSE_TEXT_HEIGHT);

        add(pause_text);

        JButton resume_button = new JButton();

        resume_button.setBounds((WIDTH - BUTTON_WIDTH) / 2, (HEIGHT - BUTTON_HEIGHT) / 2 - (BUTTON_HEIGHT + GAP_BETWEEN_BUTTONS) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        resume_button.setBackground(BUTTON_COLOR);
        resume_button.setModel(new FixedStateButtonModel());
        resume_button.setText("Resume");
        resume_button.setFont(new Font(BUTTON_FONT, BUTTON_STYLE, BUTTON_TEXT_SIZE));
        resume_button.setForeground(BUTTON_TEXT_COLOR);
        resume_button.setFocusPainted(false);

        resume_button.addActionListener(event -> {

            Game.getGame().setVisible(true);
            Game.getGame().running = true;

            setVisible(false);
            getParent().remove(this);

        });

        add(resume_button);

        JButton menu_button = new JButton();

        menu_button.setBounds((WIDTH - BUTTON_WIDTH) / 2, (HEIGHT - BUTTON_HEIGHT) / 2 + (BUTTON_HEIGHT + GAP_BETWEEN_BUTTONS) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        menu_button.setBackground(BUTTON_COLOR);
        menu_button.setModel(new FixedStateButtonModel());
        menu_button.setText("Menu");
        menu_button.setFont(new Font(BUTTON_FONT, BUTTON_STYLE, BUTTON_TEXT_SIZE));
        menu_button.setForeground(BUTTON_TEXT_COLOR);
        menu_button.setFocusPainted(false);

        menu_button.addActionListener(event -> {

            getParent().add(new MainMenu());

            setVisible(false);
            getParent().remove(this);

        });

        add(menu_button);

    }

}
