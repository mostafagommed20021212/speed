package com.example.demo2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TypingGame   implements Runnable{

    Keyboard keyboard;
    HighScore highScore;
    Timer timer;
    Cursor cursor;
    ListWord listWord;
    PlayerList playerList;

    @FXML
    private VBox board;
    public void endGame(){

    }


    @Override
    public  void run(){
        System.out.println(board);
        ListWord f = new ListWord(board);
        f.addWordsToBoard();
    }

    public void checkPlayerRegister()
    {

    }





    public void startGame(KeyEvent keyEvent) {

    }
    public void isSpaceClick(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.SPACE))
            startGame(keyEvent);
    }
}
