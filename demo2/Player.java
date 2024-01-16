package com.example.demo2;
import java.util.Map;

public class Player {

    private String name;
    private Score score;


    public Player(String name) {
        this.name = name;
        this.score = new Score();
    }

    public Score getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setScore(int newScore, int second){
        if(second == 15 && newScore > this.score.getMax15()){
            this.score.setMax15(newScore);
        }
        else if(second == 30 && newScore > this.score.getMax30()){
            this.score.setMax30(newScore);
        }
        else if (second == 60 && newScore > this.score.getMax60()){
            this.score.setMax60(newScore);
        }
    }
    public void setWorstCharacter(Map<Character,Integer>storage){
        score.updateWorstCh(storage);
    }
    @Override
    public String toString(){
        return score.toString();
    }
}
