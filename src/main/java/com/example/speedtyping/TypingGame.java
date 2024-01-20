package com.example.speedtyping;


import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.Light;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.*;

public class TypingGame  {
    private Thread endTime;
    private boolean isCorrect = true;
    private int allWords , correctWords;
    private Map<Character,Integer>worstChar;
    @FXML
    private Text wpm;
    @FXML
    private Text acc;
    @FXML
    private HBox modeTimeBtn;
    private int correctChar = 0 ,unCorrectChar = 0;
    private  List<Character> ch = List.of('=', '-', ';', '.', ',', '\'', '[', ']');

    private int mode =1 ;
    private boolean isTakeTime ;

    private Keyboard keyboard;
    private int whatTime;
    private Timer timer;
    private HighScore highScore;
    private Cursor player;
    private ListWord listWord;
    private int row = 0,col =-1;
    private int counter;
    private  Thread thread;
    private Map<Integer,Word>mapListWord;
    private Stack<Boolean>backCorrectWord;

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
    public static boolean isClose = false;

    public TypingGame() {

    }

    public void endGame(){
        listWord = new ListWord(board);
        player = new CursorPlayer();
        pane.getChildren().add(player);
        this.mapListWord = listWord.getMapOfWord();
        isStart = true;
        startRun();
        worstChar = new HashMap<>();
        backCorrectWord = new Stack<>();

    }
    private void startRun() {
        new  Thread(new Runnable() {
            @Override
            public void run() {
                while ( !isClose &&!Thread.currentThread().isInterrupted() && !Timer.endTheGame){

                }
                resetAndSave();
            }
        }).start();

    }






    public void checkPlayerRegister()
    {

    }





    public void startGame(KeyEvent e) {
        System.out.println(allWords + " "+correctWords);
        acc.setVisible(true);
        wpm.setVisible(true);
        modeTimeBtn.setVisible(false);
        if(isStart){
            timer = new Timer(whatTime,time);
            thread = new Thread(timer);
            thread.start();
            isStart = false;
        }

        if(e.isControlDown()
                && e.getCode().equals(KeyCode.BACK_SPACE)
                && counter != 0
                && col >=0
        ){

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

            wordDelete.setFill(Color.rgb(219, 216, 182));


            if(col == -2 && row == 0){
                wordDelete = mapListWord.get(counter+1);
                player.moveLeft(wordDelete);
                shrinkWords(wordDelete.getText(),true);
                col = -1;
                return;
            }else {
                player.moveLeft(wordDelete);
            }
            shrinkWords(wordDelete.getText(),false);
            return;


        }

        Word charClick = mapListWord.get(counter);


        if(e.isControlDown() && e.getCode().equals(KeyCode.ENTER)){
            resetAll();
            return;
        }

        String click = e.getText();
        click = mode == 3 || mode == 2 ?  click.toUpperCase() : click.toLowerCase();


        boolean isSpace = e.getCode().equals(KeyCode.SPACE);
        boolean isBackSpace = e.getCode().equals(KeyCode.BACK_SPACE);
        boolean isLetter = false;

        if(!click.isEmpty())
            isLetter =  click.length() ==1 && Character.isLetter(click.charAt(0))  || Character.isDigit(click.charAt(0)) || ch.contains(click.charAt(0));

        if(isLetter || isSpace){
            if(isCorrect && charClick.getText().equals(" ")) {
                correctWords++;

            }
            if(charClick.getText().equals(" "))
            {
                backCorrectWord.push(isCorrect);
                isCorrect = true;
                allWords++;
            }
            if(charClick.getText().equals(click) || (charClick.getText().equals(" ") && isSpace)){
                charClick.setFill(Color.rgb(63, 62, 63));
            }
            else{
                charClick.setFill(Color.rgb(202,71,84));
                isCorrect = false;
                char ch = charClick.getText().charAt(0);
                if(!worstChar.containsKey(ch)){
                    worstChar.put(ch,1);
                }else worstChar.put(ch,1+worstChar.get(ch));
            }
            player.moveRight(charClick);

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
            player.moveDown();
        }
        if(isBackSpace){

            if(col== -1 && row != 0){
                shrinkWords(charClick.getText(),true);
                row--;
                col = ((HBox)board.getChildren().get(row)).getChildren().size()-1;
                player.moveUp();
            }else if (col >= 0){

                charClick = mapListWord.get(--counter);
                shrinkWords(charClick.getText(),false);
                charClick.setFill(Color.rgb(219, 216, 182));
                player.moveLeft(charClick);
                col--;
            }
            return;
        }


    }
    private void shrinkWords(String ch,boolean f){
        if(ch.equals(" ") || f){
            if(!backCorrectWord.empty()){
                allWords--;
                if(backCorrectWord.pop())correctWords--;
            }
        }
    }

