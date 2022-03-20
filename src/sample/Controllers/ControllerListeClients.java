package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Classes.Client;
import sample.Classes.Fabrique.AbstractObjectFactory;
import sample.Classes.Fabrique.ObjectFactory;
import sample.Classes.Transaction;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerListeClients implements Initializable {

    private int numDirecteur = ControllerLogin.codeDirecteur;
    private int numAgence = 0;
    @FXML
    private TextField nomTextField, prenomTextField;
    public TableView<Client> clientsTable;
    public TableColumn<Client, Integer> numField;
    public TableColumn<Client, String> nomField;
    public TableColumn<Client, String> prenomField;
    public TableColumn<Client, Double> soldeField;
    public TableColumn<Client, Integer> agenceField;
    public TableColumn<Client, String> dateField;
    public ObservableList<Client> data;
    public Label errorLabel, messageLabel;
    AbstractObjectFactory abstractObjectFactory = new ObjectFactory();

    private static Connection con;
    private static PreparedStatement pst, pst2, pst3, pst4, pst5;
    private static ResultSet rs, rs2, rs3;

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
            pst = con.prepareStatement("SELECT numAgence from employe where codeEmploye = ?");
            pst2 = con.prepareStatement("SELECT * from client where numAgence = ?");
            pst3 = con.prepareStatement("UPDATE client set prenomClient = ? where numCompte = ?");
            pst4 = con.prepareStatement("UPDATE client set nomClient = ? where numCompte = ?");
            pst5 = con.prepareStatement("UPDATE client set prenomClient = ? AND nomClient = ? where numCompte = ?");
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numField.setCellValueFactory(new PropertyValueFactory<>("numCompte"));
        nomField.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        prenomField.setCellValueFactory(new PropertyValueFactory<>("prenomClient"));
        soldeField.setCellValueFactory(new PropertyValueFactory<>("soldeClient"));
        agenceField.setCellValueFactory(new PropertyValueFactory<>("agenceClient"));
        dateField.setCellValueFactory(new PropertyValueFactory<>("dateRejointClient"));

        try {
            pst.setInt(1, numDirecteur);
            rs = pst.executeQuery();
            if(rs.next()){
                numAgence = rs.getInt("numAgence");
                pst2.setInt(1, numAgence);
                rs2 = pst2.executeQuery();
                data = FXCollections.observableArrayList();
                while (rs2.next()) {
                    ArrayList<String> attributes = new ArrayList<>();
                    attributes.add(rs2.getInt("numCompte") + "");
                    attributes.add(rs2.getString("nomClient"));
                    attributes.add(rs2.getString("prenomClient"));
                    attributes.add(rs2.getDouble("soldeClient") + "");
                    attributes.add(rs2.getInt("numAgence") + "");
                    attributes.add(rs2.getString("dateCreationDuCompte"));
                    Client client = (Client) abstractObjectFactory.creerObjet("Client", attributes);
                    data.add(client);
                    clientsTable.setItems(data);
                }
            }
            pst2.setInt(1, numAgence);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
