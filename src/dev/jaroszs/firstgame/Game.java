package dev.jaroszs.firstgame;

import dev.jaroszs.firstgame.display.Display;
import dev.jaroszs.firstgame.gfx.Assets;
import dev.jaroszs.firstgame.gfx.ImageLoader;
import dev.jaroszs.firstgame.gfx.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable{

    private Display display;
    private SpriteSheet sheet;

    public String title;
    public int width;
    public int height;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    private BufferedImage testImage;

    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
    }

    private void init(){
        display = new Display(title, width, height);
        Assets.init();
//        For drawing images
//        testImage = ImageLoader.loadImage("/textures/sheet.png");
//        sheet = new SpriteSheet(testImage);
    }

    int x = 0;
    int y = 0;

    private void tick(){
        x += 1;
    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear screen
        g.clearRect(0,0, width, height);


        //Draw here!
//        FIRST TRY
//        g.setColor(Color.red);
//        g.fillRect(0,0, width, height);

//        Loading Images
//        g.drawImage(testImage, 20, 20, null);

//        Sprite Sheet
//        g.drawImage(sheet.crop(0,0,115, 115), 5, 5, null);

        g.drawImage(Assets.grass,x,y,null);

        //End drawing!


        //Need to be at the end
        bs.show();
        g.dispose();
    }

    @Override
    public void run() {

        init();

        int fps = 60;
        double timePerTick = 1000000000/fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running){
            now = System.nanoTime();
            delta += (now - lastTime)/timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

//            how many ticks and frames
            if(timer >= 1000000000){
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop();

    }

    public synchronized void start(){
        if(running){
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