    private void resetAll() {
        thread.interrupt();
        // endTime.interrupt();
        row = 0;
        col = -1;
        listWord.resetBoard();
        listWord.addWordsToBoard(mode);
        mapListWord = listWord.getMapOfWord();
        counter = 0;
        isStart = true;
        time.setText("");
        player.resetAll();
        acc.setVisible(false);
        wpm.setVisible(false);
        modeTimeBtn.setVisible(true);
        unCorrectChar = 0;
        correctChar = 0;
        acc.setText("");
        wpm.setText("");
        Timer.endTheGame = false;
        isTakeTime = false;
        allWords = 0;
        correctWords = 0;
        isCorrect = true;
        backCorrectWord.clear();
    }

    private void shrinkPointer(KeyEvent e){

    }
    public void isSpaceClick(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.SPACE))
            startGame(keyEvent);
    }

    public void resetTime(MouseEvent mouseEvent) {
        isTakeTime = true;
        whatTime =Integer.parseInt( ((Button) mouseEvent.getSource()).getText());
        HBox btnHbox = (HBox) ((Button) mouseEvent.getSource()).getParent();

        for(Node it :btnHbox.getChildren() ){
            if(it instanceof Button click){

                if(click.getText().equals("30") || click.getText().equals("60")|| click.getText().equals("15")){
                    ((Button)it).setStyle("-fx-border-color: transparent; -fx-font-size: 14px; -fx-text-fill: #646669; -fx-background-color:transparent" );
                }
            }
        }
        ((Button) mouseEvent.getSource()).setStyle("-fx-background-color:#D5AD17");
        spaceBtn.requestFocus();
        if(isTakeTime){
            pane.setOnKeyPressed(this::startGame);
        }
    }

    public void changeMode(MouseEvent mouseEvent) {

        mode = Integer.parseInt(((Button)mouseEvent.getSource()).getId());
        HBox btnHbox = (HBox) ((Button) mouseEvent.getSource()).getParent();

        for(Node it :btnHbox.getChildren() ){
            if(it instanceof Button click){

                if(click.getId() != null && (click.getId().equals("1") || click.getId().equals("2")|| click.getId().equals("3")||click.getId().equals("4"))  ){
                    ((Button)it).setStyle("-fx-border-color: transparent; -fx-font-size: 14px; -fx-text-fill: #646669; -fx-background-color:transparent" );
                }
            }
        }

        listWord.addWordsToBoard(mode);
        ((Button) mouseEvent.getSource()).setStyle("-fx-background-color:#D5AD17");
        spaceBtn.requestFocus();


    }
    public  void resetAndSave(){
        EventHandler<? super KeyEvent> currentHandler = pane.getOnKeyPressed();

// Check if there is a handler registered

        try{

            Platform.runLater(()->{
                pane.setOnKeyPressed(this::shrinkPointer);
                resetAll();
                startRun();
            });
        }catch (Exception e){
            System.out.println(e);
        }

    }


}