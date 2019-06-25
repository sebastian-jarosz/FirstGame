package dev.jaroszs.firstgame.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys;
    private boolean[] justPressed;
    private boolean[] cantPress;
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    public boolean aUp;
    public boolean aDown;
    public boolean aLeft;
    public boolean aRight;

    public KeyManager() {
        keys = new boolean[256];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];
    }

    public void tick(){
        for (int i = 0; i < keys.length; i++) {
            if(cantPress[i] && !keys[i]){
                cantPress[i] = false;
            } else if (justPressed[i]){
                cantPress[i] = true;
                justPressed[i] = false;
            }
            if(!cantPress[i] && keys[i]){
                justPressed[i] = true;
            }
        }

        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        aUp = keys[KeyEvent.VK_UP];
        aDown = keys[KeyEvent.VK_DOWN];
        aLeft = keys[KeyEvent.VK_LEFT];
        aRight = keys[KeyEvent.VK_RIGHT];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length){
            return;
        }
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length){
            return;
        }
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public boolean keyJustPressed(int keyCode) {
        if (keyCode < 0 || keyCode >= keys.length) {
            return false;
        }
        return justPressed[keyCode];
    }
}
