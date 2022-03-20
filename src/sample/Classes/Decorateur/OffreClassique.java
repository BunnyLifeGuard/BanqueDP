package sample.Classes.Decorateur;

import sample.Classes.Carte;

public class OffreClassique extends Offre {

    public OffreClassique(Double prix, Double plafond) {
        super.prix = prix;
        super.plafond = plafond;
    }

    @Override
    public double getPrix() {
        return super.prix;
    }

    @Override
    public double getPlafond() {
        return super.plafond;
    }
}
