package com.example.demo2;

import javafx.util.Pair;

import java.util.Map;
import java.util.PriorityQueue;

public class Score {

    private int max15,max30,max60;
    PriorityQueue<Pair<Integer,Character>> worstCh;

    public Score() {
        this.worstCh = new PriorityQueue<>();
        this.max15 = 0;
        this.max30 = 0;
        this.max60 = 0;
    }

    public int getMax15() {
        return max15;
    }

    public void setMax15(int max15) {
        this.max15 = max15;
    }

    public int getMax30() {
        return max30;
    }

    public void setMax30(int max30) {
        this.max30 = max30;
    }

    public int getMax60() {
        return max60;
    }

    public void setMax60(int max60) {
        this.max60 = max60;
    }

    public void updateWorstCh(Map<Character,Integer>storage){
        worstCh.clear();
        for(Map.Entry<Character, Integer> it : storage.entrySet()) {
            worstCh.add(new Pair<>(it.getValue(),it.getKey()));
        }
    }
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("[ ");
        int n = 5;
        while(n != 0 && worstCh.peek() != null) {
            str.append(worstCh.peek().getValue()).append(" ");
            n--;
        }
        str.append("]");
        return str.toString();
    }
}
