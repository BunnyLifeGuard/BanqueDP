package sample.Classes.Decorateur;

public class BonusCarteSim extends Bonus {
    public BonusCarteSim(Offre offre) {
        super.offre = offre;
    }

    @Override
    public double getPrix() {
        return offre.getPrix() + 10d;
    }

    @Override
    public double getPlafond() {
        return 0;
    }

}
