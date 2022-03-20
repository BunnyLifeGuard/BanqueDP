package sample.Classes.Fabrique;

import sample.Classes.*;

import java.util.ArrayList;

public class ObjectFactory extends AbstractObjectFactory{

    @Override
    public Object creerObjet(String type, ArrayList<String> attributes) {
        Object object = null;
        switch (type) {
            case "Agence":
                object = new Agence(Integer.parseInt(attributes.get(0)), attributes.get(1), attributes.get(2),
                        Double.parseDouble(attributes.get(3)), attributes.get(4));
                break;
            case "Client" :
                object = new Client(Integer.parseInt(attributes.get(0)), attributes.get(1), attributes.get(2),
                        Double.parseDouble(attributes.get(3)), Integer.parseInt(attributes.get(4)), attributes.get(5));
                break;
            case "Employe" :
                object = new Employe(Integer.parseInt(attributes.get(0)), attributes.get(1), attributes.get(2),
                        attributes.get(3), Double.parseDouble(attributes.get(4)), Integer.parseInt(attributes.get(5)));

        }
        return object;
    }
}
