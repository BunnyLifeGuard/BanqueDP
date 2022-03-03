package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Classes.Agence;
import sample.Classes.Client;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerListeAgences implements Initializable {

    public TableView<Agence> agencesTable;
    public TableColumn<Agence, Integer> numField;
    public TableColumn<Agence, String> nomField;
    public TableColumn<Agence, String> directeurField;
    public TableColumn<Agence, Double> plafondField;
    public TableColumn<Agence, String> dateField;
    public ObservableList<Agence> data;
    private static Connection con;
    private static PreparedStatement pst, pst2, pst3;
    private static ResultSet rs, rs2;

    static {
        try {
            con =  sample.ConnectionDB.connectionBase.getConnection();
            pst = con.prepareStatement("SELECT * from agence");
            pst2 = con.prepareStatement("SELECT nomEmploye, prenomEmploye from employe" +
                    " where codeEmploye = ?");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numField.setCellValueFactory(new PropertyValueFactory<>("numAgence"));
        nomField.setCellValueFactory(new PropertyValueFactory<>("nomAgence"));
        directeurField.setCellValueFactory(new PropertyValueFactory<>("directeurAgence"));
        plafondField.setCellValueFactory(new PropertyValueFactory<>("plafondAgence"));
        dateField.setCellValueFactory(new PropertyValueFactory<>("dateAgence"));

        try {
            rs = pst.executeQuery();
            data = FXCollections.observableArrayList();
            while (rs.next()) {
                pst2.setInt(1, rs.getInt(4));
                rs2 = pst2.executeQuery();
                while (rs2.next()) {
                    String nom = rs2.getString(1) + " " + rs2.getString(2);
                    Agence agence = new Agence(rs.getInt(1), rs.getString(2), nom, rs.getDouble(5), rs.getString(6));
                    data.add(agence);
                    agencesTable.setItems(data);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
