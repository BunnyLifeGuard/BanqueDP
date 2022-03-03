package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import sample.Classes.Transaction;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Vector;

public class ControllerInterface implements Initializable {

    @FXML
    private Button mainMenuButton, carteMenuButton, payButton, settingsButton;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private ImageView imageCarte1, imageCarte2;
    @FXML
    private Label numCarte1, nomClient1, numCarte2, nomClient2, numCompteLabel, soldeLabel;
    @FXML
    private TableView<Transaction> tableTransaction;
    @FXML
    private TableColumn<Transaction, String> columnNom;
    @FXML
    private TableColumn<Transaction, Integer> columnNum;
    @FXML
    private TableColumn<Transaction, Double> columnMontant;
    public ObservableList<Transaction> data;

    private static Connection con;
    private static PreparedStatement pst, pst2, pst3;
    private static ResultSet rs, rs2, rs3;
    private int numCompte =  ControllerLogin.numCompte;

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
            pst = con.prepareStatement("SELECT * from carte where numCompte = ?");
            pst2 = con.prepareStatement("SELECT * from client where numCompte = ?");
            pst3 = con.prepareStatement("SELECT * from historique where source = ? order by dateTransaction desc");
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        columnNum.setCellValueFactory(new PropertyValueFactory<>("numCompte"));
        columnMontant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        try {
            pst.setInt(1, numCompte);
            pst2.setInt(1, numCompte);
            pst3.setInt(1, numCompte);
            String numCarte = "test";
            rs = pst.executeQuery();
            rs2 = pst2.executeQuery();
            String nomPrenom = "test";
            int solde = 5;
            if(rs2.next()) {
                nomPrenom = rs2.getString("nomClient") + " " + rs2.getString("prenomClient");
                solde = rs2.getInt("soldeClient");
            }
            Vector<File> imageVector = new Vector<File>();
            while(rs.next()) {
                File image = new File(rs.getString("imageCarte"));
                imageVector.add(image);
                numCarte = rs.getString("numCarte");
            }
            if(imageVector.size() == 0) {
                System.out.println("Rien");
            }
            else if(imageVector.size() == 1) {
                imageCarte1.setImage(new Image((imageVector.get(0)).toURI().toString()));
                numCarte1.setText(numCarte);
                nomClient1.setText(nomPrenom);
            }
            else if(imageVector.size() > 1) {
                imageCarte1.setImage(new Image((imageVector.get(0)).toURI().toString()));
                numCarte1.setText(numCarte);
                nomClient1.setText(nomPrenom);
                imageCarte2.setImage(new Image((imageVector.get(1)).toURI().toString()));
                numCarte2.setText(numCarte);
                nomClient2.setText(nomPrenom);
            }
            soldeLabel.setText(solde + " Dhs");
            numCompteLabel.setText(numCompte + "");

            rs3 = pst3.executeQuery();
            Transaction transaction;
            data = FXCollections.observableArrayList();
            while(rs3.next()) {
                pst2.setInt(1, rs3.getInt("destination"));
                rs = pst2.executeQuery();
                while (rs.next()) {
                    transaction = new Transaction((rs.getString("nomClient") + " " + rs.getString("prenomClient")),
                            rs3.getInt("destination"), rs3.getDouble("montant"));
                    data.add(transaction);
                    tableTransaction.setItems(data);
                }

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void switchButton(ActionEvent event) throws IOException {
        if(event.getSource() == mainMenuButton) {
            Pane dashboardClient = FXMLLoader.load(getClass().getResource("../FXML/dashboard.fxml"));
            mainPane.getChildren().setAll(dashboardClient);
        }
        if(event.getSource() == carteMenuButton) {
            Pane cartePane = FXMLLoader.load(getClass().getResource("../FXML/Carte.fxml"));
            mainPane.getChildren().setAll(cartePane);
        }
        if(event.getSource() == payButton) {
            Pane versementPane = FXMLLoader.load(getClass().getResource("../FXML/Versement.fxml"));
            mainPane.getChildren().setAll(versementPane);
        }
        if(event.getSource() == settingsButton) {
            Pane parametrePane = FXMLLoader.load(getClass().getResource("../FXML/Settings.fxml"));
            mainPane.getChildren().setAll(parametrePane);
        }
    }
}
