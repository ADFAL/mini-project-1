package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class UsersAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<User> users;
    long touchStartTime = 0;

    public UsersAdapter(Context context, int simple_list_item_1, ArrayList<User> users) {
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

        View finalConvertView = convertView;
        convertView.setOnClickListener(null);

        convertView.setOnTouchListener(new OnSwipeTouchListener(context) {
            @Override
            public void swipeLeft() {
                finalConvertView.setBackgroundColor(Color.parseColor("#4DFF0000"));
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Attention").setMessage("Do you want to remove the user? ");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finalConvertView.setBackgroundColor(Color.class.getModifiers());
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        users.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.show();
            }
        });

        return convertView;
    }
}