package com.example.demo2;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private String word ;
    private boolean map[] = new boolean[256 +1] ;
    private int counter = 0;
    private ArrayList<Character>randChar = new ArrayList<>( Arrays.asList('/','.','<',',','"','!','@','#','$',' ','(','['));
    private  int row = 0  , col = 0 ;
    private ArrayList<String> wordList;
    @FXML
    private VBox scroll;
    @FXML
    private TextField text_field;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       wordList = new ArrayList<>();

        try {

            BufferedReader reader = new BufferedReader(new FileReader("wordList"));
            String line = reader.readLine();
            while (line != null){
                wordList.add(line);

                line = reader.readLine();
            }

        }catch (Exception e) {
            System.out.println(e);
        }

        addToPane(1);

    }

    private void addToPane(int flag) {
        Text label = null;
        Collections.shuffle(wordList);

            for (int i = 0 ; i < 7 * 3 ; ){


                HBox vbox = new HBox(10);

                while (++i % 7 != 0 ){
                    if(flag == 1) {
                        label = new Text(wordList.get(i));
                    }
                    else if (flag == 2) {
                        char []str = wordList.get(i).toCharArray();

                        str[0] = Character.toUpperCase(str[0]);

                        label = new Text(new String(str));
                    }
                    else if (flag == 3){
                        if ((int)Math.floor(Math.random() * 2) == 1)
                        label = new Text(wordList.get(i) + (int)Math.floor(Math.random() * 99));
                        else
                        label = new Text(String.valueOf((int)Math.floor(Math.random() * 99)));
                    }
                    else {
                        label = convertToPuncatuation(wordList.get(i));
                    }


                    vbox.getChildren().add(label);

                }
                scroll.getChildren().add(vbox);
            }

    }



    @FXML
    private  void keyPress(KeyEvent e){

        if (e.getCode().equals(KeyCode.SPACE)){

            String str = text_field.getText().trim() ;
            HBox h = (HBox)scroll.getChildren().get(row);
            if (str.equals(((Text)(h.getChildren().get(col))).getText()))
                ((Text)((HBox)scroll.getChildren().get(row)).getChildren().get(col)).setFill(Color.GREEN);
            else
                ((Text)((HBox)scroll.getChildren().get(row)).getChildren().get(col)).setFill(Color.RED);
            text_field.setText("");



            if (col++ == 5 ){

                if (++row >= 2){
                    addRow(row);
                }
                col = 0;
            }
        }
        map[e.getCode().ordinal()] = true;
        System.out.println( e.getCode().ordinal());
        if (map[0]  && map[2] ){
            scroll.getChildren().clear();
            Collections.shuffle(wordList);
            addToPane(1);
            map[0] = false;
            map[1] = false;
        }

    }

    private void addRow(int r){
        scroll.getChildren().remove(0,1);
        row--;
        HBox hBox = new HBox(10);
        for (int i = (int) Math.round(Math.random() * wordList.size() - 1),j = 0 ; j < 6 ; i++,j++){
            Text text = new Text(wordList.get(i));
            hBox.getChildren().add(text);
        }
        scroll.getChildren().add(hBox);
    }

    public void set_upper(MouseEvent mouseEvent) {
        reset();
        addToPane(2);



    }

    public void set_number(MouseEvent mouseEvent) {
        reset();
        addToPane(3);
    }

    public void set_puncatuation(MouseEvent mouseEvent) {
        reset();
        addToPane(-1);

    }
    private void reset(){
        row = 0 ;
        col = 0;
        scroll.getChildren().clear();
    }
    private Text convertToPuncatuation(String s) {

            char rand = randChar.get((int)Math.round(Math.random() * (randChar.size()-1 ) ));
            if (rand == '<') return new Text('<' + s + '>');
            else if (rand == '(')return new Text('(' + s + ')');
            else if (rand == '[')return new Text('[' + s + ']');
            else if (rand == '"')return new Text('"' + s + '"');
            else if (rand == '.' || rand == ',' || rand == '!')return new Text( s + rand);
            return new Text(rand +  s);

    }
}
