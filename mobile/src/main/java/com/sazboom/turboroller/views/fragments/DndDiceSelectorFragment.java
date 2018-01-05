package com.sazboom.turboroller.views.fragments;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sazboom.turboroller.R;
import com.sazboom.turboroller.contracts.RollerContract;
import com.sazboom.turboroller.presenters.RollPresenter;

/**
 * Created by aaronworsham on 1/3/18.
 */

public class DndDiceSelectorFragment extends Fragment implements RollerContract.View{

    RollPresenter mRollPresenter;


    public static DndDiceSelectorFragment newInstance() {

        Bundle args = new Bundle();

        DndDiceSelectorFragment fragment = new DndDiceSelectorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setPresenter(RollPresenter rp){
        Log.i("TEST", "ADDING Roll Presenter to Dice Selector Fragment");
        mRollPresenter = rp;

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dnd_dice_selector_fragment, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.i("TEST", "Fragment Activity on View Created");
        final Button buttonD4 = view.findViewById(R.id.buttonD4);
        buttonD4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.addDiceToPool(4);
                
            }
        });

        final Button buttonD6 = view.findViewById(R.id.buttonD6);
        buttonD6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.addDiceToPool(6);

            }
        });

        final Button buttonD8 = view.findViewById(R.id.buttonD8);
        buttonD8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.addDiceToPool(8);

            }
        });

        final Button buttonD10 = view.findViewById(R.id.buttonD10);
        buttonD10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.addDiceToPool(10);

            }
        });

        final Button buttonD12 = view.findViewById(R.id.buttonD12);
        buttonD12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.addDiceToPool(12);

            }
        });

        final Button buttonD20 = view.findViewById(R.id.buttonD20);
        buttonD20.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.addDiceToPool(20);

            }
        });
        final Button buttonD100 = view.findViewById(R.id.buttonD100);
        buttonD100.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.addDiceToPool(100);

            }
        });
        final Button buttonClear = view.findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.clearDiceAndPool();
            }
        });

        final Button buttonRoll = view.findViewById(R.id.buttonRoll);
        buttonRoll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.rollPool();
            }
        });
    }


    public void onAttach(){
        Log.i("TEST", "Fragment Activity On Attach");

    }

    public void onResume(){
        super.onResume();
        Log.i("TEST", "Fragment Activity On Resume");
    }

    public void onStop(){
        super.onStop();
        Log.i("TEST", "Fragment Activity On Stop");
    }


    public void onStart(){
        super.onStart();
        Log.i("TEST", "Fragment Activity On Start");
    }

    public void onDestroy(){
        super.onDestroy();
        Log.i("TEST", "Fragment Activity On Destroy");
    }

}
