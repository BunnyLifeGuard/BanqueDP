package sample.Classes;

public class Employe extends Personne {

    private String cinEmploye;

    public Employe(int numeroPersonne, String nomPersonne, String prenomPersonne, double salairePersonne, int agencePersonne, String cinEmploye) {
        super(numeroPersonne, nomPersonne, prenomPersonne, salairePersonne, agencePersonne);
        this.cinEmploye = cinEmploye;
    }

    public String getCinEmploye() {
        return cinEmploye;
    }

    public void setCinEmploye(String cinEmploye) {
        this.cinEmploye = cinEmploye;
    }


}
