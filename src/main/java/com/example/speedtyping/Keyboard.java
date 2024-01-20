package com.example.speedtyping;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Keyboard implements Colorable , Initializable {


    public Pane pane3;

    private Pane pane1;


    private Pane pane2;


    Map<String, String> hm = new HashMap<String, String>();
    public Keyboard(Pane pane3, Pane pane1, Pane pane2) {
        this.pane3 = pane3;
        this.pane1 = pane1;
        this.pane2 = pane2;
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



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

    public void changeLayout(int num)
    {
        if(num == 1)
        {
            pane1.setVisible(false);
            pane2.setVisible(false);
            pane3.setVisible(true);
        }
        if(num == 2)
        {
            pane1.setVisible(false);
            pane2.setVisible(true);
            pane3.setVisible(false);
        }
        if(num == 3)
        {
            pane1.setVisible(false);
            pane2.setVisible(false);
            pane3.setVisible(true);
        }
    }

    public void changeColor(KeyEvent e,boolean isCorrect) {
        System.out.println("hello change color");
        System.out.println(e);
        System.out.println(hm.size());
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



                if(((Button)node).getText().equals(e.getCode().toString()))
                {
                    if(isCorrect || e.getCode().toString().equals("SPACE"))
                        ((Button)node).setStyle("-fx-background-color:" + NEW_COLOR_TRUE +  "; -fx-border-color: " + NEW_COLOR_TRUE +  ";-fx-border-radius: 10; -fx-border-width: 1px 1px 1px 1px; -fx-text-fill:  #1D1C1D;");
                    else
                        ((Button)node).setStyle("-fx-background-color:" + NEW_COLOR_FALSE +  "; -fx-border-color: " + NEW_COLOR_FALSE +  ";-fx-border-radius: 10; -fx-border-width: 1px 1px 1px 1px; -fx-text-fill:  #1D1C1D;");

                    //-fx-background-color: transparent;  -fx-border-color: #dbd8b6;-fx-border-radius: 10; -fx-border-width: 1px 1px 1px 1px; -fx-text-fill: white;
                    break;
                }
                else if(hm.containsKey(e.getCode().toString()) && ((Button)node).getText().equals(hm.get(e.getCode().toString())))
                {

                    ((Button)node).setStyle("-fx-background-color:" + NEW_COLOR_FALSE +  "; -fx-border-color: #dbd8b6;-fx-border-radius: 10; -fx-border-width: 1px 1px 1px 1px; -fx-text-fill:  #1D1C1D;");
                    break;
                }

            }
        }


    }




    public void returnColor() {
        System.out.println("helloSpace");
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