package dev.jaroszs.firstgame.entities.statics;

import dev.jaroszs.firstgame.Handler;
import dev.jaroszs.firstgame.gfx.Assets;
import dev.jaroszs.firstgame.tiles.Tile;

import java.awt.*;

public class Tree extends StaticEntity {

    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT * 2);

        bounds.x = 54;
        bounds.y = 128;
        bounds.width = 20;
        bounds.height = 128;

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die() {

    }
}
