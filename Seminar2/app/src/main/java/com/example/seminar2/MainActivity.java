package com.example.seminar2;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        Log.v("my-activity", "S-a creat in memorie");
    }

    public MainActivity() {
        super();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("my-activity", "E afisata, dar inactiva");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("my-activity", "Rulam din background");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("my-activity", "Inactiv acum! A aparut alta activitate!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.wtf("my-activity", "E activaaaaaa!");

        // Adaugam Toast
        Toast.makeText(this,  R.string.mesaj, Toast.LENGTH_LONG).show();
    }
}