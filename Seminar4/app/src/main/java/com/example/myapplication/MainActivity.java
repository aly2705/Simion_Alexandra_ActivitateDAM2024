package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   private List<Antrenament> antrenamente = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        antrenamente = new ArrayList<>();

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), AdaugaAntrenament.class);
                startActivityForResult(it, 345);
            }
        });

        Button btnLista = findViewById(R.id.button2);
        btnLista.setOnClickListener((View view)->{
            Intent it = new Intent(getApplicationContext(), ListaAntrenamente.class);
            it.putParcelableArrayListExtra("antrenamente", (ArrayList<? extends Parcelable>) antrenamente);
            startActivity(it);
        });

        Button btnOnlineWorkouts = findViewById(R.id.button3);
        btnOnlineWorkouts.setOnClickListener((View view)->{
            Intent it = new Intent(getApplicationContext(), ListaOnlineWorkouts.class);
            startActivity(it);
        });

        Button btnSituatii = findViewById(R.id.button4);
        btnSituatii.setOnClickListener((View view)->{
            Intent it = new Intent(getApplicationContext(), JsonParsingActivity.class);
            startActivity(it);
        });

        Button btnFav = findViewById(R.id.button5);
        btnFav.setOnClickListener((View view)->{
            Intent it = new Intent(getApplicationContext(), ListaFavorite.class);
            startActivity(it);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 345){
            if(resultCode == RESULT_OK){
                Antrenament antrenament = data.getParcelableExtra("antrenament");
                antrenamente.add(antrenament);
                Toast.makeText(getApplicationContext(), antrenament.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
}