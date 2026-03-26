package ui.pages.gamePlay;

import utilities.IconImage;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.Timer;

public class customerPanel extends JPanel {

    TopBar bar;

    // คิวลูกค้าทั้งหมดในด่าน
    private Queue<CustomerData> queue = new LinkedList<>();
    private customerComponent[] slots = new customerComponent[3];

    // ตำแหน่งแต่ละช่อง
    private int[] xs = {211, 397, 591};
    private int[] ys = {78, 59, 80};

    public customerPanel(TopBar bar){
        this.bar = bar;
        setLayout(null);
        setPreferredSize(new Dimension(800,600));
        setOpaque(false);
    }


    public void showCustomers(int levelID){
        removeAll();

        queue.clear();
        Arrays.fill(slots, null); // เคลียร์ slot

        java.util.List<CustomerData> dataList = CustomerFactory.getCustomer(levelID);
        queue.addAll(dataList);

        // เติมให้เต็มตอนเริ่ม
        for (int i = 0; i < slots.length; i++) {
            spawnNext();
        }

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
                        d.imgPath,
                        d.patiencePath,
                        d.type,
                        d.skin,
                        d.life,
                        bubblePanel,
                        () -> removeCustomer(index,d)
                );

                c.setBounds(xs[i], ys[i], 197, 240);
                c.playSpawnBounce(xs[i], ys[i]);
                bubblePanel.setLayout(null);
                bubblePanel.setOpaque(false);
                bubblePanel.setBounds(xs[i] - 80, ys[i] - 50, 120, 120);
                playBubbleBounce(bubblePanel, xs[i] - 80, ys[i] - 50);

                JLabel bubble = new JLabel(
                        IconImage.create("resources/images/gamePlay/customer/message.png",140,140)
                );
                bubble.setBounds(0,0,120,120);

                JLabel food = new JLabel();
                food.setBounds(25,15,76,76);
                food.setIcon(IconImage.create(d.foodPath,76,76));

                bubblePanel.add(bubble);
                bubblePanel.add(food);

                bubblePanel.setComponentZOrder(food,0);
                bubblePanel.setComponentZOrder(bubble,1);

                slots[i] = c;
                add(c);
                add(bubblePanel);

                break;
            }
        }
    }

    private void removeCustomer(int index,CustomerData d) {
        customerComponent c = slots[index];

        if (c == null) return;
        bar.setMoney();
        System.out.println("You received 67 baht from "+d.type+" "+d.foodPath);
        remove(c);
        remove(c.getBubble());
        slots[index] = null;

        spawnNext(); // เติมตัวใหม่

        revalidate();
        repaint();
    }

    public void updateCustomers() {

        for (int i = 0; i < slots.length; i++) {
            customerComponent c = slots[i];

            if (c == null) continue;

            c.tick();

            if (c.isExpired()) {
                remove(c);
                remove(c.getBubble());
                slots[i] = null;

                spawnNext(); // เติมช่องนั้นทันที
            }
        }

        revalidate();
        repaint();
    }

    public boolean isFinished(){
        if (!queue.isEmpty()) return false;

        for (customerComponent c : slots) {
            if (c != null) return false;
        }
        return true;
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
        return 1 + c3 * (float)Math.pow(t - 1, 3)
                + c1 * (float)Math.pow(t - 1, 2);
    }

}
