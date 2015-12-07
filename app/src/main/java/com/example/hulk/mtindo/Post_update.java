package com.example.hulk.mtindo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

public class Post_update extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_update);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        For the textviews
        EditText edtName = (EditText) findViewById(R.id.edtName);
        EditText edtName1 = (EditText) findViewById(R.id.edtName1);
        EditText edtName2 = (EditText) findViewById(R.id.edtName2);

//
    }

   

}
   