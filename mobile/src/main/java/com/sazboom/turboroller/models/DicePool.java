package com.sazboom.turboroller.models;

import android.util.Log;

import com.sazboom.turboroller.Exceptions.DieFaceException;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by aaronworsham on 1/12/18.
 */

public class DicePool {

    private Queue<DieResult> mPool;
    private Integer mPoolRollFinal;


    //1) Add dice to pool: Straight, BestOf, WorstOf, SumOf
    //2) Actions on Pool: SumOf, BestOf, WorseOf

    public DicePool(){
        mPool = new LinkedList<>();
    }

    public Queue<DieResult> getDicePool(){
        return mPool;
    }


    //Straight Roll


    public void addStrightDie(Die die){

        DieResult dr = new DieResult(die);
        dr.addStrightRoll();
        mPool.add(dr);

    }

    public void addHitDie(Die die, int[] hitBox){
        DieResult dr = new DieResult(die);
        dr.setHitBox(hitBox);
        dr.addHitRoll();
        mPool.add(dr);

    }


    //Number of Rolls to select from


    public void addBestOfDie(Die die, int rolls){

        DieResult dr = new DieResult(die);
        for(int x = 0; x < rolls; x++){
            dr.addBestOfResult();
        }
        mPool.add(dr);

    }

    public void addWorstOfDie(Die die, int rolls){

        DieResult dr = new DieResult(die);
        for(int x = 0; x < rolls; x++){
            dr.addWorstOfResult();
        }
        mPool.add(dr);

    }

    public void addSumOfDie(Die die, int rolls){

        DieResult dr = new DieResult(die);
        for(int x = 0; x < rolls; x++){
            dr.addSumResult();
        }
        mPool.add(dr);

    }
//
//
//            01-24 15:10:55.652 17788 17788 E AndroidRuntime: Process: com.sazboom.turboroller, PID: 17788
//            01-24 15:10:55.652 17788 17788 E AndroidRuntime: java.util.NoSuchElementException
//01-24 15:10:55.652 17788 17788 E AndroidRuntime: 	at java.util.LinkedList.removeFirst(LinkedList.java:270)
//            01-24 15:10:55.652 17788 17788 E AndroidRuntime: 	at java.util.LinkedList.remove(LinkedList.java:685)
//            01-24 15:10:55.652 17788 17788 E AndroidRuntime: 	at com.sazboom.turboroller.models.DicePool.removeDie(DicePool.java:87)
//            01-24 15:10:55.652 17788 17788 E AndroidRuntime: 	at com.sazboom.turboroller.presenters.SrRollResenter.removeDiceFromPool(SrRollResenter.java:67)
//            01-24 15:10:55.652 17788 17788 E AndroidRuntime: 	at com.sazboom.turboroller.views.fragments.Sr5eFragment$2.onClick(Sr5eFragment.java:52)
//            01-24 15:10:55.652 17788 17788 E AndroidRuntime: 	at android.view.View.performClick(View.java:6294)
//            01-24 15:10:55.652 17788 17788 E AndroidRuntime: 	at android.view.View$PerformClick.run(View.java:24770)
//            01-24 15:10:55.652 17788 17788 E AndroidRuntime: 	at android.os.Handler.handleCallback(Handler.java:790)
//            01-24 15:10:55.652 17788 17788 E AndroidRuntime: 	at android.os.Handler.dispatchMessage(Handler.java:99)
//            01-24 15:10:55.652 17788 17788 E AndroidRuntime: 	at android.os.Looper.loop(Looper.java:164)
//            01-24 15:10:55.652 17788 17788 E AndroidRuntime: 	at android.app.ActivityThread.main(ActivityThread.java:6494)
//            01-24 15:10:55.652 17788 17788 E AndroidRuntime: 	at java.lang.reflect.Method.invoke(Native Method)
//            01-24 15:10:55.652 17788 17788 E AndroidRuntime: 	at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:438)
//            01-24 15:10:55.652 17788 17788 E AndroidRuntime: 	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:807)
//
    //todo fix NoSuchElementException
    public void removeDie(){
        mPool.remove();
    }

    //Reroll any results that are in this array




    public void addReRollBestOfDie(Die die, int[] rerollThese){

        DieResult dr = new DieResult(die);
        dr.setRerollOption(rerollThese);
        dr.addBestOfResult();
        mPool.add(dr);

    }

    public void addReRollWorstOfDie(Die die, int[] rerollThese){

        DieResult dr = new DieResult(die);
        dr.setRerollOption(rerollThese);
        dr.addWorstOfResult();
        mPool.add(dr);

    }

    public void addReRollSumOfDie(Die die, int[] rerollThese){

        DieResult dr = new DieResult(die);
        dr.setRerollOption(rerollThese);
        dr.addSumResult();
        mPool.add(dr);

    }


    public int dicePoolSize(){
        return mPool.size();
    }


    //Results of Rolls


    public int sumPool(){
        mPoolRollFinal = calculateSumOfPool();
        return mPoolRollFinal;

    }

    public int bestOfPool(){
        mPoolRollFinal = calculateBestOfPool();
        return mPoolRollFinal;

    }

    public int worstOfPool(){
        mPoolRollFinal = calculateWorstOfPool();
        return mPoolRollFinal;

    }




    public void clear(){
        mPool.clear();
    }



    //Private Methods

    private Integer calculateBestOfPool(){
        Integer t = 0;
        for(DieResult result : mPool){
            int r = result.getRollFinalResult();
            if( r > t) t = r;
        }
        return t;
    }
    private Integer calculateWorstOfPool(){
        Integer t = 0;
        for(DieResult result : mPool){
            int r = result.getRollFinalResult();
            if(t.equals(0)) t = r;
            else if(r < t) t = r;
        }
        return t;
    }

    private Integer calculateSumOfPool(){
        Integer t = 0;
        for(DieResult result : mPool){
            t += result.getRollFinalResult();
        }
        return t;
    }


}


