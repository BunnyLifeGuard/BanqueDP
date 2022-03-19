package sample.Classes.Adaptateur;

import sample.Classes.Agence;
import sample.Classes.Client;

public interface GestionPrelevement {
    void prelevement(Agence agence1, Agence agence2, Client client, Double montant);
}
