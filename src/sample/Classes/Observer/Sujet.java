package sample.Classes.Observer;

public interface Sujet {
    public void addObserver(Observateur observer);
    public void deleteObserver(Observateur observer);
    public void notifyObserver();
}
