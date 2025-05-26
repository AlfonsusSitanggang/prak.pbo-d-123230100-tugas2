package movieapp.model;

public class Movie {
    private String judul;
    private double alur;
    private double penokohan;
    private double akting;
    private double nilai;

    public Movie(String judul, double alur, double penokohan, double akting) {
        this.judul = judul;
        this.alur = alur;
        this.penokohan = penokohan;
        this.akting = akting;
        this.nilai = (alur + penokohan + akting) / 3.0;
    }

    public String getJudul() {
        return judul;
    }

    public double getAlur() {
        return alur;
    }

    public double getPenokohan() {
        return penokohan;
    }

    public double getAkting() {
        return akting;
    }

    public double getNilai() {
        return nilai;
    }
    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setAlur(double alur) {
        this.alur = alur;
        updateNilai();
    }

    public void setPenokohan(double penokohan) {
        this.penokohan = penokohan;
        updateNilai();
    }

    public void setAkting(double akting) {
        this.akting = akting;
        updateNilai();
    }

    private void updateNilai() {
        this.nilai = (alur + penokohan + akting) / 3.0;
    }
}