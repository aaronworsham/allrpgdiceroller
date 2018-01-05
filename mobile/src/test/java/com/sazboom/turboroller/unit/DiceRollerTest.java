package com.sazboom.turboroller.unit;


import com.sazboom.turboroller.Exceptions.DiceRollerException;
import com.sazboom.turboroller.models.*;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * Created by aaronworsham on 12/27/17.
 */

public class DiceRollerTest {

    @Test public void testDiceRoll() throws DiceRollerException {

        int x;
        int y;
        int roll;
        int die;
        Integer[] dice = DiceRoller.mDice;
        DiceRoller rm;
        for(x = 0; x < dice.length; x++ ){
            die = dice[x];
            try {
                for(y = 0; y < 100; y++){
                    rm = new DiceRoller(die);
                    roll = rm.getRoll();
                    System.out.println("Dice: " + rm.getFace() + " Roll: " + roll);
                    assertTrue(roll < die + 1);
                    assertTrue(roll > 0);
                }
            } catch (DiceRollerException e) {
                e.printStackTrace();
            }


        }
    }

    @Test (expected = DiceRollerException.class)
    public void testFace() throws DiceRollerException{
        DiceRoller dr = new DiceRoller(7);
    }


}
