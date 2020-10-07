package com.example.gowatainfoapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class keyActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key);

        //create popup window for menu selection
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        // * % of screen
        getWindow().setLayout((int)(width*.5),(int)(height*.5));


        //not needed as this is now a popup

//        final Button returnToMap = (Button) findViewById(R.id.returnToMap);
//        returnToMap.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // your handler code here
//
//                Intent goBack = new Intent(keyActivity.this, MapsActivity.class);
//                goBack.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | goBack.FLAG_ACTIVITY_NEW_TASK);
//                keyActivity.this.startActivity(goBack);
//                finish();
//
//            }
//        });
    }
}
