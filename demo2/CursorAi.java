package com.example.demo2;

import javafx.scene.paint.Color;

public class CursorAi extends Cursor{
    private int speed;

    public CursorAi(int speed) {
        super(10, 50, 10, 80);
        this.speed = speed;
        this.setStroke(Color.rgb(50, 52, 55));

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
