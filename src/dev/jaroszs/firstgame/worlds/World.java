package dev.jaroszs.firstgame.worlds;

import dev.jaroszs.firstgame.Game;
import dev.jaroszs.firstgame.Handler;
import dev.jaroszs.firstgame.entities.EntityManager;
import dev.jaroszs.firstgame.entities.creatures.Player;
import dev.jaroszs.firstgame.entities.statics.Rock;
import dev.jaroszs.firstgame.entities.statics.Tree;
import dev.jaroszs.firstgame.items.ItemManager;
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

    //Entities
    private EntityManager entityManager;

    //Item
    private ItemManager itemManager;

    public World(Handler handler, String path){
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 200, 200));
        itemManager = new ItemManager(handler);
        //Temporary entity code!
        entityManager.addEntity(new Tree(handler, 400, 400));
        entityManager.addEntity(new Rock(handler, 400, 700));
        entityManager.addEntity(new Tree(handler, 400, 800));
        entityManager.addEntity(new Rock(handler, 400, 1100));

        loadWorld(path);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);

    }

    public void tick(){
        entityManager.tick();
        itemManager.tick();
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
        //Items
        itemManager.render(g);
        //Entities
        entityManager.render(g);
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
