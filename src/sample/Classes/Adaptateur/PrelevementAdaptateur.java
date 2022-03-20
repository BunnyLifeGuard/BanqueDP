package sample.Classes.Adaptateur;

import sample.Classes.Agence;
import sample.Classes.Client;

public class PrelevementAdaptateur implements TransfertArgent {
    private final Prelevement prelevement;
    private final Agence agence2;

    public PrelevementAdaptateur(Prelevement prelevement, Agence agence2) {
        this.prelevement = prelevement;
        this.agence2 = agence2;
    }

    @Override
    public void transferer(Agence agence, Client client, Client destinataire, Double montant) {
        prelevement.prelevement(agence, agence2, client, montant);
    }
}
