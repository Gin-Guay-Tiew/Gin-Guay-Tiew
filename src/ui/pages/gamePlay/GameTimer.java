package ui.pages.gamePlay;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameTimer {
    private Timer timer;
    private int timeLeft;
    private gamePlayScreen screen;
    private TimeDisplay timeDisplay;

    // รับค่า เวลา, หน้าจอเกม, หน้าปัดเวลา
    public GameTimer(int seconds, gamePlayScreen screen, TimeDisplay timeDisplay) {
        this.timeLeft = seconds;
        this.screen = screen;
        this.timeDisplay = timeDisplay;

        // อัปเดตเวลาก่อนเริ่มเกม
        if (this.timeDisplay != null) {
            this.timeDisplay.updateTime(timeLeft);
        }

        // ให้ timer ทำงานทุกๆ 1 วิ
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;

                if (timeDisplay != null) {
                    timeDisplay.updateTime(timeLeft);
                }

                if (timeLeft <= 0) {
                    stopTimer();
                    screen.gameOver();
                }
            }
        });
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        if (timer.isRunning()) {
            timer.stop();
        }
    }
}
