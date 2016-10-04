package com.example.red_six.disasterapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

import static android.R.attr.button;
import static java.lang.System.in;

/**
 * Created by Fred on 10/09/2016.
 */
public class Severity extends AppCompatActivity {

    private LocationManager locationManager;
    private LocationListener locationListener;
    private Button locButton;
    private TextView locTextview;
    Location location;
    Button btHeard, btGentle, btModerate, btPowerful, btViolent;
    //ArrayList<Button> btnArray;

    public final static String EXTRA_MESSAGE = "com.example.red_six.MESSAGE";

    final String[] severities={"Violent, severe","Strong, powerful","Moderate", "Gentle, mild", "Heard but not felt"};
    Button[] buttons = new Button[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_severity);

        getButtons();
//        btHeard = (Button) findViewById(R.id.btHeard);
//        btnArray = new ArrayList<Button>((Collection<? extends Button>) btHeard);
//        for(int i=0; i<btnArray.size();i++){
//
//        }
//        Toast.makeText(this,btnArray.size(),Toast.LENGTH_SHORT).show();

        //location block
        //locButton = (Button) findViewById(R.id.getLoca);
        locTextview = (TextView) findViewById(R.id.locTV);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                locTextview.setText("\n " + location.getLatitude() + " " + location.getLongitude());
                Toast.makeText(Severity.this,"Location from test activity is :"+location.getLatitude() + ", " + location.getLongitude(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intent);
            }

            // if the gps is turned off
            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };

        if (ContextCompat.checkSelfPermission(Severity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                &&ContextCompat.checkSelfPermission(Severity.this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Severity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    10);
        }

        locationManager.requestLocationUpdates("gps", 1000, 0, locationListener);
        location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
        if(location != null) {
            locTextview.append("\n " + location.getLatitude() + " " + location.getLongitude());
        }

//        locButton.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View view){
//                if (ContextCompat.checkSelfPermission(Severity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                        &&ContextCompat.checkSelfPermission(Severity.this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(Severity.this,
//                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
//                            10);
//                }
//                locationManager.requestLocationUpdates("gps", 5000, 50, locationListener);
//            }
//        });
        //end of location block


//keep this part in case we need to go back to this idea
//        ArrayAdapter<String> theAdapter=new ArrayAdapter<String>(this,R.layout.row_layout,severities);
//
//        ListView theListView = (ListView) findViewById(R.id.theListView);
//        assert theListView != null;
//        theListView.setAdapter(theAdapter);
//
//        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View viewClicked, int position, long l) {
//                TextView lv = (TextView) viewClicked;
//                lv.setBackgroundColor(Color.parseColor("#ffccff"));
//                String feeling="you selected"+String.valueOf(adapterView.getItemAtPosition(position));
//
//                Toast.makeText(Severity.this,feeling,Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    private void getButtons(){
        btHeard = (Button) findViewById(R.id.btHeard);
        btGentle = (Button) findViewById(R.id.btGentel);
        btModerate = (Button) findViewById(R.id.btModerate);
        btPowerful = (Button) findViewById(R.id.btPowerful);
        btViolent = (Button) findViewById(R.id.btViolent);
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

    public void onGoHome (View view) {
        Intent intent = new Intent(this, home.class);
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

    public void onHeard(View view) {
        Intent intent = new Intent(this, Alright.class);
        startActivity(intent);
        btHeard.setBackgroundColor(Color.parseColor("#f4f142"));
        Toast.makeText(Severity.this,severities[4],Toast.LENGTH_SHORT).show();
    }

    public void onGentle(View view) {
        Intent intent = new Intent(this, Alright.class);
        startActivity(intent);
        btGentle.setBackgroundColor(Color.parseColor("#f4f142"));
        Toast.makeText(Severity.this,severities[3],Toast.LENGTH_SHORT).show();
    }

    public void onModerate(View view) {
        Intent intent = new Intent(this, Alright.class);
        startActivity(intent);
        btModerate.setBackgroundColor(Color.parseColor("#f4f142"));
        Toast.makeText(Severity.this,severities[2],Toast.LENGTH_SHORT).show();
    }

    public void onPowerful(View view) {
        Intent intent = new Intent(this, Alright.class);
        startActivity(intent);
        btPowerful.setBackgroundColor(Color.parseColor("#f4f142"));
        Toast.makeText(Severity.this,severities[1],Toast.LENGTH_SHORT).show();
    }

    public void onViolent(View view) {
        Intent intent = new Intent(this, Alright.class);
        startActivity(intent);
        btViolent.setBackgroundColor(Color.parseColor("#f4f142"));
        Toast.makeText(Severity.this,severities[0],Toast.LENGTH_SHORT).show();
    }
}