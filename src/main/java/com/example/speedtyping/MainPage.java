package com.example.speedtyping;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class MainPage {
    public Button hello;
    public TextField textArea;

    private PlayerList players;

    public void loadPlayersFromFile(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            players = (PlayerList) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public void searchPlayer(ActionEvent actionEvent) {

        getdata(players.createPlayer(textArea.getText()));

    }

    private void getdata(Player player) {

    }
}
