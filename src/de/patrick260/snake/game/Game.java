package de.patrick260.snake.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel implements ActionListener {

    private final int WIDTH = 900;
    private final int HEIGHT = 900;

    private final int PART_SIZE = 50;

    private final Color APPLE_COLOR = Color.red;
    private final Color HEAD_COLOR = Color.orange;
    private final Color TAIL_COLOR = Color.yellow;

    private int tail_amount = 3;

    private int[] snake_x = new int[WIDTH * HEIGHT / (PART_SIZE * PART_SIZE)];
    private int[] snake_y = new int[WIDTH * HEIGHT / (PART_SIZE * PART_SIZE)];

    protected Direction direction = Direction.RIGHT;

    private int apple_x;
    private int apple_y;

    private Timer timer;

    private final int GAME_SPEED = 200;

    protected boolean alreadyMovedInTick;

    private boolean running;


    public Game() {

        addKeyListener(new KeyListener());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        setBackground(Color.DARK_GRAY);

        for (int i = 0; i < tail_amount; i++) {

            snake_x[i] = 100 - i * 10;
            snake_y[i] = 100;

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

        } else {

            String text = "Game Over";

            Font font = new Font("Calibri", Font.BOLD, 50);
            FontMetrics fontMetrics = getFontMetrics(font);

            graphics.setColor(Color.RED);
            graphics.setFont(font);
            graphics.drawString(text, (WIDTH - fontMetrics.stringWidth(text)) / 2, HEIGHT / 2);

        }

    }

}
