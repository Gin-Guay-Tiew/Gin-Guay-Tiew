package pages.mainMenu;

import pages.MainFrame;
import utilities.IconFilter;
import utilities.IconImage;
import utilities.IconBtn;

import javax.swing.*;
import java.awt.*;

public class MainMenuPage extends JPanel {

    MainFrame frame;
    private Image backgroundImage;

    JButton yes = new JButton("Yes");
    JButton no = new JButton("No");
    JDialog dialog;

    public MainMenuPage(MainFrame frame) {

        this.frame = frame;
        setLayout(new BorderLayout());

        dialog = new PopupWindow().PopupTutorial(
                frame,"tutorial",true,
                "Do you want to play Tutorial",yes,no
        );

        ImageIcon original = new ImageIcon(
                "resources/images/mainMenu/image-from-rawpixel-id-14653376-jpeg.jpg"
        );
        ImageIcon newBackground = IconFilter.setOpacity(original, 0.35f);
        backgroundImage = newBackground.getImage();

        // ================= Center Container =================
        JPanel center_contain = new JPanel();
        center_contain.setLayout(new BoxLayout(center_contain, BoxLayout.Y_AXIS));
        center_contain.setOpaque(false);

        // ================= Logo =================
        ImageIcon icon = IconImage.create(
                "resources/images/mainMenu/logoGame-type01.png",
                280, 280
        );

        JLabel logo = new JLabel(icon);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        center_contain.add(logo);

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

        ImageIcon enormal = IconImage.create(
                "resources/images/mainMenu/btn-start-main.png",
                510, 50
        );
        ImageIcon ehover = IconImage.create(
                "resources/images/mainMenu/btn-start-hover.png",
                510, 50
        );
        ImageIcon epressed = IconImage.create(
                "resources/images/mainMenu/btn-start-press.png",
                510, 50
        );

        JButton start_game = new IconBtn(normal, hover, pressed);
        JButton tutorial = new IconBtn(normal, hover, pressed);
        JButton shop = new IconBtn(normal, hover, pressed);
        JButton setting = new IconBtn(normal, hover, pressed);
        JButton exit = new IconBtn(enormal, ehover, epressed);

        // ================= Row 1 =================
        JPanel row01 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        row01.setOpaque(false);
        row01.setMaximumSize(new Dimension(Integer.MAX_VALUE, normal.getIconHeight()));
        row01.add(start_game);
        row01.add(tutorial);

        // ================= Row 2 =================
        JPanel row02 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        row02.setOpaque(false);
        row02.setMaximumSize(new Dimension(Integer.MAX_VALUE, normal.getIconHeight()));
        row02.add(shop);
        row02.add(setting);

        // ================= Exit Row =================
        JPanel exitRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        exitRow.setOpaque(false);
        exitRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, enormal.getIconHeight()));
        exitRow.add(exit);

        // ================= Add to Container =================
        center_contain.add(row01);
        center_contain.add(Box.createVerticalStrut(5));
        center_contain.add(row02);
        center_contain.add(Box.createVerticalStrut(8));
        center_contain.add(exitRow);

        add(center_contain, BorderLayout.CENTER);

        // ================= Action =================
        MainBtn action_btn = new MainBtn(dialog, frame);


        start_game.setActionCommand("Start Game");
        tutorial.setActionCommand("Tutorial");
        shop.setActionCommand("Shop");
        setting.setActionCommand("Setting");
        exit.setActionCommand("Exit");


        start_game.addActionListener(action_btn);
        tutorial.addActionListener(action_btn);
        shop.addActionListener(action_btn);
        exit.addActionListener(action_btn);
        setting.addActionListener(action_btn);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public JDialog getDialog() {
        return dialog;
    }
}