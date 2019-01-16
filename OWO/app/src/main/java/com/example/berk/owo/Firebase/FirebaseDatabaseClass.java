package com.example.berk.owo.Firebase;

import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class FirebaseDatabaseClass
{

    public ArrayList<String> words_list = new ArrayList<>();
    public ArrayList<ArrayList<String>> words_meaning = new ArrayList<>();

    public ArrayList<String[]> trend_words = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    public FirebaseDatabaseClass()
    {

    }
    public void bringUserInfo(String uid)
    {

    }
    public boolean wordsLoaded()
    {
        Log.d("size", String.valueOf(words_list.size()));
        if(words_list.size() > 0) return true;
        else return false;
    }
    public void loadWordsFromDatabase()
    {
        myRef.child("Words").addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(words_list.size() != 0) words_list.clear();
                for(DataSnapshot snap  : dataSnapshot.getChildren()) {
                    words_list.add(snap.getKey());
                    ArrayList<String> aList = new ArrayList();
                    for (DataSnapshot snapshot : snap.getChildren()) {
                        aList.add(snapshot.getValue().toString());
                        words_meaning.add(aList);

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myRef.child("Words").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(words_list.size() != 0) words_list.clear();
                for(DataSnapshot snap  : dataSnapshot.getChildren()) {
                    words_list.add(snap.getKey());
                    ArrayList<String> aList = new ArrayList();
                    for (DataSnapshot snapshot : snap.getChildren()) {
                        aList.add(snapshot.getValue().toString());
                        words_meaning.add(aList);

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myRef.child("Trend").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                Iterable<DataSnapshot> iter = dataSnapshot.getChildren();
                for(DataSnapshot d : iter)
                {
                    String[] array = new String[2];
                    array[0] = d.getKey();
                    array[1] = d.getValue().toString();
                    trend_words.add(array);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public <T extends View> void wordsLoaded(T viewById, T secondView)
    {
         Button button_params = (Button)viewById;
         button_params.setEnabled(true);
         ProgressBar bar = (ProgressBar)secondView;
         bar.setProgress(100);
         bar.setVisibility(View.GONE);
         button_params.setVisibility(View.VISIBLE);
    }
}
