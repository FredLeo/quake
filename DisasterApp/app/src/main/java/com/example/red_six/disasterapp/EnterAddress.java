package com.example.red_six.disasterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EnterAddress extends AppCompatActivity {

    SQLLiteDBHandler db;
    EditText name;
    EditText address;
    EditText city;
    Spinner region;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_enter_address);

        /*Get values for controls that get data*/
        db = new SQLLiteDBHandler(this);
        name = (EditText)findViewById(R.id.enterEditTextName);
        address = (EditText)findViewById(R.id.enterEditTextStreetAddress);
        city = (EditText)findViewById(R.id.enterEditTextCity);
        region = (Spinner)findViewById(R.id.enterSpinnerRegion);

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
        String nameText = name.getText().toString();
        String addressText = address.getText().toString();
        String cityText = city.getText().toString();
        String regionText = region.getSelectedItem().toString();

        CharSequence text = nameText + " : " + addressText + " : " +cityText + " : " + regionText;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(EnterAddress.this, text, duration);
        toast.show();
        Intent intent = new Intent(this, RegisterUser.class);
        db.addAddress(new Address(1,nameText, addressText, cityText, regionText));
        startActivity(intent);
        

    }



}
