package com.example.gowatainfoapplication;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class popActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //create popup window for menu selection
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupwindow);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        // * % of screen
        getWindow().setLayout((int)(width*.5),(int)(height*.5));

        Spinner spinner = findViewById(R.id.stops);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.stops,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        final String CONDITION_1 = "WM Law School";
        final String CONDITION_2 = "Wawa";
        final String CONDITION_3 = "Stadium";
        final String CONDITION_4 = "Sadler";
        final String CONDITION_5 = "Brooks St.";
        final String CONDITION_6 = "Cafe";
        final String CONDITION_7 = "WM Police";
        final String CONDITION_8 = "Rolfe Rd. (Ludwell)";
        final String CONDITION_9 = "Indian Springs Rd.";
        final String CONDITION_10 = "Taliaferro Hall";
        final String CONDITION_11 = "Cary St.";


        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();

        Spinner sp = (Spinner) findViewById(R.id.stops);
        String spinnerState = sp.getSelectedItem().toString();
        TextView tvMostrar = (TextView) this.findViewById(R.id.spinnercontent);
        //tvMostrar.setText(sp.getSelectedItem().toString());
        String stoptext = getString(R.string.stoptext);

        if (spinnerState.equalsIgnoreCase(CONDITION_1)){
            String Text = CONDITION_1+ stoptext+"\n.19\n.45";
            tvMostrar.setText(Text.toString());

        } else if (spinnerState.equalsIgnoreCase(CONDITION_2)){
            String Text = CONDITION_2+ stoptext+"\n.24\n.54";
            tvMostrar.setText(Text.toString());


        } else if (spinnerState.equalsIgnoreCase(CONDITION_3)){
            String Text = CONDITION_3+ stoptext+"\n.04\n.34";
            tvMostrar.setText(Text.toString());

        } else if (spinnerState.equalsIgnoreCase(CONDITION_4)){
            String Text = CONDITION_4+ stoptext+"\n.26\n.56";
            tvMostrar.setText(Text.toString());

        } else if (spinnerState.equalsIgnoreCase(CONDITION_5)){
            String Text = CONDITION_5+ stoptext+"\n.00\n.30";
            tvMostrar.setText(Text.toString());

        } else if (spinnerState.equalsIgnoreCase(CONDITION_6)){
            String Text = CONDITION_6+ stoptext+"\n.06\n.36 ";
            tvMostrar.setText(Text.toString());

        } else if (spinnerState.equalsIgnoreCase(CONDITION_7)){
            String Text = CONDITION_7+ stoptext+"\n.07.37";
            tvMostrar.setText(Text.toString());

        } else if (spinnerState.equalsIgnoreCase(CONDITION_8)){
            String Text = CONDITION_8+ stoptext+"\n.09\n.39";
            tvMostrar.setText(Text.toString());

        } else if (spinnerState.equalsIgnoreCase(CONDITION_9)){
            String Text = CONDITION_9+ stoptext+"\n.11\n.41";
            tvMostrar.setText(Text.toString());

        } else if (spinnerState.equalsIgnoreCase(CONDITION_10)){
            String Text = CONDITION_10+ stoptext+"\n.19\n.45";
            tvMostrar.setText(Text.toString());

        }else if (spinnerState.equalsIgnoreCase(CONDITION_11)){
            String Text = CONDITION_11+ stoptext+"\n.12\n.42";
            tvMostrar.setText(Text.toString());

        }




    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }
}
