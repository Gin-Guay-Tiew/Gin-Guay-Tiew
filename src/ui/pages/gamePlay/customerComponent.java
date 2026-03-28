package ui.pages.gamePlay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class customerComponent extends JPanel {
    private JLabel imgLabel;
    private JLabel patienceLabel;
    private int life;
    private JPanel bubble;
    private String pathImage;
    private String typeCustomer;
    private int state_bored;
    private int state_angry;
    private int money_bored;
    private int money_angry;
    private CustomerData data;

    public customerComponent(CustomerData data, String imgPath ,String patiencePath,String type,String skin, int life,JPanel bubble, Runnable onClick){
        this.data = data;
        this.typeCustomer = type;
        Object[] results = setState(type);
        pathImage = "resources/images/gamePlay/customer/"+type+"/"+skin;
        state_bored = (int) results[0];
        state_angry = (int) results[1];
        this.life = life;
        this.bubble = bubble;

        setLayout(null);
        setOpaque(false);
        setSize(197,240);

        imgLabel = new JLabel(new ImageIcon(imgPath));
        imgLabel.setBounds(0,0,160,240);

        patienceLabel = new JLabel();
        patienceLabel.setBounds(137,83,60,107);
        setPatienceGif(patiencePath);

        add(imgLabel);
        add(patienceLabel);
    }

    public CustomerData getData() {
        return data;
    }

    public void setPatienceGif(String path){
        patienceLabel.setIcon(null);

        Image img = Toolkit.getDefaultToolkit().createImage(path);
        ImageIcon icon = new ImageIcon(img);

        patienceLabel.setIcon(icon);
    }

    public JPanel getBubble(){
        return bubble;
    }

    public void tick(){
        life--;
        if (life == state_bored){
            changeMoney();
            data.setMoney(money_bored);
            imgLabel.setIcon( new ImageIcon(pathImage+"_bored.png"));
            //System.out.println(data.toString()+"bored");
        } else if (life == state_angry){
            changeMoney();
            data.setMoney(money_angry);
            imgLabel.setIcon( new ImageIcon(pathImage+"_angry.png"));
            //System.out.println(data.toString()+"angry");
        }
    }

    public boolean isExpired(){
        return life <= 0;
    }

    public Object[] setState(String typeCustomer){
        if (typeCustomer.equals("general") || typeCustomer.equals("mars")){
            return new Object[]{24, 4};
        } else if (typeCustomer.equals("hungry") || typeCustomer.equals("VIP")){
            return new Object[]{9, 4};
        } else if (typeCustomer.equals("JarnBank")){
            return new Object[]{10, 4};
        } else if (typeCustomer.equals("working")){
            return new Object[]{20, 10};
        }
        return new Object[]{30, 21};
    }


    public void playSpawnBounce(int targetX, int targetY) {
        int startY = targetY + 100;
        setLocation(targetX, startY);

        int duration = 400;
        long startTime = System.currentTimeMillis();

        Timer timer = new Timer(16, null);
        timer.addActionListener(e -> {
            float t = (System.currentTimeMillis() - startTime) / (float) duration;
            if (t > 1) t = 1;

            float eased = easeOutBack(t);

            int currentY = (int) (startY + (targetY - startY) * eased);
            setLocation(targetX, currentY);

            if (t >= 1) {
                timer.stop();
            }
        });

        timer.start();
    }

    // easing เด้ง
    private float easeOutBack(float t) {
        float c1 = 1.70158f;
        float c3 = c1 + 1;
        return 1 + c3 * (float)Math.pow(t - 1, 3)
                + c1 * (float)Math.pow(t - 1, 2);
    }

    public void changeMoney() {
        int levelId = data.getlevelID();

        switch (typeCustomer) {

            case "general":
            case "mars":
                setMoneyByLevel(levelId,
                        new int[]{40,45,50,55,60},
                        new int[]{30,35,40,45,50});
                break;

            case "hungry":
                setMoneyByLevel(levelId,
                        new int[]{0,55,50,55,60},
                        new int[]{0,45,50,55,60});
                break;

            case "VIP":
                setMoneyByLevel(levelId,
                        new int[]{0,0,70,75,80},
                        new int[]{0,0,70,75,80});
                break;

            case "working":
                setMoneyByLevel(levelId,
                        new int[]{45,50,55,50,65},
                        new int[]{35,40,45,50,55});
                break;

            case "JarnBank":
                money_bored = data.getMoney();
                money_angry = data.getMoney();
                break;
        }
    }

    private void setMoneyByLevel(int level, int[] bored, int[] angry) {
        if (level >= 1 && level <= bored.length) {
            money_bored = bored[level - 1];
            money_angry = angry[level - 1];
        }
    }

}
