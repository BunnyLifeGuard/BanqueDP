package sample.Classes;

import java.util.Date;

public class Client extends Personne{

    private String dateRejointClient;

    public Client(int numeroPersonne, String nomPersonne, String prenomPersonne, double salairePersonne, int agencePersonne, String dateRejointClient) {
        super(numeroPersonne, nomPersonne, prenomPersonne, salairePersonne, agencePersonne);
        this.dateRejointClient = dateRejointClient;

    }

    public String getDateRejointClient() {
        return dateRejointClient;
    }

    public void setDateRejointClient(String dateRejointClient) {
        this.dateRejointClient = dateRejointClient;
    }
}
