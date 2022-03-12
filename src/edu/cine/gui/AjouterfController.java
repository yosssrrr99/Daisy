/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.cine.entities.Avis;
import edu.cine.entities.Film;
import edu.cine.services.AvisCRUD;
import edu.cine.services.FilmCRUD;
import edu.cine.utils.MyConnection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AjouterfController implements Initializable {

    @FXML
    private TextField fxNum;
    @FXML
    private TextField fxNom;
    @FXML
    private RadioButton btnAct;
    @FXML
    private RadioButton btnDra;
    @FXML
    private RadioButton fxJeu;
    @FXML
    private RadioButton fxCom;
    @FXML
    private TextField fxDes;
    @FXML
    private TextField fxImg;
    @FXML
    private Button btnAj;
    @FXML
    private TextField fxDuree;
    @FXML
    private Label fxG;
    @FXML
    private ToggleGroup Genre;
    @FXML
    private AnchorPane paneFarRight;
    @FXML
    private ImageView profileImage;
    @FXML
    private TableView<Film> table;
    @FXML
    private TableColumn<Film, Integer> idid;
    @FXML
    private TableColumn<Film, String> nom;
    @FXML
    private TableColumn<Film, String> gen;
    
    @FXML
    private TableColumn<Film, String> des;
    @FXML
    private TableColumn<Film, Integer> dure;
    @FXML
    private TableColumn<Film, String>Arch;
   
    @FXML
    private FontAwesomeIconView refresh;
    @FXML
    private FontAwesomeIconView h1;
    @FXML
    private TableColumn<Film, String> date;
    
    
    private FileChooser filechooser= new FileChooser();
    @FXML
    private TextField s;
    @FXML
    private TableColumn<Film, String> edittt;
    @FXML
    private ImageView imageView;
   
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     loadDate();
    }    
    String query = null;
    Connection cnx = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Film film = null;
      ObservableList<Film> filmListe = FXCollections.observableArrayList();
ObservableList<Avis> avisListe = FXCollections.observableArrayList();  

    @FXML
    private void getGenre(ActionEvent event) {
        if(btnAct.isSelected()){
            fxG.setText(btnAct.getText());
        }
        else if(btnDra.isSelected()){
            fxG.setText(btnDra.getText());
        }
        else if(fxJeu.isSelected()){
           fxG.setText(fxJeu.getText());
        }
         else if(fxCom.isSelected()){
           fxG.setText(fxCom.getText());
        }
    }  

    @FXML
    private void saveFilm(ActionEvent event) {
        if (fxNum.getText().isEmpty() || fxNom.getText().isEmpty()  || fxDuree.getText().isEmpty() || fxG.getText().isEmpty() 
                        || fxDes.getText().isEmpty() 
                        || fxImg.getText().isEmpty())
        {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Les champs sont vides");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();

        } else {
        int Num =Integer.parseInt(fxNum.getText());
        String nom=fxNom.getText();
        int Duree=Integer.parseInt(fxDuree.getText());
        String genre =fxG.getText();
        String Des=fxDes.getText();
        String img =fxImg.getText();
        
        Film f = new Film(nom, genre, Num, img, Des, Duree);
        FilmCRUD fc = new FilmCRUD();
        fc.ajouterFilm3(f);
        Avis a = new Avis();
        AvisCRUD ac = new AvisCRUD();
        ac.calculAvis();
        Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Connection");

            alert.setHeaderText(null);
            alert.setContentText("votre film a été ajoutée avec succées !");

            alert.showAndWait();
        }
        
    
}
// @FXML
//    private void getFil(ActionEvent event) {
//         
//                            FXMLLoader loader = new FXMLLoader ();
//                            loader.setLocation(getClass().getResource("AccueilClient.fxml"));
//                            try {
//                                loader.load();
//                            } catch (IOException ex) {
//                                System.out.println(ex.getMessage());
//                            }
//                            
//                            ReservaController addReservController = loader.getController();
//                            addReservController. insertRecord();
//                                                     
//                            Parent parent = loader.getRoot();
//                            Stage stage = new Stage();
//                            stage.setScene(new Scene(parent));
//                            stage.initStyle(StageStyle.UTILITY);
//                            stage.show();
//    }

    private void getAcc(ActionEvent event) {
    
      FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("Acc.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                            
//                            ReservaController addReservController = loader.getController();
//                            addReservController. insertRecord();
                                                     
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
    }
    

    @FXML
    private void refreshr(MouseEvent event) {
        refreshTable();
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
                fxImg.setText(file.getPath());
                System.out.println(path);
            }
        } catch (IOException ex) {
            System.out.println("erreuuuuuuuuuuuuur");        }

    }
    
