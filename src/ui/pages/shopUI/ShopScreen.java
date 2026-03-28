package ui.pages.shopUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.*;

import logic.Shop.ShopManager;
import logic.Shop.ShopItem;
import ui.components.PopupWindow;
import ui.components.ImageJButton;
import ui.components.CustomJLabel;
import main.MainFrame;
import utilities.IconImage;
import utilities.SFX;
import utilities.SFXManager;

import java.util.ArrayList;
import java.util.List;

import static utilities.IconFilter.setOpacity;

public class ShopScreen extends JPanel {

    private ShopManager controller;
    private List<ImageJButton> buyButtons = new ArrayList<>();
    private List<CustomJLabel> priceLabels = new ArrayList<>();
    private List<ShopItem> items = new ArrayList<>();
    private MainFrame mainFrame;
    private Image backgroundImage;
    private Timer updateTimer;

    // 9-Slice Background Panel
    static class BackgroundPanel extends JPanel {
        private final Image image;
        private final int margin;

        public BackgroundPanel(ImageIcon icon, int margin) {
            this.image = icon.getImage();
            this.margin = margin;
            setOpaque(false);
            setLayout(new BorderLayout(5, 0));
        }

        @Override
        protected void paintComponent(Graphics g) {
            int iw = image.getWidth(this);
            int ih = image.getHeight(this);
            int cw = getWidth();
            int ch = getHeight();

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            // Top row
            g.drawImage(image, 0, 0, margin, margin, 0, 0, margin, margin, this);
            g.drawImage(image, margin, 0, cw - margin, margin, margin, 0, iw - margin, margin, this);
            g.drawImage(image, cw - margin, 0, cw, margin, iw - margin, 0, iw, margin, this);

            // Middle row
            g.drawImage(image, 0, margin, margin, ch - margin, 0, margin, margin, ih - margin, this);
            g.drawImage(image, margin, margin, cw - margin, ch - margin, margin, margin, iw - margin, ih - margin, this);
            g.drawImage(image, cw - margin, margin, cw, ch - margin, iw - margin, margin, iw, ih - margin, this);

            // Bottom row
            g.drawImage(image, 0, ch - margin, margin, ch, 0, ih - margin, margin, ih, this);
            g.drawImage(image, margin, ch - margin, cw - margin, ch, margin, ih - margin, iw - margin, ih, this);
            g.drawImage(image, cw - margin, ch - margin, cw, ch, iw - margin, ih - margin, iw, ih, this);
        }
    }

    public ShopScreen(MainFrame frame, ShopManager gm) {
        this.mainFrame = frame;
        this.controller = gm;

        // Setup Main Background
        ImageIcon newBg = new ImageIcon("resources/images/shared/levelBackgrounds/Level3.png");
        newBg = setOpacity(newBg, 0.5f);
        this.backgroundImage = newBg.getImage();

        setLayout(new BorderLayout());

        // --- 1. Top Bar ---
        add(new TopBar(mainFrame), BorderLayout.NORTH);

        // --- 2. Grid Content ---
        JPanel gridPanel = new JPanel(new GridLayout(0, 2, 15, 15));
        gridPanel.setOpaque(false);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        for (ShopItem item : gm.getAvailableItems()) {
            gridPanel.add(createItemCard(item));
        }

        // --- 3. Custom ScrollPane ---
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Minimalist ScrollBar UI
        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        verticalBar.setPreferredSize(new Dimension(8, 0));
        verticalBar.setUI(new BasicScrollBarUI() {
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton b = new JButton();
                b.setPreferredSize(new Dimension(0, 0));
                return b;
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                g.setColor(Color.lightGray);
                g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                g.setColor(Color.darkGray);
                g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
            }
        });

        add(scrollPane, BorderLayout.CENTER);

        // Start Auto-Refresh Timer (1s)
        updateTimer = new Timer(1000, e -> refreshShopButtons());
        updateTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void refreshShopButtons() {
        int money = mainFrame.getPlayerData().getMoney();

        for (int i = 0; i < buyButtons.size(); i++) {
            ImageJButton btn = buyButtons.get(i);
            ShopItem item = items.get(i);
            CustomJLabel pLabel = priceLabels.get(i);

            boolean isPurchased = controller.isItemPurchased(item.getName());
            boolean isStageReached = controller.isItemStageReached(item);

            if (isPurchased) {
                pLabel.setText("Unlocked");
                pLabel.setTextColor(new Color(110, 207, 106));
                pLabel.setOutlineColor(new Color(34, 74, 32));
                btn.setVisible(false);
            } else {
                btn.setVisible(true);
                if (!isStageReached) {
                    pLabel.setText("Locked");
                    pLabel.setTextColor(new Color(184, 184, 184));
                    pLabel.setOutlineColor(new Color(67, 67, 67));
                    btn.setImage("resources/images/shared/buttons/lockedBuy", ".png", 75, 30);
                } else {
                    pLabel.setText(item.getPrice() + " N");
                    pLabel.setTextColor(new Color(230, 181, 42));
                    pLabel.setOutlineColor(new Color(115, 51, 12));

                    if (money < item.getPrice()) {
                        btn.setImage("resources/images/shared/buttons/noMoneyBuy", ".png", 75, 30);
                    } else {
                        btn.setImage("resources/images/shared/buttons/canBuy", ".png", 75, 30);
                    }
                }
            }
        }
    }

