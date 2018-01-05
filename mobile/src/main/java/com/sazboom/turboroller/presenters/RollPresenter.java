package com.sazboom.turboroller.presenters;


import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import com.sazboom.turboroller.Exceptions.DiceRollerException;
import com.sazboom.turboroller.contracts.RollerContract;
import com.sazboom.turboroller.models.DiceRoller;
import com.sazboom.turboroller.views.fragments.DicePoolFragment;

/**
 * Created by aaronworsham on 12/28/17.
 */

public class RollPresenter implements RollerContract.Presenter {


    private Queue<DiceRoller> mPool;
    private String poolDescription = "";
    private Integer mDicePoolRoll = 0;
    private String mPoolRollDescription = "0";
    private RollerContract.View mDiceSelectorView;
    private  RollerContract.DicePoolView mDicePoolView;


    public  RollPresenter(RollerContract.View diceSelectorView, RollerContract.DicePoolView dicePoolView) {
        mPool = new LinkedList<>();
        mDicePoolView = dicePoolView;
        mDiceSelectorView = diceSelectorView;
    }

    public void start(){}

    public void addDiceToPool(int dieFace){
        this.addRollerToPool(dieFace);
        this.setPoolDescription();
    }

    public void rollPool(){
        this.clearRoll();
        this.roll();
        this.setDicePoolRoll();
    }

    public void clearDiceAndPool(){
        this.clearPool();
        this.clearRoll();
        this.setPoolDescription();
        this.setDicePoolRoll();
    }

    private void addRollerToPool(int dieFace){
        try {
            mPool.add(new DiceRoller(dieFace));
        }
        catch(DiceRollerException e){
            Log.e("ROLLER", "Unsupported Die Face");
        }
    }

    private void clearPool(){
        mPool.clear();
    }

    private void clearRoll(){
        mDicePoolRoll = 0;
        mPoolRollDescription = "0";
        for(DiceRoller dr : mPool){
            dr.clearRoll();
        }
    }


    private void setPoolDescription(){
        if(mPool.size() > 0){
            StringBuffer strb = new StringBuffer();
            HashMap<String, Integer> hm = this.buildPoolHashMap();
            Iterator hmi = hm.keySet().iterator();
            while(hmi.hasNext()) {
                String key=(String)hmi.next();
                Integer value=hm.get(key);
                strb.append(value);  //Number of dice
                strb.append("D");
                strb.append(key); //Face of dice
                strb.append(" ");
            }
            this.poolDescription = strb.toString();
        }
        else{
            this.poolDescription = "EMPTY";
        }

        mDicePoolView.setPoolDescription(this.poolDescription);
    }


    private void setDicePoolRoll(){
        if(mPool.size() > 0){
            StringBuffer strb = new StringBuffer();
            strb.append(this.mDicePoolRoll.toString());
            strb.append(" [");
            for(DiceRoller dr : mPool){
                strb.append(dr.getFace().toString());
                strb.append(":");
                strb.append(dr.getRoll().toString());
                strb.append(", ");
            }
            strb.append("]");
            this.mPoolRollDescription = strb.toString();
        }
        mDicePoolView.setPoolRoll(this.mPoolRollDescription);
    }


    private void roll(){
        int total = 0;
        for(DiceRoller dr : mPool){
            total += dr.getRoll();
        }
        mDicePoolRoll =  total;
    }

    private HashMap<String, Integer> buildPoolHashMap(){
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        String face = "";
        for(DiceRoller dr : mPool) {
            face = dr.getFace().toString();
            if (hm.containsKey(face)) {
                hm.put(face, hm.get(face) + 1);
            } else
                hm.put(face, 1);
        }
            return hm;
    }


}

