package sample.Classes.Decorateur;

import sample.Classes.Carte;

public class OffreClassique extends Offre {

    public OffreClassique() {
        super.prix = 12d;
        super.plafond = 2000d;
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
