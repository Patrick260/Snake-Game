package de.patrick260.snake.game;

import de.patrick260.snake.main.Snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent event) {

        int key_id = event.getKeyCode();

        if (key_id == KeyEvent.VK_LEFT && Snake.game.direction != Direction.LEFT && Snake.game.direction != Direction.RIGHT && !Snake.game.alreadyMovedInTick) {

            Snake.game.direction = Direction.LEFT;

            Snake.game.alreadyMovedInTick = true;

        } else if (key_id == KeyEvent.VK_RIGHT && Snake.game.direction != Direction.RIGHT && Snake.game.direction != Direction.LEFT && !Snake.game.alreadyMovedInTick) {

            Snake.game.direction = Direction.RIGHT;

            Snake.game.alreadyMovedInTick = true;

        } else if (key_id == KeyEvent.VK_UP && Snake.game.direction != Direction.UP && Snake.game.direction != Direction.DOWN && !Snake.game.alreadyMovedInTick) {

            Snake.game.direction = Direction.UP;

            Snake.game.alreadyMovedInTick = true;

        } else if (key_id == KeyEvent.VK_DOWN && Snake.game.direction != Direction.DOWN && Snake.game.direction != Direction.UP && !Snake.game.alreadyMovedInTick) {

            Snake.game.direction = Direction.DOWN;

            Snake.game.alreadyMovedInTick = true;

        }

    }

}
