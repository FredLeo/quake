package com.example.red_six.disasterapp;

import android.content.Intent;
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

        ListAdapter theAdapter=new ArrayAdapter<String>(this,R.layout.row_layout,severities);


        ListView thelistView = (ListView) findViewById(R.id.theListView);
//        thelistView.setAdapter(theAdapter);

        thelistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String feeling="you selected"+String.valueOf(adapterView.getItemAtPosition(i));

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