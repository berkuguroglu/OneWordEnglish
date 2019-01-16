package com.example.berk.owo.Fragments;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.berk.owo.R;


public class wordFragment extends Fragment {



    private Button but;
    private TextView[] TOTAL;
    private wordFragment.OnFragmentInteractionListener listener;
    public wordFragment()
    {
         TOTAL =  new TextView[4];

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_word, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        but = view.findViewById(R.id.button);
        TOTAL[0] = view.findViewById(R.id.textView);
        TOTAL[1] = view.findViewById(R.id.textView2);
        TOTAL[2] = view.findViewById(R.id.textView3);
        TOTAL[3] = view.findViewById(R.id.engLetter);
        but.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                 setAnimation(TOTAL);
                 wordFragment.this.listener.bringNextWord(TOTAL[0].getText().toString());


            }

        });
    }
    @Override
    public void onAttach(Context context)
    {

        super.onAttach(context);
        addInteractionListener(context);



    }
    private boolean setAnimation(TextView[] array)
    {
        for(final TextView iter : array)
            iter.animate().alpha(0).translationXBy(500).setDuration(500).setListener(new Animator.AnimatorListener()
            {
                @Override
                public void onAnimationStart(Animator animation) {

                     but.setEnabled(false);
                }

                @Override
                public void onAnimationEnd(Animator animation)
                {
                    if(iter == TOTAL[0]) {
                        String[] nextWord = wordFragment.this.listener.takeNextWord();
                        TOTAL[0].setText(nextWord[0]);
                        TOTAL[1].setText(nextWord[1]);
                        TOTAL[2].setText(nextWord[2] + "\n\n" + nextWord[3]);
                    }
                    iter.setTranslationX(-1000);
                    iter.animate().alpha(1)
                              .translationXBy(1000).setDuration(500).setListener(new Animator.AnimatorListener() {
                          @Override
                          public void onAnimationStart(Animator animation) {

                          }

                          @Override
                          public void onAnimationEnd(Animator animation) {

                              but.setEnabled(true);

                          }

                          @Override
                          public void onAnimationCancel(Animator animation) {

                          }

                          @Override
                          public void onAnimationRepeat(Animator animation) {

                          }
                      });

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }

            });
        return true;
    }
    public void addInteractionListener(Context context)
    {
        listener = (OnFragmentInteractionListener) context;
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        boolean bringNextWord(String word);
        String[] takeNextWord();

    }
}
