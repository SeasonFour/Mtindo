package com.example.hulk.mtindo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;

/**
 * Created by hulk on 12/11/15.
 */
public class BaseActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks  {

    private static String TAG = "BaseActivity";

    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInResult signInResult;
    private ImageView imgProfilePic;
    private TextView txtName;
    private static final int PROFILE_PIC_SIZE = 400;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.login_proceed).setVisibility(View.INVISIBLE);

//       Start google sign in

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addConnectionCallbacks(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


    }
    @Override
    public void onConnected(Bundle bundle) {
        // Proceed to next step if already signed in.
        // If not previously signed in, then delayed success=false
        // If previously signed in, then immediate success=true
        OptionalPendingResult<GoogleSignInResult> pendingResult =
                Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (pendingResult.isDone()) {
            GoogleSignInResult result = pendingResult.get();
            Log.i(TAG, "silentSignIn immediate : isSuccess=" + result.isSuccess());
            signInResult = result;
            if (result.isSuccess()) {
                onLoginSucceeded();

            } else {
                startLoginActivity();
            }
        } else {
            // There's no immediate result ready, displays some progress indicator and waits for the
            // async callback.
            pendingResult.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult result) {
                    Log.i(TAG, "silentSignIn delayed : isSuccess=" + result.isSuccess());
                    signInResult = result;
                    if (result.isSuccess()) {
                        onLoginSucceeded();
                    } else {
                        startLoginActivity();
                    }
                }
            });
        }
    }
    private void startLoginActivity() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    /** This method is called after successful sign-in. */
    protected void onLoginSucceeded() {
    }
    /** Get email from sign-in result. Returns {@code null} if sign-in is not done yet. */
    @Nullable
    protected String getEmail() {
        if (signInResult == null) {
            return null;
        }
        return signInResult.getSignInAccount().getEmail();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
//    private  void getProfileInformation() {
//        try {
//            if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
//                Person currentPerson = Plus.PeopleApi
//                        .getCurrentPerson(mGoogleApiClient);
//                String personName = currentPerson.getDisplayName();
//                String personPhotoUrl = currentPerson.getImage().getUrl();
//                String personGooglePlusProfile = currentPerson.getUrl();
//                String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
//
//                Log.e(TAG, "Name: " + personName + ", plusProfile: "
//                        + personGooglePlusProfile + ", email: " + email
//                        + ", Image: " + personPhotoUrl);
//
//                txtName.setText(personName);
//
//                personPhotoUrl = personPhotoUrl.substring(0,
//                        personPhotoUrl.length() - 2)
//                        + PROFILE_PIC_SIZE;
//
//                new LoadProfileImage(imgProfilePic).execute(personPhotoUrl);
//
//
//        }
//            else {
//            Toast.makeText(getApplicationContext(),
//                    "Person information is null", Toast.LENGTH_LONG).show();
//        }
//
//          } catch (Exception e) {
//            e.printStackTrace();
//          }
//        }


    }



