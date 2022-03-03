package sample.Controllers;

import javafx.application.Platform;
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

public class ControllerInterfaceDirecteur implements Initializable {

    @FXML
    private Button dashboardButton ,addEmployeButton, listeEmployeButton, addAgenceButton, listeAgence, demandeButton, logOutButton;
    @FXML
    private AnchorPane mainPane;
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

    public void switchButton(ActionEvent event) throws IOException {
        if(event.getSource() == dashboardButton) {
            Pane dashboardClient = FXMLLoader.load(getClass().getResource("../FXML/InterfaceDirecteur.fxml"));
            mainPane.getChildren().setAll(dashboardClient);
        }
        if(event.getSource() == addEmployeButton) {
            Pane dashboardClient = FXMLLoader.load(getClass().getResource("../FXML/AjouteEmploye.fxml"));
            mainPane.getChildren().setAll(dashboardClient);
        }
        if(event.getSource() == listeEmployeButton) {
            Pane dashboardClient = FXMLLoader.load(getClass().getResource("../FXML/ListeEmployes.fxml"));
            mainPane.getChildren().setAll(dashboardClient);
        }
        if(event.getSource() == addAgenceButton) {
            Pane dashboardClient = FXMLLoader.load(getClass().getResource("../FXML/AjoutAgence.fxml"));
            mainPane.getChildren().setAll(dashboardClient);
        }
        if(event.getSource() == listeAgence) {
            Pane dashboardClient = FXMLLoader.load(getClass().getResource("../FXML/ListeAgences.fxml"));
            mainPane.getChildren().setAll(dashboardClient);
        }
        if(event.getSource() == demandeButton) {
            Pane dashboardClient = FXMLLoader.load(getClass().getResource("../FXML/AccorderFonds.fxml"));
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
    public void exitApplication(ActionEvent event) {
        Platform.exit();
    }
}
