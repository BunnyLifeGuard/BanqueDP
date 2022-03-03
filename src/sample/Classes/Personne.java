package sample.Classes;

public abstract class Personne {
    protected int numeroPersonne;
    protected String nomPersonne;
    protected String prenomPersonne;
    protected double salairePersonne;
    protected int agencePersonne;

    public Personne(int numeroPersonne, String nomPersonne, String prenomPersonne, double salairePersonne, int agencePersonne) {
        this.numeroPersonne = numeroPersonne;
        this.nomPersonne = nomPersonne;
        this.prenomPersonne = prenomPersonne;
        this.salairePersonne = salairePersonne;
        this.agencePersonne = agencePersonne;
    }
}
