package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Vector;

public class ControllerCarte implements Initializable {

    @FXML
    ImageView previousImage, nextImage, carteImage;
    private static Connection con;
    private static PreparedStatement pst, pst2, pst3;
    private ResultSet rs;
    static int positionVector = 0;
    static int carte = 0;

    Vector<File> imageVector = new Vector<>();
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
            pst = con.prepareStatement("Select * from listecarte");
            pst2 = con.prepareStatement("Insert into carte (numCarte, numCompte, imageCarte) value (?, ?, ?)");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            rs = pst.executeQuery();
            while(rs.next()) {
                File image = new File(rs.getString(1));
                imageVector.add(image);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void next() throws MalformedURLException {
        if(positionVector == imageVector.size() - 1) {
            positionVector = 0;
            Image image = new Image((imageVector.get(positionVector)).toURI().toString());
            carteImage.setImage(image);

        }
        else {
            positionVector++;
            Image image = new Image((imageVector.get(positionVector)).toURI().toString());
            carteImage.setImage(image);

        }
    }

    public void previous() {
        if(positionVector == 0) {
            positionVector = imageVector.size() - 1;
            Image image = new Image((imageVector.get(positionVector)).toURI().toString());
            carteImage.setImage(image);
        }
        else {
            positionVector --;
            Image image = new Image((imageVector.get(positionVector)).toURI().toString());
            carteImage.setImage(image);
        }
    }

    public void validerCarte() throws SQLException {
        int numCarte = (ControllerLogin.numCompte * 10) + positionVector;
        int numCompte = ControllerLogin.numCompte;
        String imageCarte = imageVector.get(positionVector).toString();
        pst2.setInt(1, numCarte);
        pst2.setInt(2, numCompte);
        pst2.setString(3, imageCarte);
        pst2.execute();
    }

}
