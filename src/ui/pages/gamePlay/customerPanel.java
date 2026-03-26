package ui.pages.gamePlay;

import utilities.IconImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;

public class customerPanel extends JPanel {

    TopBar bar;
    private int totalCustomers;
    private int servedCustomers = 0;

    // คิวลูกค้าทั้งหมดในด่าน
    private Queue<CustomerData> queue = new LinkedList<>();
    private customerComponent[] slots = new customerComponent[3];

    // ตำแหน่งแต่ละช่อง
    private int[] xs = {211, 397, 591};
    private int[] ys = {78, 59, 80};

    public customerPanel(TopBar bar) {
        this.bar = bar;
        setLayout(null);
        setPreferredSize(new Dimension(800, 600));
        setOpaque(false);
    }


    public void showCustomers(int levelID) {
        removeAll();
        queue.clear();
        Arrays.fill(slots, null);

        java.util.List<CustomerData> dataList = CustomerFactory.getCustomer(levelID);

        totalCustomers = dataList.size();
        servedCustomers = 0;

        bar.getTimeDisplay().updateCount(servedCustomers, totalCustomers);
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

    // ➕ เติมลูกค้าในช่องว่าง
    private void spawnNext() {
        if (queue.isEmpty()) return;

        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null) {

                CustomerData d = queue.poll();
                int index = i;
                JPanel bubblePanel = new JPanel();

                customerComponent c = new customerComponent(
                        d,
                        d.imgPath,
                        d.patiencePath,
                        d.type,
                        d.skin,
                        d.life,
                        bubblePanel,
                        () -> removeCustomer(index, d)
                );

                c.setBounds(xs[i], ys[i], 197, 240);
                c.playSpawnBounce(xs[i], ys[i]);
                bubblePanel.setLayout(null);
                bubblePanel.setOpaque(false);
                bubblePanel.setBounds(xs[i] - 80, ys[i] - 50, 120, 120);
                playBubbleBounce(bubblePanel, xs[i] - 80, ys[i] - 50);

                JLabel bubble = new JLabel(
                        IconImage.create("resources/images/gamePlay/customer/message.png", 140, 140)
                );
                bubble.setBounds(0, 0, 120, 120);

                JLabel food = new JLabel();
                food.setBounds(25, 15, 76, 76);
                food.setIcon(IconImage.create(d.foodPath, 76, 76));

                bubblePanel.add(bubble);
                bubblePanel.add(food);

                bubblePanel.setComponentZOrder(food, 0);
                bubblePanel.setComponentZOrder(bubble, 1);

                slots[i] = c;
                add(c);
                add(bubblePanel);

                break;
            }
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

    public void removeCustomer(int index, CustomerData d) {
        customerComponent c = slots[index];
        if (c == null) return;

        // Reward Logic tong nee!
        servedCustomers++;
        bar.getTimeDisplay().updateCount(servedCustomers, totalCustomers);
        bar.getTimeDisplay().playCountFlash(true);
        System.out.println("You received 67 baht from " + d.type);

        playExitAnimation(c, c.getBubble(), index, () -> {
            int randomDelay = 1000 + (int) (Math.random() * 2000);
            Timer delaySpawnTimer = new Timer(randomDelay, e -> {
                spawnNext();
                revalidate();
                repaint();
            });
            delaySpawnTimer.setRepeats(false);
            delaySpawnTimer.start();
        });
    }

    public void updateCustomers() {
        for (int i = 0; i < slots.length; i++) {
            customerComponent c = slots[i];
            if (c == null) continue;

            c.tick();

            if (c.isExpired()) {
                int index = i;
                customerComponent expiringComp = slots[i];

                servedCustomers++;
                bar.getTimeDisplay().updateCount(servedCustomers, totalCustomers);
                bar.getTimeDisplay().playCountFlash(false);

                playExitAnimation(expiringComp, expiringComp.getBubble(), index, () -> {
                    spawnNext();
                });

                slots[i] = null;
            }
        }
        revalidate();
        repaint();
    }

    public boolean isFinished() {
        if (!queue.isEmpty()) return false;

        for (customerComponent c : slots) {
            if (c != null) return false;
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
                slots[index] = null;

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

}
