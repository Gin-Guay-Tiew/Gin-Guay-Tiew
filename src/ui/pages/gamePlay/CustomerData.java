package ui.pages.gamePlay;

public class CustomerData {
    public String imgPath;
    public String patiencePath;
    public String foodPath;
    public int x,y;


    public CustomerData(String img,String patience,String foodPath,int x ,int y){
        this.imgPath = img;
        this.patiencePath = patience;
        this.foodPath = foodPath;
        this.x = x;
        this.y = y;
    }
}
