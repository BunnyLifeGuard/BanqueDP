package sample.Classes.Observer;

public class AffichageTauxEmploye implements Observateur{
    private float tauxActuel = 0.02f;
    private float ancienTaux;
    private Credit credit;

    public AffichageTauxEmploye(Credit credit) {
        this.credit = credit;
        credit.addObserver(this);
    }

    @Override
    public void update(float tauxCredit) {
        ancienTaux = tauxActuel;
        tauxActuel = tauxCredit;
        afficher();
    }

    public void afficher() {
        System.out.println("Le taux du crédit a été changer de " + ancienTaux + " a " + tauxActuel);
    }
}