    private JPanel createItemCard(ShopItem item) {
        ImageIcon cardBgIcon = IconImage.create("resources/images/shared/popups/Shop.png", 100, 100);
        BackgroundPanel card = new BackgroundPanel(cardBgIcon, 30);
        card.setPreferredSize(new Dimension(320, 100));
        card.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 15));

        // Image Panel
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setPreferredSize(new Dimension(85, 100));
        imagePanel.setOpaque(false);

        int iconSize = 80;
        if (item.getImagePath().contains("noodles") && !item.getName().equals("Green egg noodles")) {
            iconSize = 130;
        }

        ImageIcon itemIcon = IconImage.create(item.getImagePath(), iconSize, iconSize);
        JLabel imageLabel = new JLabel(itemIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);

        String name = item.getName();
        if (name.equals("Thin rice noodles")) imageLabel.setBorder(BorderFactory.createEmptyBorder(-80, -40, 0, 0));
        else if (name.equals("Wide rice noodles")) imageLabel.setBorder(BorderFactory.createEmptyBorder(40, -30, 0, 0));
        else if (name.equals("Rice vermicelli noodles"))
            imageLabel.setBorder(BorderFactory.createEmptyBorder(50, 40, 0, 0));
        else if (name.equals("Yellow egg noodles"))
            imageLabel.setBorder(BorderFactory.createEmptyBorder(-80, 40, 0, 0));
        else if (name.equals("Vegetable")) imageLabel.setBorder(BorderFactory.createEmptyBorder(0, -10, 0, 0));

        imagePanel.add(imageLabel, BorderLayout.CENTER);
        card.add(imagePanel, BorderLayout.WEST);

        // Info Panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);

        Font jerseyFont = utilities.FontLoader.loadCustomFont("resources/font/Jersey10.ttf");
        String rawName = item.getName();
        String displayName = (rawName.length() > 13) ? rawName.substring(0, 12).trim() + ".." : rawName;

        CustomJLabel nameLabel = new CustomJLabel(displayName, 4.5f);
        nameLabel.setFont(jerseyFont.deriveFont(Font.BOLD, 26f));
        nameLabel.setTextColor(Color.WHITE);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 2));

        CustomJLabel priceLabel = new CustomJLabel("", 4f);
        priceLabel.setFont(jerseyFont.deriveFont(22f));
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        priceLabel.setBorder(BorderFactory.createEmptyBorder(20, 2, 0, 2));

        infoPanel.add(Box.createVerticalGlue());
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(-8));
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createVerticalGlue());

        card.add(infoPanel, BorderLayout.CENTER);

        // Storage for updates
        priceLabels.add(priceLabel);
        items.add(item);

        // Buy Button
        ImageJButton buyBtn = new ImageJButton("resources/images/shared/buttons/canBuy", ".png", 25, 75, 30);
        buyButtons.add(buyBtn);

        buyBtn.addActionListener(e -> {
            if (!controller.isItemStageReached(item)) {
                SFXManager.play(SFX.CANT_BUY);
                showWarningPopup("Locked!\nUnlock Level " + item.getLevelRequired() + " to unlock this item.");
                return;
            }
            if (mainFrame.getPlayerData().getMoney() < item.getPrice()) {
                SFXManager.play(SFX.CANT_BUY);
                showWarningPopup("Not enough money!");
                return;
            }
            if (controller.purchaseItem(item)) {
                SFXManager.play(SFX.BUY);
                refreshShopButtons();
                showWarningPopup("Purchase successful!");
            }
        });

        buyBtn.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (buyBtn.isEnabled()) buyBtn.setBorder(BorderFactory.createEmptyBorder(3, 3, 0, 0));
            }

            public void mouseReleased(MouseEvent e) {
                buyBtn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            }
        });

        card.add(buyBtn, BorderLayout.EAST);
        refreshShopButtons();
        return card;
    }

    private void showWarningPopup(String text) {
        new PopupWindow().createPopup(
                controller.getMainFrame(),
                text,
                "resources/images/shared/popups/Demo.png",
                new String[]{"resources/images/shared/buttons/Ok"},
                new String[]{"Ok"},
                new ActionListener[]{
                        ex -> ((Window) SwingUtilities.getWindowAncestor((Component) ex.getSource())).dispose()
                }
        );
    }
}