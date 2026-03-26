package ui.pages.tutorialGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameTutorialPage extends JPanel {

    private static final int TOTAL_PAGES = 8;
    private int currentPage = 1;

    private JLabel pageLabel;
    private ArrowButton ArrowLeft;
    private ArrowButton ArrowRight;
    private int btnWidth = 80;
    private int btnHeight = 80;

    private Image backgroundImage;
    private Image backgroundImage_Two;
    private Image panelFrameImage;

    public GameTutorialPage() {
        //JLabel t = new JLabel("Welcome, new player. This is a training session.");
        //add(t);
        setLayout(null);
        setPreferredSize(new Dimension(800, 600));
        backgroundImage = new ImageIcon("resources/images/mainMenu/Background.gif").getImage();
        backgroundImage_Two = new ImageIcon("resources/images/Tutorial/BackGround_Tutorial.png").getImage();
        panelFrameImage = new ImageIcon("resources/images/Tutorial/Frame.png").getImage();

        pageLabel = new JLabel();
        pageLabel.setBounds(0, 0, 562, 342);
        // ใส่เส้นขอบสีแดง pageLabel
        pageLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        pageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(pageLabel);

        ArrowLeft = new ArrowButton(
                "resources/images/Tutorial/ArrowInTutorial/ArrowLeft.png",
                "resources/images/Tutorial/ArrowInTutorial/ArrowLeft_Hover.png",
                "resources/images/Tutorial/ArrowInTutorial/ArrowLeft_Click.png",
                btnWidth,btnHeight
        );
        ArrowLeft.setBounds(10, 232, btnWidth, btnHeight);
        ArrowLeft.setClickAction(() -> {
            if (currentPage > 1) {
                currentPage--;
                updatePage();
            }
        });
        add(ArrowLeft);

        ArrowRight = new ArrowButton(
                "resources/images/Tutorial/ArrowInTutorial/ArrowRight.png",
                "resources/images/Tutorial/ArrowInTutorial/ArrowRight_Hover.png",
                "resources/images/Tutorial/ArrowInTutorial/ArrowRight_Click.png",
                btnWidth,80
        );
        ArrowRight.setBounds(710, 232, btnWidth, btnHeight);
        ArrowRight.setClickAction(() -> {
            if (currentPage < TOTAL_PAGES) {
                currentPage++;
                updatePage();
            }
        });
        add(ArrowRight);

        updatePage();
    }

    private void updatePage() {
        String pagePath = "resources/images/Tutorial/Tutorial_Page/Tutorial_page " + currentPage + ".png";
        ImageIcon icon = new ImageIcon(pagePath);

        Image scaled = icon.getImage().getScaledInstance(
                pageLabel.getWidth(),
                pageLabel.getHeight(),
                Image.SCALE_SMOOTH
        );
        pageLabel.setIcon(new ImageIcon(scaled));

        ArrowLeft.setButtonEnabled(currentPage > 1);
        ArrowRight.setButtonEnabled(currentPage < TOTAL_PAGES);

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        if (backgroundImage_Two != null) {
            g.drawImage(backgroundImage_Two, 0, 0, getWidth(), getHeight(), this);
        }

        if (panelFrameImage != null) {
            g.drawImage(panelFrameImage, 39, 29, 575, 362, this);
        }
    }

    static class ArrowButton extends JLabel {

        private final ImageIcon normalIcon;
        private final ImageIcon hoverIcon;
        private final ImageIcon clickIcon;

        private Runnable clickAction;
        private boolean buttonEnabled = true;
        private boolean mouseInside = false;

        public ArrowButton(String normalPath, String hoverPath, String clickPath, int width, int height) {
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

                    if (mouseInside) {
                        setIcon(hoverIcon);
                        if (clickAction != null && contains(e.getPoint())) {
                            clickAction.run();
                        }
                    } else {
                        setIcon(normalIcon);
                    }
                }
            });
        }

        public void setClickAction(Runnable clickAction) {
            this.clickAction = clickAction;
        }

        public void setButtonEnabled(boolean enabled) {
            this.buttonEnabled = enabled;

            if (!enabled) {
                setIcon(normalIcon);
                setEnabled(false);
            } else {
                setEnabled(true);
                setIcon(mouseInside ? hoverIcon : normalIcon);
            }
        }
    }
}