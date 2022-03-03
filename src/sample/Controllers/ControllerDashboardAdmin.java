package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerDashboardAdmin implements Initializable {
    @FXML
    private Label labelNombreCompte, labelNombreDeTransaction, labelFond;
    private int numDirecteur = ControllerLogin.codeDirecteur;
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
        try{
            pst = con.prepareStatement("SELECT count(*) from historique INNER JOIN client c on historique.source = c.numCompte" +
                    " where c.numAgence = (SELECT numAgence from agence where directeurAgence = ?)");
            pst2 = con.prepareStatement("SELECT count(*) from client INNER JOIN agence a on client.numAgence = a.numAgence" +
                    " where a.directeurAgence = ?");
            pst3 = con.prepareStatement("SELECT plafondAgence from agence where directeurAgence = ?");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            pst.setInt(1, numDirecteur);
            rs = pst.executeQuery();
            if (rs.next()) {
                int nombreTransaction = rs.getInt(1);
                labelNombreDeTransaction.setText(String.valueOf(nombreTransaction));
            }
            pst2.setInt(1, numDirecteur);
            rs = pst2.executeQuery();
            if (rs.next()) {
                int nombreClient = rs.getInt(1);
                labelNombreCompte.setText(String.valueOf(nombreClient));
            }
            pst3.setInt(1, numDirecteur);
            rs = pst3.executeQuery();
            if (rs.next()) {
                double fond = rs.getDouble(1);
                labelFond.setText(String.valueOf(fond) + " Dhs");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
