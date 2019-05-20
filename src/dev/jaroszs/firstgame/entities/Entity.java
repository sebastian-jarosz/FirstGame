package dev.jaroszs.firstgame.entities;

import dev.jaroszs.firstgame.Game;
import dev.jaroszs.firstgame.Handler;

import java.awt.*;

public abstract class Entity {

    protected Handler handler;
    //Position of entity
    protected float x;
    protected float y;

    //Image size
    protected int width;
    protected int height;

    protected Rectangle bounds;

    public float getX() {
        return x;
    }

    public Entity(Handler handler, float x, float y, int width, int height){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        bounds = new Rectangle(0,0, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
