package pages.endGame;

import pages.MainFrame;
import utilities.CustomFontLoader;
import utilities.IconBtn;
import utilities.IconImage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageBtn extends JPanel implements ActionListener {
    private MainFrame mainFrame;
    private final Font jerseyFont = CustomFontLoader.loadCustomFont("resources/Jersey10.ttf");
    private JButton backBtn, playAgainBtn, shopBtn;

    public ManageBtn(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setOpaque(false);
        setBorder(new EmptyBorder(20,30,100,30));

        // ================= Button Images =================
        ImageIcon normal = IconImage.create(
                "resources/images/mainMenu/btn-start-main.png",
                250, 50
        );
        ImageIcon hover = IconImage.create(
                "resources/images/mainMenu/btn-start-hover.png",
                250, 50
        );
        ImageIcon pressed = IconImage.create(
                "resources/images/mainMenu/btn-start-press.png",
                250, 50
        );

        backBtn = new IconBtn(normal, hover, pressed);
        playAgainBtn = new IconBtn(normal, hover, pressed);
        shopBtn = new IconBtn(normal, hover, pressed);

        add(backBtn);
        add(playAgainBtn);
        add(shopBtn);

        // ================= Action =================
        backBtn.setActionCommand("backToMainMenu");
        playAgainBtn.setActionCommand("playAgain");
        shopBtn.setActionCommand("shop");
        backBtn.addActionListener(this);
        playAgainBtn.addActionListener(this);
        shopBtn.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("backToMainMenu")) {
            mainFrame.getNavigator().toPage("mainMenu",true,500);
        }
    }
}
