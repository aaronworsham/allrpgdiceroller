package com.sazboom.turboroller.views;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sazboom.turboroller.R;
import com.sazboom.turboroller.contracts.RollerContract;
import com.sazboom.turboroller.presenters.DndRollPresenter;
import com.sazboom.turboroller.views.fragments.Dnd5eFragment;


/**
 * Created by aaronworsham on 12/28/17.
 */

public class Dnd5eMainActivity extends AppCompatActivity implements RollerContract.View{
    RollerContract.Presenter mRollPresenter = new DndRollPresenter(this);
    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("TEST", "Main Activity On Create");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up toolbar
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("D&D 5e");
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        // Set up the navigation drawer.
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }



        setupClickListeners();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    public void setPresenter(@NonNull RollerContract.Presenter presenter) {
        mRollPresenter = presenter;
    }

    public void setPoolDescription(String str){
        TextView poolDescription =  findViewById(R.id.dicePool);
        poolDescription.setText(str);
    }

    public void setPoolRoll(String str){
        TextView poolRoll =  findViewById(R.id.dicePoolRoll);
        poolRoll.setText(str);
    }

    public void setPoolResults(String str){
        TextView poolRoll =  findViewById(R.id.dicePoolResults);
        poolRoll.setText(str);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Open the navigation drawer when the home icon is selected from the toolbar.
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Close the navigation drawer when an item is selected.
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void setupClickListeners(){


        Resources res = getResources();

        for(int i = 0; i< DndRollPresenter.DICE.length; i++) {
            final int die = DndRollPresenter.DICE[i];
            int id = res.getIdentifier("buttonD" + die , "id", this.getPackageName());
            Button b = findViewById(id);
            b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mRollPresenter.addDiceToPool(die);

                }
            });
        }

//
        final Button buttonClear = findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.clearDiceAndPool();
            }
        });

        final Button buttonRoll = findViewById(R.id.buttonRoll);
        buttonRoll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.rollPool();
            }
        });

        final Button buttonPosBonus = findViewById(R.id.buttonPositiveBonus);
        buttonPosBonus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.addPosDicePoolBonus(1);
            }
        });
        final Button buttonNegBonus = findViewById(R.id.buttonNegativeBonus);
        buttonNegBonus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRollPresenter.addNegDicePoolBonus(1);
            }
        });
    }

    private void setupSystemFragment(String str){


        switch(str){
            case "DND5e":
                Dnd5eFragment systemFragment = new Dnd5eFragment();
                if (systemFragment == null) {
                    FragmentManager fragmentManager = getFragmentManager ();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction ();
                    fragmentTransaction.add(R.id.contentFrame, systemFragment);
                    fragmentTransaction.commit ();
                }
            }
        }
        //Setup Fragment


    }


}
