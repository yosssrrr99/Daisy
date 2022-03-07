/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import esprit.entities.Presse;
import esprit.entities.Publication;
import esprit.services.PresseCRUD;
import esprit.services.SendMail;
import esprit.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author sarra
 */
public class AjouterPresseController implements Initializable {

    @FXML
    private TableView<Presse> table;
    @FXML
    private TableColumn<Presse, Integer> idcol;
    @FXML
    private TableColumn<Presse, String> namecol;
    @FXML
    private TableColumn<Presse, String> mailcol;

    @FXML
    private TableColumn<Presse, Boolean> badgecol;
    @FXML
    private TableColumn<Presse, String> editcol;
    @FXML
    private FontAwesomeIconView refresh;

    String query = null;
    Connection cnx = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Presse presse;

    ObservableList<Presse> PresseList = FXCollections.observableArrayList();
//    @FXML
//    private AnchorPane paneFarRight;
//    @FXML
//    private ImageView profileImage;
    @FXML
    private AnchorPane paneFarRight;
    @FXML
    private ImageView profileImage;
    @FXML
    private TextField search;

    //PresseCRUD pr= new PresseCRUD();
    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
    }



    @FXML
    private void refreshTable() {

        try {

            query = "SELECT C.*,P.* FROM presse P, compte C WHERE P.userName= C.userName ";

            preparedStatement = cnx.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PresseList.add(new Presse(
                        resultSet.getInt("id"),
                        resultSet.getString("userName"),
                        resultSet.getString("mail"),
                        //resultSet.getString("Image"),
                        resultSet.getBoolean("badgeAttribue")));
                table.setItems(PresseList);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void loadDate() {
        cnx = MyConnection.getInstance().getCnx();

        refreshTable();

        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        namecol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        mailcol.setCellValueFactory(new PropertyValueFactory<>("mail"));
        //imagecol.setCellValueFactory(new PropertyValueFactory<>("image"));
        badgecol.setCellValueFactory(new PropertyValueFactory<>("badgeAttribue"));

        Callback<TableColumn<Presse, String>, TableCell<Presse, String>> cellFoctory = (TableColumn<Presse, String> param) -> {
            final TableCell<Presse, String> cell = new TableCell<Presse, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        FontAwesomeIconView viewIcon = new FontAwesomeIconView(FontAwesomeIcon.EYE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill: #000000;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#000000;"
                        );

                        viewIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill: #000000;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation de suppression la presse");
                            alert.setHeaderText(null);
                            alert.setContentText("Vouliez vous supprimer une la presse ? ");
                            Optional<ButtonType> action = alert.showAndWait();

                            if (action.get() == ButtonType.OK) {

                                try {
                                    presse = table.getSelectionModel().getSelectedItem();
                                    query = "DELETE FROM `presse` WHERE id  =" + presse.getId();

                                    preparedStatement = cnx.prepareStatement(query);
                                    preparedStatement.executeUpdate();
                                    refreshTable();

                                } catch (SQLException ex) {
                                    System.err.println(ex.getMessage());
                                }
                            }
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                           
                            Presse pr = getTableView().getItems().get(getIndex());
                            boolean b = pr.getBadgeAttribue();
                            if (b == false) {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation d'ajout de la presse");
                                alert.setHeaderText(null);
                                alert.setContentText("Vouliez vous confirmer une partenariat avec cette presse ? ");
                                Optional<ButtonType> action = alert.showAndWait();

                                if (action.get() == ButtonType.OK) {

                                    try {
                                        presse = table.getSelectionModel().getSelectedItem();
                                        query = "UPDATE `presse` SET badgeAttribue=1 WHERE id= " + presse.getId();

                                        preparedStatement = cnx.prepareStatement(query);

                                        preparedStatement.executeUpdate();

                                        refreshTable();
                                        try {
                                            Presse data = getTableView().getItems().get(getIndex());
                                            String a = data.getMail();
                                            SendMail.sendMail(a, "hellooo");
                                        } catch (Exception ex) {
                                            Logger.getLogger(AjouterPresseController.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                    } catch (SQLException ex) {
                                        System.err.println(ex.getMessage());
                                    }

                                }

                            } else {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setHeaderText(null);
                                alert.setContentText("vous avez deja confirmer votre partenariat avec cette presse ");
                                Optional<ButtonType> action = alert.showAndWait();
                            }
                        });

                        viewIcon.setOnMouseClicked((MouseEvent event0) -> {

                            FXMLLoader Loader = new FXMLLoader(getClass().getResource("AfficherPubAdmin.fxml"));

                            try {
                                Parent root = Loader.load();
                                AfficherPubAdminController AfficherPubAdminc = Loader.getController();
                                Presse selected = table.getSelectionModel().getSelectedItem();
                                int idPub = selected.getId();
                                PresseCRUD fc = new PresseCRUD();
                                

                                AfficherPubAdminc.setUpdate(fc.RecupPresse(idPub));

                                Scene productDetailScene = new Scene(root);
                                Stage cineStage = (Stage) ((Node) event0.getSource()).getScene().getWindow();
                                cineStage.setScene(productDetailScene);

                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
//                                 

                        });

//                          
                        HBox managebtn = new HBox(editIcon, deleteIcon, viewIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(viewIcon, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };

        editcol.setCellFactory(cellFoctory);
        table.setItems(PresseList);

    }

    @FXML
    private void search(MouseEvent event) {
        FontAwesomeIconView searchicon = new FontAwesomeIconView(FontAwesomeIcon.SEARCH);
        

            
            PresseList.setAll();
        int n = 0;
        
            try {
                
                
                String req = "SELECT * FROM Presse WHERE userName = ? ";
                PreparedStatement pst = cnx.prepareStatement(req);
                pst.setString(1, String.valueOf(search.getText()));
                ResultSet rs = pst.executeQuery();
                Publication pub = new Publication();
                while (rs.next()) {
                    n = 2;
                    PresseList.add(new Presse(
                        rs.getInt("id"),
                        rs.getString("userName"),
                        rs.getBoolean("badgeAttribue")));
                        
                table.setItems(PresseList);
                }
                idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        namecol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        mailcol.setCellValueFactory(new PropertyValueFactory<>("mail"));
        //imagecol.setCellValueFactory(new PropertyValueFactory<>("image"));
        badgecol.setCellValueFactory(new PropertyValueFactory<>("badgeAttribue"));
                
        table.setItems(PresseList);

            } catch (Exception ex) {
                ex.getMessage();
            }
            if (n == 0) {
                Alert alt = new Alert(Alert.AlertType.ERROR, "il n'y a pas un username de ce nom", javafx.scene.control.ButtonType.OK);
                alt.showAndWait();
                loadDate();
            }
        
            
        
    }

}
