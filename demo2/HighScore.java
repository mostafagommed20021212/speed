package com.example.demo2;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public  class  HighScore {

    public static String updateHighScore(int second)
    {
        int maxi;
        String name = "Ahmed";
        File f = new File(name);

        try{
            FileInputStream fos = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fos);
            PlayerList p = (PlayerList)ois.readObject();
            if(p!=null)
            {
                return String.valueOf(p.getHighScore(second));

            }
        }catch(Exception e)
        {
            System.out.println(e);
        }


        return "Hello world!";
    }

}
