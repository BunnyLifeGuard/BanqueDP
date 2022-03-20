package sample.Classes.Adaptateur;

import sample.Classes.Agence;
import sample.Classes.Client;

public class Prelevement implements GestionPrelevement {
    @Override
    public void prelevement(Agence agence1, Agence agence2, Client client, Double montant) {
        client.setSoldeClient(client.getSoldeClient() - montant);
    }
}
