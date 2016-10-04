package com.example.red_six.disasterapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Fred on 10/09/2016.
 */
public class Alright extends FragmentActivity {
    //This is to handle the result back when an image is selected from Image Gallery.
    private static int RESULT_LOAD_IMG=1;
    String imgDecodeString;

    private final String TAG ="IMAGE INFORMATION";
    public final static String EXTRA_MESSAGE = "com.example.red_six.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alright);

    }

    public void onLoadImage(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodeString = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.imgView);
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodeString));

                Log.e(TAG,imgDecodeString);
                Toast.makeText(this,imgDecodeString,Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }
    }
    public void onGoSeverity(View view) {
        CharSequence text = "Go to Severity!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(Alright.this, text, duration);
        toast.show();

        Intent intent = new Intent(this, Severity.class);
        String message = "message from Alright";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    /* open up the "popup" to add another address*/
    public void onEnterAddress (View view) {
        Intent intent = new Intent(this, EnterAddress.class);
        String message = "message from Alright";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    public void onGoHome (View view) {
        Intent intent = new Intent(this, home.class);
        String message = "message from Alright";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
