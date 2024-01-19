package com.example.speedtyping;

import javafx.scene.input.KeyEvent;

public interface Colorable {
    String ORIGINAL_COLOR = "transparent";
    String NEW_COLOR = "#dbd8b6";
    void changeColor(KeyEvent actionEvent);
    void returnColor(KeyEvent actionEvent);
}
