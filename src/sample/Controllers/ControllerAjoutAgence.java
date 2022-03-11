package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.Controllers.Strategie.ValiderStrategie;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ControllerAjoutAgence implements Initializable, ValiderStrategie {

    @FXML
    private TextField nomField, plafondField, numTelField;
    @FXML
    private TextArea adresseArea;
    @FXML
    private ComboBox<Integer> directeurBox = new ComboBox<>();
    @FXML
    private Label errorLabel, messageLabel;
    private static Connection con;
    private static PreparedStatement pst, pst2, pst3, pst4;
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
            pst = con.prepareStatement("Select codeDirecteur from directeur");
            pst2 = con.prepareStatement("INSERT INTO agence (nomAgence, adresseAgence, plafondAgence, directeurAgence, telAgence, dateCreation) " +
                    "values (?, ?, ?, ?, ?, ?)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            rs = pst.executeQuery();
            while(rs.next()) {
                directeurBox.getItems().add(rs.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void Valider() throws SQLException {
        if(nomField.getText().equals("") || plafondField.getText().equals("") || numTelField.getText().equals("")
        || adresseArea.getText().equals("") || directeurBox.getSelectionModel().getSelectedItem() == null) {
            errorLabel.setText("Veuillez remplir tous les champs");
        }
        else {
            pst2.setString(1, nomField.getText());
            pst2.setString(2, adresseArea.getText());
            pst2.setDouble(3, Double.parseDouble(plafondField.getText()));
            pst2.setInt(4, directeurBox.getSelectionModel().getSelectedItem());
            pst2.setInt(5, Integer.parseInt(numTelField.getText()));
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDateInsertion = LocalDate.now();
            String dateInsertion = localDateInsertion.format(dateFormatter);
            pst2.setString(6, dateInsertion);
            pst2.execute();
            messageLabel.setText("Agence ajouter avec succès");
        }
    }
}
