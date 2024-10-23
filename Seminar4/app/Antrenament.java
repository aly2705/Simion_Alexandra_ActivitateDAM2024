import java.util.Date;

public class Antrenament {
    private String ziSaptamana;
    private Date data;
    private int minute;
    private int nrExercitii;
    private String focus;

    public Antrenament() {
        this.ziSaptamana = "Luni";
        this.data = new Date();
        this.minute = 45;
        this.nrExercitii = 4;
        this.focus = "Legs";
    }

    public Antrenament(String ziSaptamana, Date data, int minute, int nrExercitii, String focus) {
        this.ziSaptamana = ziSaptamana;
        this.data = data;
        this.minute = minute;
        this.nrExercitii = nrExercitii;
        this.focus = focus;
    }

    public String getZiSaptamana() {
        return ziSaptamana;
    }

    public void setZiSaptamana(String ziSaptamana) {
        this.ziSaptamana = ziSaptamana;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getNrExercitii() {
        return nrExercitii;
    }

    public void setNrExercitii(int nrExercitii) {
        this.nrExercitii = nrExercitii;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Antrenament{");
        sb.append("ziSaptamana='").append(ziSaptamana).append('\'');
        sb.append(", data=").append(data);
        sb.append(", minute=").append(minute);
        sb.append(", nrExercitii=").append(nrExercitii);
        sb.append(", focus='").append(focus).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
