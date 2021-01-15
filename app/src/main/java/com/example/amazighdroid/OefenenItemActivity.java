package com.example.amazighdroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

        // Declare array to hold values of database
        List<String> listNl = new ArrayList<>();
        List<String> listAm = new ArrayList<>();
        List<String> listImg = new ArrayList<>();

        // Get string from previous activity
        Intent intent = getIntent();
        String selectedCategorie = intent.getExtras().getString("selectedCategorie");

        // Set textview to string selectedCategorie
        TextView txtview = (TextView) findViewById(R.id.textView3);
        txtview.setText(selectedCategorie);

        // Declare instance of database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("woorden/" + selectedCategorie);

        // Retrieve values of selected categorie from database and add them into a list
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String nl = ds.child("nl").getValue(String.class);
                    String am = ds.child("am").getValue(String.class);
                    String img = ds.child("img").getValue(String.class);
                    listNl.add(nl);
                    listAm.add(am);
                    listImg.add(img);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}