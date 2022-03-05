/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.GUI;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.AttributedCharacterIterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.print.attribute.HashPrintRequestAttributeSet;
import edu.cinePro.services.Metiers;
import edu.cinePro.services.PanierCRUD;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class FactureController implements Initializable {

    @FXML
    public TextField idF;
    @FXML
    private Button idPayer;
    @FXML
    private Button rtr;
    @FXML
    private Button imp;
    public String nom;
    @FXML
    private Label idFacture;
    @FXML
    private Label Total;
    @FXML
    private Label Date;

    @FXML
    private ImageView img;

    @FXML
    private ScrollPane scroll;
    private final Label jobStatus = new Label();
    HBox jobStatusBox = new HBox(5, new Label("Job Status: "), jobStatus);
    @FXML
    private ImageView impimg;
  
    @FXML
    private Button nosFilmsButtons;
    @FXML
    private Button nosPublicationsButton;
    @FXML
    private Button nosEvenementsButton;
    @FXML
    private Button réservationsButton;
    @FXML
    private Button shouvenirShopButton;
    @FXML
    private Button donationButton;
    @FXML
    private Button parametreButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // nom= (String) idF.getCharacters() ; 
        img.setVisible(false);
        imp.setVisible(false);
        String l = idF.getText();
        //String l2 = l;*
        idF.setVisible(false);
        idFacture.setVisible(false);
        Total.setVisible(false);
        Date.setVisible(false);
        idPayer.setVisible(false);
        impimg.setVisible(false);
      

        //idFacture.setText("ll");
    }

    public void setIdF(String idF) {
        this.idF.setText(idF);
    }

    public String getIdF() {
        return idF.toString();
    }

    public void setIdFacture(String idFacture) {
        this.idFacture.setText(idF.getText());
    }

    public String khraa() {
        CharSequence l = idF.getText();
        String l2 = (String) l.subSequence(3, 17);
        return l2;

    }

    public String getNom() {
        return nom = "ss";
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Button getImp() {
        return imp;
    }

    @FXML
    private void retour(ActionEvent event) {
//        PanierCRUD pc = new PanierCRUD();
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterAchat.fxml"));
//            Parent root = loader.load();
//            AjoutProduitAuPanierController f = loader.getController();
//            f.ConsulterListeAchat(event);
//            //ActionEvent event1 = event;
//            //idF.getScene().setRoot(root);
//        } catch (IOException ex) {
//            Logger.getLogger(FactureController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        //System.out.println(idF.getText());
        idFacture.setText(idF.getText().substring(19, 21));
        Total.setText(idF.getText().substring(65, 72) + " " + "DT");
        Date.setText(idF.getText().substring(36, 46));
        //idF.setVisible(true);
        idFacture.setVisible(true);
        Total.setVisible(true);
        Date.setVisible(true);
        idPayer.setVisible(true);
        img.setVisible(true);
       
        rtr.setVisible(false);
    }

    @FXML
    private void Paiement(ActionEvent event) {
         PanierCRUD pc = new PanierCRUD();
        Hyperlink hyperlink = new Hyperlink("hyperlink");
        idPayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.paypal.com/bizsignup/#/checkAccount"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
                 pc.Update();
            }

        });
        //hyperlink.setOnAction((EventHandler<ActionEvent>) event);
        imp.setVisible(true);
        impimg.setVisible(true);

    }

    @FXML
    private void imprimer(ActionEvent event) throws PrinterException {

        Graphics graphics = null; //System.out.println(idF.getCharacters());
        PageFormat pageFormat = null;
        imp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println(idF.getCharacters());
                  Stage owner = null;
                 printSetup(scroll,owner);
            }
            private void printSetup(Node node, Stage owner) 
    {
        // Create the PrinterJob        
        PrinterJob job = PrinterJob.createPrinterJob();
     
        if (job == null) 
        {
            return;
        }
 
        // Show the print setup dialog
        boolean proceed = job.showPrintDialog(owner);
         
        if (proceed) 
        {
            print(job, node);
        }
    }

            private void print(PrinterJob job, Node node) {
                // Set the Job Status Message
                jobStatus.textProperty().bind(job.jobStatusProperty().asString());

                // Print the node
                boolean printed = job.printPage(node);

                if (printed) {
                    job.endJob();
                }
            }
        });

        //print(graphics, pageFormat, PAGE_EXISTS);
        //nom=(String) idF.getCharacters() ;
        //imp.setVisible(false);
    }

//    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
//        // Par défaut, retourne NO_SUCH_PAGE => la page n'existe pa
//        CharSequence l =idF.getText() ;
//       String l2 = (String) l.subSequence(3,17);
//        System.out.println(l2);
//        
//        int retValue = Printable.NO_SUCH_PAGE;
//        //System.out.println(idF.getCharacters());
//        switch (pageIndex) {
//            case 0: {
//                // Dessin de la première page
//                // Récupère la dimension de la zone imprimable
//                double xLeft = pageFormat.getImageableX();
//                double yTop = pageFormat.getImageableY();
//                double width = pageFormat.getImageableWidth();
//                double height = pageFormat.getImageableHeight();
//
//                // Dessine le rectangle
//                graphics.setColor(Color.BLACK);
//                graphics.drawRect((int) xLeft,
//                        (int) yTop,
//                        (int) width,
//                        (int) height);
//              graphics.drawString(l2, 100, 100);
//               
//               
//               
//                // La page est valide
//                retValue = Printable.PAGE_EXISTS;
//                break;
//            }
//            case 1: {
//                // Dessin de la seconde page
//                // Récupère la dimension de la zone imprimable
//                double xLeft = pageFormat.getImageableX();
//                double yTop = pageFormat.getImageableY();
//                double width = pageFormat.getImageableWidth();
//                double height = pageFormat.getImageableHeight();
//
//                // Dessine l'ellipse
//                graphics.setColor(Color.BLACK);
//                graphics.drawOval((int) xLeft,
//                        (int) yTop,
//                        (int) width,
//                        (int) height);
//                // La page est valide
//                retValue = Printable.PAGE_EXISTS;
//                break;
//            }
//        }
//        return retValue;
//    } 
//    public static void main(String[] args) {
//        java.awt.print.PrinterJob printJob = java.awt.print.PrinterJob.getPrinterJob();
//         HashPrintRequestAttributeSet prs = new HashPrintRequestAttributeSet();
//        if (printJob.printDialog(prs)) {
//            PageFormat pageFormat
//                    = printJob.getPageFormat(prs);
//            printJob.setPrintable((Printable) new FactureController());
//            try {
//                printJob.print(prs);
//            } catch (PrinterException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @FXML
    private void showFilmsPage(ActionEvent event) {
    }

    @FXML
    private void showPublicationPage(ActionEvent event) {
    }

    @FXML
    private void showEvenementsPage(ActionEvent event) {
    }

    @FXML
    private void showReservationPage(ActionEvent event) {
         FXMLLoader Loader = new FXMLLoader(getClass().getResource("billet.fxml"));

        try {
            Parent root = Loader.load();
            BilletController billets = Loader.getController();
            Total.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(BilletController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showSouvenirShopPage(ActionEvent event) {
         FXMLLoader Loader = new FXMLLoader(getClass().getResource("productClient.fxml"));

        try {
            Parent root = Loader.load();
            ProductClientController products = Loader.getController();
            Total.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProductClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showDonationButton(ActionEvent event) {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("don.fxml"));

        try {
            Parent root = Loader.load();
            DonController dons = Loader.getController();
            Total.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(DonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showParametrePage(ActionEvent event) {
    }
}
