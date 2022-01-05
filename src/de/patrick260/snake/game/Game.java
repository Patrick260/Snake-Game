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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Game extends JPanel implements ActionListener {

    private static final int WIDTH = GUI.WIDTH;
    private static final int HEIGHT = GUI.HEIGHT;

    private static final Color BACKGROUND_COLOR = GUI.BACKGROUND_COLOR;

    private static final int PART_SIZE = 25;

    private static final Color APPLE_COLOR = Color.RED;
    private static final Color HEAD_COLOR = Color.ORANGE;
    private static final Color TAIL_COLOR = Color.YELLOW;

    private static final Color OUTLINE_COLOR = Color.BLACK;

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

    private final ArrayList<String> possibleApplePositions = generatePossibleApplePositions();

    private int apple_x;
    private int apple_y;

    private boolean appleCollectedInTick;

    private final Timer timer;

    private boolean isDeath = false;

    public boolean running;


    public Game() {

        game = this;

        addKeyListener(new KeyListener());

        setFocusable(true);

        setBackground(BACKGROUND_COLOR);

        for (int i = 0; i < tail_amount; i++) {

            snake_x[i] = SNAKE_SPAWN_X - i * PART_SIZE;
            snake_y[i] = SNAKE_SPAWN_Y;

            possibleApplePositions.remove(snake_x[i] + "-" + snake_y[i]);

        }

        running = true;

        timer = new Timer(GAME_SPEED, this);
        timer.start();

        spawn_apple();

    }


    private void spawn_apple() {

        String[] random = possibleApplePositions.get((int) (Math.random() * possibleApplePositions.size() - 1)).split("-");

        apple_x = Integer.parseInt(random[0]);
        apple_y = Integer.parseInt(random[1]);

    }

    private void checkApple() {

        if (snake_x[0] == apple_x && snake_y[0] == apple_y) {

            tail_amount++;

            appleCollectedInTick = true;

            spawn_apple();

        }

    }

    private void checkDeath() {

        for (int i = tail_amount - 1; i > 3; i--) {

            if (snake_x[0] == snake_x[i] && snake_y[0] == snake_y[i]) {

                isDeath = true;

                break;

            }

        }

        if (snake_x[0] > WIDTH - PART_SIZE || snake_x[0] < 0 || snake_y[0] > HEIGHT - PART_SIZE || snake_y[0] < 0) {

            isDeath = true;

        }

        if (isDeath) {

            running = false;

            timer.stop();

            getParent().add(new GameOverScreen());

            setVisible(false);
            getParent().remove(this);

        }

    }

    private void moveSnake() {

        if (!appleCollectedInTick) {

            possibleApplePositions.add(snake_x[tail_amount - 1] + "-" + snake_y[tail_amount - 1]);

        }

        for (int i = tail_amount - 1; i > 0; i--) {

            snake_x[i] = snake_x[i - 1];
            snake_y[i] = snake_y[i - 1];

        }

        switch (direction) {

            case LEFT -> snake_x[0] -= PART_SIZE;

            case RIGHT -> snake_x[0] += PART_SIZE;

            case UP -> snake_y[0] -= PART_SIZE;

            case DOWN -> snake_y[0] += PART_SIZE;

        }

        possibleApplePositions.remove(snake_x[0] + "-" + snake_y[0]);

    }

    public ArrayList<String> generatePossibleApplePositions() {

        ArrayList<String> possibleApplePositions = new ArrayList<>();

        for (int i = 0; i < WIDTH; i+= PART_SIZE) {

            for (int j = 0; j < HEIGHT; j+= PART_SIZE) {

                possibleApplePositions.add(i + "-" + j);

            }

        }

        return possibleApplePositions;

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
        appleCollectedInTick = false;

    }


    @Override
    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);

        if (running) {

            graphics.setColor(APPLE_COLOR);
            graphics.fillRect(apple_x, apple_y, PART_SIZE, PART_SIZE);
            graphics.setColor(OUTLINE_COLOR);
            graphics.drawRect(apple_x, apple_y, PART_SIZE, PART_SIZE);

            for (int i = 1; i < tail_amount; i++) {

                graphics.setColor(TAIL_COLOR);
                graphics.fillRect(snake_x[i], snake_y[i], PART_SIZE, PART_SIZE);
                graphics.setColor(OUTLINE_COLOR);
                graphics.drawRect(snake_x[i], snake_y[i], PART_SIZE, PART_SIZE);

            }

            graphics.setColor(HEAD_COLOR);
            graphics.fillRect(snake_x[0], snake_y[0], PART_SIZE, PART_SIZE);
            graphics.setColor(OUTLINE_COLOR);
            graphics.drawRect(snake_x[0], snake_y[0], PART_SIZE, PART_SIZE);

            Toolkit.getDefaultToolkit().sync();

        }

    }


    public static Game getGame() {

        return game;

    }

}
