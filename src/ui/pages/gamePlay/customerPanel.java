package ui.pages.gamePlay;

import main.MainFrame;
import ui.components.CustomJLabel;
import ui.pages.endGame.WinLosePage;
import utilities.FontLoader;
import utilities.IconImage;
import utilities.SFX;
import utilities.SFXManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;

public class customerPanel extends JPanel {

    TopBar bar;
    MainFrame mainFrame;
    gamePlayScreen state;
    private int targetMoney;
    private int currentMoney;
    private int health;
    public int levelID;

    // คิวลูกค้าทั้งหมดในด่าน
    private Queue<CustomerData> queue = new LinkedList<>();
    private customerComponent[] slots = new customerComponent[3];

    // ตำแหน่งแต่ละช่อง
    private int[] xs = {211, 397, 591};
    private int[] ys = {78, 59, 80};

    public customerPanel(TopBar bar,gamePlayScreen state, MainFrame mainFrame) {
        this.bar = bar;
        this.state = state;
        this.mainFrame = mainFrame;
        setLayout(null);
        setPreferredSize(new Dimension(800, 600));
        setOpaque(false);
    }


    public void showCustomers(int levelID) {
        this.levelID = levelID;
        removeAll();
        queue.clear();
        Arrays.fill(slots, null);

        java.util.List<CustomerData> dataList = CustomerFactory.getCustomer(levelID);

        this.targetMoney = LevelFactory.getReqMoney(levelID);
        this.health = LevelFactory.getLives(levelID);
        bar.getTimeDisplay().updateCount(currentMoney, targetMoney);
        bar.getTimeDisplay().updateLives(health);
        queue.addAll(dataList);

        int startWait = 1000 + (int) (Math.random() * 1000);
        int spawnInterval = 1000 + (int) (Math.random() * 1000);
        final int[] count = {0};

        Timer staggerTimer = new Timer(spawnInterval, null);
        staggerTimer.addActionListener(e -> {
            spawnNext();
            count[0]++;

            if (count[0] >= slots.length) {
                staggerTimer.stop();
            }

            revalidate();
            repaint();
        });

        staggerTimer.setInitialDelay(startWait);
        staggerTimer.start();

        revalidate();
        repaint();
    }

