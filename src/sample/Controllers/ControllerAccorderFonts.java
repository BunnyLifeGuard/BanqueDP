package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Classes.Agence;
import sample.Classes.Fond;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerAccorderFonts implements Initializable {

    public TableView<Fond> fondTable;
    public TableColumn<Fond, Integer> numField;
    public TableColumn<Fond, Double> fondField;
    public TableColumn<Fond, Double> plafondField;
    public TableColumn<Fond, String> etatField;
    public ObservableList<Fond> data;
    private static Connection con;
    private static PreparedStatement pst, pst2, pst3, pst4;
    private static ResultSet rs, rs2;

    static {
        try {
            con =  sample.ConnectionDB.connectionBase.getConnection();
            pst = con.prepareStatement("SELECT * from fond");
            pst2 = con.prepareStatement("SELECT plafondAgence from agence" +
                    " where numAgence = ?");
            pst3 = con.prepareStatement("DELETE from fond where numAgence = ?");
            pst4 = con.prepareStatement("UPDATE agence set plafondAgence = plafondAgence + ? where numAgence = ?");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numField.setCellValueFactory(new PropertyValueFactory<>("numAgence"));
        fondField.setCellValueFactory(new PropertyValueFactory<>("montant"));
        plafondField.setCellValueFactory(new PropertyValueFactory<>("plafondActuel"));
        etatField.setCellValueFactory(new PropertyValueFactory<>("etat"));

        data = FXCollections.observableArrayList();
        try {
            rs = pst.executeQuery();
            while(rs.next()) {
                pst2.setInt(1, rs.getInt(1));
                rs2 = pst2.executeQuery();
                while(rs2.next()) {
                    Fond fond = new Fond(rs.getInt(1), rs.getDouble(2), rs2.getDouble(1), "En Attente");
                    data.add(fond);
                    fondTable.setItems(data);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void Valider() throws SQLException {
        Fond selected = fondTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            pst4.setDouble(1, selected.getMontant());
            pst4.setInt(2, selected.getNumAgence());
            pst4.executeUpdate();
            selected.setEtat("Succès");
            selected.setPlafondActuel(selected.getMontant() + selected.getPlafondActuel());
            selected.setMontant(0);
            int selectedIndex = fondTable.getSelectionModel().getSelectedIndex();
            data.set(selectedIndex, selected);
            pst3.setInt(1, selected.getNumAgence());
            pst3.execute();
        }
    }
}
