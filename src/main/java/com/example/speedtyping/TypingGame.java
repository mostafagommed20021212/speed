package com.example.speedtyping;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class TypingGame  {


    public Button enter;
    private Keyboard keyboard;
    private Timer timer;
    private HighScore highScore;
    private Cursor player;
    private ListWord listWord;
    private int row = 0,col =-1;
    private int counter;
    private  Thread thread;
    private Map<Integer,Word>mapListWord;

    private PlayerList playerList;
    @FXML
    private  Pane pane;
    @FXML
    private VBox board;
    @FXML
    private Text time;
    @FXML
    private Button spaceBtn;
    @FXML
    private  Pane pane1;
    @FXML
    private  Pane pane2;
    @FXML
    private  Pane pane3;
    private Boolean isStart ;
    public void endGame(){
        listWord = new ListWord(board);
        listWord.addWordsToBoard();
        player = new CursorPlayer();
        pane.getChildren().add(player);
        this.mapListWord = listWord.getMapOfWord();
        isStart = true;
        keyboard = new Keyboard(pane3,pane1,pane2);


    }




    public void checkPlayerRegister() throws IOException {
        Stage stage = (Stage) enter.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainGame.class.getResource("hello-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 500, 500);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TypingGame game;
        stage.setTitle("Hello!");
        stage.setScene(scene);
        game = fxmlLoader.getController();
        game.endGame();
        stage.show();
    }





    public void startGame(KeyEvent e) {
        pane.requestFocus();
        if(isStart){
            timer = new Timer(15,time);
             thread = new Thread(timer);
            thread.start();
            isStart = false;

        }
        if(e.isControlDown() && e.getCode().equals(KeyCode.ENTER)){
            resetAll();

            return;
        }
        String click = e.getCode().toString().toLowerCase();
        Word charClick = mapListWord.get(counter);
        if(e.isControlDown() && e.getCode().equals(KeyCode.BACK_SPACE) && counter != 0){
            Word wordDelete  = mapListWord.get(counter-1);
            if(wordDelete.getText().equals(" ")){
                player.moveLeft(wordDelete);
                if(col== -1 && row != 0){
                    row--;
                    col = ((HBox)board.getChildren().get(row)).getChildren().size()-1;
                    player.moveUp();
                }
                counter--;
                col--;

                return;

            }
            while (counter != 0 && !wordDelete.getText().equals(" ")){
                wordDelete.setFill(Color.rgb(219, 216, 182));
                wordDelete = mapListWord.get(--counter);
                if(col== -1 && row != 0){
                    row--;
                    col = ((HBox)board.getChildren().get(row)).getChildren().size()-1;
                    player.moveUp();
                }
                col--;

            }

            System.out.println("deleot"+row +" "+ col);
            wordDelete.setFill(Color.rgb(219, 216, 182));
            if(col == -2 && row == 0){
                wordDelete = mapListWord.get(counter+1);
                player.moveLeft(wordDelete);

            }else {
                player.moveLeft(wordDelete);

            }

            return;


        }
        boolean isSpace = e.getCode().equals(KeyCode.SPACE);
        boolean isLetter = Character.isLetter(click.charAt(0)) && click.length() ==1 ;
        boolean isBackSpace = e.getCode().equals(KeyCode.BACK_SPACE);
        if(isLetter || isSpace){
            if(charClick.getText().equals(click)) {
                charClick.setFill(Color.rgb(63, 62, 63));
                keyboard.changeColor(e,true);
            }
            else {
                charClick.setFill(Color.rgb(202, 71, 84));
                keyboard.changeColor(e,false);
            }
            player.moveRight(charClick);
            System.out.println("right" + row +" "+ col);
            counter++;
            col++;
        }
        if(col == ((HBox)board.getChildren().get(row)).getChildren().size()-1 && charClick.getText().equals(" ")){
            row++;
            if(row == 2){
                player.reset();
                listWord.removeLineOfWord();
                col = -1;
                row--;
                return;
            }
            col = -1;
            System.out.println("down" + row +" "+ col);
            player.moveDown();
        }
        if(isBackSpace){


            if(col== -1 && row != 0){


                row--;
                col = ((HBox)board.getChildren().get(row)).getChildren().size()-1;
                player.moveUp();
            }else if (col >= 0){
                System.out.println("left" + row +" "+ col);
                charClick = mapListWord.get(--counter);
                charClick.setFill(Color.rgb(219, 216, 182));
                player.moveLeft(charClick);
                col--;
            }


        }
    }

    private void resetAll() {
        row = 0;
        col = -1;
        listWord.resetBoard();
        mapListWord = listWord.getMapOfWord();
        counter = 0;
        thread.interrupt();
        isStart = true;
        time.setText("");
        player.resetAll();
    }

    private void shrinkPointer(){

    }
    public void isSpaceClick(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.SPACE))
            startGame(keyEvent);
    }


    public void releaseKey() {
        System.out.println("i love mostafa");
        keyboard.returnColor();
    }
}