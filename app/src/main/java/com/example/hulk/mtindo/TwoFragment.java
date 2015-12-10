package com.example.hulk.mtindo;

/**
 * Created by maureen on 12/10/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class TwoFragment extends Fragment /*implements View.OnClickListener*/{

    public TwoFragment() {
        // Required empty public constructor
    }
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Intent onclick button form join community to create store
      Button  createButton = (Button) inflater.inflate(R.layout.fragment_two, container, false).findViewById(R.id.button1);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),Createstore.class);
                ((Maint) getActivity()).startActivity(i);

            }
        });

        return inflater.inflate(R.layout.fragment_two, container, false);

    }



}

