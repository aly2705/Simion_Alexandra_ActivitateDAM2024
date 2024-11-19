package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ListaOnlineWorkouts extends AppCompatActivity {
    List<Bitmap> imagini = null;
    List<ItemImagine> imgItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_online_workouts);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<String> linkuriImagini = new ArrayList<>();
        imagini = new ArrayList<>();
        // Cardio
        linkuriImagini.add("https://imgs.search.brave.com/glwwHZ2cXaQRlxB_f4RV7QSMTb0mNeRtSvTB_KorzOw/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9pbWcu/ZnJlZXBpay5jb20v/ZnJlZS1waG90by95/b3VuZy13b21hbi1l/eGVyY2lzaW5nLWVs/bGlwdGljYWwtY2Fy/ZGlvLW1hY2hpbmVf/MjMtMjE0NzgyNzg4/MC5qcGc_c2l6ZT02/MjYmZXh0PWpwZw");
        // Legs
        linkuriImagini.add("https://imgs.search.brave.com/g0Gjx-k-Ro_2y46ItYJbzh8aLA0KQtAPuidsWc5XoFk/rs:fit:500:0:0:0/g:ce/aHR0cHM6Ly9tZWRp/YS5nZXR0eWltYWdl/cy5jb20vaWQvNjIy/ODA5MjgwL3Bob3Rv/L2RvLW5vdC1za2lw/LWxlZy1kYXkuanBn/P3M9NjEyeDYxMiZ3/PTAmaz0yMCZjPXR6/bGlCNDdXeTJjRVlw/RXZnRGdCRWdzdlRw/dkRUc29aRzdhdW4x/OHN3U2M9");
        // Push
        linkuriImagini.add("https://imgs.search.brave.com/CLT8nzN1O0KE6RrBuupOV7YB1EybSC-Js4u4AGbW_Io/rs:fit:500:0:0:0/g:ce/aHR0cHM6Ly9pbWcu/ZnJlZXBpay5jb20v/cHJlbWl1bS1waG90/by95b3VuZy1hZnJp/Y2FuLWFtZXJpY2Fu/LXNwb3J0LXdvbWFu/LXdoaXRlLXdhbGwt/bWFraW5nLXdlaWdo/dGxpZnRpbmdfMTM2/OC05NjQxOC5qcGc_/c2VtdD1haXNfaHli/cmlk");
        // Pull
        linkuriImagini.add("https://imgs.search.brave.com/mG7jgD3tqSapgHcmMqjJkgOXfi_-hmnSWI2L-xasXbc/rs:fit:500:0:0:0/g:ce/aHR0cHM6Ly9pbWcu/ZnJlZXBpay5jb20v/ZnJlZS1waG90by9i/YWNrLXZpZXctd29t/YW4tcGVyZm9ybWlu/Zy1wdWxsLXVwc18y/My0yMTQ3Nzg5NTcy/LmpwZz9zaXplPTYy/NiZleHQ9anBn");
        // Swimming
        linkuriImagini.add("https://img.freepik.com/free-photo/back-view-woman-exercising-with-dumbbells_23-2147789670.jpg?semt=ais_hybrid");


        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                for(String link:linkuriImagini){
                    HttpURLConnection connection = null;
                    try {
                        URL url = new URL(link);
                        connection = (HttpURLConnection) url.openConnection();

                        InputStream is = connection.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(is));
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imgItems = new ArrayList<>();
                        imgItems.add(new ItemImagine("Cardio Workout", imagini.get(0), "https://www.medicalnewstoday.com/articles/cardio-exercises-at-home"));
                        imgItems.add(new ItemImagine("Legs Workout", imagini.get(1), "https://www.barbellmedicine.com/blog/best-leg-day-workout-exercises/"));
                        imgItems.add(new ItemImagine("Push Workout", imagini.get(2), "https://www.strengthlog.com/push-day-workout/"));
                        imgItems.add(new ItemImagine("Pull Workout", imagini.get(3), "https://www.strengthlog.com/pull-day-workout/"));
                        imgItems.add(new ItemImagine("Back Workout", imagini.get(4), "https://www.tuasaude.com/en/back-workout/"));

                        ListView lv = findViewById(R.id.lv_online_workouts);
                        ImagineAdapter adapter = new ImagineAdapter(getApplicationContext(), R.layout.imagine_list_item, imgItems);
                        lv.setAdapter(adapter);
                    }
                });
            }
        });


        ListView lv = findViewById(R.id.lv_online_workouts);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getApplicationContext(), WebViewActivity.class);
                it.putExtra("link", imgItems.get(position).getLink());
                startActivity(it);
            }
        });

    }
}