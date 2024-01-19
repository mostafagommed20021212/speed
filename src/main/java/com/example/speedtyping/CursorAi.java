package com.example.speedtyping;

import javafx.scene.paint.Color;

public class CursorAi extends Cursor{
    private int speed;

    public CursorAi(int speed) {
        super(10, 50, 10, 80);
        this.speed = speed;
        this.setStroke(Color.rgb(50, 52, 55));

    }

    @Override
    public void moveRight(Word word) {

    }

    @Override
    public void moveLeft(Word word) {

    }

    @Override
    public void moveDown() {

    }

    @Override
    public void moveUp() {

    }

    @Override
    public void reset() {

    }

    @Override
    public void resetAll() {

    }
}
