package com.example.amazighdroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

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

    ViewPager2 viewPager2;

    // Setting name for TAG
    private static final String TAG = "OefenenItemActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefenen_item);

        // Get string from previous activity
        Intent intent = getIntent();
        String selectedCategorie = intent.getExtras().getString("selectedCategorie");

        // Feed oefenen_viewpager.xml with data with adapter in ValueEventListener
        viewPager2 = findViewById(R.id.viewPager2);

        // Array populated with data from firebase
        List<String> listNl = new ArrayList<>();
        List<String> listAm = new ArrayList<>();
        List<String> listImg = new ArrayList<>();

        // Array for testing purposes
        List<String> list = new ArrayList<>();
        list.add("First Screen");
        list.add("Second Screen");
        list.add("Third Screen");
        list.add("Fourth Screen");

        // Declare instance of database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("woorden/" + selectedCategorie);

        // Retrieve values of selected categorie from database and add them into a list
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Dynamically create pages based on array size
                viewPager2.setAdapter(new ViewPagerAdapter(getApplicationContext(), listNl, viewPager2));
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