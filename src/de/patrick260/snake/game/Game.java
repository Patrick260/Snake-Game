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

    @Override
    public void actionPerformed(ActionEvent event) {

        if (running) {

            checkApple();
            checkDeath();

        }

    }

}
