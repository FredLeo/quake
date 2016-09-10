package com.example.red_six.disasterapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class RegisterUser extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.red_six.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
    }


    public void onGoSeverity(View view) {
        CharSequence text = "Go to Severity!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(RegisterUser.this, text, duration);
        toast.show();

        Intent intent = new Intent(this, Severity.class);
        String message = "message from RegisterUser";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
