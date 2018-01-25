package com.sazboom.turboroller.unit;

import org.junit.Test;
import static org.mockito.Mockito.*;

import com.sazboom.turboroller.contracts.RollerContract;
import com.sazboom.turboroller.models.Die;
import com.sazboom.turboroller.presenters.DndRollPresenter;

import java.util.LinkedList;
import java.util.Queue;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by aaronworsham on 12/28/17.
 */

public class DndRollPresenterTest {
    RollerContract.View mockedView = mock(RollerContract.View.class);
    DndRollPresenter rp = new DndRollPresenter(mockedView);


    @Test
    public void addToPoolTest(){
        rp.clearPool();
        rp.addDiceToPool(6);
        assertTrue(rp.getPoolSize() == 1);
    }

    @Test
    public void describePoolTest(){
        rp.clearPool();
        rp.addDiceToPool(6);
        assertEquals("1D6 ", rp.getmPoolDesc());
    }

    @Test
    public void clearPoolTest(){
        rp.clearPool();
        rp.addDiceToPool(6);
        rp.clearPool();
        assertTrue(rp.getPoolSize() == 0);
        assertEquals("", rp.getmPoolDesc());
        assertEquals("0", rp.getmPoolRollDesc());
        assertEquals("[]", rp.getmPoolResultsDesc());
    }

    @Test
    public void poolRollTest(){
        rp.clearPool();
        rp.addMockDiceToPool(new MockDie(6));
        rp.setResults();
        assertEquals("6", rp.getmPoolRollDesc());
        rp.addMockDiceToPool(new MockDie(6));
        rp.setResults();
        assertEquals("12", rp.getmPoolRollDesc());
    }

    @Test
    public void poolResultTest(){
        rp.clearPool();
        rp.addMockDiceToPool(new MockDie(6));
        rp.setResults();
        assertEquals("[D6:6]", rp.getmPoolResultsDesc());
    }

    @Test
    public void rollAdvantageTest(){
        rp.clearPool();
        rp.setMockDie(new MockD20(), new MockD20b());
        rp.rollAdvantage();
        rp.setResults();
        assertEquals("2D20 ", rp.getmPoolDesc());
        assertEquals("20", rp.getmPoolRollDesc());
        assertEquals("[D20:2][D20:20]", rp.getmPoolResultsDesc());

    }

    @Test
    public void rollDisadvantageTest(){
        rp.clearPool();
        rp.setMockDie(new MockD20(), new MockD20b());
        rp.rollDisadvantage();
        rp.setResults();
        assertEquals("2D20 ", rp.getmPoolDesc());
        assertEquals("2", rp.getmPoolRollDesc());
        assertEquals("[D20:2][D20:20]", rp.getmPoolResultsDesc());

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
            return 6;
        }
    }

    private class MockD20 extends Die{

        Queue<Integer> rolls = new LinkedList<>();


        public MockD20(){
            super(20);

        }

        @Override
        public Integer getRoll(){
            return 2;
        }
    }

    private class MockD20b extends Die{

        Queue<Integer> rolls = new LinkedList<>();


        public MockD20b(){
            super(20);
        }

        @Override
        public Integer getRoll(){
            return 20;
        }
    }



}