private void refreshTable() {

       
 try {

            filmListe.clear();

            query = "SELECT * FROM film ";

            preparedStatement = cnx.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
               Film f = new Film();
               f.setIdF(resultSet.getInt("idF"));
              f.setNomF(resultSet.getString("nomF"));
               f.setGenre(resultSet.getString("Genre"));
               f.setArchive(resultSet.getBoolean("Archive"));
               f.setEtatAcc(resultSet.getString("EtatAcc"));
               f.setNumRea(resultSet.getInt("NumRea"));
               f.setImage(resultSet.getString("Image"));
               f.setDescription(resultSet.getString("Description"));
              f.setDateDispo(resultSet.getTimestamp("DateDispo"));
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
         
        idid.setCellValueFactory(new PropertyValueFactory<Film, Integer>("NumRea"));
        nom.setCellValueFactory(new PropertyValueFactory<Film, String>("nomF"));
        gen.setCellValueFactory(new PropertyValueFactory<Film, String>("Genre"));
        Arch.setCellValueFactory(new PropertyValueFactory<Film, String>("Image"));
        date.setCellValueFactory(new PropertyValueFactory<Film, String>("dateDispo"));
                
        des.setCellValueFactory(new PropertyValueFactory<Film, String>("Description"));
        dure.setCellValueFactory(new PropertyValueFactory<Film, Integer>("duree"));
       

    

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
                        //FontAwesomeIconView edittIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        FontAwesomeIconView viewIcon = new FontAwesomeIconView(FontAwesomeIcon.EYE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#000000;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#000000;"
                        );
//                         edittIcon.setStyle(
//                                " -fx-cursor: hand ;"
//                                + "-glyph-size:28px;"
//                                + "-fx-fill:#000000;"
//                        );


                        viewIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#39add1;"
                        );
                        
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            System.out.println("houniiiiiiii");
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation de suppression ");
                            alert.setHeaderText(null);
                            alert.setContentText("Vouliez vous supprimer vraiment le film ? ");
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
                               
                         
                           
                   
                            
                            });
//                        edittIcon.setOnMouseClicked((MouseEvent event1) -> {
////
//                          
//                            try {
//                               film= table.getSelectionModel().getSelectedItem();
//                                query = "UPDATE film SET Archive=1 WHERE idF= " + film.getIdF();
//
//                                preparedStatement = cnx.prepareStatement(query);
//
//                                preparedStatement.executeUpdate();
//
//                                refreshTable();
//
//                            } catch (SQLException ex) {
//                                System.err.println(ex.getMessage());
//                            }
//                            });

                            viewIcon.setOnMouseClicked((MouseEvent event0) -> {

                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("AfficherMoy.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                            
//                            ReservaController addReservController = loader.getController();
//                            addReservController. insertRecord();
                                                     
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
    
                        });


                        HBox managebtn = new HBox(deleteIcon);
                        //managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                      // HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        //HBox.setMargin(viewIcon, new Insets(2, 2, 0, 3));
                        //HBox.setMargin(viewIcon, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };

        edittt.setCellFactory(cellFoctory);
        table.setItems(filmListe);

    }

   

//    private void getarch(ActionEvent event) {
//         FXMLLoader loader = new FXMLLoader ();
//         loader.setLocation(getClass().getResource("Archive.fxml"));
//                            try {
//                                loader.load();
//                            } catch (IOException ex) {
//                                System.out.println(ex.getMessage());
//                            }
//                            
////                         
//                                                     
//                            Parent parent = loader.getRoot();
//                            Stage stage = new Stage();
//                            stage.setScene(new Scene(parent));
//                            stage.initStyle(StageStyle.UTILITY);
//                            stage.show();
// 
    @FXML
    private void updateF(ActionEvent event) {
        
         Film f = table.getSelectionModel().getSelectedItem();
                             String idNum= fxNum.getText();
                                int iddd=Integer.parseInt(idNum);
                             String nom = fxNom.getText().toString();
                                String idd= fxDuree.getText();
                                int id=Integer.parseInt(idd);
                                String g = fxG.getText().toString();
                                String des = fxDes.getText().toString();
                                FilmCRUD fc = new FilmCRUD();
                               
                                Film f2 = new Film();
                             
                                fc.modifierFilm( f.getIdF(),nom, g,fxImg.getText().toString(),iddd, des,id);
                                
                                refreshTable();
                              
    }


   

    @FXML
    private void geta(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader ();
         loader.setLocation(getClass().getResource("Archive.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                            
//                            ReservaController addReservController = loader.getController();
//                            addReservController. insertRecord();
                                                     
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
    }

    @FXML
    private void getFil(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("AccueilClient.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                            
//                            ReservaController addReservController = loader.getController();
//                            addReservController. insertRecord();
                                                     
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
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
             idid.setCellValueFactory(new PropertyValueFactory<Film, Integer>("NumRea"));
        nom.setCellValueFactory(new PropertyValueFactory<Film, String>("nomF"));
        gen.setCellValueFactory(new PropertyValueFactory<Film, String>("Genre"));
        Arch.setCellValueFactory(new PropertyValueFactory<Film, String>("Image"));
        date.setCellValueFactory(new PropertyValueFactory<Film, String>("dateDispo"));
              
        des.setCellValueFactory(new PropertyValueFactory<Film, String>("Description"));
        dure.setCellValueFactory(new PropertyValueFactory<Film, Integer>("duree"));
                table.setItems(filmListe);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            if (n == 0) {
                Alert alt = new Alert(AlertType.ERROR, "il n'y a pas un film de ce nom", javafx.scene.control.ButtonType.OK);
                alt.showAndWait();
                loadDate();
            }
    }

    @FXML
    private void getedit(MouseEvent event) {
        Film f = table.getSelectionModel().getSelectedItem();
        fxNum.setText(String.valueOf(f.getNumRea()));
        fxNom.setText(f.getNomF());
        fxImg.setText(f.getImage());
        fxDuree.setText(String.valueOf(f.getDuree()));
        fxG.setText(String.valueOf(f.getGenre()));
        fxDes.setText(String.valueOf(f.getDescription()));
    }

    
    

}