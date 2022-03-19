package sample.Classes.Strategie;

import java.sql.SQLException;

public interface TransactionStrategie {
    public void envoieArgent(Double montant, Double solde) throws SQLException;
}
