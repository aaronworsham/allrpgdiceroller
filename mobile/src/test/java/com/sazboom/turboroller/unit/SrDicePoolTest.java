package com.sazboom.turboroller.unit;

import android.support.v4.util.ArraySet;

import com.sazboom.turboroller.models.Die;
import com.sazboom.turboroller.models.Dnd5eDicePool;
import com.sazboom.turboroller.models.Sr5eDicePool;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by aaronworsham on 1/12/18.
 */

public class SrDicePoolTest {

    Sr5eDicePool dp;

    @Before
    public void Before(){
        dp = new Sr5eDicePool();
    }


    @Test
    public void setHitTest(){
        ArraySet<Die> dice = new ArraySet<Die>();
        dice.add(new MockDie(6));
        dice.add(new MockDie(6));
        dice.add(new MockDie2(6));
        dp.addDice(dice);
        assertEquals(dp.getResults(), 2);
    }

    public class MockDie extends Die {

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

    public class MockDie2 extends Die {

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




}


