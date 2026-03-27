package ui.pages.settingMenu;

import main.MainFrame;
import logic.GamePlay.PlayerData;
import ui.components.CustomJLabel;
import ui.components.PopupWindow;
import utilities.FontLoader;
import ui.components.ImageJButton;
import utilities.IconImage;
import utilities.SoundManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.*;

public class MainSettingPage extends JPanel {

    private JSlider slider;
    private JLabel images01;
    private Image backgroundImage;
    PopupWindow pop = new PopupWindow();
    private final Font loadedFont = FontLoader.loadCustomFont("resources/font/Jersey10.ttf");

    public MainSettingPage(MainFrame frame, PlayerData playerData) {

        setLayout(new BorderLayout());

        JPanel main_container = new JPanel(new GridBagLayout());
        main_container.setOpaque(false);
        this.backgroundImage = new ImageIcon("resources/images/settingMenu/Background.gif").getImage();

        GridBagConstraints gbc = new GridBagConstraints();

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setOpaque(false);

        JLabel textAudio = new CustomJLabel("Audio", 5f);
        textAudio.setBorder(BorderFactory.createEmptyBorder(10, 45, 10, 10));
        textAudio.setFont(loadedFont.deriveFont(40f));
        textAudio.setForeground(Color.orange);
        textAudio.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel con_1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 5));
        con_1.setOpaque(false);
        con_1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        con_1.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel con_2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 5));
        con_2.setOpaque(false);
        con_2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        con_2.setAlignmentX(Component.LEFT_ALIGNMENT);

        ImageIcon iconMusic = IconImage.create("resources/images/settingMenu/speaker.png", 45, 45);
        ImageIcon iconMute = IconImage.create("resources/images/settingMenu/mute.png", 45, 45);
        ImageIcon iconFX = IconImage.create("resources/images/settingMenu/sound_fx.png", 45, 45);
        ImageIcon offIcon = IconImage.create("resources/images/settingMenu/off.png", 100, 45);
        ImageIcon onIcon = IconImage.create("resources/images/settingMenu/on.png", 100, 45);

        images01 = new JLabel(playerData.getVolumeLv() == 0 ? iconMute : iconMusic);
        images01.setPreferredSize(new Dimension(50, 50));

        JLabel text2 = new CustomJLabel("Music Volume", 5f);
        text2.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        text2.setForeground(Color.lightGray);
        text2.setFont(loadedFont.deriveFont(30f));

        slider = new JSlider(0, 100, playerData.getVolumeLv());
        slider.setOpaque(false);
        slider.setPreferredSize(new Dimension(200, 40));

        Image thumbImg = IconImage.create("resources/images/settingMenu/thumb.png", 25, 25).getImage();
        slider.setUI(new ModernSliderUI(slider, thumbImg));

        slider.addChangeListener(e -> {
            if (!slider.getValueIsAdjusting()) {
                int volume = slider.getValue();
                playerData.setVolumeLv(volume);
                SoundManager.setVolume(volume / 100f);
                images01.setIcon(volume == 0 ? iconMute : iconMusic);
            }
        });

        con_1.add(images01);
        con_1.add(text2);
        con_1.add(slider);

        JLabel images02 = new JLabel(iconFX);
        images02.setPreferredSize(new Dimension(50, 50));

        JLabel text03 = new CustomJLabel("Sound FX", 5f);
        text03.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        text03.setForeground(Color.lightGray);
        text03.setFont(loadedFont.deriveFont(30f));

        JButton btnSFX = new JButton(playerData.isStateSFX() ? onIcon : offIcon);
        btnSFX.setBorderPainted(false);
        btnSFX.setContentAreaFilled(false);
        btnSFX.setFocusPainted(false);
        btnSFX.setOpaque(false);

        btnSFX.addActionListener(e -> {
            boolean newState = !playerData.isStateSFX();
            playerData.setStateSFX(newState);
            btnSFX.setIcon(newState ? onIcon : offIcon);
        });

        con_2.add(images02);
        con_2.add(text03);
        con_2.add(btnSFX);

        container.add(textAudio);
        container.add(Box.createVerticalStrut(20));
        container.add(con_1);
        container.add(Box.createVerticalStrut(20));
        container.add(con_2);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(20, 0, 0, 0);
        gbc.anchor = GridBagConstraints.NORTH;
        main_container.add(container, gbc);

        JButton resetBtn = new ImageJButton("resources/images/settingMenu/ResetGame", ".png", 30, 175, 30);
        resetBtn.addActionListener(e -> {
            // Create popUp
            String[] btnPaths = {
                    "resources/images/shared/buttons/Yes",
                    "resources/images/shared/buttons/No"
            };
            String[] btnLabels = {"Yes", "No"};
            ActionListener[] btnActions = {
                    ex -> {
                        int randomNum = (int)(Math.random() * 5 + 1);
                        String loadingKey = "loading_" + randomNum;
                        frame.getNavigator().toPage(loadingKey, true, 250);
                        Timer timer = new Timer(5000, event -> {
                            frame.getNavigator().toPage(MainFrame.MAIN_MENU, true, 500);
                            Timer timerzz = new Timer(525, eventzz -> {
                                frame.resetAndRefresh();
                            });
                            timerzz.setRepeats(false);
                            timerzz.start();
                        });
                        timer.setRepeats(false);
                        timer.start();
                    },
                    null
            };
            pop.createPopup(
                    frame,
                    "WARNING!\n This will wipe all of your data.", // Message
                    "resources/images/shared/popups/Warning.png", // Background Path
                    btnPaths,
                    btnLabels,
                    btnActions
            );
        });

        gbc.gridy = 1;
        gbc.weighty = 0;
        gbc.insets = new Insets(0, 0, 40, 0);
        gbc.anchor = GridBagConstraints.SOUTH;
        main_container.add(resetBtn, gbc);

        add(new TopBar(frame), BorderLayout.NORTH);
        add(main_container, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private static class ModernSliderUI extends BasicSliderUI {
        private final Image thumbImg;

        public ModernSliderUI(JSlider b, Image thumbImg) {
            super(b);
            this.thumbImg = thumbImg;
        }

        @Override
        protected Dimension getThumbSize() {
            return new Dimension(25, 25);
        }

        @Override
        public void paintTrack(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;

            int trackWidth = trackRect.width;
            int trackHeight = 10;
            int x = trackRect.x;
            int y = trackRect.y + (trackRect.height - trackHeight) / 2;

            // Background square
            g2d.setColor(new Color(50, 50, 50));
            g2d.fillRect(x, y, trackWidth, trackHeight);

            // Progress square
            int currentPos = thumbRect.x + (thumbRect.width / 2);
            g2d.setColor(Color.lightGray);
            g2d.fillRect(x, y, currentPos - x, trackHeight);

            // Black outline
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2.5f));
            g2d.drawRect(x, y, trackWidth, trackHeight);
        }

        @Override
        public void paintThumb(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawImage(thumbImg, thumbRect.x, thumbRect.y, null);
        }

        @Override
        public void paintFocus(Graphics g) {
        }
    }
}