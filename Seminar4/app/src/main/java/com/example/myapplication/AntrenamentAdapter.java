package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class AntrenamentAdapter extends BaseAdapter {
    private List<Antrenament> antrenamente = null;
    private Context ctx;
    private int resursaLayout;

    public AntrenamentAdapter(List<Antrenament> antrenamente, Context ctx, int resursaLayout) {
        this.antrenamente = antrenamente;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return antrenamente.size();
    }

    @Override
    public Object getItem(int i) {
        return antrenamente.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout, viewGroup, false);

        TextView focusTV = v.findViewById(R.id.focusData);
        TextView ziSaptTV = v.findViewById(R.id.ziSaptData);
        TextView dataTV = v.findViewById(R.id.dataData);
        TextView nrExercitiiTV = v.findViewById(R.id.nrExercitiiData);
        TextView durataTV = v.findViewById(R.id.durataData);

        Antrenament antrenament = (Antrenament)getItem(i);

        focusTV.setText(antrenament.getFocus());
        ziSaptTV.setText(antrenament.getZiSapt());

        Date data = antrenament.getData();
        StringBuilder sb = new StringBuilder();
        sb.append(data.getDate()).append("-").append(data.getMonth()).append("-").append(data.getYear());
        dataTV.setText(sb.toString());
        nrExercitiiTV.setText(String.valueOf(antrenament.getNrExercitii()));
        durataTV.setText(String.valueOf(antrenament.getDurata()));

        return v;
    }
}
