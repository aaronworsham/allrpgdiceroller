package com.sazboom.turboroller.unit;

import com.sazboom.turboroller.Exceptions.DieFaceException;
import com.sazboom.turboroller.models.DicePool;
import com.sazboom.turboroller.models.Die;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by aaronworsham on 1/12/18.
 */

public class DicePoolTest {

    DicePool dp;

    @Before
    public void Before(){
        dp = new DicePool();
    }


    @Test
    public void addDiceToPoolTest() throws DieFaceException {
        dp.addStrightDie(new MockDie(6));
        assertEquals(1, dp.dicePoolSize());
    }

    @Test
    public void sumPoolTest() {

        dp.addStrightDie(new MockDie(6));
        dp.addStrightDie(new MockDie(6));
        dp.addStrightDie(new MockDie(6));
        dp.addStrightDie(new MockDie(6));
        dp.addStrightDie(new MockDie(6));
        int sum = dp.sumPool();
        assertEquals(30, sum);
    }

    @Test
    public void bestPoolTest(){

        dp.addStrightDie(new MockDie(6));
        dp.addStrightDie(new MockDie2(6));
        dp.addStrightDie(new MockDie3(6));
        int best = dp.bestOfPool();
        assertEquals(6, best);
    }

    @Test
    public void worstPoolTest() {

        dp.addStrightDie(new MockDie(6));
        dp.addStrightDie(new MockDie2(6));
        dp.addStrightDie(new MockDie3(6));
        int worst = dp.worstOfPool();
        assertEquals(1, worst);
    }





    @Test
    public void bestOfDieTest() {
        dp.addBestOfDie(new MockDie(6), 2);
        int sum = dp.sumPool();
        assertEquals(6, sum);
    }

    @Test
    public void worstOfDieTest(){
        dp.addWorstOfDie(new MockDie(6), 2);
        int sum = dp.sumPool();
        assertEquals(1, sum);
    }

    @Test
    public void sumOfDieTest() {
        dp.addSumOfDie(new MockDie(6), 2);
        int sum = dp.sumPool();
        assertEquals(7, sum);
    }



    @Test
    public void rerollDieSumTest(){
        int[] rerollThese = {6};
        dp.addReRollSumOfDie(new MockDie(6),rerollThese );
        int rerolled = dp.sumPool();
        assertEquals(7, rerolled);
    }

    @Test
    public void rerollDieBestTest(){
        int[] rerollThese = {6};
        dp.addReRollBestOfDie(new MockDie(6),rerollThese );
        int rerolled = dp.sumPool();
        assertEquals(6, rerolled);
    }

    @Test
    public void rerollDieWorstTest(){
        int[] rerollThese = {6};
        dp.addReRollWorstOfDie(new MockDie(6),rerollThese );
        int rerolled = dp.sumPool();
        assertEquals(1, rerolled);
    }





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

    private class MockDie2 extends Die{

        Queue<Integer> rolls = new LinkedList<>();

        public MockDie2(Integer dieFace){
            super(dieFace);
            rolls.add(1);
            rolls.add(2);
            rolls.add(6);
        }

        @Override
        public Integer getRoll(){
            return rolls.remove();
        }
    }

    private class MockDie3 extends Die{

        Queue<Integer> rolls = new LinkedList<>();

        public MockDie3(Integer dieFace){
            super(dieFace);
            rolls.add(5);
            rolls.add(4);
            rolls.add(3);
        }

        @Override
        public Integer getRoll(){
            return rolls.remove();
        }
    }

}
