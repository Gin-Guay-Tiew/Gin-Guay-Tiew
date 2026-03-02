package ui.pages.gamePlay;

import javax.swing.*;
import java.awt.*;

public class SlotSpec {
    private final String id;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final String iconPath;

    public SlotSpec(String id ,int x ,int y , int width , int height,String iconPath) {
        //record image property
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.iconPath = iconPath;
    }

    public String getId() {
        return id;
    }


    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public String getIconPath() {
        return iconPath;
    }
}
