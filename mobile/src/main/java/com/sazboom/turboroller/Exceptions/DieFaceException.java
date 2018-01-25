package com.sazboom.turboroller.Exceptions;

/**
 * Created by aaronworsham on 12/27/17.
 */

public class DieFaceException extends Exception{
    String expString;

    public DieFaceException(String str){
        expString = str;
    }

    public String toString(){
        return expString;
    }

}
