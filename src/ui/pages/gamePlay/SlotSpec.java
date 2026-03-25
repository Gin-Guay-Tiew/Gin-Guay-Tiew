package ui.pages.gamePlay;

public class SlotSpec {
    private final String id;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final String iconPath;
    private String type;
    private String spawnPath;

    public SlotSpec(String id ,int x ,int y , int width , int height,String iconPath,String type,String spawnPath) {
        //record image property
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.iconPath = iconPath;
        this.type = type;
        this.spawnPath = spawnPath;
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

    public String getType() { return type;}

    public String getSpawnPath() {return spawnPath;}
}
