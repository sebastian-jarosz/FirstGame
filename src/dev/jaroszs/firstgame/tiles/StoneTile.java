package dev.jaroszs.firstgame.tiles;

import dev.jaroszs.firstgame.gfx.Assets;

import java.awt.image.BufferedImage;

public class StoneTile extends Tile {

    public StoneTile(int id) {
        super(Assets.stone, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
