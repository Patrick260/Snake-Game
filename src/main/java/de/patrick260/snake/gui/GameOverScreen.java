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

package de.patrick260.snake.gui;

import de.patrick260.snake.game.Game;
import de.patrick260.snake.menu.MainMenu;

import javax.swing.*;
import java.awt.*;

public final class GameOverScreen extends JPanel {

    private static final int WIDTH = GUI.WIDTH;
    private static final int HEIGHT = GUI.HEIGHT;

    private static final Color BACKGROUND_COLOR = GUI.BACKGROUND_COLOR;

    private static final String GAME_OVER_TEXT = "Game Over!";

    private static final int GAME_OVER_TEXT_WIDTH = 600;
    private static final int GAME_OVER_TEXT_HEIGHT = 150;

    private static final int GAME_OVER_TEXT_Y = 100;

    private static final String GAME_OVER_TEXT_FONT = null;
    private static final int GAME_OVER_TEXT_STYLE = Font.BOLD;
    private static final int GAME_OVER_TEXT_SIZE = 100;

    private static final Color GAME_OVER_TEXT_COLOR = Color.RED;

    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 75;

    private static final int GAP_BETWEEN_BUTTONS = 10;

    private static final String BUTTON_FONT = null;
    private static final int BUTTON_STYLE = Font.BOLD;
    private static final int BUTTON_TEXT_SIZE = 25;

    private static final Color BUTTON_TEXT_COLOR = Color.BLACK;

    private static final Color BUTTON_COLOR = Color.DARK_GRAY;
    

    public GameOverScreen() {

        setLayout(null);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        setBackground(BACKGROUND_COLOR);

        final JTextField gameOverText = new JTextField(GAME_OVER_TEXT);

        gameOverText.setEditable(false);
        gameOverText.setFocusable(false);
        gameOverText.setForeground(GAME_OVER_TEXT_COLOR);
        gameOverText.setBackground(BACKGROUND_COLOR);
        gameOverText.setBorder(null);
        gameOverText.setFont(new Font(GAME_OVER_TEXT_FONT, GAME_OVER_TEXT_STYLE, GAME_OVER_TEXT_SIZE));
        gameOverText.setHorizontalAlignment(JTextField.CENTER);
        gameOverText.setBounds((WIDTH - GAME_OVER_TEXT_WIDTH) / 2, GAME_OVER_TEXT_Y, GAME_OVER_TEXT_WIDTH, GAME_OVER_TEXT_HEIGHT);

        add(gameOverText);

        final JButton playAgainButton = new JButton();

        playAgainButton.setBounds((WIDTH - BUTTON_WIDTH) / 2, (HEIGHT - BUTTON_HEIGHT) / 2 - (BUTTON_HEIGHT + GAP_BETWEEN_BUTTONS) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        playAgainButton.setBackground(BUTTON_COLOR);
        playAgainButton.setModel(new FixedStateButtonModel());
        playAgainButton.setText("Play again");
        playAgainButton.setFont(new Font(BUTTON_FONT, BUTTON_STYLE, BUTTON_TEXT_SIZE));
        playAgainButton.setForeground(BUTTON_TEXT_COLOR);
        playAgainButton.setFocusPainted(false);

        playAgainButton.addActionListener(event -> {

            getParent().add(new Game());

            setVisible(false);
            getParent().remove(this);

        });

        add(playAgainButton);

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

        add(menuButton);

    }

}
