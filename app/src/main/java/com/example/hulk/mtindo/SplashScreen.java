package com.example.hulk.mtindo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

/**
 * Created by moses on 12/16/15.
 */
public class SplashScreen extends Activity {

    public static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                findViewById(R.id.avloadingIndicatorView).setVisibility(View.VISIBLE);
                /*after the splash screen it moves on to the MainActivity*/
                Intent data = new Intent(SplashScreen.this, MyAppIntro.class);
                startActivity(data);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
