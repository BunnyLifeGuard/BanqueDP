package sample.Classes;

import sample.Classes.Strategie.TransactionStrategie;

import java.sql.SQLException;

public class Transaction {
    private String nom;
    private int numCompte;
    private Double montant;

    public Transaction(String nom, int numCompte, Double montant) {
        this.nom = nom;
        this.numCompte = numCompte;
        this.montant = montant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(int numCompte) {
        this.numCompte = numCompte;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "nom='" + nom + '\'' +
                ", numCompte=" + numCompte +
                ", montant=" + montant +
                '}';
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public void payer(TransactionStrategie methode) throws SQLException {
        Double montant = this.getMontant();
        Double solde = Client.getSoldeClient();
        methode.envoieArgent(montant, solde);
    }
}
