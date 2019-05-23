package dev.jaroszs.firstgame.states;

import dev.jaroszs.firstgame.Game;
import dev.jaroszs.firstgame.Handler;
import dev.jaroszs.firstgame.entities.creatures.Player;
import dev.jaroszs.firstgame.entities.statics.Tree;
import dev.jaroszs.firstgame.gfx.Assets;
import dev.jaroszs.firstgame.tiles.Tile;
import dev.jaroszs.firstgame.worlds.World;

import java.awt.*;

public class GameState extends State {

    private Player player;
    private World world;
    private Tree tree;

    public GameState(Handler handler){
        super(handler);
        world = new World(handler,"res/worlds/world1.txt");
        handler.setWorld(world);
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }

}
