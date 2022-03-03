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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable {
    @FXML
    private TextField loginField, passwordField;
    @FXML
    private RadioButton clientRadio, adminRadio;
    @FXML
    private Button connectButton;
    @FXML
    private Label errorLabel;

    public static int numCompte, codeDirecteur;


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
        try {
            pst = con.prepareStatement("SELECT numCompte, motDePasse from client where numCompte = ? AND motDePasse = ?");
            pst2 = con.prepareStatement("SELECT codeDirecteur, motDePasseDirecteur from directeur where codeDirecteur = ? AND motDePasseDirecteur = ?");
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(!(loginField.getText().equals("") || passwordField.getText().equals(""))) {
            try {
                pst.setInt(1, Integer.parseInt(loginField.getText()));
                rs = pst.executeQuery();
                if(rs.next()){
                    numCompte = rs.getInt(1);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void Connect(ActionEvent event) throws SQLException, IOException {
        if(loginField.getText().equals("") || passwordField.getText().equals("")) {
            errorLabel.setText("Veuillez remplir les champs");
        }
        else
        {
            int login = Integer.parseInt(loginField.getText());
            String motDePasse = passwordField.getText();
            if(clientRadio.isSelected()) {
                pst.setInt(1, login);
                pst.setString(2, motDePasse);
                rs = pst.executeQuery();
                if(rs.next()) {
                    numCompte = rs.getInt(1);
                    Parent interfacePrincipal = FXMLLoader.load(getClass().getResource("../FXML/DasboardClient.fxml"));
                    Scene interfaceScene = new Scene(interfacePrincipal);
                    Stage Window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    Window.setScene(interfaceScene);
                    Window.setResizable(true);
                    Window.show();
                    Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                    Window.setX((primScreenBounds.getWidth() - Window.getWidth()) / 2);
                    Window.setY((primScreenBounds.getHeight() - Window.getHeight()) / 2);
                }
                else {
                    errorLabel.setText("Veuillez verifier vos coordonnées");
                }
            }
            else if(adminRadio.isSelected()) {
                pst2.setInt(1, login);
                pst2.setString(2, motDePasse);
                rs = pst2.executeQuery();
                if(rs.next()) {
                    if(login != 1) {
                        codeDirecteur = rs.getInt(1);
                        Parent interfacePrincipal = FXMLLoader.load(getClass().getResource("../FXML/Admin.fxml"));
                        Scene interfaceScene = new Scene(interfacePrincipal);
                        Stage Window = (Stage)((Node)event.getSource()).getScene().getWindow();
                        Window.setScene(interfaceScene);
                        Window.setResizable(true);
                        Window.show();
                        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                        Window.setX((primScreenBounds.getWidth() - Window.getWidth()) / 2);
                        Window.setY((primScreenBounds.getHeight() - Window.getHeight()) / 2);
                    }
                    else if(login == 1) {
                        codeDirecteur = rs.getInt(1);
                        Parent interfacePrincipal = FXMLLoader.load(getClass().getResource("../FXML/DashboardDirecteur.fxml"));
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
                else {
                    errorLabel.setText("Veuillez verifier vos coordonnées");
                }
            }
        }
    }




}
