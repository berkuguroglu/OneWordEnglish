package com.example.berk.owo;

import android.animation.Animator;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.berk.owo.Adapters.PagerAdapt;
import com.example.berk.owo.Firebase.FirebaseDatabaseClass;
import com.example.berk.owo.Fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity implements com.example.berk.owo.Fragments.wordFragment.OnFragmentInteractionListener, Runnable, ProfileFragment.OnFragmentInteractionListener, View.OnLongClickListener{


    private ViewPager pager;
    private PagerAdapt adapter;
    private TabLayout layout_tab;
    private Handler trendHandler;
    private Runnable delayedOpt;
    private wordsLearned newWord = null;
    private int wordTemp = 0;
    private int trendTemp = 0;
    private com.example.berk.owo.Firebase.FirebaseDatabaseClass fb;

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout_tab = (TabLayout)findViewById(R.id.tabLayout);
        pager = (ViewPager)findViewById(R.id.pager);
        adapter = new PagerAdapt(getSupportFragmentManager(), layout_tab.getTabCount());
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(layout_tab));
        layout_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        delayedOpt = this;
        trendHandler = new Handler();
        this.trendHandler.postDelayed(this.delayedOpt, 10000);
        fb = new FirebaseDatabaseClass();
        fb.loadWordsFromDatabase();


    }

    @Override
    public boolean bringNextWord(String nextWord)
    {


        LinearLayout line = findViewById(R.id.hsw);
        if(wordsLearned.getLayoutCount() == 0)
        {
            newWord = new wordsLearned(line, this.getApplicationContext());
            newWord.addWord(nextWord);
        }
        else
            return newWord.addWord(nextWord);
        return false;
    }

    @Override
    public String[] takeNextWord()
    {

        if(wordTemp == fb.words_list.size()-1)
            wordTemp = 0;
        String[] word = {fb.words_list.get(wordTemp), fb.words_meaning.get(wordTemp).get(0), fb.words_meaning.get(wordTemp).get(0), fb.words_meaning.get(wordTemp).get(0)};
        wordTemp++;
        TextView twt = (TextView)this.findViewById(R.id.learW);
        String point = twt.getText().toString();
        twt.setText(String.valueOf(Integer.parseInt(point)+1));
        return word;

        
    }


    @Override
    public boolean onLongClick(View v)
    {
        Button hovering = (Button)v;

        return false;
    }
    public long swapTrendButtons()
    {
        Button b1, b2;
        b1 = findViewById(R.id.button5);
        b2 = findViewById(R.id.button4);

        b1.animate().alpha(0).setDuration(200).setListener(new Animator.AnimatorListener()
        {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                
                 b1.animate().alpha(1).setDuration(200);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        b2.animate().alpha(0).setDuration(200).setListener(new Animator.AnimatorListener()
        {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation)
            {
                b2.animate().alpha(1).setDuration(00);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation)
            {
            }
        });
        Drawable back_temp = b1.getBackground();
        int temp = b1.getCurrentTextColor();
        b1.setTextColor(b2.getCurrentTextColor());
        b2.setTextColor(temp);
        b1.setBackground(b2.getBackground());
        b2.setBackground(back_temp);
        b1.setText(fb.trend_words.get(trendTemp)[0]);
        b2.setText(fb.trend_words.get(trendTemp)[1]);
        if(fb.trend_words.size() == trendTemp+1)
            trendTemp = 0;
        else
            this.trendTemp++;
        return 0;
    }
    @Override
    public void run()
    {
        swapTrendButtons();
        if(fb.wordsLoaded())
            this.fb.wordsLoaded(findViewById(R.id.button), findViewById(R.id.progressBar));
        this.trendHandler.postDelayed(this.delayedOpt, 10000);

    }

    @Override
    public void onFragmentInteraction(int type)
    {
        if(type == 1) {
            this.findViewById(R.id.hsl).setVisibility(View.VISIBLE);
            this.findViewById(R.id.trend).setVisibility(View.GONE);
        }
        else
        {
            this.findViewById(R.id.hsl).setVisibility(View.GONE);
            this.findViewById(R.id.trend).setVisibility(View.VISIBLE);
        }
    }
}
