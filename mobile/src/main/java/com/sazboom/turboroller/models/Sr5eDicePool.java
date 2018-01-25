package com.sazboom.turboroller.models;

import android.support.v4.util.ArraySet;

/**
 * Created by aaronworsham on 1/12/18.
 */

public class Sr5eDicePool extends DicePool {
    public static final Integer[] Dice = {4,6,8,10,12,20,100};
    private static int[] hitBox = {5,6};
    private boolean isAdv = false;
    private boolean isDis = false;
    private boolean isSum = false;
    public Sr5eDicePool(){
        super();
    }

    public void addDice (ArraySet<Die> dice){
       for(Die d : dice){
           addHitDie(d, hitBox);
       }
    }

    public void addDie (Die die){
        addHitDie(die, hitBox);
    }



    public int getResults(){
        return sumPool();
    }

}
