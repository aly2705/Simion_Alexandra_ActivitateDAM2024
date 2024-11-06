package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class ListaAntrenamente extends AppCompatActivity {
    private AntrenamentAdapter adapter = null;
    private int idModificat = 0;

    List<Antrenament> antrenamente = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_antrenamente);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent it = getIntent();
        antrenamente = it.getParcelableArrayListExtra("antrenamente");

        ListView lv = findViewById(R.id.lista);

        adapter = new AntrenamentAdapter(antrenamente, getApplicationContext(), R.layout.list_item_antrenament);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent modificaAntrenamentIntent = new Intent(getApplicationContext(), AdaugaAntrenament.class);
                modificaAntrenamentIntent.putExtra("antrenament" , antrenamente.get(position));
                int idModificat = position;
                startActivityForResult(modificaAntrenamentIntent, 234);



            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                antrenamente.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode==234){
            antrenamente.set(idModificat, data.getParcelableExtra("antrenament"));
            adapter.notifyDataSetChanged();
        }
    }
}