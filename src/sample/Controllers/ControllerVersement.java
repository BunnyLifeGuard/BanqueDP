package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Classes.Strategie.TransactionStrategie;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ControllerVersement implements Initializable, TransactionStrategie {

    @FXML
    private Label labelNom, labelPrenom, labelMontant, labelNum, labelPassword, labelSolde, errorLabel;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField numField;
    @FXML
    private TextField montantField;
    @FXML
    private static TextField passwordField;
    @FXML
    private Button validerButton;
    static int numCompte = ControllerLogin.numCompte;


    private static Connection con;
    private static PreparedStatement pst, pst2, pst3, pst4, pst5;
    private static ResultSet rs, rs2;

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
            pst = con.prepareStatement("SELECT * from client where numCompte = ? AND motDePasse = ?");
            pst2 = con.prepareStatement("SELECT * from client where numCompte = ? AND nomClient = ? AND prenomClient = ?");
            pst3 = con.prepareStatement("update client set soldeClient = soldeClient + ? where numCompte = ?");
            pst4 = con.prepareStatement("update client set soldeClient = soldeClient - ? where numCompte = ?");
            pst5 = con.prepareStatement("Insert into historique (source, destination, montant, dateTransaction) values (?, ?, ?, ?)");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            pst.setInt(1, numCompte);
            pst.setString(2, passwordField.getText());
            rs = pst.executeQuery();
            if(rs.next()) {
                Double solde = rs.getDouble("soldeClient");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void envoieArgent(Double montant, Double solde) throws SQLException {
        pst2.setInt(1, Integer.parseInt(numField.getText()));
        pst2.setString(2, nomField.getText());
        pst2.setString(3, prenomField.getText());
        rs2 = pst2.executeQuery();
        if(rs2.next()) {
            if(solde > montant) {
                pst3.setDouble(1, montant);
                pst3.setInt(2, Integer.parseInt(numField.getText()));
                pst3.executeUpdate();
                pst4.setDouble(1, montant);
                pst4.setInt(2, numCompte);
                pst4.executeUpdate();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
                LocalDate localDate = LocalDate.now();
                LocalTime localTime = LocalTime.now();
                LocalDateTime dt = LocalDateTime.of(localDate, localTime);
                String dateTime = dt.format(dateTimeFormatter);
                pst5.setInt(1, numCompte);
                pst5.setInt(2, Integer.parseInt(numField.getText()));
                pst5.setDouble(3, montant);
                pst5.setString(4, dateTime);
                pst5.execute();
            }
            else {
                errorLabel.setText("Votre solde ne vous permet pas d'effectuer cette transaction");
            }
        }
        else {
            errorLabel.setText("Ce compte n'existe pas");
        }
    }


    public void valider() throws SQLException {
        if(nomField.getText().equals("") || prenomField.getText().equals("") || numField.getText().equals("")
        || montantField.getText().equals("") || passwordField.getText().equals("")) {
            errorLabel.setText("Veuillez remplir tous les champs");
        }
        else {
            pst.setInt(1, numCompte);
            pst.setString(2, passwordField.getText());
            rs = pst.executeQuery();
            if(rs.next()) {
                pst2.setInt(1, Integer.parseInt(numField.getText()));
                pst2.setString(2, nomField.getText());
                pst2.setString(3, prenomField.getText());
                rs2 = pst2.executeQuery();
                Double solde = rs.getDouble("soldeClient");
                Double montant = Double.parseDouble(montantField.getText());
                envoieArgent(montant, solde);
            }
            else {
                errorLabel.setText("Mot de passe incorrect");
            }
        }
    }

}
