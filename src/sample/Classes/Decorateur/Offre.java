package sample.Classes.Decorateur;

import sample.Classes.Carte;

public abstract class Offre {
    protected double prix;
    protected double plafond;

    public abstract double getPrix();
    public abstract double getPlafond();
}
