package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AdaugaAntrenament extends AppCompatActivity {
    public AntrenamentDatabase database = null;

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
        Intent startedFromIntent = getIntent();
        if(startedFromIntent.hasExtra("antrenament")){
            Antrenament a = startedFromIntent.getParcelableExtra("antrenament");
            Spinner spZi = findViewById(R.id.zi);
            EditText etNrExercitii = findViewById(R.id.nrExercitii);
            EditText etDurata = findViewById(R.id.minute);
            EditText etFocus = findViewById(R.id.focus);
            DatePicker dp = findViewById(R.id.data);

            String[] zileArray = getResources().getStringArray(R.array.zileSapt);
            int desiredPosition = Arrays.asList(zileArray).indexOf(a.getZiSapt());
            spZi.setSelection(desiredPosition);

            etNrExercitii.setText(String.valueOf(a.getNrExercitii()));
            etDurata.setText(String.valueOf(a.getDurata()));
            etFocus.setText(a.getFocus());
            dp.init(a.getData().getYear(), a.getData().getMonth(), a.getData().getDate(), null);
        }
        FirebaseDatabase databaseFB = FirebaseDatabase.getInstance();
        DatabaseReference myRef = databaseFB.getReference("");

        database = Room.databaseBuilder(getApplicationContext(), AntrenamentDatabase.class, "AntrenamentDatabase").build();

        Button submitBtn = findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener((view) -> {
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

            CheckBox cb = findViewById(R.id.checkOnline);
            if(cb.isChecked()){
                Log.d("buggy debuggy", "Hello i guess");
                Toast.makeText(getApplicationContext(), antrenament.toString(), Toast.LENGTH_SHORT).show();
                myRef.child("antrenamente").child(String.valueOf(antrenament.getId())).setValue(antrenament);
            }

            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        FileOutputStream fs = openFileOutput("antrenamente.txt", MODE_PRIVATE);
                        OutputStreamWriter out = new OutputStreamWriter(fs);
                        BufferedWriter writer = new BufferedWriter(out);
                        writer.write(antrenament.toString());


                        writer.close();
                        out.close();
                        fs.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    database.antrenamentDAO().insertAntrenament(antrenament);
                }
            });

            Intent it = new Intent();
            it.putExtra("antrenament", antrenament);
            setResult(RESULT_OK, it);
            finish();
        });
    }
}