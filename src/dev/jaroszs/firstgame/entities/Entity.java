package dev.jaroszs.firstgame.entities;

import java.awt.*;

public abstract class Entity {

    //Position of entity
    protected float x;
    protected float y;

    public Entity(float x, float y){
        this.x = x;
        this.y = y;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

}
