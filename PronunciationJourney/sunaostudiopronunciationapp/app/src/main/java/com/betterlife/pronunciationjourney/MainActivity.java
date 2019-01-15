package com.betterlife.pronunciationjourney;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.betterlife.pronunciationjourney.fragment.FragmentDrawer;
import com.betterlife.pronunciationjourney.fragment.FragmentHome;
import com.betterlife.pronunciationjourney.fragment.FragmentPractice;
import com.betterlife.pronunciationjourney.utils.OnClickDialogInterface;
import com.betterlife.pronunciationjourney.utils.ScreenManager;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {
    private FragmentManager mFragmentManager;
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
        openFragmentHome();

    }

    private void openFragmentHome() {
        ScreenManager.getInst().openFragmentWithAnimation(getSupportFragmentManager(),R.id.fragment_container,new FragmentHome(),false,FragmentHome.TAG);
    }

    private void setUpView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {

    }

    @Override
    public void onBackPressed() {
        final FragmentPractice fragmentPractice = (FragmentPractice) getSupportFragmentManager().findFragmentByTag(FragmentPractice.TAG);
        FragmentHome fragmentHome = (FragmentHome) getSupportFragmentManager().findFragmentByTag(FragmentHome.TAG);
        if(fragmentPractice == null || !fragmentPractice.isVisible()){
            if(fragmentHome == null || !fragmentHome.isVisible()){
                super.onBackPressed();
            }else {
                ScreenManager.getInst().showDialog(MainActivity.this, "", getString(R.string.want_exit_app),
                        new OnClickDialogInterface() {
                            @Override
                            public void onClickOK() {
                                MainActivity.super.onBackPressed();
                            }

                            @Override
                            public void onClickCancel() {

                            }
                        });
            }
        }else {
            ScreenManager.getInst().showDialog(MainActivity.this, "", getString(R.string.want_exit_practice),
                    new OnClickDialogInterface() {
                        @Override
                        public void onClickOK() {
                            fragmentPractice.saveScore();
                            MainActivity.super.onBackPressed();
                        }

                        @Override
                        public void onClickCancel() {

                        }
                    });
        }
    }
}
