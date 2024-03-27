package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnUsersActLoadUsers, btnUsersActQuit;
    RadioButton radioMales,radioFamales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUsersActLoadUsers = findViewById(R.id.btnUsersActLoadUsers);
        btnUsersActQuit = findViewById(R.id.btnUsersActQuit);
        radioMales = findViewById(R.id.radioMales);
        radioFamales = findViewById(R.id.radioFamales);

        btnUsersActLoadUsers.setOnClickListener(this);
        btnUsersActQuit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnUsersActLoadUsers) {
            try {
                InputStream inputStream = getAssets().open("users.json");
                int code;
                StringBuilder stringBuilder = new StringBuilder();
                String jsonString;

                code = inputStream.read();
                while (code != -1) {
                    stringBuilder.append((char) code);

                    code = inputStream.read();
                }
                jsonString = stringBuilder.toString();

                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("users");

                StringBuilder stringBuilderFullNames = new StringBuilder();
                StringBuilder stringBuilderCities = new StringBuilder();
                StringBuilder stringBuilderUsersFemales = new StringBuilder();
                StringBuilder stringBuilderUsersMales = new StringBuilder();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject user = jsonArray.getJSONObject(i);

                    JSONObject userName = user.getJSONObject("name");
                    String fullName = String.format("%s %s", userName.get("first"), userName.get("last"));

                    String gender  = user.getString("gender");
                    String city = user.get("city").toString();

                    stringBuilderFullNames.append(fullName);
                    stringBuilderCities.append(city);

                    if (gender.equals("male")) {
                        stringBuilderUsersMales.append(String.format("%s | %s\n",fullName,city));
                    }
                    else {
                        stringBuilderUsersFemales.append(String.format("%s | %s\n",fullName,city));
                    }

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                if (radioMales.isChecked()) {
                    builder.setTitle("Male users");
                    builder.setMessage(stringBuilderUsersMales);
                }
                else if (radioFamales.isChecked()) {
                    builder.setTitle("Female Users");
                    builder.setMessage(stringBuilderUsersFemales);
                }
                builder.show();

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        } else if (v.getId() == R.id.btnUsersActQuit) {
            finish();
        }
    }
}