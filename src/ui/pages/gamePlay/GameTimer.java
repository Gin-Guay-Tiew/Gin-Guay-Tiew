package ui.pages.gamePlay;

import javax.swing.SwingUtilities;

public class GameTimer implements Runnable {
    private int timeLeft;
    private gamePlayScreen screen;
    private TimeDisplay timeDisplay;
    private Thread timer;
    private customerPanel c;
    private volatile boolean isRunning = false;
    private volatile boolean isPaused = false;

    public GameTimer(int seconds, gamePlayScreen screen, TimeDisplay timeDisplay,customerPanel c) {
        this.timeLeft = seconds;
        this.screen = screen;
        this.timeDisplay = timeDisplay;
        this.c = c;

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
                break;
            }

            if (isPaused) {
                continue;
            }

            this.timeLeft--;

            SwingUtilities.invokeLater(() -> {
                if (timeDisplay != null) {
                    timeDisplay.updateTime(timeLeft);
                }

                screen.updateGame();

                if (timeLeft <= 0) {
                    stopTimer();
                    screen.updateGame();
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

    public void pauseTimer() {
        isPaused = true;
    }

    public void resumeTimer() {
        isPaused = false;
    }

    public int getTimeLeft(){
        return timeLeft;
    }
}