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

public final class Game extends JPanel implements ActionListener {

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

    private static final int GAME_SPEED = 100;

    private static Game game;

    private int tailAmount = SNAKE_SPAWN_TAIL_AMOUNT + 1;

    private final int[] snakeX = new int[WIDTH * HEIGHT / (PART_SIZE * PART_SIZE)];
    private final int[] snakeY = new int[WIDTH * HEIGHT / (PART_SIZE * PART_SIZE)];

    Direction direction = Direction.RIGHT;

    boolean alreadyMovedInTick;

    private final ArrayList<String> possibleApplePositions = generatePossibleApplePositions();

    private int appleX;
    private int appleY;

    private boolean appleCollectedInTick;

    private final Timer timer;

    private boolean isDeath = false;

    public boolean running;


    public Game() {

        game = this;

        addKeyListener(new KeyListener());

        setFocusable(true);

        setBackground(BACKGROUND_COLOR);

        for (int i = 0; i < tailAmount; i++) {

            snakeX[i] = SNAKE_SPAWN_X - i * PART_SIZE;
            snakeY[i] = SNAKE_SPAWN_Y;

            possibleApplePositions.remove(snakeX[i] + "-" + snakeY[i]);

        }

        running = true;

        timer = new Timer(GAME_SPEED, this);
        timer.start();

        spawn_apple();

    }


    private void spawn_apple() {

        final String[] random = possibleApplePositions.get((int) (Math.random() * possibleApplePositions.size() - 1)).split("-");

        appleX = Integer.parseInt(random[0]);
        appleY = Integer.parseInt(random[1]);

    }

    private void checkApple() {

        if (snakeX[0] == appleX && snakeY[0] == appleY) {

            tailAmount++;

            appleCollectedInTick = true;

            spawn_apple();

        }

    }

    private void checkDeath() {

        for (int i = tailAmount - 1; i > 3; i--) {

            if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {

                isDeath = true;

                break;

            }

        }

        if (snakeX[0] > WIDTH - PART_SIZE || snakeX[0] < 0 || snakeY[0] > HEIGHT - PART_SIZE || snakeY[0] < 0) {

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

            possibleApplePositions.add(snakeX[tailAmount - 1] + "-" + snakeY[tailAmount - 1]);

        }

        for (int i = tailAmount - 1; i > 0; i--) {

            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];

        }

        switch (direction) {

            case LEFT -> snakeX[0] -= PART_SIZE;

            case RIGHT -> snakeX[0] += PART_SIZE;

            case UP -> snakeY[0] -= PART_SIZE;

            case DOWN -> snakeY[0] += PART_SIZE;

        }

        possibleApplePositions.remove(snakeX[0] + "-" + snakeY[0]);

    }

    public ArrayList<String> generatePossibleApplePositions() {

        final ArrayList<String> possibleApplePositions = new ArrayList<>();

        for (int i = 0; i < WIDTH; i+= PART_SIZE) {

            for (int j = 0; j < HEIGHT; j+= PART_SIZE) {

                possibleApplePositions.add(i + "-" + j);

            }

        }

        return possibleApplePositions;

    }


    @Override
    public void actionPerformed(final ActionEvent event) {

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
    public void paintComponent(final Graphics graphics) {

        super.paintComponent(graphics);

        if (running) {

            graphics.setColor(APPLE_COLOR);
            graphics.fillRect(appleX, appleY, PART_SIZE, PART_SIZE);
            graphics.setColor(OUTLINE_COLOR);
            graphics.drawRect(appleX, appleY, PART_SIZE, PART_SIZE);

            for (int i = 1; i < tailAmount; i++) {

                graphics.setColor(TAIL_COLOR);
                graphics.fillRect(snakeX[i], snakeY[i], PART_SIZE, PART_SIZE);
                graphics.setColor(OUTLINE_COLOR);
                graphics.drawRect(snakeX[i], snakeY[i], PART_SIZE, PART_SIZE);

            }

            graphics.setColor(HEAD_COLOR);
            graphics.fillRect(snakeX[0], snakeY[0], PART_SIZE, PART_SIZE);
            graphics.setColor(OUTLINE_COLOR);
            graphics.drawRect(snakeX[0], snakeY[0], PART_SIZE, PART_SIZE);

            Toolkit.getDefaultToolkit().sync();

        }

    }


    public static Game getGame() {

        return game;

    }

}
