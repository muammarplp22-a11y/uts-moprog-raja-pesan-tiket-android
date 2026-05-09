package com.example.pesantiketbioskop;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {

    TextView txtLastBooking;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_profile,
                container,
                false
        );

        txtLastBooking =
                view.findViewById(R.id.txtLastBooking);

        SharedPreferences preferences =
                requireActivity().getSharedPreferences(
                        "CineTIX",
                        getContext().MODE_PRIVATE
                );

        String movie =
                preferences.getString(
                        "lastMovie",
                        "Belum ada booking"
                );

        String seat =
                preferences.getString(
                        "lastSeat",
                        "-"
                );

        txtLastBooking.setText(
                "🎬 " + movie + "\n💺 " + seat
        );

        return view;
    }
}