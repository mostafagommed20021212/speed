package com.example.demo2;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class CursorPlayer extends Cursor{

    public CursorPlayer(){
        super(10, 50, 10, 80);
        this.setStroke(Color.YELLOW);
        setAnimation();


    }

    private void setAnimation() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(this.opacityProperty(), 1)),
                new KeyFrame(Duration.millis(400), new KeyValue(this.opacityProperty(), 0))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @Override
    public void moveRight() {

    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void moveDown() {

    }

    @Override
    public void reset() {

    }
}