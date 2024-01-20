package com.example.speedtyping;

import java.io.Serializable;
import java.util.*;

public class PlayerList implements Serializable {
    List<Player>listOfPlayers;

    public PlayerList() {

        this.listOfPlayers = new ArrayList<>();
    }

    public Player createPlayer(String namePlayer){
        if(!isFound(namePlayer)){
            System.out.println("not found player");
            this.listOfPlayers.add(new Player(namePlayer));
            return this.listOfPlayers.get(listOfPlayers.size()-1);
        }
        return this.listOfPlayers.stream().filter(e->e.getName().equals(namePlayer)).findAny().get();
    }

    public void deletePlayer(String namePlayer){
        this.listOfPlayers.removeIf(e -> e.getName().equals(namePlayer));
    }

    private boolean isFound(String namePlayer){
        return this.listOfPlayers.stream().anyMatch(e->e.getName().equals(namePlayer));
    }

    public int getHighScore(int second) {
        if(second == 15)
            return this.listOfPlayers.stream().max(Comparator.comparingInt(x -> x.getScore().getMax15())).get().getScore().getMax15();
        if(second == 30)
            return this.listOfPlayers.stream().max(Comparator.comparingInt(x -> x.getScore().getMax30())).get().getScore().getMax30();
        if(second == 60)
            return this.listOfPlayers.stream().max(Comparator.comparingInt(x -> x.getScore().getMax60())).get().getScore().getMax60();

        return 0;
    }
    public Map<Integer,List<Player>> updateScore(){
        Map<Integer,List<Player>>map = null;
        this.listOfPlayers.sort(Comparator.comparingInt(e -> e.getScore().getMax15()));
        map.put(15,listOfPlayers);
        this.listOfPlayers.sort(Comparator.comparingInt(e -> e.getScore().getMax30()));
        map.put(30,listOfPlayers);
        this.listOfPlayers.sort(Comparator.comparingInt(e -> e.getScore().getMax60()));
        map.put(60,listOfPlayers);
        return map;
    }

}