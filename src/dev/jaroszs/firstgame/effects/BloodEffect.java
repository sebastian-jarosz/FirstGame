package dev.jaroszs.firstgame.effects;

import dev.jaroszs.firstgame.gfx.Assets;

import java.awt.*;

public class BloodEffect extends Effect{

    public BloodEffect() {
        super(Assets.blood, 0);
    }

    @Override
    public void render(Graphics g, int x, int y) {
        super.render(g, x, y);
    }
}
