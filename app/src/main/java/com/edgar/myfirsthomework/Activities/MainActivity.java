package com.edgar.myfirsthomework.Activities;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.edgar.myfirsthomework.Fragments.FourFragment;
import com.edgar.myfirsthomework.Fragments.OneFragment;
import com.edgar.myfirsthomework.Fragments.TwoFragment;
import com.edgar.myfirsthomework.Fragments.ThreeFragment;
import com.edgar.myfirsthomework.Services.RingtonePlayService;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialtabs.R;


public class MainActivity extends AppCompatActivity {
//    private android.app.FragmentManager fragmentManager;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static final String LOG = "myLogs";


    private int[] tabIcons = {
            R.drawable.ic_time,
            R.drawable.ic_alarm2,
            R.drawable.ic_light,
            R.drawable.ic_contacts
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        if(getIntent()!=null){
            String letterFromService = getIntent().getStringExtra(RingtonePlayService.KEY);
            if(letterFromService != null) {
                switch (letterFromService) {
                    case ("extra"):
                        Log.d(LOG, "Прошла проверку");
                        viewPager.setCurrentItem(1);
                }
            }
        }

    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "TIME");
        adapter.addFragment(new TwoFragment(), "ALARM");
        adapter.addFragment(new ThreeFragment(), "FLASH");
        adapter.addFragment(new FourFragment(), "CONTACTS");
        viewPager.setAdapter(adapter);
    }


    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }






