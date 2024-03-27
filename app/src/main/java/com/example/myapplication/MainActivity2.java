package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView fullName,City;
    ImageView symbolGender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        fullName = findViewById(R.id.fullName);
        symbolGender = findViewById(R.id.symbolGender);
        City = findViewById(R.id.City);

        String firstName = getIntent().getStringExtra("firstName");
        String lastName = getIntent().getStringExtra("lastName");
        String gender = getIntent().getStringExtra("gender");
        String city = getIntent().getStringExtra("city");
        fullName.setText(String.format("%s\n%s",firstName,lastName));
        if (gender != null) {
            if (gender.equals("male")) {
                symbolGender.setImageResource(R.drawable.baseline_male_24);
            }
            else {
                symbolGender.setImageResource(R.drawable.baseline_female_24);
            }
        }
        City.setText(city);
    }
}