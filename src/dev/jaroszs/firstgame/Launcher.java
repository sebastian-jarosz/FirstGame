package dev.jaroszs.firstgame;

import dev.jaroszs.firstgame.display.Display;

public class Launcher {

    public static void main(String[] args) {
        Game game = new Game("My First Game!", 500, 500);

        game.start();
    }

}
