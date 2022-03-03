package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerSettings implements Initializable {
    @FXML
    private TextField currentPasswordField, newPasswordField, confirmPasswordField;
    @FXML
    private Button saveButton;
    @FXML
    private Label errorLabel, messageLabel;

    private int numCompte = ControllerLogin.numCompte;
    private static Connection con;
    private static PreparedStatement pst ,pst2;
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
            pst = con.prepareStatement("SELECT motDePasse from client where numCompte = ?");
            pst2 = con.prepareStatement("UPDATE client set motDePasse = ? where numCompte = ?");
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void Enregistrer() throws SQLException {
        if((currentPasswordField.getText()).equals("") || (newPasswordField.getText()).equals("") ||
                (currentPasswordField.getText()).equals("")) {
            errorLabel.setText("Veuillez remplir tous les champs");
        }
        else {
            pst.setInt(1, numCompte);
            rs = pst.executeQuery();
            if (rs.next()) {
                if (rs.getString("motDePasse").equals(currentPasswordField.getText())) {
                    if ((newPasswordField.getText()).equals(confirmPasswordField.getText())) {
                        pst2.setString(1, newPasswordField.getText());
                        pst2.setInt(2, numCompte);
                        pst2.executeUpdate();
                        messageLabel.setText("Mot de passe changé avec succès");
                        errorLabel.setVisible(false);
                    } else {
                        errorLabel.setText("Les mots de passe ne se rassemblent pas");
                    }
                } else {
                    errorLabel.setText("Votre mot de passe est incorrecte");
                }
            }
        }

    }
}
