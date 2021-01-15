package com.example.amazighdroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OefenenItemActivity extends AppCompatActivity {
    // Setting name for TAG Debug
    private static final String TAG = "OefenenItemActivity";


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

        // Declare instance of database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("woorden/dieren0");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
                List<String> list = new ArrayList<>();
                List<String> list2 = new ArrayList<>();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String title = ds.child("nl").getValue(String.class);
                    String title2 = ds.child("am").getValue(String.class);
                    list.add(title);
                    list2.add(title2);
                    Log.d(TAG, "NL woorden: " + title);
                    Log.d(TAG, "AM woorden: " + title2);
                }
//                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }


}