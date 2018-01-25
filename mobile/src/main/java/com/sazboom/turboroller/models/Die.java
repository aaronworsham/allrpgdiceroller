package com.sazboom.turboroller.models;

import android.support.v4.util.ArraySet;

import com.sazboom.turboroller.Exceptions.DieFaceException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;

/**
 * Created by aaronworsham on 12/27/17.
 */

public class Die {

    private int dieFace;
    private int mRollResult = 0;
    private ArraySet<Integer> hitBox = new ArraySet<Integer>();

    public Die(int dieFace) {
        this.dieFace = dieFace;
    }

    //Used for mocking
    public Die(){}

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

    public void setHitBox(int[] array){
        for(int x : array){
            hitBox.add(x);
        }
    }

    public boolean isAHit(){
        if( mRollResult == 0){
            roll();
        }
        return hitBox.contains(mRollResult);
    }


    public void clearRoll(){
        mRollResult = 0;
    }

    public Integer getFace(){
        return this.dieFace;
    }
}
