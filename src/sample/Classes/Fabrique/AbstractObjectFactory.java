package sample.Classes.Fabrique;

import java.util.ArrayList;

public abstract class AbstractObjectFactory {
    public abstract Object creerObjet(String type, ArrayList<String> attributes);
}
