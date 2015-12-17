package com.example.hulk.mtindo;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import fr.ganfra.materialspinner.MaterialSpinner;

import static com.example.hulk.mtindo.R.id.ImageView01;


public class Createstore extends AppCompatActivity {
    //select gallery images variables
    private static int RESULT_LOAD_IMAGE = 1;
    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;
    private ImageView image;
    public Bitmap bitmapImage;
    final Createstore context = this;
    private static final String TAG = "Createstore";


    private EditText inputName, /*inputEmail*/
            inputStorename, inputDescription, inputTelephone;
    private TextInputLayout inputLayoutName, /*inputLayoutEmail,*/
            inputLayoutStore, inputLayoutDescription, inputLayoutTelephone;


//variables phone


    //spinner variables
    private static final String ERROR_MSG = "Very very very long error message to get scrolling or multiline animation when the error button is clicked";
    private static final String[] ITEMS = {"Mtindo", "Beauty", "Makeup"};


    private ArrayAdapter<String> adapter;

    MaterialSpinner spinner1;
    private boolean shown = false;


    private Button btnSignUp;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createstore);
        Firebase.setAndroidContext(this);
        final Firebase STOREPROFILES = new Firebase("https://mtindo.firebaseio.com/");
        final Firebase thestores = STOREPROFILES.child("stores");


        //Material spinner adapter
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        initSpinnerHintAndFloatingLabel();

        //Edit text input layouts
        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutStore = (TextInputLayout) findViewById(R.id.input_layout_storename);
        inputLayoutTelephone = (TextInputLayout) findViewById(R.id.input_layout_telephone);
        inputLayoutDescription = (TextInputLayout) findViewById(R.id.input_layout_description);
        /*inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);*/

        //Edit text id
        /*inputEmail = (EditText) findViewById(R.id.input_email);*/
        inputName = (EditText) findViewById(R.id.input_name);
        inputTelephone = (EditText) findViewById(R.id.input_telephone);
        inputStorename = (EditText) findViewById(R.id.input_storename);
        inputDescription = (EditText) findViewById(R.id.input_description);
        image = (ImageView) findViewById(R.id.imageView3);


        //Create store button
        btnSignUp = (Button) findViewById(R.id.btn_createstore);

        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        /*inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));*/
        inputStorename.addTextChangedListener(new MyTextWatcher(inputStorename));
        inputDescription.addTextChangedListener(new MyTextWatcher(inputDescription));
        inputTelephone.addTextChangedListener(new MyTextWatcher(inputStorename));

        // create store button on clicklistener
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
                encodeToBase64(bitmapImage);


//                Capturing user data from textfields
                String theinputname = inputName.getText().toString();
                String theinputStorename = inputStorename.getText().toString();
                String theinputDescription = inputDescription.getText().toString();
                String theinputTelephone = inputTelephone.getText().toString();

//                Connect to constructor class
                Store store = new Store(theinputname, theinputStorename, theinputDescription, theinputTelephone);
                thestores.push().setValue(store, new Firebase.CompletionListener() {
                    @Override
                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                        if (firebaseError != null) {
                            Toast.makeText(context, "Store Created Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Store not created succefully", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });


//load image onclick intent
     /*   Button buttonLoadImage = (Button) findViewById(R.id.buttonLoadPicture);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });*/

        // Select image from Gallery
        image = (ImageView) findViewById(ImageView01);
        //Intent onclick add profile image button
        ((ImageView) findViewById(R.id.ImageView02))
                .setOnClickListener(new View.OnClickListener() {
                    public void onClick(View arg0) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
                    }
                });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    //Spinner method
    private void initSpinnerHintAndFloatingLabel() {
        spinner1 = (MaterialSpinner) findViewById(R.id.spinner1);
        spinner1.setAdapter(adapter);
        spinner1.setPaddingSafe(0, 0, 0, 0);
    }

    //spinner validation
    public void activateError(View view) {
        if (!shown) {
            spinner1.setError(ERROR_MSG);

        } else {
            spinner1.setError(null);

        }
        shown = !shown;
    }
//select image

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageView imageView = (ImageView) findViewById(R.id.imgView);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }*/
//    Handles image once uploaded
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                Log.i(TAG, "Image Uri : " + selectedImageUri);
                selectedImagePath = getPath(selectedImageUri);
                Log.i(TAG, "Image Path : " + selectedImagePath);
                image.setImageURI(selectedImageUri);

                try {
                    image.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);


    }
    /**
     * Validating form
     */

    private void submitForm() {
        if (!validateName()) {
            return;
        }

        /*if (!validateEmail()) {
            return;
        }*/

        if (!validateStorename()) {
            return;
        }

        if (!validateDescription()) {
            return;
        }
        if (!validateTelephone()) {
            return;
        }


        Toast.makeText(getApplicationContext(), "Store created!", Toast.LENGTH_SHORT).show();
    }

    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty() || inputName.length() < 3) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateStorename() {
        if (inputTelephone.getText().toString().trim().isEmpty() || inputStorename.length() < 3) {
            inputLayoutStore.setError(getString(R.string.err_msg_storename));
            requestFocus(inputStorename);
            return false;

        } else {
            inputLayoutStore.setErrorEnabled(false);
        }

        return true;
    }

/*    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();
        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }
        return true;
    }*/

    private boolean validateDescription() {


        if (inputDescription.getText().toString().trim().isEmpty() || inputDescription.length() < 3) {
            inputLayoutDescription.setError(getString(R.string.err_msg_description));
            requestFocus(inputDescription);
            return false;
        } else {
            inputLayoutDescription.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateTelephone() {
        if (inputTelephone.getText().toString().trim().isEmpty() || inputTelephone.length() > 10) {
            inputLayoutTelephone.setError(getString(R.string.err_msg_telephone));
            requestFocus(inputTelephone);
            return false;
        } else {
            inputLayoutTelephone.setErrorEnabled(false);
        }

        return true;
    }


   /* private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }*/

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Createstore Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.hulk.mtindo/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Createstore Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.hulk.mtindo/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
              /*  case R.id.input_email:
                    validateEmail();
                    break;*/

                case R.id.input_storename:
                    validateStorename();
                    break;
                case R.id.input_description:
                    validateStorename();
                    break;

                case R.id.input_telephone:
                    validateTelephone();
                    break;


            }


        }
    }

    //    Method to convert image to base64
    public static String encodeToBase64(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = com.firebase.client.utilities.Base64.encodeBytes(b);

        return imageEncoded;
    }
    public static Bitmap decodeFromBase64(String input) throws IOException {
        byte[] decodedByte = com.firebase.client.utilities.Base64.decode(input);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }


}
