package sample.Classes.Observer;

import java.util.ArrayList;
import java.util.List;

public class Credit implements Sujet {
    private List<Observateur> observateurs = new ArrayList();
    private float tauxCredit;

    @Override
    public void addObserver(Observateur observer) {
        observateurs.add(observer);
    }

    @Override
    public void deleteObserver(Observateur observer) {
        observateurs.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observateur obs: observateurs) {
            obs.update(tauxCredit);
        }
    }

    public void setTauxCredit(float tauxCredit) {
        this.tauxCredit = tauxCredit;
        notifyObserver();
    }
}
