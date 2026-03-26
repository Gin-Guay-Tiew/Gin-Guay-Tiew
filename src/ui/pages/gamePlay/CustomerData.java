package ui.pages.gamePlay;

public class CustomerData {
    public String type;
    public String skin;
    public String imgPath;
    public String patiencePath;
    public int x,y;

    public int life;

    public CustomerData(String img, String patience,String type,String skin, int x, int y, int life){
        this.type = type;
        this.skin = skin;
        this.imgPath = img;
        this.patiencePath = patience;
        this.x = x;
        this.y = y;
        this.life = life;
    }
}