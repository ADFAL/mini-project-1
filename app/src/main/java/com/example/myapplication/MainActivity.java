package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnUsersActLoadUsers, btnUsersActQuit;
    ListView lvUsersActUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUsersActLoadUsers = findViewById(R.id.btnUsersActLoadUsers);
        btnUsersActQuit = findViewById(R.id.btnUsersActQuit);
        lvUsersActUsers = findViewById(R.id.lvUsersActUsers);

        btnUsersActLoadUsers.setOnClickListener(this);
        btnUsersActQuit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnUsersActLoadUsers) {
            UsersAdapter adapter = new UsersAdapter(this, getUsers());

            lvUsersActUsers.setAdapter(adapter);
        } else if (v.getId() == R.id.btnUsersActQuit) {
            finish();
        }
    }

    private ArrayList<User> getUsers() {
        ArrayList<User> usersFullNames = new ArrayList<>();

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

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject user = jsonArray.getJSONObject(i);
                JSONObject userName = user.getJSONObject("name");

                usersFullNames.add(new User(
                        userName.getString("first"),
                        userName.getString("last"),
                        user.getString("gender"),
                        user.getString("city")));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return usersFullNames;
    }
}