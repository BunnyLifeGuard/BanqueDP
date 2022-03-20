package sample.Classes.Decorateur;

public class BonusCarteSim extends Bonus {
    public BonusCarteSim(Offre offre) {
        super.offre = offre;
    }

    @Override
    double getPrix() {
        return offre.getPrix() + 10d;
    }

    @Override
    double getPlafond() {
        return offre.getPlafond();
    }
}
