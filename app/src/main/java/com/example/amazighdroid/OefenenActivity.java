package com.example.amazighdroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OefenenActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Required for database
//    private DatabaseReference mDatabase;

    // Setting name for TAG Debug
    private static final String TAG = "OefenenActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefenen);




//        // Selection of the spinner
//        Spinner spinner = (Spinner) findViewById(R.id.spinner);
//
//        // Application of the Array to the Spinner
//        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
//        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
//        spinner.setAdapter(spinnerArrayAdapter);
//        spinner.setOnItemSelectedListener(this);

        // Read from the database


// Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("woorden/yes");

        myRef.setValue("Hello, World!");
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        Log.d(TAG, "Item has been selected: " + (String) parent.getItemAtPosition(pos));
//        Log.d(TAG, (String) parent.getItemAtPosition(pos));
//        selectedValue = (String) parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

//    public void showItem(View view) {
//        // Do something in response to button
//        Intent intent = new Intent(this, oefenitem.class);
//        intent.putExtra(EXTRA_MESSAGE, selectedValue);
//        startActivity(intent);
//    }

}