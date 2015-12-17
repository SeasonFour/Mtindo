package com.example.hulk.mtindo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class Post_update extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_update);
        Firebase.setAndroidContext(this);

//        Connect to Firebase
        final Firebase POSTUPDATES = new Firebase("https://mtindo.firebaseio.com/");
        final Firebase theupdates = POSTUPDATES.child("updates");

//        Setting the onclick listener for the post button
        Button Post = (Button) findViewById(R.id.Post_btn);
        Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//               First set custom variables to capture user data
                final EditText updateName = (EditText) findViewById(R.id.Name);
                final EditText updateDescription = (EditText) findViewById(R.id.Description);
                final EditText updatePrice = (EditText) findViewById(R.id.Price);
                final EditText updateTag = (EditText) findViewById(R.id.Tag);
                Button Post_btn = (Button) findViewById(R.id.Post_btn);


                //Capture user input
                String theName = updateName.getText().toString();
                String theDescription = updateDescription.getText().toString();
                String thePrice = updatePrice.getText().toString();
                String theTag = updateTag.getText().toString();

//                Make connection to class update
                Update update = new Update(theName, theDescription, thePrice, theTag);

//                Push new user input to firebase
                theupdates.push().setValue(update, new Firebase.CompletionListener() {
                    @Override
                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {

                        if (firebaseError != null) {
                            Toast.makeText(getApplicationContext(), "Contact not saved! Check Connection", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Contact saved Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }




}