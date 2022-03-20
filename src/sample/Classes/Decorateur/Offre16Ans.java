package sample.Classes.Decorateur;

import sample.Classes.Carte;

public class Offre16Ans extends Offre {

    public Offre16Ans(Double prix, Double plafond) {
        super.prix = 2d;
        super.plafond = 500d;
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
