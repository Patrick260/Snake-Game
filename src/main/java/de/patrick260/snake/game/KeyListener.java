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

import de.patrick260.snake.menu.PauseMenu;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class KeyListener extends KeyAdapter {

    @Override
    public void keyPressed(final KeyEvent event) {

        final Game game = Game.getGame();

        int key_id = event.getKeyCode();

        if (key_id == KeyEvent.VK_LEFT && game.direction != Direction.LEFT && game.direction != Direction.RIGHT && !game.alreadyMovedInTick) {

            game.direction = Direction.LEFT;

            game.alreadyMovedInTick = true;

        } else if (key_id == KeyEvent.VK_RIGHT && game.direction != Direction.RIGHT && game.direction != Direction.LEFT && !game.alreadyMovedInTick) {

            game.direction = Direction.RIGHT;

            game.alreadyMovedInTick = true;

        } else if (key_id == KeyEvent.VK_UP && game.direction != Direction.UP && game.direction != Direction.DOWN && !game.alreadyMovedInTick) {

            game.direction = Direction.UP;

            game.alreadyMovedInTick = true;

        } else if (key_id == KeyEvent.VK_DOWN && game.direction != Direction.DOWN && game.direction != Direction.UP && !game.alreadyMovedInTick) {

            game.direction = Direction.DOWN;

            game.alreadyMovedInTick = true;

        } else if (key_id == KeyEvent.VK_ESCAPE) {

            game.running = false;

            game.setVisible(false);

            game.getParent().add(new PauseMenu());

        }

    }

}
