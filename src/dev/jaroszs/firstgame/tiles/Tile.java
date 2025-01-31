package dev.jaroszs.firstgame.tiles;

import javax.print.attribute.standard.NumberUp;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    //Static stuff here

    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile dirtTile = new DirtTile(1);
    public static Tile stoneTile = new StoneTile(2);
    public static Tile treeTile = new TreeTile(3);


    //Class

    public static final int TILE_WIDTH = 128,
                            TILE_HEIGHT = 128;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick(){

    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    public int getId(){
        return id;
    }

    public boolean isSolid(){
        return false;
    }
}
