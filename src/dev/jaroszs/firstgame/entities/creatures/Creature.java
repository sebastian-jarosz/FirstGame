package dev.jaroszs.firstgame.entities.creatures;

import dev.jaroszs.firstgame.entities.Entity;

public abstract class Creature extends Entity {

    protected int health;

    public Creature(float x, float y) {
        super(x, y);
        health = 10;
    }

}
