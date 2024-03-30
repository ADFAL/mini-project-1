package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class UserDetailsDialog extends DialogFragment {
    User user;

    public UserDetailsDialog(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_user_details, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView tvUserDetailsItmFirstName = view.findViewById(R.id.tvUserDetailsItmFirstName);
        TextView tvUserDetailsItmLastName = view.findViewById(R.id.tvUserDetailsItmLastName);
        TextView tvUserDetailsItmCity = view.findViewById(R.id.tvUserDetailsItmCity);

        tvUserDetailsItmFirstName.setText(user.getFirstName());
        tvUserDetailsItmLastName.setText(user.getLastName());
        tvUserDetailsItmCity.setText(user.getCity());

        if (user.getGender().equals("male"))
            view.setBackgroundColor(Color.parseColor("#ADD8E6"));
        else
            view.setBackgroundColor(Color.parseColor("#ffb6c1"));
    }
}
