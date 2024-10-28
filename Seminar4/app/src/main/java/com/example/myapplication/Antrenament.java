package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Antrenament implements Parcelable {
    private String ziSapt;
    private int nrExercitii;
    private int durata;
    private String focus;
    private Date data;

    public Antrenament() {
        this.nrExercitii = 5;
        this.ziSapt = "Luni";
        this.durata = 45;
        this.focus = "Legs";
        this.data = new Date();
    }

    public Antrenament(int nrExercitii, String ziSapt, int durata, String focus, Date data) {
        this.nrExercitii = nrExercitii;
        this.ziSapt = ziSapt;
        this.durata = durata;
        this.focus = focus;
        this.data = data;
    }

    protected Antrenament(Parcel in) {
        ziSapt = in.readString();
        nrExercitii = in.readInt();
        durata = in.readInt();
        focus = in.readString();
        data=(java.util.Date)in.readSerializable();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ziSapt);
        dest.writeInt(nrExercitii);
        dest.writeInt(durata);
        dest.writeString(focus);
        dest.writeSerializable(data);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Antrenament> CREATOR = new Creator<Antrenament>() {
        @Override
        public Antrenament createFromParcel(Parcel in) {
            return new Antrenament(in);
        }

        @Override
        public Antrenament[] newArray(int size) {
            return new Antrenament[size];
        }
    };

    public String getZiSapt() {
        return ziSapt;
    }

    public void setZiSapt(String ziSapt) {
        this.ziSapt = ziSapt;
    }

    public int getNrExercitii() {
        return nrExercitii;
    }

    public void setNrExercitii(int nrExercitii) {
        this.nrExercitii = nrExercitii;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Antrenament{");
        sb.append("ziSapt='").append(ziSapt).append('\'');
        sb.append(", nrExercitii=").append(nrExercitii);
        sb.append(", durata=").append(durata);
        sb.append(", focus='").append(focus).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
