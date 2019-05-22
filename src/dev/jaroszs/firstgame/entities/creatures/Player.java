package dev.jaroszs.firstgame.entities.creatures;

import dev.jaroszs.firstgame.Handler;
import dev.jaroszs.firstgame.gfx.Animation;
import dev.jaroszs.firstgame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    //Animation
    private Animation animDown;
    private Animation animUp;
    private Animation animLeft;
    private Animation animRight;
    private Animation animStanding;
    private int animSpeed = 300;



    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        bounds.x = 46;
        bounds.y = 0;
        bounds.width = 34;
        bounds.height = 120;

        //Animations
        animStanding = new Animation(animSpeed, Assets.playes_stands);
        animDown = new Animation(animSpeed, Assets.player_down);
        animUp = new Animation(animSpeed, Assets.player_up);
        animRight = new Animation(animSpeed, Assets.player_right);
        animLeft = new Animation(animSpeed, Assets.player_left);
    }

    @Override
    public void tick() {
        //Animations
        animDown.tick();
        animUp.tick();
        animLeft.tick();
        animRight.tick();
        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up){
            yMove = -speed;
        }
        if(handler.getKeyManager().down){
            yMove = speed;
        }
        if(handler.getKeyManager().left){
            xMove = -speed;
        }
        if(handler.getKeyManager().right){
            xMove = speed;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(),(int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), width, height, null);

    }

    private BufferedImage getCurrentAnimationFrame(){
        if(xMove < 0){
            return animLeft.getCurrentFrame();
        } else if(xMove > 0){
            return animRight.getCurrentFrame();
        } else if (yMove < 0){
            return animUp.getCurrentFrame();
        } else if (yMove > 0){
            return animDown.getCurrentFrame();
        } else {
            return animStanding.getCurrentFrame();
        }
    }
}
