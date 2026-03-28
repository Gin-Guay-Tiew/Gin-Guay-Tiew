package ui.pages.loadingScreen;

import utilities.IconImage;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class TripleIcons extends JPanel {

    private final Random random = new Random();

    public TripleIcons() {
        setLayout(new GridLayout(1,3));
        setBorder(BorderFactory.createEmptyBorder(0, 40, 135, 35));

        ImageIcon img_1 = IconImage.create("/images/loadingScreen/randomIcon/"+this.GetRandom()+".png", 125, 125); // Icon for JLabel
        JLabel icon_1 = new JLabel(img_1);

        ImageIcon img_2 = IconImage.create("/images/loadingScreen/randomIcon/"+this.GetRandom()+".png", 125, 125); // Icon for JLabel
        JLabel icon_2 = new JLabel(img_2);

        ImageIcon img_3 = IconImage.create("/images/loadingScreen/randomIcon/"+this.GetRandom()+".png", 125, 125); // Icon for JLabel
        JLabel icon_3 = new JLabel(img_3);

        add(icon_1);
        add(icon_2);
        add(icon_3);
    }

    private String GetRandom() {
        return String.valueOf(random.nextInt(3) + 1);
    }
}
