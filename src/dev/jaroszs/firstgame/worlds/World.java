package dev.jaroszs.firstgame.worlds;

import dev.jaroszs.firstgame.Game;
import dev.jaroszs.firstgame.tiles.Tile;
import dev.jaroszs.firstgame.utils.Utils;

import java.awt.*;

public class World {

    private Game game;
    private int width;
    private int height;
    private int spawnX;
    private int spawnY;
    private int[][] tiles;

    public World(Game game, String path){
        loadWorld(path);
        this.game = game;
    }

    public void tick(){

    }

    public void render(Graphics g){
        for(int y = 0; y < height; y++){
            for(int x = 0; x< width; x++){
                getTile(x, y).render(g, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
            }
        }
    }

    public Tile getTile(int x, int y){
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null){
            return Tile.dirtTile;
        } else {
            return t;
        }
    }

    private void loadWorld(String path){
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for(int y=0; y<height; y++){
            for (int x = 0; x<width; x++){
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }

    }
}
