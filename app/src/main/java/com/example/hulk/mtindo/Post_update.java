package com.example.hulk.mtindo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import fr.ganfra.materialspinner.MaterialSpinner;

public class Post_update extends AppCompatActivity {
    private static final String ERROR_MSG = "No";
    private static final String[] ITEMS = {"Hair", "Beauty", "Make up"};

    private ArrayAdapter<String> adapter;

    MaterialSpinner spinner1;
    private boolean shown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_update);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        For the Spinner

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        initSpinnerHintAndFloatingLabel();

    }

    private void initSpinnerHintAndFloatingLabel() {
        spinner1 = (MaterialSpinner) findViewById(R.id.spinner1);
        spinner1.setAdapter(adapter);
        spinner1.setPaddingSafe(0, 0, 0, 0);
    }

}