package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ControllerAjoutEmploye implements Initializable {

    @FXML
    private TextField prenomField, numTelField, nomField, cinField, salaireField;
    @FXML
    private TextArea adresseArea;
    @FXML
    private ComboBox<Integer> agenceBox = new ComboBox<>();
    @FXML
    private DatePicker dateNaissancePicker;
    @FXML
    private Label errorLabel, messageLabel;
    @FXML
    private RadioButton directeurRadio;

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
            pst = con.prepareStatement("SELECT numAgence from agence where numAgence != 1");
            pst2 = con.prepareStatement("INSERT INTO employe (nomEmploye, prenomEmploye, CIN,salaireEmploye, adresseEmploye," +
                    " telEmploye, numAgence, dateRejoindre, dateNaissance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst3 = con.prepareStatement("INSERT INTO directeur (codeDirecteur, motDePasseDirecteur) VALUES (?, ?)");
            pst4 = con.prepareStatement("SELECT codeEmploye from employe");
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            rs = pst.executeQuery();
            while(rs.next()) {
                agenceBox.getItems().add(rs.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void Valider() throws SQLException {
        if(((prenomField.getText()).equals("")) || (agenceBox.getSelectionModel().getSelectedItem() == null)
                || (numTelField.getText()).equals("") || (nomField.getText()).equals("") || (cinField.getText()).equals("")
                || (dateNaissancePicker == null) || (adresseArea.getText()).equals("") || (salaireField.getText()).equals("")) {
            errorLabel.setText("Veuillez remplir tous les champs");
        } else {
            pst2.setString(1, nomField.getText());
            pst2.setString(2, prenomField.getText());
            pst2.setString(3, cinField.getText());
            pst2.setDouble(4, Double.parseDouble(salaireField.getText()));
            pst2.setString(5, adresseArea.getText());
            pst2.setInt(6, Integer.parseInt(numTelField.getText()));
            pst2.setInt(7, agenceBox.getSelectionModel().getSelectedItem());
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDateInsertion = LocalDate.now();
            String dateInsertion = localDateInsertion.format(dateFormatter);
            pst2.setString(8, dateInsertion);
            LocalDate localDate = dateNaissancePicker.getValue();
            String date = localDate.format(dateFormatter);
            pst2.setString(9, date);
            pst2.execute();
            messageLabel.setText("Employé ajouté avec succès");
            int num = -4;
            rs = pst4.executeQuery();
            while (rs.next()) {
                num = rs.getInt(1);
            }
            if(directeurRadio.isSelected()) {
                pst3.setInt(1, num);
                pst3.setString(2, "test");
                pst3.execute();
            }
        }
    }
}
