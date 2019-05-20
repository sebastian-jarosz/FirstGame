package dev.jaroszs.firstgame;

import dev.jaroszs.firstgame.display.Display;
import dev.jaroszs.firstgame.gfx.Assets;
import dev.jaroszs.firstgame.gfx.GameCamera;
import dev.jaroszs.firstgame.gfx.ImageLoader;
import dev.jaroszs.firstgame.gfx.SpriteSheet;
import dev.jaroszs.firstgame.input.KeyManager;
import dev.jaroszs.firstgame.states.GameState;
import dev.jaroszs.firstgame.states.MenuState;
import dev.jaroszs.firstgame.states.SettingsState;
import dev.jaroszs.firstgame.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable{

    private Display display;
    private SpriteSheet sheet;

    public String title;
    private int width;
    private int height;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

//    For drawing image
//    private BufferedImage testImage;

    //States
    private State gameState;
    private State menuState;
    private State settingsState;

    //Input
    private KeyManager keyManager;

    //Camera
    private GameCamera gameCamera;

    //Handler
    private Handler handler;

    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
    }

    private void init(){
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0,0);

//        For drawing images
//        testImage = ImageLoader.loadImage("/textures/sheet.png");
//        sheet = new SpriteSheet(testImage);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        settingsState = new SettingsState(handler);
        State.setState(gameState);
    }


    private void tick(){
        keyManager.tick();
        if(State.getState() != null){
            State.getState().tick();
        }
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

        if(State.getState() != null){
            State.getState().render(g);
        }


        //Draw here!
//        FIRST TRY
//        g.setColor(Color.red);
//        g.fillRect(0,0, width, height);

//        Loading Images
//        g.drawImage(testImage, 20, 20, null);

//        Sprite Sheet
//        g.drawImage(sheet.crop(0,0,115, 115), 5, 5, null);


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

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public GameCamera getGameCamera(){
        return gameCamera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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
