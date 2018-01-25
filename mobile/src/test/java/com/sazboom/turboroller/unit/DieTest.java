package com.sazboom.turboroller.unit;


import com.sazboom.turboroller.Exceptions.DieFaceException;
import com.sazboom.turboroller.models.*;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by aaronworsham on 12/27/17.
 */

public class DieTest {

    @Test public void testDiceRoll() {

        int roll;
        Die rm;
        for(int x = 1; x < 100; x++ ){
            for(int y = 1; y < 100; y++){
                rm = new Die(x);
                roll = rm.getRoll();
                assertTrue(roll < (x + 1));
                assertTrue(roll > 0);
            }



        }
    }




}
