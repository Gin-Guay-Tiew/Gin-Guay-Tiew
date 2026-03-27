package ui.pages.gamePlay;

public class CustomerData implements CustomerPay{
    public String type;
    public String skin;
    public String imgPath;
    public String patiencePath;
    public String foodPath;
    public int levelID;
    public int money;
    public int x,y;

    public int life;

    public CustomerData(int levelID,String img, String patience,String type,String skin,String foodPath,int money, int x, int y, int life){
        this.levelID = levelID;
        this.type = type;
        this.skin = skin;
        this.imgPath = img;
        this.money = money;
        this.patiencePath = patience;
        this.foodPath = foodPath;
        this.x = x;
        this.y = y;
        this.life = life;
    }

    public int pay(){
        return money;
    }

    public void setMoney(int money){
        this.money = money;
    }

    public int getMoney(){
        return money;
    }

    public int getlevelID(){
        return levelID;
    }

    @Override
    public String toString() {
        return "CustomerData"+"\n" +
                "levelID= "+ levelID+'\n'+
                "type='" + type + '\n' +
                ", foodPath='" + foodPath + '\n' +
                ", money=" + money+'\n';
    }

}