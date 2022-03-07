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
import esprit.services.PublicationCRUD;
import esprit.utils.MyConnection;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author sarra
 */
public class AfficherPubAdminController implements Initializable {

    String query = null;
    Connection cnx = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Presse presse = null;

    private boolean update;
    @FXML
    private FontAwesomeIconView refresh;
    @FXML
    private AnchorPane paneFarRight1;
    @FXML
    private ImageView profileImage1;
    @FXML
    private ImageView imagePresse;
    @FXML
    private TableView<Publication> table;
    @FXML
    private TableColumn<Publication, Integer> id;
    @FXML
    private TableColumn<Publication, String> img1;
    @FXML
    private TableColumn<Publication, String> txt1;
    @FXML
    private TableColumn<Publication, Timestamp> date;
    @FXML
    private TableColumn<Publication, Boolean> archive;
    @FXML
    private TableColumn<Publication, String> editcol;
    @FXML
    private TableColumn<Publication, String> tr;

    @FXML
    private TableColumn<Publication, Integer> idp;
    @FXML
    private TextField search;

    Publication publication;

    ObservableList<Publication> PubList = FXCollections.observableArrayList();
    @FXML
    private Label mimi;
    @FXML
    private Label yoyo;
    @FXML
    private Label badg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        loadDate();
    }

    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    void setUpdate(Presse p) throws FileNotFoundException {

        try {

            query = "SELECT * FROM  publication WHERE idPresse=  ?";

            preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setInt(1, p.getId());

            resultSet = preparedStatement.executeQuery();
            System.out.println("lennaa");

            while (resultSet.next()) {

                System.out.println("yosr");
                PubList.add(new Publication(
                        resultSet.getInt("idPub"),
                        resultSet.getString("titre"),
                        resultSet.getString("imgPub"),
                        resultSet.getString("txtPub"),
                        resultSet.getTimestamp("dateCreationPub"),
                        resultSet.getInt("idPresse"),
                        resultSet.getBoolean("archive")));

                table.setItems(PubList);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        mimi.setText(p.getMail());
        yoyo.setText(p.getUserName());
        badg.setText(String.valueOf(p.getBadgeAttribue()));
        id.setCellValueFactory(new PropertyValueFactory<>("idPub"));
        tr.setCellValueFactory(new PropertyValueFactory<>("titre"));
        img1.setCellValueFactory(new PropertyValueFactory<>("imgPub"));
        txt1.setCellValueFactory(new PropertyValueFactory<>("txtPub"));
        date.setCellValueFactory(new PropertyValueFactory<>("dateCreationPub"));
        idp.setCellValueFactory(new PropertyValueFactory<>("idPresse"));

        archive.setCellValueFactory(new PropertyValueFactory<>("archive"));

    }

    @FXML
    private void refreshTable() {

    }

    private void loadDate() {

        cnx = MyConnection.getInstance().getCnx();

        refreshTable();

//        id.setCellValueFactory(new PropertyValueFactory<>("idPub"));
//        tr.setCellValueFactory(new PropertyValueFactory<>("titre"));
//        img1.setCellValueFactory(new PropertyValueFactory<>("imgPub"));
//        txt1.setCellValueFactory(new PropertyValueFactory<>("txtPub"));
//        date.setCellValueFactory(new PropertyValueFactory<>("dateCreationPub"));
//        archive.setCellValueFactory(new PropertyValueFactory<>("archive"));
        Callback<TableColumn<Publication, String>, TableCell<Publication, String>> cellFoctory = (TableColumn<Publication, String> param) -> {
            final TableCell<Publication, String> cell = new TableCell<Publication, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView viewIcon = new FontAwesomeIconView(FontAwesomeIcon.EYE);
                        viewIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill: #000000;"
                        );

                        viewIcon.setOnMouseClicked((MouseEvent event) -> {

                            FXMLLoader Loader = new FXMLLoader(getClass().getResource("Detail.fxml"));

                            try {
                               
                                Parent root = Loader.load();
                                DetailController detail = Loader.getController();
                                
                                Publication selected = table.getSelectionModel().getSelectedItem();
                                 

                                int idPub = selected.getIdPub();
                                PublicationCRUD fc = new PublicationCRUD();
//
                                detail.setDetail(fc.RecupPub(idPub));

                                Scene DetailScene = new Scene(root);
                                Stage cineStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                cineStage.setScene(DetailScene);

                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }

                        });
                        HBox managebtn = new HBox(viewIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(viewIcon, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };

        editcol.setCellFactory(cellFoctory);
        table.setItems(PubList);

    }

    @FXML
    private void search(MouseEvent event) {
        FontAwesomeIconView searchicon = new FontAwesomeIconView(FontAwesomeIcon.SEARCH);

        PubList.setAll();
        int n = 0;

        try {

            String req = "SELECT * FROM Publication WHERE titre = ? ";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, String.valueOf(search.getText()));
            ResultSet rs = pst.executeQuery();
            Publication pub = new Publication();
            while (rs.next()) {
                n = 2;
                PubList.add(new Publication(
                        rs.getInt("idPub"),
                        rs.getString("titre"),
                        rs.getString("imgPub"),
                        rs.getString("txtPub"),
                        rs.getTimestamp("dateCreationPub"),
                        rs.getInt("idPresse"),
                        rs.getBoolean("archive")));
                table.setItems(PubList);
            }
            id.setCellValueFactory(new PropertyValueFactory<>("idPub"));
            tr.setCellValueFactory(new PropertyValueFactory<>("titre"));
            img1.setCellValueFactory(new PropertyValueFactory<>("imgPub"));
            txt1.setCellValueFactory(new PropertyValueFactory<>("txtPub"));
            date.setCellValueFactory(new PropertyValueFactory<>("dateCreationPub"));
            archive.setCellValueFactory(new PropertyValueFactory<>("archive"));
            table.setItems(PubList);

        } catch (Exception ex) {
            ex.getMessage();
        }
        if (n == 0) {
            Alert alt = new Alert(Alert.AlertType.ERROR, "il n'y a pas un titre de ce nom", javafx.scene.control.ButtonType.OK);
            alt.showAndWait();
            loadDate();
        }

    }

    
    @FXML
    private void Retour(MouseEvent event) {

        try {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            
            Stage primaryStage= new Stage();
            FXMLLoader FL = new FXMLLoader(getClass().getResource("AjouterPresse.fxml"));
            Parent root = FL.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
