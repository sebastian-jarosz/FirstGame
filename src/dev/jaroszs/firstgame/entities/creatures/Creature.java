package dev.jaroszs.firstgame.entities.creatures;

import dev.jaroszs.firstgame.Game;
import dev.jaroszs.firstgame.Handler;
import dev.jaroszs.firstgame.entities.Entity;

public abstract class Creature extends Entity {

    public static final int DEFAULT_HEALTH = 100;
    public static final float DEFAULT_SPEED = 3.0f;
    //Default size of creature
    public static final int DEFAULT_CREATURE_WIDTH = 64,
                            DEFAULT_CREATURE_HEIGHT = 64;

    protected int health;
    protected float speed;
    protected float xMove;
    protected float yMove;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move(){
        x+=xMove;
        y+=yMove;
    }

    //Getters and setters

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }
}
