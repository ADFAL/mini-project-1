package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class UsersAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<User> users;
    long touchStartTime = 0;

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

        tvUsersItmFullName.setText(user.fullName());
        tvUsersItmCity.setText(user.getCity());

        convertView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    touchStartTime = System.currentTimeMillis();
                }
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    long touchDuration = System.currentTimeMillis() - touchStartTime;
                    if (touchDuration >= 1000 && touchDuration < 2000) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle(String.format("Details of user %d",position+1)).setMessage(user.toString()).show();
                    }
                }
                return true;
            }
        });

        return convertView;
    }
}