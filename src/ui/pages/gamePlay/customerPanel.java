package ui.pages.gamePlay;

import main.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.*;

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

                customerComponent c = new customerComponent(
                        d.imgPath,
                        d.patiencePath,
                        d.type,
                        d.skin,
                        d.life,
                        () -> removeCustomer(index,d)
                );

                c.setBounds(xs[i], ys[i], 197, 240);
                slots[i] = c;
                add(c);

                break;
            }
        }
    }
    private void removeCustomer(int index,CustomerData d) {
        customerComponent c = slots[index];

        if (c == null) return;
        bar.setMoney();
        System.out.println("You received 67 baht from "+d.type);
        remove(c);
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
}