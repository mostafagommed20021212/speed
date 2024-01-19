package com.example.speedtyping;
import javafx.application.Platform;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.concurrent.atomic.AtomicInteger;

public class Timer implements Runnable{
    private AtomicInteger time;
    private Text stage;
    public Timer(int time, Text stage) {
        this.time = new AtomicInteger(time);
        this.stage = stage;
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted() && this.time.get() > 0) {
            Platform.runLater(() -> stage.setText(String.valueOf(this.time.getAndDecrement())));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
