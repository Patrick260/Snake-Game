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

package de.patrick260.snake.game;

import de.patrick260.snake.gui.GUI;
import de.patrick260.snake.gui.GameOverScreen;
import de.patrick260.snake.menu.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel implements ActionListener {

    private static final int WIDTH = GUI.WIDTH;
    private static final int HEIGHT = GUI.HEIGHT;

    private static final Color BACKGROUND_COLOR = GUI.BACKGROUND_COLOR;

    private static final int PART_SIZE = 50;

    private static final Color APPLE_COLOR = Color.red;
    private static final Color HEAD_COLOR = Color.orange;
    private static final Color TAIL_COLOR = Color.yellow;

    private static final int SNAKE_SPAWN_TAIL_AMOUNT = 2;

    private static final int SNAKE_SPAWN_X = PART_SIZE * SNAKE_SPAWN_TAIL_AMOUNT;
    private static final int SNAKE_SPAWN_Y = 0;

    private static final int GAME_SPEED = 200;

    private static Game game;

    private int tail_amount = SNAKE_SPAWN_TAIL_AMOUNT + 1;

    private final int[] snake_x = new int[WIDTH * HEIGHT / (PART_SIZE * PART_SIZE)];
    private final int[] snake_y = new int[WIDTH * HEIGHT / (PART_SIZE * PART_SIZE)];

    Direction direction = Direction.RIGHT;

    boolean alreadyMovedInTick;

    private int apple_x;
    private int apple_y;

    private final Timer timer;

    private boolean running;


    public Game() {

        game = this;

        addKeyListener(new KeyListener());

        setFocusable(true);

        setBackground(BACKGROUND_COLOR);

        for (int i = 0; i < tail_amount; i++) {

            snake_x[i] = SNAKE_SPAWN_X - i * PART_SIZE;
            snake_y[i] = SNAKE_SPAWN_Y;

        }

        running = true;

        timer = new Timer(GAME_SPEED, this);
        timer.start();

        spawn_apple();

    }


    private void spawn_apple() {

        apple_x = (int) (Math.random() * ((WIDTH - PART_SIZE) / PART_SIZE)) * PART_SIZE;
        apple_y = (int) (Math.random() * ((HEIGHT - PART_SIZE) / PART_SIZE)) * PART_SIZE;

        for (int i = 0; i < tail_amount; i++) {

            if (snake_x[i] == apple_x && snake_y[i] == apple_y) {

                spawn_apple();

                break;

            }

        }

    }

    private void checkApple() {

        if (snake_x[0] == apple_x && snake_y[0] == apple_y) {

            tail_amount++;

            spawn_apple();

        }

    }

    private void checkDeath() {

        for (int i = tail_amount; i > 3; i--) {

            if (snake_x[0] == snake_x[i] && snake_y[0] == snake_y[i]) {

                running = false;

                break;

            }

        }

        if (snake_x[0] > WIDTH || snake_x[0] < 0 || snake_y[0] > HEIGHT || snake_y[0] < 0) {

            running = false;

        }

        if (!running) {

            timer.stop();

            getParent().add(new GameOverScreen());

            setVisible(false);
            getParent().remove(this);

        }

    }

    private void moveSnake() {

        for (int i = tail_amount; i > 0; i--) {

            snake_x[i] = snake_x[i - 1];
            snake_y[i] = snake_y[i - 1];

        }

        switch (direction) {

            case LEFT -> snake_x[0] -= PART_SIZE;

            case RIGHT -> snake_x[0] += PART_SIZE;

            case UP -> snake_y[0] -= PART_SIZE;

            case DOWN -> snake_y[0] += PART_SIZE;

        }

    }


    @Override
    public void actionPerformed(ActionEvent event) {

        requestFocus();

        if (running) {

            checkApple();
            moveSnake();
            checkDeath();

        }

        repaint();

        alreadyMovedInTick = false;

    }


    @Override
    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);

        if (running) {

            graphics.setColor(APPLE_COLOR);
            graphics.fillRect(apple_x, apple_y, PART_SIZE, PART_SIZE);

            for (int i = 1; i < tail_amount; i++) {

                graphics.setColor(TAIL_COLOR);
                graphics.fillRect(snake_x[i], snake_y[i], PART_SIZE, PART_SIZE);

            }

            graphics.setColor(HEAD_COLOR);
            graphics.fillRect(snake_x[0], snake_y[0], PART_SIZE, PART_SIZE);

            Toolkit.getDefaultToolkit().sync();

        }

    }


    public static Game getGame() {

        return game;

    }

}
