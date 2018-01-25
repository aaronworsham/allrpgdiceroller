package com.sazboom.turboroller.views.fragments;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.sazboom.turboroller.R;
import com.sazboom.turboroller.presenters.DndRollPresenter;

/**
 * Created by aaronworsham on 1/10/18.
 */

public class Dnd5eFragment extends Fragment{

    View mView;
    DndRollPresenter mRollPresenter;


    public void setmRollPresenter(DndRollPresenter rp){
        mRollPresenter = rp;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView =  inflater.inflate(R.layout.dnd5e_fragment, container, false);

        setupClickListeners();
        return mView;
    }

    private void setupClickListeners(){


        Resources res = getResources();

        for(int i = 0; i< DndRollPresenter.DICE.length; i++) {
            final int die = DndRollPresenter.DICE[i];
            int id = res.getIdentifier("buttonD" + die , "id", getActivity().getPackageName());
            Button b = (Button) mView.findViewById(id);
            b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mRollPresenter.addDiceToPool(die);

                }
            });
        }

        final Button buttonClear = (Button) mView.findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.clearPool();
            }
        });

        final Button buttonRoll = (Button) mView.findViewById(R.id.buttonRoll);
        buttonRoll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.setResults();
            }
        });

        final Button buttonPosBonus = (Button) mView.findViewById(R.id.buttonPositiveBonus);
        buttonPosBonus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.incrementBonus();
            }
        });
        final Button buttonNegBonus = (Button) mView.findViewById(R.id.buttonNegativeBonus);
        buttonNegBonus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.decrementBonus();
            }
        });

        final Button buttonAdv = (Button) mView.findViewById(R.id.buttonAdv);
        buttonAdv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.rollAdvantage();
            }
        });
        final Button buttonDis = (Button) mView.findViewById(R.id.buttonDis);
        buttonDis.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.rollDisadvantage();
            }
        });
    }


}
