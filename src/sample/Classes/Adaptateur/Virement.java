package sample.Classes.Adaptateur;

import sample.Classes.Agence;
import sample.Classes.Client;

public class Virement implements TransfertArgent {

    public Virement(Agence agence, Client client, Client client2, Double montant) {
        transferer(agence, client, client2, montant);
    }

    @Override
    public void transferer(Agence agence, Client client1, Client client2, Double montant) {
        client1.setSoldeClient(client1.getSoldeClient() - montant);
        client1.setSoldeClient(client1.getSoldeClient() + montant);
    }
}
