package com.example.hulk.mtindo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.client.Firebase;

/**
 * Created by hulk on 12/14/15.
 */
public class Update_manager extends Activity{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty);
        Firebase.setAndroidContext(this);
        Firebase rootref = new Firebase("https://mtindo.firebaseio.com/updates");
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


        //Helps with performance of the recler view page
        mRecyclerView.setHasFixedSize(true);

        //This is a  linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Specify now the Adapter
//        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);


    }
}
