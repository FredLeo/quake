package com.example.red_six.disasterapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.view.ViewGroup;
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
//        setListViewHeightBasedOnItems(theListView);

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

    /**
     * Sets ListView height dynamically based on the height of the items.
     *
     * @param listView to be resized
     * @return true if the listView is successfully resized, false otherwise
     */
    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                //Two integers passed to measure() are measure specifications, which means
                // width and height that a view wants to have. The zeros mean that we don't
                // specify the width/height demands.
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }

    }
}