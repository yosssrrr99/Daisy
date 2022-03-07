/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import esprit.entities.Publication;
import esprit.services.PublicationCRUD;
import esprit.utils.MyConnection;

import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author sarra
 */
public class AjouterPubPresseController implements Initializable {

    @FXML
    private Button btnAjout;
    @FXML
    private Button br;
    @FXML
    private ImageView imageView;
    private FileChooser filechooser = new FileChooser();
    private TextArea txt;
    private File file;
    String query = null;
    Connection cnx;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    @FXML
    private TextField idP;
    @FXML
    private TextField imgUrl;
    @FXML
    private AnchorPane paneFarRight;
    @FXML
    private ImageView profileImage;
    @FXML
    private TableView<Publication> table;
    @FXML
    private TableColumn<Publication, Integer> id;
    @FXML
    private TableColumn<Publication, String> img;
    @FXML
    private TableColumn<Publication, String> txt1;
    @FXML
    private TableColumn<Publication, Timestamp> date;
    @FXML
    private TableColumn<Publication, Boolean> archive;
    @FXML
    private TableColumn<Publication, String> editcol;
    @FXML
    private TextField search;
    @FXML
    private FontAwesomeIconView refresh;

    Publication publication;

    ObservableList<Publication> PubList = FXCollections.observableArrayList();
    @FXML
    private Button modif;
    @FXML
    private FontAwesomeIconView e1;
    @FXML
    private TextArea samahni;

    @FXML
    private TableColumn<Publication, String> tr;
    @FXML
    private TextField tit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void browse(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        try {
            File file = filechooser.showOpenDialog(null);

            Image img = SwingFXUtils.toFXImage(ImageIO.read(file), null);
            imageView.setImage(img);
            if (file != null) {
                String cwd = System.getProperty("user.dir");
                String path = new File(cwd).toURI().relativize(file.toURI()).getPath();
                imgUrl.setText(file.getPath());
                System.out.println(path);
            }
        } catch (IOException ex) {
            Logger.getLogger(AjouterPubPresseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void getAddView(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("AjouterPubPresse.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void AjouterPub(ActionEvent event) {

        if (imgUrl.getText().isEmpty() || samahni.getText().isEmpty()
                || idP.getText().isEmpty() || tit.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Les champs sont vides");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();

        } else {

            String imgPub = imgUrl.getText();

            String txtPub = samahni.getText();
            int idPresse = Integer.parseInt(idP.getText());
            String titre = tit.getText();

            Publication P = new Publication(titre, imgPub, txtPub, idPresse);
            PublicationCRUD pc = new PublicationCRUD();
            pc.ajouterPublication(P);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Connection");

            alert.setHeaderText(null);
            alert.setContentText("votre publication a été ajoutée avec succées !");

            alert.showAndWait();
        }

    }

//    
    @FXML
    private void refreshTable() {
        try {
            PubList.clear();
            query = "SELECT * FROM publication ";

            preparedStatement = cnx.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
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

    }

    private void loadDate() {

        cnx = MyConnection.getInstance().getCnx();

        refreshTable();

        id.setCellValueFactory(new PropertyValueFactory<>("idPub"));
        tr.setCellValueFactory(new PropertyValueFactory<>("titre"));
        img.setCellValueFactory(new PropertyValueFactory<>("imgPub"));
        txt1.setCellValueFactory(new PropertyValueFactory<>("txtPub"));
        date.setCellValueFactory(new PropertyValueFactory<>("dateCreationPub"));
        archive.setCellValueFactory(new PropertyValueFactory<>("archive"));

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

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill: #5f687a;"
                        );

                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation de suppression la publication");
                            alert.setHeaderText(null);
                            alert.setContentText("Vouliez vous supprimer la publication ? ");
                            Optional<ButtonType> action = alert.showAndWait();

                            if (action.get() == ButtonType.OK) {

                                try {
                                    publication = table.getSelectionModel().getSelectedItem();
                                    query = "DELETE FROM `publication` WHERE idPub  =" + publication.getIdPub();

                                    preparedStatement = cnx.prepareStatement(query);
                                    preparedStatement.executeUpdate();
                                    refreshTable();

                                } catch (SQLException ex) {
                                    System.err.println(ex.getMessage());
                                }
                            }
                        });

                          
                        HBox managebtn = new HBox(deleteIcon);
                        //managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));

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
    private void getPub(MouseEvent event) {
        Publication c = table.getSelectionModel().getSelectedItem();
        idP.setText(String.valueOf(c.getIdPub()));
        samahni.setText(c.getTxtPub());
        tit.setText(c.getTitre());
        imgUrl.setText(c.getImgPub());

    }

    @FXML
    private void ModifierPub(ActionEvent event) {

        if (imgUrl.getText().isEmpty() || samahni.getText().isEmpty()
                || idP.getText().isEmpty() || tit.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Les champs sont vides");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();

        } else {

            Publication c = table.getSelectionModel().getSelectedItem();

            //int idPub = Integer.parseInt(idP.getText());
            String titre = tit.getText();
            String imgPub = imgUrl.getText();

            String txtPub = samahni.getText();

            //Publication P = new Publication(titre,imgPub, txtPub);
            PublicationCRUD pc = new PublicationCRUD();
            pc.modifierPublication1(c.getIdPub(), titre, imgPub, txtPub);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Connection");

            alert.setHeaderText(null);
            alert.setContentText("votre publication a été modifié avec succées !");

            alert.showAndWait();
        }
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
            img.setCellValueFactory(new PropertyValueFactory<>("imgPub"));
            txt1.setCellValueFactory(new PropertyValueFactory<>("txtPub"));
            date.setCellValueFactory(new PropertyValueFactory<>("dateCreationPub"));
            archive.setCellValueFactory(new PropertyValueFactory<>("archive"));
            table.setItems(PubList);

        } catch (Exception ex) {
            ex.getMessage();
        }
        if (n == 0) {
            Alert alt = new Alert(AlertType.ERROR, "il n'y a pas un titre de ce nom", javafx.scene.control.ButtonType.OK);
            alt.showAndWait();
            loadDate();
        }

    }

}
