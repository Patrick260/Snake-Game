package de.patrick260.snake.game;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {

    private final int WIDTH = 300;
    private final int HEIGHT = 300;

    private final int PART_SIZE = 10;

    private final Color APPLE_COLOR = Color.red;
    private final Color HEAD_COLOR = Color.orange;
    private final Color TAIL_COLOR = Color.yellow;

    private int tail_amount = 3;

    private int[] snake_x = new int[WIDTH * HEIGHT / (PART_SIZE * PART_SIZE)];
    private int[] snake_y = new int[WIDTH * HEIGHT / (PART_SIZE * PART_SIZE)];


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

}
