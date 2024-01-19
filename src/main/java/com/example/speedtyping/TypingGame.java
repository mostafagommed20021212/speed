package com.example.speedtyping;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class TypingGame implements Colorable ,Initializable  {

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
    private Boolean isStart ;
    public void endGame(){
        listWord = new ListWord(board);
        listWord.addWordsToBoard();
        player = new CursorPlayer();
        pane.getChildren().add(player);
        this.mapListWord = listWord.getMapOfWord();
        isStart = true;


    }




    public void checkPlayerRegister()
    {

    }





    public void startGame(KeyEvent e) {
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
            if(charClick.getText().equals(click))
                charClick.setFill(Color.rgb(63, 62, 63));
            else
                charClick.setFill(Color.rgb(202,71,84));
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



        @FXML
        public Pane pane3;
        @FXML
        private Pane pane1;

        @FXML
        private Pane pane2;
        Map<String, String> hm = new HashMap<String, String>();
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            pane2.setVisible(false);
            pane1.setVisible(true);
            pane3.setVisible(false);


            hm.put("OPEN_BRACKET", "[");
            hm.put("CLOSE_BRACKET", "]");
            hm.put("SEMICOLON", ";");
            hm.put("QUOTE", "'");
            hm.put("COMMA",",");
            hm.put("PERIOD",".");
            hm.put("SLASH","/");
            hm.put("DIGIT1","1");
            hm.put("DIGIT2","2");
            hm.put("DIGIT3","3");
            hm.put("DIGIT4","4");
            hm.put("DIGIT5","5");
            hm.put("DIGIT6","6");
            hm.put("DIGIT7","7");
            hm.put("DIGIT8","8");
            hm.put("DIGIT9","9");
            hm.put("DIGIT0","0");
            hm.put("MINUS","-");
            hm.put("EQUALS","+");
            hm.put("BACK_SPACE","BACK");


        }



        public void changeColor(KeyEvent e) {
            System.out.println(e);
            Pane pane = (Pane)e.getSource();
            System.out.println(e.getCode());
            System.out.println(hm.size());
            for (Node node:pane.getChildren()) {

                if(node instanceof Button)
                {



                    if(((Button)node).getText().equals(e.getCode().toString()))
                    {
                        ((Button)node).setStyle("-fx-background-color:" + NEW_COLOR +  "; -fx-border-color: #dbd8b6;-fx-border-radius: 10; -fx-border-width: 1px 1px 1px 1px; -fx-text-fill:  #1D1C1D;");
                        //-fx-background-color: transparent;  -fx-border-color: #dbd8b6;-fx-border-radius: 10; -fx-border-width: 1px 1px 1px 1px; -fx-text-fill: white;
                        break;
                    }
                    else if(hm.containsKey(e.getCode().toString()) && ((Button)node).getText().equals(hm.get(e.getCode().toString())))
                    {

                        ((Button)node).setStyle("-fx-background-color:" + NEW_COLOR +  "; -fx-border-color: #dbd8b6;-fx-border-radius: 10; -fx-border-width: 1px 1px 1px 1px; -fx-text-fill:  #1D1C1D;");
                        break;
                    }

                }
            }


        }

    @Override
    public void returnColor(KeyEvent actionEvent) {

    }

    public void changeSpace(String key)
        {
            Pane pane ;
            if(pane1.isVisible())
            {
                pane = pane1;
            } else if (pane2.isVisible()) {
                pane = pane2;
            }
            else {
                pane = pane3;
            }

            System.out.println(hm.size());
            for (Node node:pane.getChildren()) {

                if(node instanceof Button)
                {



                    if(((Button)node).getText().equals(key))
                    {
                        ((Button)node).setStyle("-fx-background-color:" + NEW_COLOR +  "; -fx-border-color: #dbd8b6;-fx-border-radius: 10; -fx-border-width: 1px 1px 1px 1px; -fx-text-fill:  #1D1C1D;");
                        //-fx-background-color: transparent;  -fx-border-color: #dbd8b6;-fx-border-radius: 10; -fx-border-width: 1px 1px 1px 1px; -fx-text-fill: white;
                        break;
                    }

                }
            }
        }
        public void returnColor() {

            Pane pane ;
            if(pane1.isVisible())
            {
                pane = pane1;
            } else if (pane2.isVisible()) {
                pane = pane2;
            }
            else {
                pane = pane3;
            }
            for (Node node:pane.getChildren()) {

                if(node instanceof Button)
                {

                    ((Button)node).setStyle("-fx-background-color:" +  ORIGINAL_COLOR + ";  -fx-border-color: #dbd8b6;-fx-border-radius: 10; -fx-border-width: 1px 1px 1px 1px; -fx-text-fill: white;");


                }
            }

        }


        public void hello(KeyEvent keyEvent) {
            System.out.println("hello world");
        }


}