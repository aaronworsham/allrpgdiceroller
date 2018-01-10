package com.sazboom.turboroller.presenters;


import android.app.Activity;
import android.util.Log;


import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import com.sazboom.turboroller.Exceptions.DiceRollerException;
import com.sazboom.turboroller.contracts.RollerContract;
import com.sazboom.turboroller.models.DiceRoller;
import com.sazboom.turboroller.views.Dnd5eMainActivity;

/**
 * Created by aaronworsham on 12/28/17.
 */

public class DndRollPresenter implements RollerContract.Presenter {

    public static final int[] DICE = {4,6,8,10,12,20,100};
    private Queue<DiceRoller> mPool;
    private String poolDescription = "";

    private Integer mDicePoolRoll = 0;
    private String mPoolRollDescription = "0";
    private String mPoolResults = "[]";
    private Integer mDicePoolBonus = 0;
    private RollerContract.View mView;


    public DndRollPresenter(RollerContract.View view) {
        mPool = new LinkedList<>();
        mView = view;
    }

    public void start(){}

    public void addDiceToPool(int dieFace){
        this.addRollerToPool(dieFace);
        this.setPoolDescription();
    }

    public void addPosDicePoolBonus(int bonus){
        mDicePoolBonus += bonus;
        this.setPoolDescription();
    }

    public void addNegDicePoolBonus(int bonus){
        mDicePoolBonus -= bonus;
        this.setPoolDescription();
    }

    public void rollPool(){
        this.clearRoll();
        this.roll();
        this.setDicePoolRoll();
        this.setDicePoolResults();
    }

    public void clearDiceAndPool(){
        this.clearPool();
        this.clearRoll();
        this.clearBonus();
        this.setPoolDescription();
        this.setDicePoolRoll();
        this.setDicePoolResults();
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
        mPoolResults = "[]";
        for(DiceRoller dr : mPool){
            dr.clearRoll();
        }
    }

    private void clearBonus(){
        mDicePoolBonus = 0;
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
            if(mDicePoolBonus > 0){
                strb.append("+");
                strb.append(mDicePoolBonus.toString());
            }
            else if(mDicePoolBonus < 0){
                strb.append(mDicePoolBonus.toString());
            }
            this.poolDescription = strb.toString();
        }
        else{
            this.poolDescription = "EMPTY";
        }

        mView.setPoolDescription(this.poolDescription);
    }


    private void setDicePoolRoll(){

        if(mPool.size() > 0){
            Integer total = this.mDicePoolRoll + mDicePoolBonus;
            StringBuffer strb = new StringBuffer();
            strb.append(total.toString());
            this.mPoolRollDescription = strb.toString();

        }

        mView.setPoolRoll(this.mPoolRollDescription);
    }

    private void setDicePoolResults(){
        if(mPool.size() > 0){
            StringBuffer strb = new StringBuffer();
            for(DiceRoller dr : mPool){
                strb.append("[");
                strb.append("D");
                strb.append(dr.getFace().toString());
                strb.append(":");
                strb.append(dr.getRoll().toString());
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
            this.mPoolResults = strb.toString();
        }
        mView.setPoolResults(this.mPoolResults);
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

