package com.example.red_six.disasterapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Array;

/**
 * Created by littl on 10/09/2016.
 */
public class Severity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.red_six.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_severity);

        final String[] severities={"Violent, severe","Strong, powerful","Moderate", "Gentle, mild", "Heard but not felt"};

        ArrayAdapter<String> theAdapter=new ArrayAdapter<String>(this,R.layout.row_layout,severities);

        ListView theListView = (ListView) findViewById(R.id.theListView);
        theListView.setAdapter(theAdapter);


        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View viewClicked, int position, long l) {
                TextView lv = (TextView) viewClicked;
                lv.setBackgroundColor(Color.parseColor("#ffccff"));
                String feeling="you selected"+String.valueOf(adapterView.getItemAtPosition(position));

                Toast.makeText(Severity.this,feeling,Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void onAlright(View view) {
        Intent intent = new Intent(this, Alright.class);
        String message = "message from Severity";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void onNeedHelp(View view) {
        Intent intent = new Intent(this, Help.class);
        String message = "message from Severity";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}