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

package de.patrick260.snake.main;

import de.patrick260.snake.game.Game;

import javax.swing.*;
import java.awt.*;

public class Snake extends JFrame {

    public static Game game;


    public Snake() {

        add((game = new Game()));

        setResizable(false);

        pack();

        setTitle("Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            JFrame snake = new Snake();
            snake.setVisible(true);

        });

    }

}
