package sample.Classes;

public class Agence {

    private int numAgence;
    private String nomAgence;
    private String directeurAgence;
    private Double plafondAgence;
    private String dateAgence;

    public Agence(int numAgence, String nomAgence, String directeurAgence, Double plafondAgence, String dateAgence) {
        this.numAgence = numAgence;
        this.nomAgence = nomAgence;
        this.directeurAgence = directeurAgence;
        this.plafondAgence = plafondAgence;
        this.dateAgence = dateAgence;
    }

    public int getNumAgence() {
        return numAgence;
    }

    public void setNumAgence(int numAgence) {
        this.numAgence = numAgence;
    }

    public String getNomAgence() {
        return nomAgence;
    }

    public void setNomAgence(String nomAgence) {
        this.nomAgence = nomAgence;
    }

    public String getDirecteurAgence() {
        return directeurAgence;
    }

    public void setDirecteurAgence(String directeurAgence) {
        this.directeurAgence = directeurAgence;
    }

    public Double getPlafondAgence() {
        return plafondAgence;
    }

    public void setPlafondAgence(Double plafondAgence) {
        this.plafondAgence = plafondAgence;
    }

    public String getDateAgence() {
        return dateAgence;
    }

    public void setDateAgence(String dateAgence) {
        this.dateAgence = dateAgence;
    }
}
