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

public class ControllerDashboardDirecteur implements Initializable {

    @FXML
    private Label labelNombreAgence, labelNombreEmploye, labelNombreClients;
    private static Connection con;
    private static PreparedStatement pst, pst2, pst3;
    private static ResultSet rs;

    static {
        try {
            con =  sample.ConnectionDB.connectionBase.getConnection();
            pst = con.prepareStatement("SELECT count(*) from employe");
            pst2 = con.prepareStatement("SELECT count(*) from agence");
            pst3 = con.prepareStatement("SELECT count(*) from client");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            rs = pst.executeQuery();
            if(rs.next()) {
                labelNombreEmploye.setText(String.valueOf(rs.getInt(1)));
            }
            rs = pst2.executeQuery();
            if(rs.next()) {
                labelNombreAgence.setText(String.valueOf(rs.getInt(1)));
            }
            rs = pst3.executeQuery();
            if(rs.next()) {
                labelNombreClients.setText(String.valueOf(rs.getInt(1)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
