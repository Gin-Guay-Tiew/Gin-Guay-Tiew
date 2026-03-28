package ui.pages.tutorialGame;

import utilities.SFX;
import utilities.SFXManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class AbstractNavButton extends JLabel {
    protected ImageIcon normalIcon;
    protected ImageIcon hoverIcon;
    protected ImageIcon clickIcon;

    protected Runnable clickAction;
    protected boolean buttonEnabled = true;
    protected boolean mouseInside = false;

    public AbstractNavButton(String normalPath, String hoverPath, String clickPath, int width, int height) {
        // โหลดรูปต้นฉบับ
        ImageIcon tempNormal = new ImageIcon(normalPath);
        ImageIcon tempHover = new ImageIcon(hoverPath);
        ImageIcon tempClick = new ImageIcon(clickPath);

        // ปรับขนาดรูปภาพ
        Image imgNormal = tempNormal.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        Image imgHover = tempHover.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        Image imgClick = tempClick.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        // บันทึกรูปที่ปรับขนาดแล้ว
        normalIcon = new ImageIcon(imgNormal);
        hoverIcon = new ImageIcon(imgHover);
        clickIcon = new ImageIcon(imgClick);

        setIcon(normalIcon);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!buttonEnabled) return;
                mouseInside = true;
                setIcon(hoverIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!buttonEnabled) return;
                mouseInside = false;
                setIcon(normalIcon);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (!buttonEnabled) return;
                setIcon(clickIcon);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!buttonEnabled) return;

                System.out.println("BUTTON RELEASED");

                if (mouseInside) {
                    setIcon(hoverIcon);
                    if (clickAction != null && contains(e.getPoint())) {

                        System.out.println("PLAY CLICK SFX");
                        SFXManager.play(SFX.CLICK);
                        clickAction.run();
                    }
                } else {
                    setIcon(normalIcon);
                }
            }
        });
    }

    // Abstract Method
    public abstract void setClickAction(Runnable clickAction);

    public abstract void setButtonEnabled(boolean enabled);
}