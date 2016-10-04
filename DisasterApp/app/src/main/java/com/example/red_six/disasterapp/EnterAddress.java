package com.example.red_six.disasterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.text.TextWatcher;

public class EnterAddress extends AppCompatActivity {

    SQLLiteDBHandler db;
    EditText name, address,city;
    Spinner region;
    private TextInputLayout inputLayoutName, inputLayoutStreet, inputLayoutCity, inputLayoutRegion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_enter_address);
        region = (Spinner) findViewById(R.id.enterSpinnerRegion);
        region.setFocusable(true);
        region.setFocusableInTouchMode(true);
        region.requestFocus();
        /*Get values for controls that get data*/
        db = new SQLLiteDBHandler(this);
        name = (EditText)findViewById(R.id.enterEditTextName);
        address = (EditText)findViewById(R.id.enterEditTextStreetAddress);
        city = (EditText)findViewById(R.id.enterEditTextCity);
        region = (Spinner)findViewById(R.id.enterSpinnerRegion);
        inputLayoutName = (TextInputLayout) findViewById(R.id.addressName);
        inputLayoutStreet = (TextInputLayout) findViewById(R.id.addressStreet);
        inputLayoutCity = (TextInputLayout) findViewById(R.id.addressCity);
        inputLayoutRegion = (TextInputLayout) findViewById(R.id.addressRegion);
        name.addTextChangedListener(new MyTextWatcher(name));
    }



    private boolean validateName() {
        if (name.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(name);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateStreet() {
        String email = address.getText().toString().trim();

        if (email.isEmpty()) {
            inputLayoutStreet.setError(getString(R.string.err_msg_address));
            requestFocus(address);
            return false;
        } else {
            inputLayoutStreet.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateCity() {
        if (city.getText().toString().trim().isEmpty()) {
            inputLayoutCity.setError(getString(R.string.err_msg_city));
            requestFocus(city);
            return false;
        } else {
            inputLayoutCity.setErrorEnabled(false);
        }

        return true;
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void onCancel(View view) {
        CharSequence text = "You pressed cancel. Nothing was saved";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(EnterAddress.this, text, duration);
        toast.show();
        Intent intent = new Intent(this, RegisterUser.class);
        startActivity(intent);

    }

    public void onEnter(View view) {
        if (!validateName()) {
            return;
        }

        if (!validateStreet()) {
            return;
        }

        if (!validateCity()) {
            return;
        }
        String nameText = name.getText().toString();
        String addressText = address.getText().toString();
        String cityText = city.getText().toString();
        String regionText = region.getSelectedItem().toString();

        CharSequence text = nameText + " : " + addressText + " : " +cityText + " : " + regionText;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(EnterAddress.this, text, duration);
        toast.show();
        Intent intent = new Intent(this, RegisterUser.class);
        db.addAddress(new Address(nameText, addressText, cityText, regionText));
        startActivity(intent);
    }

    public void onGoHome (View view) {
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
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
                case R.id.enterEditTextName:
                    validateName();
                    break;
                case R.id.enterEditTextStreetAddress:
                    validateStreet();
                    break;
                case R.id.enterEditTextCity:
                    validateCity();
                    break;
            }
        }
    }

}
