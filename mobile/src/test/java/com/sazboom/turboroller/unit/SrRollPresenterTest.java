package com.sazboom.turboroller.unit;

import com.sazboom.turboroller.contracts.RollerContract;
import com.sazboom.turboroller.models.Die;
import com.sazboom.turboroller.presenters.DndRollPresenter;
import com.sazboom.turboroller.presenters.SrRollResenter;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by aaronworsham on 12/28/17.
 */

public class SrRollPresenterTest {
    RollerContract.View mockedView = mock(RollerContract.View.class);
    SrRollResenter rp = new SrRollResenter(mockedView);


    @Test
    public void addToPoolTest(){
        rp.clearPool();
        rp.addDiceToPool(3);
        assertTrue(rp.getPoolSize() == 3);
    }

    @Test
    public void poolDescriptionTest(){
        rp.clearPool();
        rp.addDiceToPool(3);
        assertEquals("3D6 ", rp.getmPoolDesc());
    }

    @Test
    public void poolResultTest(){
        rp.clearPool();
        rp.addMockDiceToPool(new MockDie(6));
        rp.addMockDiceToPool(new MockDie(6));
        rp.addMockDiceToPool(new MockDie2(6));
        rp.setResults();
        assertEquals("2 HITS", rp.getmPoolRollDesc());
    }



    private class MockDie extends Die {

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

    private class MockDie2 extends Die {

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
