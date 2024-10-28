package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;

public class AdaugaAntrenament extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_antrenament);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button submitBtn = findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener((view) -> {
            // De completat
            EditText etNrExercitii = findViewById(R.id.nrExercitii);
            int nrExercitii = Integer.parseInt(etNrExercitii.getText().toString());

            EditText etDurata = findViewById(R.id.minute);
            int minute = Integer.parseInt(etDurata.getText().toString());

            Spinner spZi = findViewById(R.id.zi);
            String zi = spZi.getSelectedItem().toString();

            EditText etFocus = findViewById(R.id.focus);
            String focus = etFocus.getText().toString();

            DatePicker dp = findViewById(R.id.data);
            Date data = new Date(dp.getYear(), dp.getMonth(), dp.getDayOfMonth());

            Antrenament antrenament = new Antrenament(nrExercitii, zi, minute, focus, data);

            Intent it = new Intent();
            it.putExtra("antrenament", antrenament);
            setResult(RESULT_OK, it);
            finish();
        });
    }
}