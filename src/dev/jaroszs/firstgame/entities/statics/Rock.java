package dev.jaroszs.firstgame.entities.statics;

import dev.jaroszs.firstgame.Handler;
import dev.jaroszs.firstgame.gfx.Assets;
import dev.jaroszs.firstgame.items.Item;
import dev.jaroszs.firstgame.tiles.Tile;

import java.awt.*;

public class Rock extends StaticEntity {

    public Rock(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

        bounds.x = 13;
        bounds.y = 26;
        bounds.width = 100;
        bounds.height = 76;

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.rock, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int) x, (int) y));
    }
}
