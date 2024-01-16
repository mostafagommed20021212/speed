package com.example.demo2;

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

    @FXML
    public Pane pane3;
    @FXML
    private Pane pane1;

    @FXML
    private Pane pane2;
    Map<String, String> hm = new HashMap<String, String>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //pane2.setVisible(!false);
        //pane1.setVisible(!true);
        hm.put("OPEN_BRACKET", "[");
        hm.put("CLOSE_BRACKET", "]");
        hm.put("SEMICOLON", ";");
        hm.put("QUOTE", "'");
        hm.put("COMMA",",");
        hm.put("PERIOD",".");
        hm.put("SLASH","/");
    }



    public void changeColor(KeyEvent e) {
        Pane pane = (Pane)e.getSource();
        System.out.println(hm.size());
        for (Node node:pane.getChildren()) {

            if(node instanceof Button)
            {

                System.out.println(e.getCode());
                System.out.println(((Button) node).getText());
                if(((Button)node).getText().equals(e.getCode().toString()))
                {
                    ((Button)node).setStyle("-fx-background-color:" + NEW_COLOR +  "; -fx-border-color: #dbd8b6;-fx-border-radius: 10; -fx-border-width: 1px 1px 1px 1px; -fx-text-fill:  #1D1C1D;");
                    break;
                }
                else if(hm.containsKey(e.getCode().toString()) && ((Button)node).getText().equals(hm.get(e.getCode().toString())))
                {
                    System.out.println("hello");
                    ((Button)node).setStyle("-fx-background-color:" + NEW_COLOR +  "; -fx-border-color: #dbd8b6;-fx-border-radius: 10; -fx-border-width: 1px 1px 1px 1px; -fx-text-fill:  #1D1C1D;");
                    break;
                }

            }
        }


    }


    public void returnColor(KeyEvent e) {

        Pane pane = (Pane)e.getSource();
        for (Node node:pane.getChildren()) {

            if(node instanceof Button)
            {

                if(((Button)node).getText().equals(e.getCode().toString()))
                {

                    ((Button)node).setStyle("-fx-background-color:" +  ORIGINAL_COLOR + ";  -fx-border-color: #dbd8b6;-fx-border-radius: 10; -fx-border-width: 1px 1px 1px 1px; -fx-text-fill: white;");
                    break;
                }
                else if(hm.containsKey(e.getCode().toString()) && ((Button)node).getText().equals(hm.get(e.getCode().toString())))
                {

                    ((Button)node).setStyle("-fx-background-color:" + ORIGINAL_COLOR +  "; -fx-border-color: #dbd8b6;-fx-border-radius: 10; -fx-border-width: 1px 1px 1px 1px; -fx-text-fill:  white;");
                    break;
                }


            }
        }

    }
}
