package sample.Classes;

public class Carte {
    private Client client;
    private String numCarte;
    private String codeCarte;

    public Carte(Client client, String numCarte, String codeCarte) {
        this.client = client;
        this.numCarte = numCarte;
        this.codeCarte = codeCarte;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getNumCarte() {
        return numCarte;
    }

    public void setNumCarte(String numCarte) {
        this.numCarte = numCarte;
    }

    public String getCodeCarte() {
        return codeCarte;
    }

    public void setCodeCarte(String codeCarte) {
        this.codeCarte = codeCarte;
    }
}
