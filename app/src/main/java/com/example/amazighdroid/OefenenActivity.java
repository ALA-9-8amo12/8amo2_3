package com.example.amazighdroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.util.ArrayList;

public class OefenenActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Setting name for TAG Debug
    private static final String TAG = "OefenenActivity";

    // Define array for spinner
    ArrayList<String> categorieArray = new ArrayList<>();

    // Variable for next activity
    String selectedCategorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefenen);

        // Selection of the spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Application of the Array to the Spinner
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categorieArray);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(this);

        // Declare instance of database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("woorden");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String data = snapshot.getKey();
                    categorieArray.add(data);
                    spinnerArrayAdapter.notifyDataSetChanged();
                }
//                Log.v(TAG, "First data : " + youNameArray.get(0));
//                Log.v(TAG, "Second data : " + youNameArray.get(2));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        Log.d(TAG, "Item has been selected: " + (String) parent.getItemAtPosition(pos));
        selectedCategorie = (String) parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void selectCategorie(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, OefenenItemActivity.class);
        intent.putExtra("selectedCategorie", selectedCategorie);
        startActivity(intent);
    }

}