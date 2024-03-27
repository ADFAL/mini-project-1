package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

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


    public class UsersAdapter extends BaseAdapter {
        Context context;
        LayoutInflater inflater;
        ArrayList<User> users;

        public UsersAdapter(Context context, ArrayList<User> users) {
            this.users = users;
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public Object getItem(int position) {
            return users.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            User user = users.get(position);
            convertView = inflater.inflate(R.layout.item_user, null);

            TextView tvUsersItmFullName = convertView.findViewById(R.id.tvUsersItmFullName);
            TextView tvUsersItmCity = convertView.findViewById(R.id.tvUsersItmCity);
            ImageButton tvUsersItemDetails = convertView.findViewById(R.id.tvUsersItemDetails);

            tvUsersItmFullName.setText(user.fullName());
            tvUsersItmCity.setText(user.getCity());
            tvUsersItemDetails.setOnClickListener(v->{
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("firstName",user.getFirstName());
                intent.putExtra("lastName",user.getLastName());
                intent.putExtra("gender",user.getGender());
                intent.putExtra("city",user.getCity());
                startActivity(intent);
            });

            return convertView;
        }
    }
}