    private void spawnNext() {
        if (queue.isEmpty()) return;

        int emptyIndex = -1;
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null) {
                emptyIndex = i;
                break;
            }
        }
        if (emptyIndex == -1) return;

        CustomerData d = queue.poll();

        JPanel bubblePanel = new JPanel();
        final int slotIndex = emptyIndex;
        customerComponent c = new customerComponent(
                d, d.imgPath, d.patiencePath, d.type, d.skin, d.life, bubblePanel,
                () -> removeCustomer(slotIndex, d)
        );

        c.setBounds(xs[emptyIndex], ys[emptyIndex], 197, 240);
        c.playSpawnBounce(xs[emptyIndex], ys[emptyIndex]);

        bubblePanel.setLayout(null);
        bubblePanel.setOpaque(false);
        bubblePanel.setBounds(xs[emptyIndex]-80, ys[emptyIndex]-50, 120, 120);
        playBubbleBounce(bubblePanel, xs[emptyIndex]-80, ys[emptyIndex]-50);

        slots[emptyIndex] = c;
        add(c);
        add(bubblePanel);

        SFXManager.play(SFX.POP);

        if (!queue.isEmpty()) {
            new Timer(300 + (int)(Math.random()*700), e -> {
                ((Timer)e.getSource()).stop();
                spawnNext();
            }).start();
        }
    }

    public CustomerData getCustomerDataAt(int index) {
        if (index >= 0 && index < slots.length && slots[index] != null) {
            return slots[index].getData();
        }
        return null;
    }

    public void wrongOrder(int index) {
        customerComponent c = slots[index];
        if (c == null) return;

        JPanel bubblePanel = c.getBubble();

        if (bubblePanel.getComponentCount() >= 2) {
            JLabel bubbleBg = (JLabel) bubblePanel.getComponent(1);
            bubbleBg.setIcon(IconImage.create("resources/images/gamePlay/customer/wrong.png", 140, 140));
            playShakeAnimation(bubblePanel);
            Timer resetTimer = new Timer(800, e -> {
                bubbleBg.setIcon(IconImage.create("resources/images/gamePlay/customer/message.png", 140, 140));
            });
            resetTimer.setRepeats(false);
            resetTimer.start();
        }
    }

    private void playShakeAnimation(JPanel panel) {
        int originalX = panel.getX();

        Timer shakeTimer = new Timer(40, new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (count % 2 == 0) {
                    panel.setLocation(originalX + 5, panel.getY());
                } else {
                    panel.setLocation(originalX - 5, panel.getY());
                }
                count++;
                if (count >= 6) { // Shake 3 times
                    ((Timer) e.getSource()).stop();
                    panel.setLocation(originalX, panel.getY());
                }
            }
        });
        shakeTimer.start();
    }

    public void removeCustomer(int index, CustomerPay d) {
        customerComponent c = slots[index];
        if (c == null) return;

        currentMoney += d.pay();
        CustomerData data = (CustomerData) d;

        bar.getTimeDisplay().updateCount(currentMoney, targetMoney);

        int popupX = c.getX() + (c.getWidth() / 2) - 100;
        int popupY = c.getY() - 20;

        FloatingMoneyLabel moneyPopup =
                new FloatingMoneyLabel("+" + data.pay() + "$", popupX, popupY);

        add(moneyPopup);
        setComponentZOrder(moneyPopup, 0);

        if (currentMoney >= targetMoney) {
            bar.getTimeDisplay().setCountColor(new Color(110, 207, 106));
        }

        playExitAnimation(c, c.getBubble(), index, () -> {
            spawnNext();
            revalidate();
            repaint();
        });
    }

    public void updateCustomers() {
        for (int i = 0; i < slots.length; i++) {
            customerComponent c = slots[i];
            if (c == null) continue;

            c.tick();

            if (c.isExpired()) {
                SFXManager.play(SFX.CUSTOMER_FLEES);
                int index = i;
                customerComponent expiringComp = slots[i];

                health--;
                bar.getTimeDisplay().updateLives(Math.max(0, health));

                int popupX = c.getX() + (c.getWidth() / 2) - 100;
                int popupY = c.getY() - 20;

                FloatingMoneyLabel moneyPopup = new FloatingMoneyLabel("X", popupX, popupY);
                add(moneyPopup);
                setComponentZOrder(moneyPopup, 0);

                playExitAnimation(expiringComp, expiringComp.getBubble(), index, () -> {
                    spawnNext();
                });

                if (health <= 0){
                    state.gameWiner(false);
                    return;
                }
            }
        }
        revalidate();
        repaint();
    }

    public boolean isFinished() {

        if (!queue.isEmpty()) {
            return false;
        }

        for (customerComponent c : slots) {
            if (c != null) {
                return false;
            }
        }
        return true;
    }

    private void playExitAnimation(customerComponent c, JPanel bubble, int index, Runnable onComplete) {
        remove(bubble);
        revalidate();
        repaint();

        int startY = c.getY();
        int targetY = startY + 200;

        int duration = 500;
        long startTime = System.currentTimeMillis();

        Timer timer = new Timer(16, null);
        timer.addActionListener(e -> {
            float t = (System.currentTimeMillis() - startTime) / (float) duration;
            if (t > 1) t = 1;

            float eased = easeOutBack(t);
            int currentY = (int) (startY + (targetY - startY) * eased);

            c.setLocation(c.getX(), currentY);

            if (t >= 1) {
                timer.stop();
                remove(c);
                slots[index] = null; // slot ว่างทันที

                revalidate();
                repaint();

                if (onComplete != null) onComplete.run();
            }
        });

        timer.start();
    }

    private void playBubbleBounce(JPanel panel, int targetX, int targetY) {
        int startY = targetY + 80;
        panel.setLocation(targetX, startY);

        int duration = 400;
        long startTime = System.currentTimeMillis();

        Timer timer = new Timer(16, null);
        timer.addActionListener(e -> {
            float t = (System.currentTimeMillis() - startTime) / (float) duration;
            if (t > 1) t = 1;

            float eased = easeOutBack(t);

            int currentY = (int) (startY + (targetY - startY) * eased);
            panel.setLocation(targetX, currentY);

            if (t >= 1) {
                timer.stop();
            }
        });

        timer.start();
    }

    // ใช้ร่วมกันได้
    private float easeOutBack(float t) {
        float c1 = 1.70158f;
        float c3 = c1 + 1;
        return 1 + c3 * (float) Math.pow(t - 1, 3)
                + c1 * (float) Math.pow(t - 1, 2);
    }

    public void clearAllCustomers() {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != null) {
                remove(slots[i]);
                slots[i] = null;
            }
        }

        removeAll();

        queue.clear();

        revalidate();
        repaint();
    }


    public int getBonus() {
        return health*20;
    }

    public int getHealth() {
        return health;
    }

    public int getcurrentMoney() {
        return currentMoney;
    }
}
