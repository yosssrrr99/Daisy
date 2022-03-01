/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinepro.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Compte;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import utiles.MyConnection;
import edu.cinepro.services.CompteCrud;

/**
 * FXML Controller class
 *
 * @author 21653
 */
public class ConsulterController implements Initializable {

    @FXML
    private AnchorPane getaddview;
    @FXML
    private TableView<Compte> tableconsulter;
    @FXML
    private TableColumn<Compte, String> usercol;
    @FXML
    private TableColumn<Compte, String> mailcol;
    @FXML
    private TableColumn<Compte, String> rolecol;
    @FXML
    private TableColumn<Compte, String> imagecol;
    @FXML
    private TableColumn<Compte, String> editcol;
    @FXML
    private Text listedescomptes;
    @FXML
    private FontAwesomeIconView close;
    @FXML
    private FontAwesomeIconView refresh;
    @FXML
    private Pane barre;
    
//    @FXML
//    private Pane barre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        table();
    }   
    String query = null;
    Connection cnx = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Compte Compte = null ;
   
    ObservableList<Compte>  CompteList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    
    
   
    private void getAddView(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("consulter.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
   
   
    
   
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
   
   
   
    private void refresh () {

        try {

            CompteList.clear();

            query = "SELECT userName,mail,role,Image FROM compte";

            preparedStatement = cnx.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CompteList.add(new Compte(
                        resultSet.getString("username"),
                        resultSet.getString("mail"),
                        resultSet.getString("Role"),
                        resultSet.getString("Image")));
                tableconsulter.setItems(CompteList);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
 
   
    private void table() {
        cnx = MyConnection.getInstance().getCnx();

        refresh();

        usercol.setCellValueFactory(new PropertyValueFactory<>("username"));
        mailcol.setCellValueFactory(new PropertyValueFactory<>("mail"));
        rolecol.setCellValueFactory(new PropertyValueFactory<>("Role"));
        imagecol.setCellValueFactory(new PropertyValueFactory<>("Image"));
       

        Callback<TableColumn<Compte, String>, TableCell<Compte, String>> cellFoctory = (TableColumn<Compte, String> param) -> {
            final TableCell<Compte, String> cell = new TableCell<Compte, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                       

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#8a0a0a;"
                        );
                        
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                Compte = tableconsulter.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM compte WHERE userName  = ?" + Compte.getUsername();

                                preparedStatement = cnx.prepareStatement(query);
                                preparedStatement.executeUpdate();
                                refresh();
                                
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
       
                                alert.setTitle("Compte");
                                alert.setHeaderText("Supprimer Compte");
                                alert.setContentText("Votre Compte a été supprimé avec succès!");
                                alert.showAndWait();
                               

                            } catch (SQLException ex) {
                                System.err.println(ex.getMessage());
                            }

                        });
                        
                              

                        HBox managebtn = new HBox(deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };

        editcol.setCellFactory(cellFoctory);
        tableconsulter.setItems(CompteList);
       
       
      

    }
   
   
   
}


    


    

   
 
   
    