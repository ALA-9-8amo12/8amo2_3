package com.example.amazighdroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button oefenen = (Button) findViewById(R.id.btnOefenen);
        oefenen.setOnClickListener(this); // calling onClick() method

        Button over_ons = (Button) findViewById(R.id.btnOver_Ons);
        over_ons.setOnClickListener(this); // calling onClick() method
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOefenen:
                startActivity(new Intent(MainActivity.this, OefenenActivity.class));
                break;
            case R.id.btnOver_Ons:
                startActivity(new Intent(MainActivity.this, OverOnsActivity.class));
                break;
            default:
                break;
        }
    }
}