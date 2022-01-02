package de.patrick260.snake.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel implements ActionListener {

    private final int WIDTH = 300;
    private final int HEIGHT = 300;

    private final int PART_SIZE = 10;

    private final Color APPLE_COLOR = Color.red;
    private final Color HEAD_COLOR = Color.orange;
    private final Color TAIL_COLOR = Color.yellow;

    private int tail_amount = 3;

    private int[] snake_x = new int[WIDTH * HEIGHT / (PART_SIZE * PART_SIZE)];
    private int[] snake_y = new int[WIDTH * HEIGHT / (PART_SIZE * PART_SIZE)];

    private Direction direction;

    private int apple_x;
    private int apple_y;

    private boolean running;


    public Game() {

        addKeyListener(new KeyListener());
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        setBackground(Color.DARK_GRAY);

        for (int i = 0; i < tail_amount; i++) {

            snake_x[i] = 100 - i * 10;
            snake_y[i] = 100 - i * 10;

        }

    }


    private void spawn_apple() {

       int random = (int) (Math.random() * 29);

       apple_x = random * PART_SIZE;
       apple_y = random * PART_SIZE;

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

        if (snake_x[0] >= WIDTH || snake_y[0] >= HEIGHT) {

            running = false;

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
            checkDeath();
            moveSnake();

        }

        repaint();

    }

    @Override
    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);

        if (running) {

            graphics.setColor(APPLE_COLOR);
            graphics.drawRect(apple_x, apple_y, PART_SIZE, PART_SIZE);

            for (int i = 1; i < tail_amount; i++) {

                graphics.setColor(TAIL_COLOR);
                graphics.drawRect(snake_x[i], snake_y[i], PART_SIZE, PART_SIZE);

            }

            graphics.setColor(HEAD_COLOR);
            graphics.drawRect(snake_x[0], snake_y[0], PART_SIZE, PART_SIZE);

            Toolkit.getDefaultToolkit().sync();

        } else {

            String text = "Game Over";

            Font font = new Font("Calibri", Font.BOLD, 16);
            FontMetrics fontMetrics = getFontMetrics(font);

            graphics.setColor(Color.RED);
            graphics.setFont(font);
            graphics.drawString(text, (WIDTH - fontMetrics.stringWidth(text)), HEIGHT / 2);

        }

    }

}
