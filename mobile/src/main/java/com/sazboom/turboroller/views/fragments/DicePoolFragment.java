package com.sazboom.turboroller.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sazboom.turboroller.R;
import com.sazboom.turboroller.contracts.RollerContract;
import com.sazboom.turboroller.presenters.RollPresenter;

/**
 * Created by aaronworsham on 1/3/18.
 */

public class DicePoolFragment extends Fragment implements RollerContract.DicePoolView {

    View mView;

    private RollPresenter mRollPresenter;

    public static DicePoolFragment newInstance() {

        Bundle args = new Bundle();

        DicePoolFragment fragment = new DicePoolFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dice_pool_fragment, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mView = view;
    }

    public void setPresenter(RollPresenter rp){
        mRollPresenter = rp;

    }

    public void setPoolDescription(String str){
        TextView poolDescription =  mView.findViewById(R.id.dicePool);
        poolDescription.setText(str);
    }

    public void setPoolRoll(String str){
        TextView poolRoll =  mView.findViewById(R.id.dicePoolRoll);
        poolRoll.setText(str);
    }


}
