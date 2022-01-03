package de.patrick260.snake.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent event) {

        int key_id = event.getKeyCode();

        if (key_id == KeyEvent.VK_LEFT && Game.direction != Direction.LEFT && Game.direction != Direction.RIGHT && !Game.alreadyMovedInTick) {

            Game.direction = Direction.LEFT;

            Game.alreadyMovedInTick = true;

        } else if (key_id == KeyEvent.VK_RIGHT && Game.direction != Direction.RIGHT && Game.direction != Direction.LEFT && !Game.alreadyMovedInTick) {

            Game.direction = Direction.RIGHT;

            Game.alreadyMovedInTick = true;

        } else if (key_id == KeyEvent.VK_UP && Game.direction != Direction.UP && Game.direction != Direction.DOWN && !Game.alreadyMovedInTick) {

            Game.direction = Direction.UP;

            Game.alreadyMovedInTick = true;

        } else if (key_id == KeyEvent.VK_DOWN && Game.direction != Direction.DOWN && Game.direction != Direction.UP && !Game.alreadyMovedInTick) {

            Game.direction = Direction.DOWN;

            Game.alreadyMovedInTick = true;

        }

    }

}
