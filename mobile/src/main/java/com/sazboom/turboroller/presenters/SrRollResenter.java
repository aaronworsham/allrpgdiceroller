package com.sazboom.turboroller.presenters;


import android.support.v4.util.ArraySet;

import com.sazboom.turboroller.contracts.RollerContract;
import com.sazboom.turboroller.models.Die;
import com.sazboom.turboroller.models.DieResult;
import com.sazboom.turboroller.models.Dnd5eDicePool;
import com.sazboom.turboroller.models.Sr5eDicePool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Queue;

/**
 * Created by aaronworsham on 12/28/17.
 */

public class SrRollResenter implements RollerContract.Presenter {

    public static final int[] DICE = {4,6,8,10,12,20,100};

    private String mPoolDesc = "";
    private String mPoolRollDesc = "0";
    private String mPoolResultsDesc = "[]";

    private Queue<DieResult> mPoolDieQueue;

    private Sr5eDicePool mDicePool = new Sr5eDicePool();


    private Integer mDicePoolRoll = 0;


    private Integer mDicePoolBonus = 0;

    private RollerContract.View mView;

    private Die mockDie1 = null;
    private Die mockDie2 = null;


    //Constructors

    public SrRollResenter(RollerContract.View view) {
        mView = view;
    }

    public void start(){}

    //Rollers


    //Adders

    public void addDiceToPool(int num){
        for(int x = 0; x < num; x++){
           mDicePool.addDie(new Die(6));
            buildPoolDescription();
        }

    }

    public void removeDiceFromPool(int num){
        for(int x = 0; x < num; x++){
            mDicePool.removeDie();
            buildPoolDescription();
        }
    }

    public void addMockDiceToPool(Die d){
        mDicePool.addDie(d);
        buildPoolDescription();
    }




    //Getters

    public int getPoolSize(){
        return mDicePool.dicePoolSize();
    }

    public String getmPoolDesc(){
        return mPoolDesc;
    }

    public String getmPoolRollDesc(){
        return mPoolRollDesc;
    }

    public String getmPoolResultsDesc(){
        return mPoolResultsDesc;
    }

    //Setters

    public void setResults(){
        buildPoolRollDescription();
        buildPoolResultDescription();

    }

    public void setMockDie(Die d1, Die d2){
        mockDie1 = d1;
        mockDie2 = d2;
    }


    //Cleaners

    public void clearPool(){
        mDicePool = new Sr5eDicePool();
        mPoolDesc = "";
        mPoolRollDesc = "0";
        mPoolResultsDesc = "[]";
        mDicePoolBonus = 0;
        setPoolDescription();
        setPoolRollDescription();
        setPoolResultDescription();
    }





    //Private Builders and Setters

    //Set the Description of the Pool.
    // 1) Create a String Buffer
    // 2) Get a Queue of Dice in Pool
    // 3) Create Hashmap of Queue to sum up number of like dice
    // 4) Iterate over Hashmap to append to Stringbuffer

    private void buildPoolDescription(){
        mPoolDieQueue = mDicePool.getDicePool();
        StringBuffer strb = new StringBuffer();
        if(mPoolDieQueue.size() > 0) {
            HashMap<String, Integer> hm = this.buildPoolHashMap();
            Iterator hmi = hm.keySet().iterator();

            while (hmi.hasNext()) {
                String key = (String) hmi.next();
                Integer value = hm.get(key);
                strb.append(value);  //Number of dice
                strb.append("D");
                strb.append(key); //Face of dice
                strb.append(" ");
            }
        }
        if(mDicePoolBonus > 0){
            strb.append("+");
            strb.append(mDicePoolBonus.toString());
        }
        if(mDicePoolBonus < 0){
            strb.append(mDicePoolBonus.toString());
        }
        mPoolDesc = strb.toString();
        setPoolDescription();

    }


    // Private Setters for the View

    private void setPoolDescription(){
        mView.setPoolDescription(this.mPoolDesc);
    }


    //Set the Result of the Pool Roll


    private void buildPoolRollDescription(){
        int x = mDicePool.getResults();
        finalPoolRollDescription(x);


    }


    private void finalPoolRollDescription(int x){
        if (x > 0) {
            x += mDicePoolBonus;
            mPoolRollDesc = Integer.toString(x);
        }
        else{
            mPoolRollDesc = "0 HITS";
        }

        mPoolRollDesc = mPoolRollDesc + " HITS";

        setPoolRollDescription();
    }

    private void setPoolRollDescription(){
        mView.setPoolRoll(mPoolRollDesc);
    }




    //Set the Breakdown Results of each Die Roll.

    private void buildPoolResultDescription(){
        mPoolDieQueue = mDicePool.getDicePool();

        if(mPoolDieQueue.size() > 0){
            StringBuffer strb = new StringBuffer();
            for(DieResult dr : mPoolDieQueue){
                strb.append("[");
                strb.append("D");
                strb.append(Integer.toString(dr.getDieFace()));
                strb.append(":");
                strb.append(Integer.toString(dr.getDieRoll()));
                strb.append("]");
            }
            if(mDicePoolBonus > 0){
                strb.append("[");
                strb.append("+");
                strb.append(mDicePoolBonus.toString());
                strb.append("]");
            }
            else if(mDicePoolBonus < 0){
                strb.append("[");
                strb.append(mDicePoolBonus.toString());
                strb.append("]");
            }
            mPoolResultsDesc = strb.toString();
        }

        setPoolResultDescription();

    }

    public void setPoolResultDescription(){
        mView.setPoolResults(mPoolResultsDesc);
    }



    private HashMap<String, Integer> buildPoolHashMap(){
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        String face = "";
        for(DieResult dr : mDicePool.getDicePool()) {
            face = Integer.toString(dr.getDieFace());
            if (hm.containsKey(face)) {
                hm.put(face, hm.get(face) + 1);
            } else
                hm.put(face, 1);
        }
            return hm;
    }


}

