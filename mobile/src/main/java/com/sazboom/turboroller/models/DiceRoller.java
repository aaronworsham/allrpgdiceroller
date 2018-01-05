package com.sazboom.turboroller.models;

import com.sazboom.turboroller.Exceptions.DiceRollerException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by aaronworsham on 12/27/17.
 */

public class DiceRoller {
    public static final Integer[] mDice = {4,6,8,10,12,20,100};
    public static final String mTestBinding = "TEST BINDING";
    private int dieFace;
    private int roll = 0;
    private int mRollResult = 0;

    public DiceRoller(int dieFace) throws DiceRollerException{
        ArrayList<Integer> al = new ArrayList<>(Arrays.asList(mDice));
        if(!al.contains(dieFace)){
            throw new DiceRollerException("Die face " + dieFace + " not supported");
        }
        this.dieFace = dieFace;
    }




    private void roll(){
        Random rnd = new Random();
        int r = rnd.nextInt(this.dieFace);
        mRollResult = r + 1;
    }

    public Integer getRoll(){
        if(mRollResult<1){
            this.roll();
        }
        return mRollResult;
    }

    public void clearRoll(){
        mRollResult = 0;
    }



    public Integer getTotal(){
        return this.roll;
    }

    public Integer getFace(){
        return this.dieFace;
    }
}
