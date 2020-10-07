package com.example.gowatainfoapplication;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class wataActivity extends AppCompatActivity {
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        mWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);



        mWebView.postDelayed(new Runnable() {

            @Override
            public void run() {
                //mWebView.loadUrl(getString(R.string.link));
                String myurl= getString(R.string.link);
                if (myurl.equals("https://gowata.org/157/Route-8-William-and-Mary-Green"))
                    mWebView.loadUrl(myurl);
                else {
                    Toast.makeText(getApplicationContext(),"WebView URL Altered/ Compromised!", Toast.LENGTH_LONG).show();

                    finish();

                }

            }
        }, 500);


        final Button backToMap = (Button) findViewById(R.id.backToMap);
        backToMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                Intent backMap = new Intent(wataActivity.this, MapsActivity.class);
                backMap.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | backMap.FLAG_ACTIVITY_NEW_TASK);

                Log.v("Returning To Map", "Map Generating");
                startActivity(backMap);


                finish();


            }

        });

        final Button backToMenu = (Button) findViewById(R.id.backToMenu);
        backToMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                Intent backMenu = new Intent(wataActivity.this, startActivity.class);
                backMenu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | backMenu.FLAG_ACTIVITY_NEW_TASK);

                Log.v("Returning To Main Menu", "Menu Generating");
                startActivity(backMenu);


                finish();
            }

        });


    }

}
