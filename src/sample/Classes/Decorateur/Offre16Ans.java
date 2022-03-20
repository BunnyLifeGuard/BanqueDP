package sample.Classes.Decorateur;

import sample.Classes.Carte;

public class Offre16Ans extends Offre {

    public Offre16Ans(Double prix, Double plafond) {
        super.prix = prix;
        super.plafond = plafond;
    }

    @Override
    double getPrix() {
        return super.prix;
    }

    @Override
    double getPlafond() {
        return super.plafond;
    }
}
