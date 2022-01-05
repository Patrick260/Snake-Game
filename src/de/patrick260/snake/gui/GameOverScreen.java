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

public class GameOverScreen extends JPanel {

    private static final int WIDTH = GUI.WIDTH;
    private static final int HEIGHT = GUI.HEIGHT;

    private static final Color BACKGROUND_COLOR = GUI.BACKGROUND_COLOR;

    private static final String GAME_OVER_TEXT = "Game Over!";

    private static final int GAME_OVER_TEXT_WIDTH = 600;
    private static final int GAME_OVER_TEXT_HEIGHT = 150;

    private static final Color GAME_OVER_TEXT_COLOR = Color.RED;

    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 75;

    private static final int GAP_BETWEEN_BUTTONS = 10;

    private static final Color BUTTON_COLOR = Color.DARK_GRAY;
    

    public GameOverScreen() {

        setLayout(null);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        setBackground(BACKGROUND_COLOR);

        JTextField game_over_text = new JTextField(GAME_OVER_TEXT);

        game_over_text.setEditable(false);
        game_over_text.setForeground(GAME_OVER_TEXT_COLOR);
        game_over_text.setBackground(BACKGROUND_COLOR);
        game_over_text.setBorder(null);
        game_over_text.setFont(new Font(null, Font.BOLD, 100));
        game_over_text.setHorizontalAlignment(JTextField.CENTER);
        game_over_text.setBounds((WIDTH - GAME_OVER_TEXT_WIDTH) / 2, 100, GAME_OVER_TEXT_WIDTH, GAME_OVER_TEXT_HEIGHT);

        add(game_over_text);

        JButton play_again_button = new JButton();

        play_again_button.setBounds((WIDTH - BUTTON_WIDTH) / 2, (HEIGHT - BUTTON_HEIGHT) / 2 - (BUTTON_HEIGHT + GAP_BETWEEN_BUTTONS) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        play_again_button.setBackground(BUTTON_COLOR);
        play_again_button.setModel(new FixedStateButtonModel());
        play_again_button.setText("Play again");
        play_again_button.setFont(new Font(null, Font.BOLD, 25));
        play_again_button.setForeground(Color.black);
        play_again_button.setFocusPainted(false);

        play_again_button.addActionListener(event -> {

            getParent().add(new Game());

            setVisible(false);
            getParent().remove(this);

        });

        add(play_again_button);

        JButton menu_button = new JButton();

        menu_button.setBounds((WIDTH - BUTTON_WIDTH) / 2, (HEIGHT - BUTTON_HEIGHT) / 2 + (BUTTON_HEIGHT + GAP_BETWEEN_BUTTONS) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        menu_button.setBackground(BUTTON_COLOR);
        menu_button.setModel(new FixedStateButtonModel());
        menu_button.setText("Menu");
        menu_button.setFont(new Font(null, Font.BOLD, 25));
        menu_button.setForeground(Color.black);
        menu_button.setFocusPainted(false);

        menu_button.addActionListener(event -> {

            getParent().add(new MainMenu());

            setVisible(false);
            getParent().remove(this);

        });

        add(menu_button);

    }

}
