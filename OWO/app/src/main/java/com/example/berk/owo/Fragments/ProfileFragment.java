package com.example.berk.owo.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.berk.owo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements com.example.berk.owo.Firebase.callBackState, View.OnHoverListener{


    Switch[] switches = new Switch[2];
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {

        switches[0] = view.findViewById(R.id.switch1);
        switches[1] = view.findViewById(R.id.switch2);
        switches[1].setChecked(true);
        for(int i = 0; i<2; i++) {
            int finalI = i;
            switches[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    final int j = finalI;
                    int type = 0;
                    if(isChecked == true && finalI == 1) {
                        switches[finalI - 1].setChecked(false);
                        type = 0;
                    }
                    else if(!isChecked && finalI == 1) {
                        switches[finalI - 1].setChecked(true);
                        type = 1;
                    }
                    else if(isChecked == true && finalI == 0) {
                        switches[finalI + 1].setChecked(false);
                        type = 1;
                    }
                    else if(!isChecked && finalI == 0) {
                        switches[finalI + 1].setChecked(true);
                        type = 0;
                    }
                    OnFragmentInteractionListener listener = (OnFragmentInteractionListener) ProfileFragment.this.getActivity();
                    listener.onFragmentInteraction(type);


                }
            });
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void changeValue(String messsage, boolean state) {

            Toast.makeText(ProfileFragment.this.getContext(), messsage, Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onHover(View v, MotionEvent event)
    {
         return false;
    }

    public interface OnFragmentInteractionListener
    {
        void onFragmentInteraction(int type);
    }
}
