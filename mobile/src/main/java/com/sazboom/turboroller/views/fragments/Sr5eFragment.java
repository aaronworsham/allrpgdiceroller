package com.sazboom.turboroller.views.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sazboom.turboroller.R;
import com.sazboom.turboroller.presenters.DndRollPresenter;
import com.sazboom.turboroller.presenters.SrRollResenter;

/**
 * Created by aaronworsham on 1/10/18.
 */

public class Sr5eFragment extends Fragment {
    View mView;
    SrRollResenter mRollPresenter;


    public void setmRollPresenter(SrRollResenter rp){
        mRollPresenter = rp;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView =  inflater.inflate(R.layout.sr5e_fragment, container, false);

        setupClickListeners();
        return mView;
    }

    private void setupClickListeners(){


        final Button b1 = (Button) mView.findViewById(R.id.buttonAdd1);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.addDiceToPool(1);
            }
        });

        final Button b2 = (Button) mView.findViewById(R.id.buttonMinus1);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.removeDiceFromPool(1);
            }
        });

        final Button b3 = (Button) mView.findViewById(R.id.buttonAdd2);
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.addDiceToPool(2);
            }
        });

        final Button b4= (Button) mView.findViewById(R.id.buttonAdd5);
        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.addDiceToPool(5);
            }
        });
        final Button b5= (Button) mView.findViewById(R.id.buttonAdd10);
        b5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.addDiceToPool(10);
            }
        });
        final Button b6= (Button) mView.findViewById(R.id.buttonAdd20);
        b6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.addDiceToPool(20);
            }
        });
        final Button b7= (Button) mView.findViewById(R.id.buttonRoll);
        b7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.setResults();
            }
        });
        final Button b8= (Button) mView.findViewById(R.id.buttonClear);
        b8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.clearPool();
            }
        });
    }
}
