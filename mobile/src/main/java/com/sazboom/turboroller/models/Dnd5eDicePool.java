package com.sazboom.turboroller.models;

/**
 * Created by aaronworsham on 1/12/18.
 */

public class Dnd5eDicePool extends DicePool {
    public static final Integer[] Dice = {4,6,8,10,12,20,100};
    private boolean isAdv = false;
    private boolean isDis = false;
    private boolean isSum = false;
    public Dnd5eDicePool(){
        super();
    }

    @Override
    public void addStrightDie (Die die){
        if(!isAdv || !isDis) super.addStrightDie(die);

        isSum = true;
    }

    public void addAdv (Die die1, Die die2){
        if(!isSum) {
            super.addStrightDie(die1);
            super.addStrightDie(die2);

            isAdv = true;
        }
    }

    public void addDis (Die die1, Die die2){
        if(!isSum) {
            super.addStrightDie(die1);
            super.addStrightDie(die2);
            isDis = true;
        }
    }

    public boolean isVantage(){
        if (isAdv || isDis ) return true;
        return false;
    }

    public boolean isStraight(){
        return isSum;
    }

    public int getResults(){
        if(isAdv){
           return super.bestOfPool();
        }
        else if(isDis){
            return super.worstOfPool();
        }
        else {
            return super.sumPool();
        }
    }

}
