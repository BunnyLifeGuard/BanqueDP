package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ControllerAjoutClient implements Initializable {
    @FXML
    private TextField numField, prenomField, numAgenceField, numTelField, nomField, cinField, soldeField;
    @FXML
    private TextArea adresseArea;
    @FXML
    private DatePicker dateNaissancePicker;
    @FXML
    private Label errorLabel, messageLabel;

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
            pst = con.prepareStatement("SELECT * from client where numCompte = ?");
            pst2 = con.prepareStatement("INSERT into client(numCompte, motDePasse, nomClient, prenomClient, adresseClient, dateNaissanceClient, numAgence, soldeClient, cinClient, numTelephoneClient, dateCreationDuCompte)" +
                    " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void Valider() throws SQLException {

        if((numField.getText()).equals("") || (prenomField.getText()).equals("") || (numAgenceField.getText()).equals("")
        || (numTelField.getText()).equals("") || (nomField.getText()).equals("") || (cinField.getText()).equals("")
        || (dateNaissancePicker == null) || (adresseArea.getText()).equals("") || (soldeField.getText()).equals("")) {
                errorLabel.setText("Veuillez remplir tous les champs");
        }
        else {
            pst.setInt(1, Integer.parseInt(numField.getText()));
            rs = pst.executeQuery();
            if(rs.next()) {
                errorLabel.setText("Ce numéro de compte existe déjà");
            }
            else {
                pst2.setInt(1, Integer.parseInt(numField.getText()));
                pst2.setString(2 ,"0000");
                pst2.setString(3, nomField.getText());
                pst2.setString(4, prenomField.getText());
                pst2.setString(5, adresseArea.getText());
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = dateNaissancePicker.getValue();
                String date = localDate.format(dateFormatter);
                pst2.setString(6, date);
                pst2.setInt(7, Integer.parseInt(numAgenceField.getText()));
                pst2.setDouble(8, Double.parseDouble(soldeField.getText()));
                pst2.setString(9, cinField.getText());
                LocalDate localDateInsertion = LocalDate.now();
                String dateInsertion = localDateInsertion.format(dateFormatter);
                pst2.setInt(10, Integer.parseInt(numTelField.getText()));
                pst2.setString(11, dateInsertion);
                pst2.execute();
                messageLabel.setText("Client ajouté avec succès");

            }
        }

    }
}
