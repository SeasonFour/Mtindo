package com.example.hulk.mtindo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.Firebase;

public class Beauty_pros extends Fragment {
        View view;


    private RecyclerView mRecyclerView1;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

        public Beauty_pros() {
        // Required empty public constructor
    }

        @Override
        public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




            View view = inflater.inflate(R.layout.activity_beauty_pros, container, false);

        Toolbar toolbar = (Toolbar) view. findViewById(R.id.toolbar);
       /* setSupportActionBar(toolbar);*/




        Firebase.setAndroidContext(getActivity());
        Firebase rootref2 = new Firebase("https://mtindo.firebaseio.com/stores");
        mRecyclerView1 = (RecyclerView) view.findViewById(R.id.my_recycler_view1);


        //Helps with performance of the recler view page
        mRecyclerView1.setHasFixedSize(true);

        //This is a  linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView1.setLayoutManager(mLayoutManager);

        //Specify now the Adapter
        mRecyclerView1.setAdapter(mAdapter);

//        Link the Adapter
        Store_Adapter store_adapter = new Store_Adapter(Store.class, R.layout.store_card,ViewHolder.class,rootref2);
        mRecyclerView1.setAdapter(store_adapter);


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_beauty_pros, container, false);
    }



}



