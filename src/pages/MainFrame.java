package pages;

import pages.home.MainMenuPage;
import pages.levelSelection.LevelSelect;

import javax.swing.*;
import java.awt.*;



public class MainFrame extends JFrame{

    private CardLayout cardLayout = new CardLayout();
    private JPanel container = new JPanel(cardLayout);


    /* สร้างหน้า page จาก class ที่เราสร้างใน folder pages
    เมื่อสร้าง class page เสร็จแล้วในทำเอาไปใส่ container.add(ojb_page,/path)
    */
    public void showPage(String name){
        cardLayout.show(container, name);
    }

    public MainFrame(){
        setTitle("Gin-Guay-Tiew");
        setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        MainMenuPage mainMenuPage = new MainMenuPage(this);
        LevelSelect levelSelectPage = new LevelSelect(this);

        container.add(mainMenuPage,"mainMenu");
        container.add(levelSelectPage,"levelSelect");

        add(container);
        setVisible(true);

    }}
