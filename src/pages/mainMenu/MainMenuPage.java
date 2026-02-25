package pages.mainMenu;

import pages.MainFrame;
import utilities.IconFilter;
import utilities.IconImage;
import utilities.ImageJButton;

import javax.swing.*;
import java.awt.*;

public class MainMenuPage extends JPanel {

    MainFrame frame;
    private Image backgroundImage;

    public MainMenuPage(MainFrame frame) {

        this.frame = frame;

        // เปลี่ยนเป็น GridBagLayout เพื่อให้จัดกึ่งกลางจริง ๆ
        setLayout(new GridBagLayout());

        // ================= Background =================
        ImageIcon original = new ImageIcon(
                "resources/images/mainMenu/image-from-rawpixel-id-14653376-jpeg.jpg"
        );
        ImageIcon newBackground = IconFilter.setOpacity(original, 0.35f);
        backgroundImage = newBackground.getImage();

        // ================= Center Container =================
        JPanel centerContain = new JPanel();
        centerContain.setLayout(new BoxLayout(centerContain, BoxLayout.Y_AXIS));
        centerContain.setOpaque(false);

        // ================= Logo =================
        ImageIcon icon = IconImage.create(
                "resources/images/mainMenu/logoGame-type01.png",
                280, 280
        );

        JLabel logo = new JLabel(icon);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerContain.add(logo);
        centerContain.add(Box.createVerticalStrut(20));

        // ================= Buttons =================
        JButton startGame = new ImageJButton("resources/images/mainMenu/BtnShort", ".png", 30,250,50);
        JButton tutorial  = new ImageJButton("resources/images/mainMenu/BtnShort", ".png", 30,250,50);
        JButton shop      = new ImageJButton("resources/images/mainMenu/BtnShort", ".png", 30,250,50);
        JButton setting   = new ImageJButton("resources/images/mainMenu/BtnShort", ".png", 30,250,50);
        JButton exit      = new ImageJButton("resources/images/mainMenu/BtnLong", ".png", 30,510,50);

        // ================= Row 1 =================
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        row1.setOpaque(false);
        row1.add(startGame);
        row1.add(tutorial);

        // ================= Row 2 =================
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        row2.setOpaque(false);
        row2.add(shop);
        row2.add(setting);

        // ================= Row 3 =================
        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        row3.setOpaque(false);
        row3.add(exit);

        // ================= Add rows with controlled spacing =================
        centerContain.add(row1);
        centerContain.add(row2);
        centerContain.add(row3);

        // ================= Add to center using GridBag =================
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        add(centerContain, gbc);

        // ================= Action =================
        MainBtn actionBtn = new MainBtn(frame);

        startGame.setActionCommand("Start Game");
        tutorial.setActionCommand("Tutorial");
        shop.setActionCommand("Shop");
        setting.setActionCommand("Setting");
        exit.setActionCommand("Exit");

        startGame.addActionListener(actionBtn);
        tutorial.addActionListener(actionBtn);
        shop.addActionListener(actionBtn);
        setting.addActionListener(actionBtn);
        exit.addActionListener(actionBtn);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}