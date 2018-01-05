package com.sazboom.turboroller.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sazboom.turboroller.R;
import com.sazboom.turboroller.presenters.RollPresenter;
import com.sazboom.turboroller.utils.ActivityUtils;
import com.sazboom.turboroller.views.fragments.DicePoolFragment;
import com.sazboom.turboroller.views.fragments.DndDiceSelectorFragment;

/**
 * Created by aaronworsham on 12/28/17.
 */

public class MainActivity extends AppCompatActivity {
    RollPresenter mRollPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("TEST", "Main Activity On Create");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DicePoolFragment poolFragment = (DicePoolFragment) getSupportFragmentManager()
                .findFragmentById(R.id.pool_output);
        if(poolFragment == null){
            poolFragment = DicePoolFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), poolFragment, R.id.pool_output);
        }

        DndDiceSelectorFragment diceFragment = (DndDiceSelectorFragment) getSupportFragmentManager()
                .findFragmentById(R.id.dice_selector);
        if(diceFragment == null){
            diceFragment = DndDiceSelectorFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), diceFragment, R.id.dice_selector);
        }

        mRollPresenter = new RollPresenter(diceFragment, poolFragment);
        diceFragment.setPresenter(mRollPresenter);
        poolFragment.setPresenter(mRollPresenter);




    }

    public void onPause(){
        super.onPause();
        Log.i("TEST", "Main Activity On Pause");

    }

    public void onResume(){
        super.onResume();
        Log.i("TEST", "Main Activity On Resume");
    }

    public void onStop(){
        super.onStop();
        Log.i("TEST", "Main Activity On Stop");
    }


    public void onStart(){
        super.onStart();
        Log.i("TEST", "Main Activity On Start");
    }

    public void onDestroy(){
        super.onDestroy();
        Log.i("TEST", "Main Activity On Destroy");
    }


    public void setRollDescription(String str){

    }
}
