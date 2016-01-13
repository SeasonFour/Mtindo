package com.example.hulk.mtindo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.firebase.client.Firebase;

public class Beauty_pros extends AppCompatActivity {
    private RecyclerView mRecyclerView1;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty_pros);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Firebase.setAndroidContext(this);
        Firebase rootref2 = new Firebase("https://mtindo.firebaseio.com/stores");
        mRecyclerView1 = (RecyclerView) findViewById(R.id.my_recycler_view1);


        //Helps with performance of the recler view page
        mRecyclerView1.setHasFixedSize(true);

        //This is a  linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView1.setLayoutManager(mLayoutManager);

        //Specify now the Adapter
        mRecyclerView1.setAdapter(mAdapter);

//        Link the Adapter
        Store_Adapter store_adapter = new Store_Adapter(Store.class, R.layout.store_card,ViewHolder.class,rootref2);
        mRecyclerView1.setAdapter(store_adapter);
    }


}
