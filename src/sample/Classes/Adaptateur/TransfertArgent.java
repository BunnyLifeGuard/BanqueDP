package sample.Classes.Adaptateur;

import sample.Classes.Agence;
import sample.Classes.Client;

public interface TransfertArgent {
    void transferer(Agence agence, Client client, Client destinaire, Double montant);
}
