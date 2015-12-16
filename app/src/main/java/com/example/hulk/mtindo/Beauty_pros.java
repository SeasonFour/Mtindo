package com.example.hulk.mtindo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.firebase.client.Firebase;

public class Beauty_pros extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty_pros);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Firebase.setAndroidContext(this);
        Firebase rootref = new Firebase("https://mtindo.firebaseio.com/stores");
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view1);


        //Helps with performance of the recler view page
        mRecyclerView.setHasFixedSize(true);

        //This is a  linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Specify now the Adapter
//        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

//        Link Adapter
        Update_adapter update_adapter = new Update_adapter(Update.class,R.layout.update_card,ViewHolder.class,rootref);
        mRecyclerView.setAdapter(update_adapter);


    }

}
