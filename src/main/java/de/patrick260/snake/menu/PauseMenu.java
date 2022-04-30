/*
    Copyright (C) 2022  Patrick260

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

package de.patrick260.snake.menu;

import de.patrick260.snake.game.Game;
import de.patrick260.snake.gui.FixedStateButtonModel;
import de.patrick260.snake.gui.GUI;

import javax.swing.*;
import java.awt.*;

public final class PauseMenu extends JPanel {

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

        add(buildPauseText());
        add(buildResumeButton());
        add(buildMenuButton());

    }


    private JTextField buildPauseText() {

        final JTextField pauseText = new JTextField(PAUSE_TEXT);

        pauseText.setEditable(false);
        pauseText.setFocusable(false);
        pauseText.setForeground(PAUSE_TEXT_COLOR);
        pauseText.setBackground(BACKGROUND_COLOR);
        pauseText.setBorder(null);
        pauseText.setFont(new Font(PAUSE_TEXT_FONT, PAUSE_TEXT_STYLE, PAUSE_TEXT_SIZE));
        pauseText.setHorizontalAlignment(JTextField.CENTER);
        pauseText.setBounds((WIDTH - PAUSE_TEXT_WIDTH) / 2, PAUSE_TEXT_Y, PAUSE_TEXT_WIDTH, PAUSE_TEXT_HEIGHT);

        return pauseText;

    }

    private JButton buildResumeButton() {

        final JButton resumeButton = new JButton();

        resumeButton.setBounds((WIDTH - BUTTON_WIDTH) / 2, (HEIGHT - BUTTON_HEIGHT) / 2 - (BUTTON_HEIGHT + GAP_BETWEEN_BUTTONS) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        resumeButton.setBackground(BUTTON_COLOR);
        resumeButton.setModel(new FixedStateButtonModel());
        resumeButton.setText("Resume");
        resumeButton.setFont(new Font(BUTTON_FONT, BUTTON_STYLE, BUTTON_TEXT_SIZE));
        resumeButton.setForeground(BUTTON_TEXT_COLOR);
        resumeButton.setFocusPainted(false);

        resumeButton.addActionListener(event -> {

            Game.getGame().setVisible(true);
            Game.getGame().running = true;

            setVisible(false);
            getParent().remove(this);

        });

        return resumeButton;

    }

    private JButton buildMenuButton() {

        final JButton menuButton = new JButton();

        menuButton.setBounds((WIDTH - BUTTON_WIDTH) / 2, (HEIGHT - BUTTON_HEIGHT) / 2 + (BUTTON_HEIGHT + GAP_BETWEEN_BUTTONS) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        menuButton.setBackground(BUTTON_COLOR);
        menuButton.setModel(new FixedStateButtonModel());
        menuButton.setText("Menu");
        menuButton.setFont(new Font(BUTTON_FONT, BUTTON_STYLE, BUTTON_TEXT_SIZE));
        menuButton.setForeground(BUTTON_TEXT_COLOR);
        menuButton.setFocusPainted(false);

        menuButton.addActionListener(event -> {

            getParent().add(new MainMenu());

            setVisible(false);
            getParent().remove(this);

        });

        return menuButton;

    }

}
