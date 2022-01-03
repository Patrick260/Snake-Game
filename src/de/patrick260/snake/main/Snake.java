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
