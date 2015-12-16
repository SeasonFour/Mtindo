package com.example.hulk.mtindo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.client.Firebase;

public class Beauty extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty);

        //DB connection
        Firebase.setAndroidContext(this);
        Firebase rootref = new Firebase("https://mtindo.firebaseio.com/updates");





    }

}
