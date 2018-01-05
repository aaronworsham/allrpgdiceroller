package com.sazboom.turboroller.Exceptions;

/**
 * Created by aaronworsham on 12/27/17.
 */

public class DiceRollerException extends Exception{
    String expString;

    public DiceRollerException(String str){
        expString = str;
    }

    public String toString(){
        return expString;
    }

}
