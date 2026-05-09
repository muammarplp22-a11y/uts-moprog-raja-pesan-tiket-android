package com.example.pesantiketbioskop;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView txtBookingCode;

    TextView txtMovie, txtTime, txtStudio,
            txtSnack, txtSeat, txtTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txtMovie = findViewById(R.id.txtMovie);
        txtTime = findViewById(R.id.txtTime);
        txtStudio = findViewById(R.id.txtStudio);
        txtSnack = findViewById(R.id.txtSnack);
        txtTotal = findViewById(R.id.txtTotal);
        txtSeat = findViewById(R.id.txtSeat);

        txtBookingCode =
                findViewById(R.id.txtBookingCode);

        // AMBIL DATA
        String movie = getIntent().getStringExtra("movie");
        String time = getIntent().getStringExtra("time");
        String studio = getIntent().getStringExtra("studio");
        String snack = getIntent().getStringExtra("snack");
        String seat = getIntent().getStringExtra("seat");

        int total = getIntent().getIntExtra("total", 0);

        int randomCode =
                (int)(Math.random() * 90000) + 10000;

        txtBookingCode.setText(
                "BOOK-" + randomCode
        );

        // SET DATA
        txtMovie.setText("🎬 " + movie);
        txtTime.setText("🕒 Jam: " + time);
        txtStudio.setText("🏢 Studio: " + studio);
        txtSnack.setText("🍿 Snack: " + snack);
        txtSeat.setText("💺 Seat: " + seat);
        txtTotal.setText("💰 Total: Rp " + total);
    }
}