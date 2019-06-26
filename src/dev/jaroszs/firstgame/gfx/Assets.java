package dev.jaroszs.firstgame.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 128;
    private static final int height = 128;

    public static Font font28;

    public static BufferedImage grass;
    public static BufferedImage dirt;
    public static BufferedImage stone;
    public static BufferedImage tree;
    public static BufferedImage wood;
    public static BufferedImage rock;
    public static BufferedImage blood;

    public static BufferedImage[] playes_stands;
    public static BufferedImage[] player_down;
    public static BufferedImage[] player_up;
    public static BufferedImage[] player_right;
    public static BufferedImage[] player_left;
    
    public static BufferedImage[] zombie_down;
    public static BufferedImage[] zombie_up;
    public static BufferedImage[] zombie_right;
    public static BufferedImage[] zombie_left;

    public static BufferedImage[] button_start;

    public static BufferedImage inventoryScreen;

    public static void init(){
        font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheetBigger2.png"));

        inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");

        button_start = new BufferedImage[2];
        button_start[0] = sheet.crop(width * 4, 0, width * 2, height);
        button_start[1] = sheet.crop(width * 4, height, width * 2, height);

        playes_stands = new BufferedImage[2];
        player_down = new BufferedImage[2];
        player_up = new BufferedImage[2];
        player_left = new BufferedImage[2];
        player_right = new BufferedImage[2];

        zombie_down = new BufferedImage[2];
        zombie_up = new BufferedImage[2];
        zombie_left = new BufferedImage[2];
        zombie_right = new BufferedImage[2];

        playes_stands[0] = sheet.crop(0, 0, width, height);
        playes_stands[1] = sheet.crop(0, 0, width, height);

        player_down[0] = sheet.crop(0, height * 2, width, height);
        player_down[1] = sheet.crop(width, height * 2, width, height);

        player_up[0] = sheet.crop(width * 2, height * 2, width, height);
        player_up[1] = sheet.crop(width * 3, height * 2, width, height);

        player_right[0] = sheet.crop(0, height * 3, width, height);
        player_right[1] = sheet.crop(width, height * 3, width, height);

        player_left[0] = sheet.crop(width * 2, height * 3, width, height);
        player_left[1] = sheet.crop(width * 3, height * 3, width, height);

        zombie_down[0] = sheet.crop(0, height * 4, width, height);
        zombie_down[1] = sheet.crop(width, height * 4, width, height);

        zombie_up[0] = sheet.crop(width * 2, height * 4, width, height);
        zombie_up[1] = sheet.crop(width * 3, height * 4, width, height);

        zombie_right[0] = sheet.crop(0, height * 5, width, height);
        zombie_right[1] = sheet.crop(width, height * 5, width, height);

        zombie_left[0] = sheet.crop(width * 2, height * 5, width, height);
        zombie_left[1] = sheet.crop(width * 3, height * 5, width, height);

        grass = sheet.crop(width,0, width, height);
        dirt = sheet.crop(width * 2,0, width, height);
        stone = sheet.crop(width * 3,0, width, height);
        tree = sheet.crop(0, height, width, height);
        wood = sheet.crop(width, height, width, height);
        rock = sheet.crop(width * 2, height, width, height);
        blood = sheet.crop(width * 3, height, width, height);
    }


}
