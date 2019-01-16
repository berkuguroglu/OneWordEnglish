package com.example.berk.owo.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.berk.owo.Fragments.ProfileFragment;
import com.example.berk.owo.Fragments.wordFragment;

public class PagerAdapt extends FragmentPagerAdapter
{


    private int numtabs;
    public PagerAdapt(FragmentManager fm, int numtabs)
    {
        super(fm);
        this.numtabs = numtabs;




    }

    @Override
    public Fragment getItem(int i)
    {
        Fragment frag;
        switch (i)
        {
            case 0:
                frag = new wordFragment(); break;
            case 1:
                frag = new ProfileFragment(); break;
            default: return null;
        }
        return frag;
    }

    @Override
    public int getCount()
    {
        return this.numtabs;
    }
}
