package com.example.speedtyping;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainGame extends Application {
    TypingGame game;
    @Override
    public void start(Stage stage) throws IOException {

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(MainGame.class.getResource("main-page.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();


        }catch (Exception e){
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        launch();
    }
}