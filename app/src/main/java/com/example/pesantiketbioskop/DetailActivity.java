package com.example.pesantiketbioskop;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;
import android.widget.ToggleButton;

import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    ImageView imgDetail;
    TextView txtTitle, txtGenre, txtRating, txtDescription;

    Spinner spinnerTime;
    RadioGroup radioStudio;

    CheckBox checkPopcorn, checkDrink;

    Button btnBook, btnImdb, btnNetflix;
    ToggleButton seatA1, seatA2, seatA3,
            seatB1, seatB2, seatB3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        seatA1 = findViewById(R.id.seatA1);
        seatA2 = findViewById(R.id.seatA2);
        seatA3 = findViewById(R.id.seatA3);

        seatB1 = findViewById(R.id.seatB1);
        seatB2 = findViewById(R.id.seatB2);
        seatB3 = findViewById(R.id.seatB3);

        imgDetail = findViewById(R.id.imgDetail);
        txtTitle = findViewById(R.id.txtDetailTitle);
        txtGenre = findViewById(R.id.txtDetailGenre);
        txtRating = findViewById(R.id.txtDetailRating);
        txtDescription = findViewById(R.id.txtDescription);

        spinnerTime = findViewById(R.id.spinnerTime);
        radioStudio = findViewById(R.id.radioStudio);

        checkPopcorn = findViewById(R.id.checkPopcorn);
        checkDrink = findViewById(R.id.checkDrink);

        btnBook = findViewById(R.id.btnBook);

        btnImdb = findViewById(R.id.btnImdb);
        btnNetflix = findViewById(R.id.btnNetflix);

        btnImdb.setOnClickListener(v -> {

            Intent intent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.imdb.com")
            );

            startActivity(intent);
        });

        btnNetflix.setOnClickListener(v -> {

            Intent intent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.netflix.com")
            );

            startActivity(intent);
        });

        // AMBIL DATA
        String title = getIntent().getStringExtra("title");
        String genre = getIntent().getStringExtra("genre");
        String rating = getIntent().getStringExtra("rating");
        String description = getIntent().getStringExtra("description");
        int image = getIntent().getIntExtra("image", 0);

        // SET DATA
        txtTitle.setText(title);
        txtGenre.setText(genre);
        txtRating.setText("⭐ " + rating);
        txtDescription.setText(description);
        imgDetail.setImageResource(image);

        // SPINNER JAM
        String[] times = {
                "10:00",
                "13:00",
                "16:00",
                "19:00"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        times
                );

        spinnerTime.setAdapter(adapter);

        btnBook.setOnClickListener(v -> {

            // VALIDASI
            if (radioStudio.getCheckedRadioButtonId() == -1) {

                Toast.makeText(
                        this,
                        "Pilih studio terlebih dahulu!",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            // AMBIL JAM
            String time =
                    spinnerTime.getSelectedItem().toString();

            // AMBIL RADIO BUTTON
            int selectedId =
                    radioStudio.getCheckedRadioButtonId();

            RadioButton selectedRadio =
                    findViewById(selectedId);

            String studio =
                    selectedRadio.getText().toString();

            // SNACK
            String snack = "";

            int total = 50000;

            if (checkPopcorn.isChecked()) {
                snack += "Popcorn ";
                total += 25000;
            }

            if (checkDrink.isChecked()) {
                snack += "Soft Drink";
                total += 15000;
            }

            if (snack.isEmpty()) {
                snack = "Tidak ada";
            }

            String selectedSeat = "";

            if (seatA1.isChecked()) selectedSeat += "A1 ";
            if (seatA2.isChecked()) selectedSeat += "A2 ";
            if (seatA3.isChecked()) selectedSeat += "A3 ";

            if (seatB1.isChecked()) selectedSeat += "B1 ";
            if (seatB2.isChecked()) selectedSeat += "B2 ";
            if (seatB3.isChecked()) selectedSeat += "B3 ";

            if (selectedSeat.isEmpty()) {

                Toast.makeText(
                        this,
                        "Pilih minimal 1 kursi!",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            // PINDAH KE RESULT
            Intent intent = new Intent(
                    DetailActivity.this,
                    ResultActivity.class
            );

            intent.putExtra("seat", selectedSeat);

            intent.putExtra("movie", title);
            intent.putExtra("time", time);
            intent.putExtra("studio", studio);
            intent.putExtra("snack", snack);
            intent.putExtra("total", total);

            SharedPreferences preferences =
                    getSharedPreferences(
                            "CineTIX",
                            MODE_PRIVATE
                    );

            SharedPreferences.Editor editor =
                    preferences.edit();

            editor.putString("lastMovie", title);
            editor.putString("lastSeat", selectedSeat);

            editor.apply();

            startActivity(intent);
        });
    }
}