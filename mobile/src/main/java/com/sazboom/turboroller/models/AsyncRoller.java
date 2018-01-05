package com.sazboom.turboroller.models;

import android.os.AsyncTask;

import java.util.Random;

/**
 * Created by aaronworsham on 12/27/17.
 */

public class AsyncRoller extends AsyncTask<String, Integer, String> {
    @Override
    protected String doInBackground(String... params) {
        try {
            System.out.println("Entering Sleep");
            Thread.sleep(1000);
            System.out.println("Entering Sleep");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "Done!";

    }

    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
    }
    protected void onPostExecute(String result){
        super.onPostExecute(result);

    }

}
