/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.cine.entities.Compte;
import edu.cine.entities.Film;
import edu.cine.services.FilmCRUD;
import edu.cine.utils.MyConnection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
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
 * @author DELL
 */
public class AccueilController implements Initializable {

    /**
     * Initializes the controller class.
     */
    String query = null;
    Connection cnx = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Film film = null;

    ObservableList<Film> filmListe = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Film, String> editcol;
    @FXML
    public TableView<Film> table;

    int idFilm;

   
    @FXML
    private TableColumn<Film, String> nom;
    @FXML
    private TableColumn<Film, String> gen;
    @FXML
    private TableColumn<Film, String> etat;

    @FXML
    private TableColumn<Film, Integer> dure;
    @FXML
    private TableColumn<Film, Integer> Arch;

    @FXML
    private TableColumn<Film, String> date;
    @FXML
    private AnchorPane paneFarRight;
    @FXML
    private ImageView profileImage;
    @FXML
    private TableColumn<Film, Integer> Réa;
    @FXML
    private FontAwesomeIconView refresh;
    @FXML
    private TextField s;
    @FXML
    private TableColumn<Film, String> Image;
    @FXML
    private TableColumn<Film, String> Description;
    @FXML
    private ImageView imageView;
    @FXML
    private TableColumn<Film, Integer> idid;
    @FXML
    private TableColumn<Film, String> mim;
    FilmCRUD fc = new FilmCRUD();
    @FXML
    private Label momo;
   
  
    
    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        loadDate();
    }
    
  

    private void getAddView(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void refreshTable() {

        try {

            filmListe.clear();

            query = "SELECT f.* ,c.mail FROM film f,compte c, realisateur r,client cc where r.NumRea=f.NumRea and c.userName=cc.userName and cc.idC=r.idC";

            preparedStatement = cnx.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Film f = new Film();
                Compte c = new Compte();
                f.setIdF(resultSet.getInt("idF"));
                f.setNomF(resultSet.getString("nomF"));
                f.setGenre(resultSet.getString("Genre"));
                f.setArchive(resultSet.getBoolean("Archive"));
                f.setEtatAcc(resultSet.getString("EtatAcc"));
                f.setNumRea(resultSet.getInt("NumRea"));
                f.setImage(resultSet.getString("Image"));
                f.setDateDispo(resultSet.getTimestamp("dateDispo"));
                f.setDescription(resultSet.getString("Description"));
                c.setMail(resultSet.getString("mail"));

                f.setDuree(resultSet.getInt("duree"));
                filmListe.add(f);
                table.setItems(filmListe);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void loadDate() {
        cnx = MyConnection.getInstance().getCnx();

        refreshTable();
        mim.setCellValueFactory(new PropertyValueFactory<Film, String>("mail"));
        Image.setCellValueFactory(new PropertyValueFactory<Film, String>("Image"));
        idid.setCellValueFactory(new PropertyValueFactory<Film, Integer>("idF"));
        date.setCellValueFactory(new PropertyValueFactory<Film, String>("dateDispo"));
        nom.setCellValueFactory(new PropertyValueFactory<Film, String>("nomF"));
        gen.setCellValueFactory(new PropertyValueFactory<Film, String>("Genre"));
        etat.setCellValueFactory(new PropertyValueFactory<Film, String>("EtatAcc"));
        Réa.setCellValueFactory(new PropertyValueFactory<Film, Integer>("NumRea"));
        Description.setCellValueFactory(new PropertyValueFactory<Film, String>("Description"));
        dure.setCellValueFactory(new PropertyValueFactory<Film, Integer>("duree"));
        Arch.setCellValueFactory(new PropertyValueFactory<Film, Integer>("Archive"));

        Callback<TableColumn<Film, String>, TableCell<Film, String>> cellFoctory = (TableColumn<Film, String> param) -> {
            final TableCell<Film, String> cell = new TableCell<Film, String>() {
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
                        FontAwesomeIconView edittIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        FontAwesomeIconView viewIcon = new FontAwesomeIconView(FontAwesomeIcon.EYE);
                        FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.CALENDAR_CHECK_ALT);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#8a0a0a;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#000000;"
                        );
                        edittIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#000000;"
                        );

                        viewIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#8a0a0a;"
                        );
                        icon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#000000;"
                        );

                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            System.out.println("houniiiiiiii");
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation de suppression du film");
                            alert.setHeaderText(null);
                            alert.setContentText("Vouliez vous vraiment supprimer le film ? ");
                            Optional<ButtonType> action = alert.showAndWait();

                            if (action.get() == ButtonType.OK) {
                                try {
                                    film = table.getSelectionModel().getSelectedItem();
                                    query = "DELETE FROM film WHERE idF =" + film.getIdF();

                                    preparedStatement = cnx.prepareStatement(query);
                                    preparedStatement.executeUpdate();
                                    refreshTable();

                                } catch (SQLException ex) {
                                    System.err.println(ex.getMessage());
                                }
                            }
                        });
                        editIcon.setOnMouseClicked((MouseEvent event1) -> {
// System.out.println("houniiiiiiii");
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation d'acceptation du film");
                            alert.setHeaderText(null);
                            alert.setContentText("Vouliez vous vraiment accepter le film ? ");
                            Optional<ButtonType> action = alert.showAndWait();

                            if (action.get() == ButtonType.OK) {
                                
                                try {
                                    film = table.getSelectionModel().getSelectedItem();
                                    query = "UPDATE film SET EtatAcc='Accepté' WHERE idF= " + film.getIdF();

                                    preparedStatement = cnx.prepareStatement(query);

                                    preparedStatement.executeUpdate();
//                                     List<List<String>>  liste = fc.afficheMail();
//                                       for (int i = 0; i < liste.size(); i++) {
//                                             System.out.println(liste);
//                                            mim.setCellValueFactory(new PropertyValueFactory<Film, Integer>"");
//                                       }

                                    refreshTable();

                                } catch (SQLException ex) {
                                    System.err.println(ex.getMessage());
                                }
                            }
                        });
                        edittIcon.setOnMouseClicked((MouseEvent event1) -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation d'archivage du film");
                            alert.setHeaderText(null);
                            alert.setContentText("Vouliez vous vraiment archiver le film ? ");
                            Optional<ButtonType> action = alert.showAndWait();

                            if (action.get() == ButtonType.OK) {

                                try {
                                    film = table.getSelectionModel().getSelectedItem();
                                    query = "UPDATE film SET Archive=1 WHERE idF= " + film.getIdF();

                                    preparedStatement = cnx.prepareStatement(query);

                                    preparedStatement.executeUpdate();

                                    refreshTable();

                                } catch (SQLException ex) {
                                    System.err.println(ex.getMessage());
                                }
                            }
                        });
                        

                        viewIcon.setOnMouseClicked((MouseEvent event1) -> {
//                            Film selected = table.getSelectionModel().getSelectedItem();
//                             int idd = selected.getIdF();
//                             
//                          productDetails.setProductID(PM.recupProduit(productID));
//                            FXMLLoader loader = new FXMLLoader();
//                            loader.setLocation(getClass().getResource("detailFilm.fxml"));
//                            try {
//                                loader.load();
//                            } catch (IOException ex) {
//                                System.out.println(ex.getMessage());
//                            }
//
//                            Parent parent = loader.getRoot();
//                            Stage stage = new Stage();
//                            stage.setScene(new Scene(parent));
//                            stage.initStyle(StageStyle.UTILITY);
//                            stage.show();
                            FXMLLoader Loader = new FXMLLoader(getClass().getResource("detailFilm.fxml"));

        try {
            Parent root = Loader.load();
            DetailFilmController FilmDetail = Loader.getController();
            Film selected = table.getSelectionModel().getSelectedItem();
        idFilm = selected.getIdF();
        FilmCRUD fc = new FilmCRUD();
        FilmDetail.setFilmID(fc.recupFilm(idFilm));
            
            Scene productDetailScene = new Scene(root);
            Stage cineStage = (Stage) ((Node) event1.getSource()).getScene().getWindow();
            cineStage.setScene(productDetailScene);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
           
                            

                        });
                        
                        
                        icon.setOnMouseClicked((MouseEvent event1) -> {
                        
                        
                            });

                        HBox managebtn = new HBox(editIcon, deleteIcon, edittIcon, viewIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(viewIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(viewIcon, new Insets(2, 2, 0, 3));
                       // HBox.setMargin(icon, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };

        editcol.setCellFactory(cellFoctory);
        table.setItems(filmListe);

    }

    @FXML
    private void refreshr(MouseEvent event) {
        refreshTable();
    }

    @FXML
    private void search(ActionEvent event) {
        filmListe.setAll();
        int n = 0;

        try {

            String req = "SELECT * FROM film WHERE nomF = ? ";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, String.valueOf(s.getText()));
            ResultSet res = pst.executeQuery();
            Film f = new Film();
            while (res.next()) {
                n = 2;

                System.out.println("lenaaa");
                f.setIdF(res.getInt("idF"));
                f.setNomF(res.getString("nomF"));
                f.setGenre(res.getString("Genre"));
                f.setArchive(res.getBoolean("Archive"));
                f.setEtatAcc(res.getString("EtatAcc"));
                f.setNumRea(res.getInt("NumRea"));
                f.setImage(res.getString("Image"));
                f.setDescription(res.getString("Description"));
                f.setDateDispo(res.getTimestamp("DateDispo"));
                f.setDuree(res.getInt("duree"));
                filmListe.add(f);
                table.setItems(filmListe);

                table.setItems(filmListe);
            }
            Image.setCellValueFactory(new PropertyValueFactory<Film, String>("Image"));
            date.setCellValueFactory(new PropertyValueFactory<Film, String>("dateDispo"));
            nom.setCellValueFactory(new PropertyValueFactory<Film, String>("nomF"));
            gen.setCellValueFactory(new PropertyValueFactory<Film, String>("Genre"));
            etat.setCellValueFactory(new PropertyValueFactory<Film, String>("EtatAcc"));
            Réa.setCellValueFactory(new PropertyValueFactory<Film, Integer>("NumRea"));
            Description.setCellValueFactory(new PropertyValueFactory<Film, String>("Description"));
            dure.setCellValueFactory(new PropertyValueFactory<Film, Integer>("duree"));
            Arch.setCellValueFactory(new PropertyValueFactory<Film, Integer>("Archive"));
            table.setItems(filmListe);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        if (n == 0) {
            Alert alt = new Alert(Alert.AlertType.ERROR, "il n'y a pas un film de ce nom", javafx.scene.control.ButtonType.OK);
            alt.showAndWait();
            loadDate();
        }
    }
     
   
}
