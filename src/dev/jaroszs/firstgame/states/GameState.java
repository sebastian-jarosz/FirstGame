package dev.jaroszs.firstgame.states;

import dev.jaroszs.firstgame.Game;
import dev.jaroszs.firstgame.Handler;
import dev.jaroszs.firstgame.entities.creatures.Player;
import dev.jaroszs.firstgame.gfx.Assets;
import dev.jaroszs.firstgame.tiles.Tile;
import dev.jaroszs.firstgame.worlds.World;

import java.awt.*;

public class GameState extends State {

    private Player player;
    private World world;

    public GameState(Handler handler){
        super(handler);
        world = new World(handler,"res/worlds/world1.txt");
        handler.setWorld(world);
        player = new Player(handler,100,100);

    }

    @Override
    public void tick() {
        world.tick();
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
    }

}
