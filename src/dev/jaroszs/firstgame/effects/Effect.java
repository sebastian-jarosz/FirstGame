package dev.jaroszs.firstgame.effects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Effect {

    //Static stuff here

    public static Effect[] effects = new Effect[256];


    //Class

    public static final int EFFECT_WIDTH = 64,
                            EFFECT_HEIGHT = 64;

    protected BufferedImage effect;
    protected final int id;

    public Effect(int id) {
        this.id = id;
    }

    public Effect(BufferedImage effect, int id) {
        this.effect = effect;
        this.id = id;
        effects[id] = this;
    }

    public void tick(){

    }

    public void render(Graphics g, int x, int y){
        g.drawImage(effect, x, y, EFFECT_WIDTH, EFFECT_HEIGHT, null);
    }


}
