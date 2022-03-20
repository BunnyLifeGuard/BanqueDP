package sample.Classes.Observer;

public class ObserverSimulatorMain {
    public static void main(String[] args) {
        Credit credit = new Credit();
        AffichageTauxEmploye affichageTauxEmploye = new AffichageTauxEmploye(credit);
        AffichageTaux affichageTaux = new AffichageTaux(credit);
        System.out.println("************************************************************");
        credit.setTauxCredit(5.45f);

    }

}
