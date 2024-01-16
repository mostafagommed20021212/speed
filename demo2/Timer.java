package com.example.demo2;

import javafx.application.Platform;
import javafx.stage.Stage;
import java.util.concurrent.atomic.AtomicInteger;

public class Timer {
    private AtomicInteger time;
    private Stage stage;

    public Timer(int time, Stage stage) {
        this.time = new AtomicInteger(time);
        this.stage = stage;

        new Thread(() -> {
            HelloController h = new HelloController();

            while (this.time.get() > 0) {
                Platform.runLater(() -> stage.setTitle("ahmed " + this.time.getAndDecrement()));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}

