package com.sazboom.turboroller.views.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sazboom.turboroller.R;

/**
 * Created by aaronworsham on 1/10/18.
 */

public class Dnd5eFragment extends AppCompatActivity{


    public static Dnd5eFragment newInstance(String text) {

        Dnd5eFragment f = new Dnd5eFragment();
        return f;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.dnd5e_fragment, container, false);

        return v;
    }
}
