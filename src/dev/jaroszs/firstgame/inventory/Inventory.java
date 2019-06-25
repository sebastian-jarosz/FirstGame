package dev.jaroszs.firstgame.inventory;

import dev.jaroszs.firstgame.Handler;
import dev.jaroszs.firstgame.gfx.Assets;
import dev.jaroszs.firstgame.gfx.Text;
import dev.jaroszs.firstgame.items.Item;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Inventory {

    private Handler handler;
    private boolean active = false;
    private ArrayList<Item> inventoryItems;

    private int invX = 384;
    private int invY = 208;
    private int invWidth = 512;
    private int invHeight = 384;
    private int invListCenterX = invX + 171;
    private int invListCenterY = invY + invHeight / 2 + 5;
    private int invListSpacing = 30;

    private int invImageX = 772;
    private int invImageY = 242;
    private int invImageWidth = 64;
    private int invImageHeight = 64;

    private int invCountX = 804;
    private int invCountY = 332;

    private int selectedItem = 0;

    public Inventory(Handler handler){
        this.handler = handler;
        inventoryItems = new ArrayList<Item>();
    }

    public void tick(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)){
            active = !active;
        }
        if(!active){
            return;
        }

        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)){
            selectedItem--;
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)){
            selectedItem++;
        }

        if(selectedItem<0){
            selectedItem = inventoryItems.size() - 1;
        } else if (selectedItem >= inventoryItems.size()){
            selectedItem = 0;
        }

    }

    public void render(Graphics g){
        if(!active){
            return;
        }

        g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);

        int lenght = inventoryItems.size();
        if(lenght == 0){
            return;
        }
        for(int i = -5; i < 6 ; i++){
            if(selectedItem + i < 0 || selectedItem + i >= lenght) {
                continue;
            }
            if(i == 0) {
                Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " <", invListCenterX, invListCenterY + i * invListSpacing, true, Color.YELLOW, Assets.font28);
            } else {
                Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX, invListCenterY + i * invListSpacing, true, Color.WHITE, Assets.font28);
            }

            Item item = inventoryItems.get(selectedItem);
            g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
            Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.WHITE, Assets.font28);
        }
    }

    //Inventory methods

    public void addItem(Item item){
        for (Item i : inventoryItems) {
            if(i.getId() == item.getId()){
                i.setCount(i.getCount() + item.getCount());
                return;
            }
        }
        inventoryItems.add(item);
    }

    //Getters and Setters

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public boolean isActive() {
        return active;
    }
}
