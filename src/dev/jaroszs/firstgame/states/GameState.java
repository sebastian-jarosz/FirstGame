package dev.jaroszs.firstgame.states;

import dev.jaroszs.firstgame.Game;
import dev.jaroszs.firstgame.entities.creatures.Player;
import dev.jaroszs.firstgame.gfx.Assets;
import dev.jaroszs.firstgame.tiles.Tile;
import dev.jaroszs.firstgame.worlds.World;

import java.awt.*;

public class GameState extends State {

    private Player player;
    private World world;

    public GameState(Game game){
        super(game);
        player = new Player(game,100,100);
        world = new World("");
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
