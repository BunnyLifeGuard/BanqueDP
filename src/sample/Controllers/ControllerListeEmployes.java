package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Classes.Client;
import sample.Classes.Employe;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerListeEmployes implements Initializable {

    public TableView<Employe> employesTable;
    public TableColumn<Employe, Integer> numField;
    public TableColumn<Employe, String> nomField;
    public TableColumn<Employe, String> prenomField;
    public TableColumn<Employe, String> cinField;
    public TableColumn<Employe, Double> salaireField;
    public TableColumn<Employe, Integer> agenceField;
    public ObservableList<Employe> data;

    private static Connection con;
    private static PreparedStatement pst;
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
            pst = con.prepareStatement("SELECT * from employe");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numField.setCellValueFactory(new PropertyValueFactory<>("numEmploye"));
        nomField.setCellValueFactory(new PropertyValueFactory<>("nomEmploye"));
        prenomField.setCellValueFactory(new PropertyValueFactory<>("prenomEmploye"));
        cinField.setCellValueFactory(new PropertyValueFactory<>("cinEmploye"));
        salaireField.setCellValueFactory(new PropertyValueFactory<>("salaireEmploye"));
        agenceField.setCellValueFactory(new PropertyValueFactory<>("agenceEmploye"));

        try {
            data = FXCollections.observableArrayList();
            rs = pst.executeQuery();
            while (rs.next()) {
                Employe employe = new Employe(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                         rs.getDouble(5), rs.getInt(8));
                data.add(employe);
                employesTable.setItems(data);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
