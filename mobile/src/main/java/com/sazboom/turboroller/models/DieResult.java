package com.sazboom.turboroller.models;

import android.support.v4.util.ArraySet;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by aaronworsham on 1/12/18.
 */

public class DieResult {

    private Die mDie;
    private Integer mRollFinalResult = 0;
    private boolean mHit = false;
    private ArrayList<Integer> mRolls = new ArrayList<>();
    private boolean rerollOption = false;
    private ArrayList<Integer> rerollArray = new ArrayList<>();
    private ArraySet<Integer> hitBox = new ArraySet<Integer>();

    public DieResult(Die d){
        mDie = d;
    }

    private int roll(){
        int roll = mDie.getRoll();
        mRolls.add(roll);
        return roll;
    }

    public void setHitBox(int[] array){
        for(int x : array){
            hitBox.add(x);
        }
    }

    private int hit(){
        int x = roll();
        if (hitBox.contains(x)){
            mHit = true;
            return 1;
        }
        return 0;
    }

    public int getDieFace(){
        return mDie.getFace();
    }

    public int getDieRoll(){
        return mDie.getRoll();
    }

    public void addStrightRoll(){
        mRollFinalResult = roll();
    }

    public void addHitRoll(){
        mRollFinalResult = hit();
    }

    public void addSumResult(){
        int r = roll();
        if(shouldIReroll(r)){
            addSumResult();
            return;
        }
        mRollFinalResult = calculateSum();

    }

    public void addBestOfResult(){
        int r = roll();
        if(shouldIReroll(r)){
            addBestOfResult();
            return;
        }
        mRollFinalResult = calculateBestOf();

    }

    public void addWorstOfResult(){
        int r = roll();
        if(shouldIReroll(r)){
            addWorstOfResult();
            return;
        }
        mRollFinalResult = calculateWorstOf();

    }

    public void addReplacedWith(ArrayList<Integer> replace, Integer with){
        Integer x = mDie.getRoll();
        mRolls.add(x);
        if (replace.contains(x)) mRollFinalResult = with;
        else mRollFinalResult = x;
    }

    public Integer NumberOfResults(){
        return mRolls.size();
    }

    public Integer getRollFinalResult(){
        return mRollFinalResult;
    }

    public void clearResults(){
        mRollFinalResult = 0;
        mRolls.clear();
    }

    private Integer calculateSum(){
        Integer t = 0;
        for(Integer r : mRolls){
            t += r;
        }
        return t;
    }

    private Integer calculateBestOf(){
        Integer t = 0;
        for(Integer r : mRolls){
            if(r > t) t = r;
        }
        return t;
    }

    private Integer calculateWorstOf(){
        Integer t = 0;
        for(Integer r : mRolls){
            if(t.equals(0)) t = r;
            else if(r < t) t = r;
        }
        return t;
    }


    public void setRerollOption(int[] rerollThese){
        rerollOption = true;
        for(int i : rerollThese){
            rerollArray.add(i);
        }
    }

    public boolean shouldIReroll(int x){
        if(rerollOption && rerollArray.contains(x)) return true;
        return false;
    }
}
