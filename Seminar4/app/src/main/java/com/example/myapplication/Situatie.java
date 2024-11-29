package com.example.myapplication;

public class Situatie {
    private String disciplina;
    private String activitate;

    private  int valoare;
    private double pondere;
    private String data;
    private String descriere;

    public Situatie(String disciplina, String activitate, int valoare, double pondere, String data, String descriere) {
        this.disciplina = disciplina;
        this.activitate = activitate;
        this.valoare = valoare;
        this.pondere = pondere;
        this.data = data;
        this.descriere = descriere;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getActivitate() {
        return activitate;
    }

    public void setActivitate(String activitate) {
        this.activitate = activitate;
    }

    public int getValoare() {
        return valoare;
    }

    public void setValoare(int valoare) {
        this.valoare = valoare;
    }

    public double getPondere() {
        return pondere;
    }

    public void setPondere(double pondere) {
        this.pondere = pondere;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Situatie{");
        sb.append("disciplina='").append(disciplina).append('\'');
        sb.append(", activitate='").append(activitate).append('\'');
        sb.append(", valoare=").append(valoare);
        sb.append(", pondere=").append(pondere);
        sb.append(", data='").append(data).append('\'');
        sb.append(", descriere='").append(descriere).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
