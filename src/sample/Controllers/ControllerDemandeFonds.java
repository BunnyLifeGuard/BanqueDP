package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerDemandeFonds implements Initializable {

    @FXML
    private TextField montantField;
    @FXML
    private Label fondLabel, errorLabel, messageLabel;
    private int numDirecteur = ControllerLogin.codeDirecteur;
    private int numAgence = 5;
    private static Connection con;
    private static PreparedStatement pst, pst2, pst3;
    private static ResultSet rs;

    static {
        try {
            con =  sample.ConnectionDB.connectionBase.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            pst = con.prepareStatement("SELECT plafondAgence, numAgence from agence where directeurAgence = ?");
            pst2 = con.prepareStatement("INSERT INTO fond (numAgence, Montant) VALUES (?, ?)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            pst.setInt(1, numDirecteur);
            rs = pst.executeQuery();
            if (rs.next()) {
                numAgence = rs.getInt("numAgence");
                double fond = rs.getDouble("plafondAgence");
                fondLabel.setText(String.valueOf(fond));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Valider() throws SQLException {
        if (Double.parseDouble(montantField.getText()) > 500000) {
            errorLabel.setText("Le montant ne doit dépasser 500000 DHS");
        } else {
            pst.setInt(1, numDirecteur);
            rs = pst.executeQuery();
            if (rs.next()) {
                numAgence = rs.getInt("numAgence");
                double fond = rs.getDouble("plafondAgence");
                fondLabel.setText(String.valueOf(fond));
                if (fond >= 200000) {
                    errorLabel.setText("Veuillez lire la note");
                } else {
                    pst2.setInt(1, numAgence);
                    pst2.setDouble(2, Double.parseDouble(montantField.getText()));
                    pst2.execute();
                    messageLabel.setText("Demande enregistré avec succès");
                }
            }
        }
    }
}
