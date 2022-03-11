package sample.Classes;

public class Employe {

    private int numEmploye;
    private String nomEmploye;
    private String prenomEmploye;
    private String cinEmploye;
    private Double salaireEmploye;
    private int agenceEmploye;

    public Employe(int numEmploye, String nomEmploye, String prenomEmploye, String cinEmploye, Double salaireEmploye, int agenceEmploye) {
        this.numEmploye = numEmploye;
        this.nomEmploye = nomEmploye;
        this.prenomEmploye = prenomEmploye;
        this.cinEmploye = cinEmploye;
        this.salaireEmploye = salaireEmploye;
        this.agenceEmploye = agenceEmploye;
    }

    public int getNumEmploye() {
        return numEmploye;
    }

    public void setNumEmploye(int numEmploye) {
        this.numEmploye = numEmploye;
    }

    public String getNomEmploye() {
        return nomEmploye;
    }

    public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }

    public String getPrenomEmploye() {
        return prenomEmploye;
    }

    public void setPrenomEmploye(String prenomEmploye) {
        this.prenomEmploye = prenomEmploye;
    }

    public String getCinEmploye() {
        return cinEmploye;
    }

    public void setCinEmploye(String cinEmploye) {
        this.cinEmploye = cinEmploye;
    }

    public Double getSalaireEmploye() {
        return salaireEmploye;
    }

    public void setSalaireEmploye(Double salaireEmploye) {
        this.salaireEmploye = salaireEmploye;
    }

    public int getAgenceEmploye() {
        return agenceEmploye;
    }

    public void setAgenceEmploye(int agenceEmploye) {
        this.agenceEmploye = agenceEmploye;
    }
}
