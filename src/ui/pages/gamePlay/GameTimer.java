package ui.pages.gamePlay;

import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

public class GameTimer implements Runnable {
    private int timeLeft;
    private gamePlayScreen screen;
    private TimeDisplay timeDisplay;
    private Thread timer;
    private volatile boolean isRunning = false;

    // รับค่า เวลา, หน้าจอเกม, หน้าปัดเวลา
    public GameTimer(int seconds, gamePlayScreen screen, TimeDisplay timeDisplay) {
        this.timeLeft = seconds;
        this.screen = screen;
        this.timeDisplay = timeDisplay;

        // อัปเดตเวลาก่อนเริ่มเกม
        if (this.timeDisplay != null) {
            this.timeDisplay.updateTime(timeLeft);
        }
    }

    @Override
    public void run() {
        while (isRunning && this.timeLeft > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                break;
            }
            this.timeLeft--;

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
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
    }

    public void startTimer() {
        if (!isRunning) {
            isRunning = true;
            timer = new Thread(this);
            timer.start();
        }
    }

    public void stopTimer() {
        isRunning = false;
        if (timer != null) {
            timer.interrupt();
        }
    }
}
