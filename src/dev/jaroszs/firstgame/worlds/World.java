package dev.jaroszs.firstgame.worlds;

import dev.jaroszs.firstgame.Game;
import dev.jaroszs.firstgame.Handler;
import dev.jaroszs.firstgame.tiles.Tile;
import dev.jaroszs.firstgame.utils.Utils;

import java.awt.*;

public class World {

    private Handler handler;
    private int width;
    private int height;
    private int spawnX;
    private int spawnY;
    private int[][] tiles;

    public World(Handler handler, String path){
        loadWorld(path);
        this.handler = handler;
    }

    public void tick(){

    }

    public void render(Graphics g){
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);

        for(int y = yStart; y < yEnd; y++){
            for(int x = xStart; x< xEnd; x++){
                getTile(x, y).render(g,(int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
                                       (int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
    }

    public Tile getTile(int x, int y){
        //Just to prevent if player will stand outside of the map
        if(x < 0 || y < 0 || x >= width || y >= height){
            return Tile.grassTile;
        }

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
