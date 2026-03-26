package ui.pages.gamePlay;

public class CustomerData {
    public String imgPath;
    public String patiencePath;
    public String requestFood;
    public int x,y;


    public CustomerData(String img,String patience,String requestFood,int x ,int y){
        this.imgPath = img;
        this.patiencePath = patience;
        this.requestFood = requestFood;
        this.x = x;
        this.y = y;
    }
}
