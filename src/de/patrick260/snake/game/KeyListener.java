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
