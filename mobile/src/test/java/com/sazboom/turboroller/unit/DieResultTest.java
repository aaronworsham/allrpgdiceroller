package com.sazboom.turboroller.unit;

import com.sazboom.turboroller.Exceptions.DieFaceException;
import com.sazboom.turboroller.models.DieResult;
import com.sazboom.turboroller.models.Die;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by aaronworsham on 1/12/18.
 */

public class DieResultTest {
    DieResult dr;


    private class MockDie extends Die{

        Queue<Integer> rolls = new LinkedList<>();

        public MockDie(Integer dieFace){
            super(dieFace);
            rolls.add(6);
            rolls.add(1);
            rolls.add(2);
        }

        @Override
        public Integer getRoll(){
            return rolls.remove();
        }
    }


    @Test
    public void AddRollToResultTest(){

        Die d = new MockDie(6);
        dr = new DieResult(d);
        dr.addSumResult();  //Roll 6
        assertEquals((Integer) 1, dr.NumberOfResults());


    }

    @Test
    public void GetFinalResult(){

        Die d = new MockDie(6);
        dr = new DieResult(d);
        dr.addSumResult();  //Roll 6
        dr.addSumResult();  //Roll 1
        dr.addSumResult();  //Roll 2
        assertEquals((Integer) 9, dr.getRollFinalResult());



    }


    @Test
    public void GetBestOfResult(){

        Die d = new MockDie(6);
        dr = new DieResult(d);
        dr.addBestOfResult();  //Roll 6
        dr.addBestOfResult();  //Roll 1
        dr.addBestOfResult();  //Roll 2
        assertEquals((Integer) 6, dr.getRollFinalResult());


    }


    @Test
    public void GetWorstOfResult(){

        Die d = new MockDie(6);
        dr = new DieResult(d);
        dr.addWorstOfResult();  //Roll 6
        dr.addWorstOfResult();  //Roll 1
        dr.addWorstOfResult();  //Roll 2
        assertEquals((Integer) 1, dr.getRollFinalResult());




    }

    @Test
    public void ReplaceWithResult(){
        ArrayList<Integer> replace = new ArrayList<>();
        replace.add(6);

        Die d = new MockDie(6);
        dr = new DieResult(d);
        dr.addReplacedWith(replace, 1);  //Roll 6
        assertEquals((Integer) 1, dr.getRollFinalResult());

    }

    @Test
    public void rerollSumTest(){
        int[] reroll = {6};

        Die d = new MockDie(6);
        dr = new DieResult(d);
        dr.setRerollOption(reroll);
        dr.addSumResult(); //Roll 6 then 1
        assertEquals((Integer)7, dr.getRollFinalResult());

    }

    @Test
    public void rerollBestTest(){
        int[] reroll = {6};

        Die d = new MockDie(6);
        dr = new DieResult(d);
        dr.setRerollOption(reroll);
        dr.addBestOfResult(); //Roll 6 then 1
        assertEquals((Integer)6, dr.getRollFinalResult());

    }

    @Test
    public void rerollWorstTest(){
        int[] reroll = {6};

        Die d = new MockDie(6);
        dr = new DieResult(d);
        dr.setRerollOption(reroll);
        dr.addWorstOfResult(); //Roll 6 then 1
        assertEquals((Integer)1, dr.getRollFinalResult());

    }


    @Test public void testPositiveHitBox() {
        Die d = new MockDie(6);
        int[] array = {6};
        dr = new DieResult(d);
        dr.setHitBox(array);
        dr.addHitRoll();
        assertEquals((Integer)1,dr.getRollFinalResult());


    }
    @Test public void testNegativeHitBox() {
        Die d = new MockDie(6);
        int[] array = {1};
        dr = new DieResult(d);
        dr.setHitBox(array);
        dr.addHitRoll();
        assertEquals((Integer)0,dr.getRollFinalResult());


    }


}
