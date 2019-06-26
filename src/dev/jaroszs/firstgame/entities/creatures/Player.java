package dev.jaroszs.firstgame.entities.creatures;

import dev.jaroszs.firstgame.Handler;
import dev.jaroszs.firstgame.entities.Entity;
import dev.jaroszs.firstgame.gfx.Animation;
import dev.jaroszs.firstgame.gfx.Assets;
import dev.jaroszs.firstgame.inventory.Inventory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    //Animation
    private Animation animDown;
    private Animation animUp;
    private Animation animLeft;
    private Animation animRight;
    private Animation animStanding;
    private int animSpeed = 300;

    //Attack timer
    private long lastAttackTimer;
    private long attackCooldown = 800;
    private long attackTimer = attackCooldown;

    //Inventory
    private Inventory inventory;

    //Movement
    private boolean runningActive = false;

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

        inventory = new Inventory(handler);
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
        //Attacks
        checkAttacks();
        //Inventory
        inventory.tick();
    }

    private void checkAttacks() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();

        if(attackTimer < attackCooldown){
            return;
        }

        if(inventory.isActive()){
            return;
        }

        Rectangle cB = getCollisionBounds(0,0); //Collision Bounds
        Rectangle aR = new Rectangle(); //Attack rectangle
        int aRSize = 64;
        aR.width = aRSize;
        aR.height = aRSize;

        if(handler.getKeyManager().aUp){
            aR.x = cB.x + cB.width / 2 - aRSize / 2;
            aR.y = cB.y - aRSize;
        } else if(handler.getKeyManager().aDown){
            aR.x = cB.x + cB.width / 2 - aRSize / 2;
            aR.y = cB.y + cB.height;
        } else if(handler.getKeyManager().aLeft){
            aR.x = cB.x - aRSize;
            aR.y = cB.y + cB.height / 2 - aRSize /2;
        } else if(handler.getKeyManager().aRight){
            aR.x = cB.x + cB.width;
            aR.y = cB.y + cB.height / 2 - aRSize /2;
        } else {
            return;
        }

        attackTimer = 0;

        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if(e.equals(this)){
                continue;
            }
            if(e.getCollisionBounds(0,0).intersects(aR)){
                e.hurt(10);

                return;
            }
        }
    }

    private void getInput(){

        if(inventory.isActive()){
            return;
        }

        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_SHIFT)){
            runningActive = !runningActive;
        }

        if(runningActive){
            speed = DEFAULT_SPEED * 2;
        } else {
            speed = DEFAULT_SPEED;
        }

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
        inventory.render(g);
    }

    public void postRender(Graphics g){
        inventory.render(g);
    }

    @Override
    public void die() {
        System.out.println("You lose!");
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

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
