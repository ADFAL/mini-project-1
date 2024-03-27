package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

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
        TextView tvUsersItmNumber = convertView.findViewById(R.id.tvUsersItmNumber);

        tvUsersItmFullName.setText(user.fullName());
        tvUsersItmCity.setText(user.getCity());
        tvUsersItmNumber.setText("#"+(position+1));

        if (user.getGender().equals("male")) {
            convertView.setBackgroundColor(Color.rgb(51,153,255));
        }
        else {
            convertView.setBackgroundColor(Color.rgb(255,182,193));
        }

        return convertView;
    }
}
