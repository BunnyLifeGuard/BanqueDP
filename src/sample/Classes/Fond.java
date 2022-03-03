package sample.Classes;

public class Fond {
    private int numAgence;
    private double montant;
    private double plafondActuel;
    private String etat;

    public Fond(int numAgence, double montant, double plafondActuel, String etat) {
        this.numAgence = numAgence;
        this.montant = montant;
        this.plafondActuel = plafondActuel;
        this.etat = etat;
    }

    public int getNumAgence() {
        return numAgence;
    }

    public void setNumAgence(int numAgence) {
        this.numAgence = numAgence;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getPlafondActuel() {
        return plafondActuel;
    }

    public void setPlafondActuel(double plafondActuel) {
        this.plafondActuel = plafondActuel;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
