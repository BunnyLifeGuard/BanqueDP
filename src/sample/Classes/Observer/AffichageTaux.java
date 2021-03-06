package sample.Classes.Observer;

public class AffichageTaux implements Observateur{

    private float tauxActuel = 0.05f;
    private float ancienTaux;
    private Credit credit;

    public AffichageTaux(Credit credit) {
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
        if(tauxActuel != ancienTaux) {
            System.out.println("Le taux du crédit a été changer de " + ancienTaux + " a " + tauxActuel);
        }
        else {
            System.out.println("Le taux n'a pas changé");
        }
    }
}
