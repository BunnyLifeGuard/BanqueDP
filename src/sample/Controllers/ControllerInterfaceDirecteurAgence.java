package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerInterfaceDirecteurAgence implements Initializable {

    @FXML
    private Button listeClientButton, addClientButton, demandeButton, logOutButton, dashboardButton;
    @FXML
    private Label labelNombreCompte, labelNombreDeTransaction, labelFond;
    @FXML
    private AnchorPane mainPane;

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

    public void switchButton(ActionEvent event) throws IOException {
        if(event.getSource() == addClientButton) {
            Pane dashboardClient = FXMLLoader.load(getClass().getResource("../FXML/AjouteClient.fxml"));
            mainPane.getChildren().setAll(dashboardClient);
        }
        if(event.getSource() == listeClientButton) {
            Pane dashboardClient = FXMLLoader.load(getClass().getResource("../FXML/ListeClients.fxml"));
            mainPane.getChildren().setAll(dashboardClient);
        }
        if(event.getSource() == demandeButton) {
            Pane dashboardClient = FXMLLoader.load(getClass().getResource("../FXML/DemandeFond.fxml"));
            mainPane.getChildren().setAll(dashboardClient);
        }
        if(event.getSource() == dashboardButton) {
            Pane dashboardClient = FXMLLoader.load(getClass().getResource("../FXML/DashboardAdmin.fxml"));
            mainPane.getChildren().setAll(dashboardClient);
        }
        if(event.getSource() == logOutButton) {
            Parent interfacePrincipal = FXMLLoader.load(getClass().getResource("../FXML/Login.fxml"));
            Scene interfaceScene = new Scene(interfacePrincipal);
            Stage Window = (Stage)((Node)event.getSource()).getScene().getWindow();
            Window.setScene(interfaceScene);
            Window.setResizable(true);
            Window.show();
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            Window.setX((primScreenBounds.getWidth() - Window.getWidth()) / 2);
            Window.setY((primScreenBounds.getHeight() - Window.getHeight()) / 2);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            pst.setInt(1, numDirecteur);
            rs = pst.executeQuery();
            if(rs.next()) {
                int nombreTransaction = rs.getInt(1);
                labelNombreDeTransaction.setText(String.valueOf(nombreTransaction));
            }
            pst2.setInt(1, numDirecteur);
            rs = pst2.executeQuery();
            if(rs.next()) {
                int nombreClient = rs.getInt(1);
                labelNombreCompte.setText(String.valueOf(nombreClient));
            }
            pst3.setInt(1, numDirecteur);
            rs = pst3.executeQuery();
            if(rs.next()) {
                double fond = rs.getDouble(1);
                labelFond.setText(String.valueOf(fond) + " Dhs");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
