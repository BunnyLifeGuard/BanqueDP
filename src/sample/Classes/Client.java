package sample.Classes;

import java.util.Date;

public class Client {
    private int numCompte;
    private String nomClient;
    private String prenomClient;
    private static Double soldeClient;
    private int agenceClient;
    private String dateRejointClient;

    public Client(int numCompte, String nomClient, String prenomClient, Double soldeClient, int agenceClient, String dateRejointClient) {
        this.numCompte = numCompte;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.soldeClient = soldeClient;
        this.agenceClient = agenceClient;
        this.dateRejointClient = dateRejointClient;
    }

    public int getAgenceClient() {
        return agenceClient;
    }

    public void setAgenceClient(int agenceClient) {
        this.agenceClient = agenceClient;
    }

    public int getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(int numCompte) {
        this.numCompte = numCompte;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    static public Double getSoldeClient() {
        return soldeClient;
    }

    public void setSoldeClient(Double soldeClient) {
        this.soldeClient = soldeClient;
    }

    public String getDateRejointClient() {
        return dateRejointClient;
    }

    public void setDateRejointClient(String dateRejointClient) {
        this.dateRejointClient = dateRejointClient;
    }
}
