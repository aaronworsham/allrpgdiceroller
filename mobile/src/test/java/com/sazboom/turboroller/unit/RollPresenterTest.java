package com.sazboom.turboroller.unit;

import org.junit.Before;
import org.junit.Test;

import com.sazboom.turboroller.presenters.DndRollPresenter;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by aaronworsham on 12/28/17.
 */

public class RollPresenterTest {
    DndRollPresenter rp = new DndRollPresenter();

    @Before
    public void Before(){
        rp.addRollerToPool(6);
    }

    @Test
    public void addToPoolTest(){
        assertTrue(rp.getPoolLength() == 1);
    }

    @Test
    public void describePoolTest(){
        rp.setPoolDescription("");
        assertEquals("1D6 ", rp.getPoolDescription());
    }

    @Test
    public void rollPoolTest(){
        int tmp = rp.rollPool();
        assertTrue(tmp > 0);
        assertTrue(tmp < 7);
    }
}
