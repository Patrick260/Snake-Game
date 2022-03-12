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

public final class MainMenu extends JPanel {

    private static final int WIDTH = GUI.WIDTH;
    private static final int HEIGHT = GUI.HEIGHT;

    private static final Color BACKGROUND_COLOR = GUI.BACKGROUND_COLOR;

    private static final String TITLE = GUI.TITLE;

    private static final int TITLE_WIDTH = 500;
    private static final int TITLE_HEIGHT = 150;

    private static final int TITLE_Y = 100;

    private static final String TITLE_FONT = null;
    private static final int TITLE_STYLE = Font.BOLD;
    private static final int TITLE_SIZE = 100;

    private static final Color TITLE_COLOR = Color.BLACK;

    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 75;

    private static final int GAP_BETWEEN_BUTTONS = 10;

    private static final String BUTTON_FONT = null;
    private static final int BUTTON_STYLE = Font.BOLD;
    private static final int BUTTON_TEXT_SIZE = 25;

    private static final Color BUTTON_TEXT_COLOR = Color.BLACK;

    private static final Color BUTTON_COLOR = Color.DARK_GRAY;


    public MainMenu() {

        setLayout(null);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        setBackground(BACKGROUND_COLOR);

        final JTextField title = new JTextField(TITLE);

        title.setEditable(false);
        title.setFocusable(false);
        title.setForeground(TITLE_COLOR);
        title.setBackground(BACKGROUND_COLOR);
        title.setBorder(null);
        title.setFont(new Font(TITLE_FONT, TITLE_STYLE, TITLE_SIZE));
        title.setHorizontalAlignment(JTextField.CENTER);
        title.setBounds((WIDTH - TITLE_WIDTH) / 2, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT);

        add(title);

        final JButton playButton = new JButton();

        playButton.setBounds((WIDTH - BUTTON_WIDTH) / 2, (HEIGHT - BUTTON_HEIGHT) / 2 - (BUTTON_HEIGHT + GAP_BETWEEN_BUTTONS) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        playButton.setBackground(BUTTON_COLOR);
        playButton.setModel(new FixedStateButtonModel());
        playButton.setText("Play");
        playButton.setFont(new Font(BUTTON_FONT, BUTTON_STYLE, BUTTON_TEXT_SIZE));
        playButton.setForeground(BUTTON_TEXT_COLOR);
        playButton.setFocusPainted(false);

        playButton.addActionListener(event -> {

            getParent().add(new Game());

            setVisible(false);
            getParent().remove(this);

        });

        add(playButton);

        final JButton exitButton = new JButton();

        exitButton.setBounds((WIDTH - BUTTON_WIDTH) / 2, (HEIGHT - BUTTON_HEIGHT) / 2 + (BUTTON_HEIGHT + GAP_BETWEEN_BUTTONS) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        exitButton.setBackground(BUTTON_COLOR);
        exitButton.setModel(new FixedStateButtonModel());
        exitButton.setText("Exit");
        exitButton.setFont(new Font(BUTTON_FONT, BUTTON_STYLE, BUTTON_TEXT_SIZE));
        exitButton.setForeground(BUTTON_TEXT_COLOR);
        exitButton.setFocusPainted(false);

        exitButton.addActionListener(event -> System.exit(0));

        add(exitButton);

    }

}
