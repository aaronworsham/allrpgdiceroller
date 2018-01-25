package com.sazboom.turboroller.unit;

import com.sazboom.turboroller.Exceptions.DieFaceException;
import com.sazboom.turboroller.models.DicePool;
import com.sazboom.turboroller.models.Die;
import com.sazboom.turboroller.models.Dnd5eDicePool;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

/**
 * Created by aaronworsham on 1/12/18.
 */

public class DndDicePoolTest {

    Dnd5eDicePool dp;

    @Before
    public void Before(){
        dp = new Dnd5eDicePool();
    }




    private class MockDie extends Die {

        Queue<Integer> rolls = new LinkedList<>();

        public MockDie(Integer dieFace){
            super(dieFace);
            rolls.add(7);
            rolls.add(18);
            rolls.add(2);
        }



        @Override
        public Integer getRoll(){
            return rolls.remove();
        }
    }

    private class MockDie2 extends Die {

        Queue<Integer> rolls = new LinkedList<>();

        public MockDie2(Integer dieFace){
            super(dieFace);
            rolls.add(18);
            rolls.add(13);
            rolls.add(2);
        }



        @Override
        public Integer getRoll(){
            return rolls.remove();
        }
    }



}


