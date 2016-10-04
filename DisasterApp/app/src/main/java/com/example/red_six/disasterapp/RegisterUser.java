package com.example.red_six.disasterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RegisterUser extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.red_six.MESSAGE";
    public List<Address> loadAddresses;
    public SQLLiteDBHandler db;
    public LinearLayout root;
    //following lists will hold a list of each label and the data to put into it
    public List<TextView> nameList = new ArrayList<TextView>();
    public List<TextView> nameDataList = new ArrayList<TextView>();
    public List<TextView> streetList = new ArrayList<TextView>();
    public List<TextView> streetDataList = new ArrayList<TextView>();
    public List<TextView> cityList = new ArrayList<TextView>();
    public List<TextView> cityDataList = new ArrayList<TextView>();
    public List<TextView> regionList = new ArrayList<TextView>();
    public List<TextView> regionDataList = new ArrayList<TextView>();
    public List<String> nameListString = new ArrayList<>();
    public List<String> streetListString = new ArrayList<>();
    public List<String> cityListString = new ArrayList<>();
    public List<String> regionListString = new ArrayList<>();

    public List<Button> editList = new ArrayList<>();
    public List<Button> deleteList = new ArrayList<>();


    //These strings will hold data from each address in the Dbase
    String nameData = "";
    String streetData = "";
    String cityData = "";
    String regionData = "";
    //params for LinearLayout
    LinearLayout.LayoutParams paramsVert;
    LinearLayout.LayoutParams paramsHor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        root = (LinearLayout)findViewById(R.id.Home);
        //add LayoutParams
        paramsVert = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        root.setOrientation(LinearLayout.VERTICAL);
        paramsHor = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        root.setOrientation(LinearLayout.VERTICAL);
        db = new SQLLiteDBHandler(this);
        loadDataBase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadViews();
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
    /* open up the "popup" to add another address*/
    public void onEnterAddress (View view) {
        Intent intent = new Intent(this, EnterAddress.class);
        String message = "message from RegisterUser";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    public void onGoHome (View view) {
        Intent intent = new Intent(this, home.class);
        String message = "message from RegisterUser";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void loadDataBase() {
        loadAddresses=db.getAllAddresses();
        Address getAddress;
        String toastMessage="";
        for(int i =0; i<loadAddresses.size(); i ++) {
            getAddress=loadAddresses.get(i);
            nameData=getAddress.getName();
            streetData=getAddress.getAddress();
            cityData=getAddress.getCity();
            regionData=getAddress.getRegion();
            nameListString.add(nameData);
            streetListString.add(streetData);
            cityListString.add(cityData);
            regionListString.add(regionData);
            toastMessage =  nameData + " + " + streetData + " + "  + cityData +  " + " + regionData;
        }

        for(int i = 0; i<nameListString.size(); i++) {
            //instantiate the views
            TextView nameText = new TextView(this);
            TextView nameDataText = new TextView(this);
            TextView streetText = new TextView(this);
            TextView streetDataText = new TextView(this);
            TextView cityText = new TextView(this);
            TextView cityDataText = new TextView(this);
            TextView regionText = new TextView(this);
            TextView regionDataText = new TextView(this);
            //setup each text view

            //setup buttons for edit and delete
            Button edit=new Button(this);
            Button delete=new Button(this);

            //Name Label
            nameText.setText("Name");
            nameText.setLayoutParams(paramsVert);
            nameText.setId(100+i);
//            nameText.setBackgroundResource(R.drawable.edittext_normal);
            //Address Label
            streetText.setText("Address");
            streetText.setLayoutParams(paramsVert);
            streetText.setId(200+i);
            //City Label
            cityText.setText("City");
            cityText.setLayoutParams(paramsVert);
            cityText.setId(300+i);
            //Region Label
            regionText.setText("Region");
            regionText.setLayoutParams(paramsVert);
            regionText.setId(400+i);
            //Name Data
            nameDataText.setText(nameListString.get(i));
            nameDataText.setLayoutParams(paramsVert);
            nameDataText.setId(500+i);
            nameDataText.setBackgroundResource(R.drawable.edittext_normal);
            //Address Data
            streetDataText.setText(streetListString.get(i));
            streetDataText.setLayoutParams(paramsVert);
            streetDataText.setId(600+i);
            //City Data
            cityDataText.setText(cityListString.get(i)+", "+regionListString.get(i));
            cityDataText.setLayoutParams(paramsVert);
            cityDataText.setId(700+i);

            //edit button and delete button
            edit.setText("edit");
            edit.setLayoutParams(paramsHor);
            edit.setId(800+i);

            delete.setText("delete");
            delete.setLayoutParams(paramsHor);
            delete.setId(900+i);

            //Region Data
//            regionDataText.setText(regionListString.get(i));
//            regionDataText.setLayoutParams(paramsVert);
//            regionDataText.setId(800+i);
            //put each view into a list
            nameList.add(nameText);
            nameDataList.add(nameDataText);
            streetList.add(streetText);
            streetDataList.add(streetDataText);
            cityList.add(cityText);
            cityDataList.add(cityDataText);
            editList.add(edit);
            deleteList.add(delete);
//            regionList.add(regionText);
//            regionDataList.add(regionDataText);
        }
    }

    public void loadViews() {

        //loop through each textview list and add to Root Linear Layout
        for(int j =0;j<cityList.size();j++) {
            //Name Label
//            if(nameList.get(j).getParent()!=null)
//                ((ViewGroup)nameList.get(j).getParent()).removeView(nameList.get(j));
//            root.addView(nameList.get(j));
            //Address Label
            if(nameDataList.get(j).getParent()!=null)
                ((ViewGroup)nameDataList.get(j).getParent()).removeView(nameDataList.get(j));
            root.addView(nameDataList.get(j));
            //City Label
//            if(streetList.get(j).getParent()!=null)
//                ((ViewGroup)streetList.get(j).getParent()).removeView(streetList.get(j));
//            root.addView(streetList.get(j));
            //Region Label
            if(streetDataList.get(j).getParent()!=null)
                ((ViewGroup)streetDataList.get(j).getParent()).removeView(streetDataList.get(j));
            root.addView(streetDataList.get(j));
            //Name data
//            if(cityList.get(j).getParent()!=null)
//                ((ViewGroup)cityList.get(j).getParent()).removeView(cityList.get(j));
//            root.addView(cityList.get(j));
            //Address data
            if(cityDataList.get(j).getParent()!=null)
                ((ViewGroup)cityDataList.get(j).getParent()).removeView(cityDataList.get(j));
            root.addView(cityDataList.get(j));

            root.addView(editList.get(j));
            root.addView(deleteList.get(j));
            //City data
//            if(regionList.get(j).getParent()!=null)
//                ((ViewGroup)regionList.get(j).getParent()).removeView(regionList.get(j));
//            root.addView(regionList.get(j));
            //Region data
//            if(regionDataList.get(j).getParent()!=null)
//                ((ViewGroup)regionDataList.get(j).getParent()).removeView(regionDataList.get(j));
//            root.addView(regionDataList.get(j));
        }
    }
}