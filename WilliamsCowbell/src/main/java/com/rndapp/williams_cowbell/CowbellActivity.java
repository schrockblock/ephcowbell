package com.rndapp.williams_cowbell;

import android.content.Context;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class CowbellActivity extends ActionBarActivity
        implements ViewPager.OnPageChangeListener, ShakingCallback {
    private PlayerManager playerManager;
    private ShakingModel shaker;
    private boolean playLocked;

    protected CowbellTypeFragment firstFragment;
    protected CowbellTypeFragment secondFragment;
    protected CowbellTypeFragment thirdFragment;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    CowbellTypesPagerAdapter mCowbellTypesPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cowbell);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mCowbellTypesPagerAdapter = new CowbellTypesPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setAdapter(mCowbellTypesPagerAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        playLocked = false;
        playerManager = new PlayerManager(this);

        SensorManager manager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        shaker = new ShakingModel(this, manager);
    }

    @Override
    protected void onPause() {
        super.onPause();

        playerManager.pause();
        shaker.finish();
    }

    @Override
    public void didStartShaking() {
        if (!playLocked){
            playerManager.play(mViewPager.getCurrentItem());
        }
    }

    @Override
    public void didStopShaking() {
        playerManager.pause();
    }

    @Override public void onPageSelected(int i) {}
    @Override public void onPageScrolled(int i, float v, int i2) {}
    @Override public void onPageScrollStateChanged(int i) {
        switch (i){
            case ViewPager.SCROLL_STATE_SETTLING:
            case ViewPager.SCROLL_STATE_DRAGGING:
                playLocked = true;
                playerManager.pause();
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                playLocked = false;
                break;
        }
    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class CowbellTypesPagerAdapter extends FragmentPagerAdapter {

        public CowbellTypesPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position) {
                case 0:
                    if (firstFragment == null){
                        firstFragment = CowbellTypeFragment.newInstance(getString(R.string.goofy),
                                R.drawable.goofy_cow);
                    }
                    fragment = firstFragment;
                    break;
                case 1:
                    if (secondFragment == null){
                        secondFragment = CowbellTypeFragment.newInstance(getString(R.string.normal),
                                R.drawable.cow_face2);
                    }
                    fragment = secondFragment;
                    break;
                case 2:
                    if (thirdFragment == null){
                        thirdFragment = CowbellTypeFragment.newInstance(getString(R.string.tinny),
                                R.drawable.purple_cow2);
                    }
                    fragment = thirdFragment;
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }

}
