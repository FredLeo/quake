package com.example.red_six.disasterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Fred on 9/09/2016.
 */
public class Severity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_severity);

        Intent activityThatCalled = getIntent();
        String str = activityThatCalled.getExtras().getString("callingActivity");
    }


    public void onAlright(View view) {
    }

    public void onNeedHelp(View view) {
        //        Intent getNameScreenIntent = new Intent(this,SecondScreen.class);
        final int result=1;
//        getNameScreenIntent.putExtra("callingActivity","MainActivity");
//        startActivityForResult(getNameScreenIntent,result);
        //Human human= new Human(6.53,123,"bot");
        Intent sendBob=new Intent(this,Help.class);
        sendBob.putExtra("callingActivity","Severity");
        //sendBob.putExtra("human",human);
        startActivityForResult(sendBob,result);
    }
}
