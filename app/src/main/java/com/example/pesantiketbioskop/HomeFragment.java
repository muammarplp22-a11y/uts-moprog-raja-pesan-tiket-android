package com.example.pesantiketbioskop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerMovie;
    ArrayList<Movie> movieList;
    MovieAdapter adapter;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_home,
                container,
                false
        );

        recyclerMovie =
                view.findViewById(R.id.recyclerMovie);

        movieList = new ArrayList<>();

        movieList.add(new Movie(
                "Avengers Endgame",
                "Action",
                "8.9",
                "Superhero Marvel",
                R.drawable.avengers
        ));

        movieList.add(new Movie(
                "Interstellar",
                "Sci-Fi",
                "9.1",
                "Perjalanan luar angkasa",
                R.drawable.interstellar
        ));

        movieList.add(new Movie(
                "The Batman",
                "Action",
                "8.4",
                "Dark Gotham City",
                R.drawable.batman
        ));

        adapter = new MovieAdapter(movieList);

        recyclerMovie.setLayoutManager(
                new LinearLayoutManager(getContext())
        );

        recyclerMovie.setAdapter(adapter);

        return view;
    }
}