package sample.Classes;

import sample.Classes.Decorateur.BonusCarteSim;
import sample.Classes.Decorateur.Offre;
import sample.Classes.Decorateur.Offre16Ans;

public class Test {
    public static void main(String[] args) {
        Offre offre = new Offre16Ans(25d, 2500d);
        System.out.println(offre.getPrix());
        offre = new BonusCarteSim(offre);
        System.out.println(offre.getPrix());
    }
}
