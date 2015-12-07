package com.example.hulk.mtindo;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import fr.ganfra.materialspinner.MaterialSpinner;


public class Createstore extends AppCompatActivity {
    //select gallery images variables
    private static int RESULT_LOAD_IMAGE = 1;
    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;
    private ImageView img;

    private EditText inputName, /*inputEmail*/
            inputStorename, inputDescription, inputTelephone;
    private TextInputLayout inputLayoutName, /*inputLayoutEmail,*/
            inputLayoutStore, inputLayoutDescription, inputLayoutTelephone;

//variables phone


    //spinner variables
    private static final String ERROR_MSG = "Very very very long error message to get scrolling or multiline animation when the error button is clicked";
    private static final String[] ITEMS = {"Hair", "Beauty", "Makeup"};


    private ArrayAdapter<String> adapter;

    MaterialSpinner spinner1;
    private boolean shown = false;


    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createstore);


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        initSpinnerHintAndFloatingLabel();


        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutStore = (TextInputLayout) findViewById(R.id.input_layout_storename);
        inputLayoutTelephone = (TextInputLayout) findViewById(R.id.input_layout_telephone);
        inputLayoutDescription = (TextInputLayout) findViewById(R.id.input_layout_description);
        /*inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);*/


        /*inputEmail = (EditText) findViewById(R.id.input_email);*/
        inputName = (EditText) findViewById(R.id.input_name);
        inputTelephone = (EditText) findViewById(R.id.input_telephone);
        inputStorename = (EditText) findViewById(R.id.input_storename);
        inputDescription = (EditText) findViewById(R.id.input_description);

        btnSignUp = (Button) findViewById(R.id.btn_createstore);

        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        /*inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));*/
        inputStorename.addTextChangedListener(new MyTextWatcher(inputStorename));
        inputDescription.addTextChangedListener(new MyTextWatcher(inputDescription));
        inputTelephone.addTextChangedListener(new MyTextWatcher(inputStorename));


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
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


        img = (ImageView)findViewById(R.id.ImageView01);

        ((Button) findViewById(R.id.Button01))
                .setOnClickListener(new View.OnClickListener() {
                    public void onClick(View arg0) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
                    }
                });


    }






    private void initSpinnerHintAndFloatingLabel() {
        spinner1 = (MaterialSpinner) findViewById(R.id.spinner1);
        spinner1.setAdapter(adapter);
        spinner1.setPaddingSafe(0, 0, 0, 0);
    }

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                System.out.println("Image Path : " + selectedImagePath);
                img.setImageURI(selectedImageUri);
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
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

   /* private boolean validateTelephone() {
        String phone = inputTelephone.getText().toString().trim();
        if (phone.isEmpty() || phone.equals("")) {
            inputLayoutTelephone.setError(getString(R.string.err_msg_telephone));
            requestFocus(inputTelephone);
            return false;


        } else if (phone.equals("0")) {
            inputLayoutTelephone.setError(getString(R.string.err_msg_telephone));
            requestFocus(inputTelephone);
            return false;
        } else {
            inputLayoutTelephone.setErrorEnabled(false);
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

  /*  private boolean validateData() {
        boolean result = true;
        String phone = edtPhone.getText().toString();
        if (phone == null || phone.equals("")) {
            phoneLayout.setError(getString(R.string.invalidNumber));
            result = false;
        }
        else if (phone.equals("0")) {
            phoneLayout.setError(getString(R.string.invalidNumberZero));
            result = false;
        }

        return result;
    }*/



   /* private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }*/

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
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
}

