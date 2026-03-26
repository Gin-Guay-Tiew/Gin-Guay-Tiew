package ui.pages.gamePlay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class customerComponent extends JPanel {
    private JLabel imgLabel;
    private JLabel patienceLabel;
    private int life;
    private String pathImage;
    private int state_bored;
    private int state_angry;

    public customerComponent(String imgPath ,String patiencePath,String type,String skin, int life, Runnable onClick){
        Object[] results = setState(type);
        pathImage = "resources/images/gamePlay/customer/"+type+"/"+skin;
        state_bored = (int) results[0];
        state_angry = (int) results[1];
        this.life = life;
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


        MouseAdapter clickHandler = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onClick.run();
            }
        };

        this.addMouseListener(clickHandler);
        imgLabel.addMouseListener(clickHandler);
        patienceLabel.addMouseListener(clickHandler);
    }
    public void setPatienceGif(String path){
        patienceLabel.setIcon(null);

        Image img = Toolkit.getDefaultToolkit().createImage(path);
        ImageIcon icon = new ImageIcon(img);

        patienceLabel.setIcon(icon);
    }



    public void tick(){
        life--;
        if (life == state_bored){
            imgLabel.setIcon( new ImageIcon(pathImage+"_bored.png"));
        } else if (life == state_angry){
            imgLabel.setIcon( new ImageIcon(pathImage+"_angry.png"));
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
}