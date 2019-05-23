package dev.jaroszs.firstgame.entities.statics;

import dev.jaroszs.firstgame.Handler;
import dev.jaroszs.firstgame.entities.Entity;

public abstract class StaticEntity extends Entity {

    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }


}
