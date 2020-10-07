package com.example.gowatainfoapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class startActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        final Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                Intent myIntent = new Intent(startActivity.this, MapsActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | myIntent.FLAG_ACTIVITY_NEW_TASK);
                startActivity.this.startActivity(myIntent);
                finish();

            }
        });



        final Button show_wata = (Button) findViewById(R.id.show_wata);
        show_wata.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                Intent myIntent = new Intent(startActivity.this, wataActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | myIntent.FLAG_ACTIVITY_NEW_TASK);
                startActivity.this.startActivity(myIntent);
                finish();

            }
        });
    }
}
