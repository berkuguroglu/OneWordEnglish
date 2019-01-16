package com.example.berk.owo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class wordsLearned
{

   private LinearLayout vertical, horizontal;
   private static int layout_count = 0;
   private Context app_context;
   static ArrayList<TextView> words_list = new ArrayList<>();
   LinearLayout.LayoutParams params;
   public wordsLearned(LinearLayout vertical, Context app_context)
   {
       this.vertical = vertical;
       this.app_context = app_context;
       horizontal = new LinearLayout(app_context);
       horizontal.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
       horizontal.setOrientation(LinearLayout.HORIZONTAL);
       horizontal.setPadding(10,20,10,20);
       horizontal.setGravity(Gravity.CENTER_HORIZONTAL);
       vertical.addView(horizontal);
       params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
   }
   public boolean addWord(String nextWord)
   {

       params.setMargins(20, 0, 70, 30);
       TextView text_word = new TextView(this.app_context);
       text_word.setLayoutParams(params);
       GradientDrawable gradientDrawable = new GradientDrawable();
       gradientDrawable.setCornerRadius(20);
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           gradientDrawable.setColor(Color.argb(0.6f, new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat()));
       }
       else gradientDrawable.setColor(Color.LTGRAY);
       text_word.setBackground(gradientDrawable);
       text_word.setText(nextWord);
       text_word.setPadding(60, 40, 60, 40);
       text_word.setTextColor(Color.WHITE);
       text_word.setGravity(Gravity.CENTER_HORIZONTAL);
       text_word.setAlpha(0);
       text_word.animate().alpha(1).setDuration(1500);
       horizontal.addView(text_word);
       layout_count++;
       if(layout_count == 2) layout_count = 0;
       return true;
   }
   public static int getLayoutCount()
   {
       return wordsLearned.layout_count;
   }

}
