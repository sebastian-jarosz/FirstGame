package dev.jaroszs.firstgame.ui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject{

    private BufferedImage[] images;
    private ClickListener clickListener;

    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clickListener) {
        super(x, y, width, height);
        this.images = images;
        this.clickListener = clickListener;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if(hovering){
            g.drawImage(images[1], (int) x, (int) y, null);
        } else {
            g.drawImage(images[0], (int) x, (int) y, null);
        }
    }

    @Override
    public void onClick() {
        clickListener.onClick();
    }
}
