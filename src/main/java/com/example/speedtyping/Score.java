package com.example.speedtyping;


import javafx.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Score implements Serializable {

    private int max15,max30,max60;
    private int acc15,acc30,acc60;

    List<Map.Entry<Character, Integer>> sortedList;

    public int getAcc15() {
        return acc15;
    }

    public void setAcc15(int acc15) {
        this.acc15 = acc15;
    }

    public int getAcc30() {
        return acc30;
    }

    public void setAcc30(int acc30) {
        this.acc30 = acc30;
    }

    public int getAcc60() {
        return acc60;
    }

    public void setAcc60(int acc60) {
        this.acc60 = acc60;
    }

    public Score() {

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
        sortedList = new ArrayList<>(storage.entrySet());
        sortedList.sort(Map.Entry.<Character, Integer>comparingByValue().reversed());
    }
    @Override
    public String toString(){

        StringBuilder str = new StringBuilder("[ ");
        int n = 5;
        for (Map.Entry<Character, Integer> entry : sortedList) {
            str.append(entry.getKey()).append(" ");
            if(--n == 0)break;
        }
        str.append("]");

        return str.toString();
    }
}