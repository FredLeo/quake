package com.example.red_six.disasterapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class home extends Activity{

    public final static String EXTRA_MESSAGE = "com.example.red_six.MESSAGE";


    ImageView one, two, three, four;
    String videoId1,videoId2,videoId3,videoId4 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.home);
        ImageView myImage1 = (ImageView) findViewById(R.id.imageOne);
        myImage1.setAlpha((float) 0.40); //value: [0.0-1]. Where 0 is fully transparent and 1 is fully opaque.
        ImageView myImage2 = (ImageView) findViewById(R.id.imageTwo);
        myImage2.setAlpha((float) 0.40); //value: [0.0-1]. Where 0 is fully transparent and 1 is fully opaque.
        ImageView myImage3 = (ImageView) findViewById(R.id.imageThree);
        myImage3.setAlpha((float) 0.40); //value: [0.0-1]. Where 0 is fully transparent and 1 is fully opaque.
        ImageView myImage4 = (ImageView) findViewById(R.id.imageFour);
        myImage4.setAlpha((float) 0.40); //value: [0.0-1]. Where 0 is fully transparent and 1 is fully opaque.

        init();

        videoId1="4f_FqkHgUGw";
        videoId2="7Fahnl7Scxw";
        videoId3="J1SMFaLTqoo";
        videoId4="WXmdVEkJKGg";

        String img_url1="http://img.youtube.com/vi/"+videoId1+"/0.jpg"; // this is link which will give u thumnail image of that video
        String img_url2="http://img.youtube.com/vi/"+videoId2+"/0.jpg";
        String img_url3="http://img.youtube.com/vi/"+videoId3+"/0.jpg";
        String img_url4="http://img.youtube.com/vi/WXmdVEkJKGg/0.jpg";
        // picasso jar file download image for u and set image in imagview

        Picasso.with(home.this)
                .load(img_url1)
                .placeholder(R.drawable.button_pressed)
                .into(one);

        Picasso.with(home.this)
                .load(img_url2)
                .placeholder(R.drawable.button_pressed)
                .into(two);

        Picasso.with(home.this)
                .load(img_url3)
                .placeholder(R.drawable.button_pressed)
                .into(three);
        Picasso.with(home.this)
                .load(img_url4)
                .placeholder(R.drawable.button_pressed)
                .into(four);

    }
    public void init()
    {
        one=(ImageView)findViewById(R.id.one);
        two=(ImageView)findViewById(R.id.two);
        three=(ImageView)findViewById(R.id.three);
        four=(ImageView)findViewById(R.id.four);
    }

    public void onGoSeverity(View view) {
        CharSequence text = "Go to Severity!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(home.this, text, duration);
        toast.show();

        Intent intent = new Intent(this, Severity.class);
        String message = "message from Home";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    /* open up the "popup" to add another address*/
    public void onShowAddress (View view) {
        Intent intent = new Intent(this, RegisterUser.class);
        String message = "message from Home";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    public void onGoHome (View view) {
        Intent intent = new Intent(this, home.class);
        String message = "message from home";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void video1 (View view) {
        Intent intent = new Intent(this, PlayVideo.class);
        intent.putExtra("id", videoId1);
        startActivity(intent);
    }

    public void video2 (View view) {
        Intent intent = new Intent(this, PlayVideo.class);
        intent.putExtra("id", videoId2);
        startActivity(intent);
    }

    public void video3 (View view) {
        Intent intent = new Intent(this, PlayVideo.class);
        intent.putExtra("id", videoId3);
        startActivity(intent);
    }
    public void video4 (View view) {
        Intent intent = new Intent(this, PlayVideo.class);
        intent.putExtra("id", videoId4);
        startActivity(intent);
    }


}
