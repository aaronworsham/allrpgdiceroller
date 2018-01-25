package com.sazboom.turboroller.views;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.constraint.ConstraintLayout;
import android.app.Fragment;
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
import android.widget.Toast;

import com.sazboom.turboroller.R;
import com.sazboom.turboroller.contracts.RollerContract;
import com.sazboom.turboroller.presenters.DndRollPresenter;
import com.sazboom.turboroller.presenters.SrRollResenter;
import com.sazboom.turboroller.views.fragments.Dnd5eFragment;
import com.sazboom.turboroller.views.fragments.Sr5eFragment;

import org.w3c.dom.Text;


/**
 * Created by aaronworsham on 12/28/17.
 */

public class MainActivity extends AppCompatActivity implements RollerContract.View{
    RollerContract.Presenter mRollPresenter = new DndRollPresenter(this);
    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("TEST", "Main Activity On Create");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

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

        //Setup Fragment
        setupSystemFragment("DND5E");



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
        TextView poolDescription =  (TextView) findViewById(R.id.dicePool);
        poolDescription.setText(str);
    }

    public void setPoolRoll(String str){
        TextView poolRoll =  (TextView) findViewById(R.id.dicePoolRoll);
        poolRoll.setText(str);
    }

    public void setPoolResults(String str){
        TextView poolResults =  (TextView) findViewById(R.id.dicePoolResults);
        poolResults.setText(str);
    }

    public void setToast(String str){
        Toast.makeText(getApplicationContext(), str,
                Toast.LENGTH_SHORT).show();
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
                        switch (menuItem.getItemId()) {
                            case R.id.system_dnd5e:
                                Log.i("ROLLER", "DND 5e Selected" );
                                setupSystemFragment("DND5ENEW");
                                mDrawerLayout.closeDrawer(GravityCompat.START);
                                break;
                            case R.id.system_sr5e:
                                Log.i("ROLLER", "SR5 5e Selected" );
                                setupSystemFragment("SR5E");
                                mDrawerLayout.closeDrawer(GravityCompat.START);
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });
    }


    private void setupSystemFragment(String str){
        FragmentManager fragmentManager = getFragmentManager ();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction ();

        switch(str){
            case "DND5E":
                getSupportActionBar().setTitle("D&D 5e");
                Dnd5eFragment dndfragment = new Dnd5eFragment();
                dndfragment.setmRollPresenter((DndRollPresenter) mRollPresenter);
                fragmentTransaction.add(R.id.contentFrame, dndfragment);
                fragmentTransaction.commit ();
                break;
            case "DND5ENEW":
                mRollPresenter = new DndRollPresenter(this);
                getSupportActionBar().setTitle("D&D 5e");
                Dnd5eFragment dndnewfragment = new Dnd5eFragment();
                dndnewfragment.setmRollPresenter((DndRollPresenter) mRollPresenter);
                fragmentTransaction.replace(R.id.contentFrame, dndnewfragment);
                fragmentTransaction.commit ();
                break;
            case "SR5E":
                mRollPresenter = new SrRollResenter(this);
                getSupportActionBar().setTitle("Shadowrun 5e");
                Sr5eFragment srFragment = new Sr5eFragment();
                srFragment.setmRollPresenter((SrRollResenter) mRollPresenter);
                fragmentTransaction.replace(R.id.contentFrame, srFragment);
                fragmentTransaction.commit ();
                break;
            default :
                Log.e("ROLLER", "Unknown system");

        }
        //Setup Fragment


    }


}
