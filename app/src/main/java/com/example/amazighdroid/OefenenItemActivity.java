package com.example.amazighdroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OefenenItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefenen_item);

        // Get string from previous activity
        Intent intent = getIntent();
        String selectedCategorie = intent.getExtras().getString("selectedCategorie");

        // Set textview to string selectedCategorie
        TextView txtview = (TextView) findViewById(R.id.textView3);
        txtview.setText(selectedCategorie);
    }
}