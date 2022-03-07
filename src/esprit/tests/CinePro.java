/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.tests;

import esprit.entities.Presse;
import esprit.entities.Publication;
import esprit.entities.Signale;
import esprit.services.PresseCRUD;
import esprit.services.PublicationCRUD;
import esprit.services.SendMail;
import esprit.services.SignaleCRUD;
import esprit.utils.MyConnection;
import static java.time.LocalDate.now;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author sarra
 */
public class CinePro {

    private final static Logger logger = Logger.getLogger(CinePro.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SchedulerException, Exception {

        //MyConnection con = new MyConnection();
        //ajout,suppression,modification,consultation de publications
//        Publication pub1 = new Publication("imag3", "textesarah3", 1, false);
//
//        Publication pub3 = new Publication("imag3", "textesarah3", 1, false);
//        Publication pub4 = new Publication("imag", "textesarah", 2, true);
//        Publication pub6 = new Publication("salmakkk", "yasmbjline", 16);
////
//        PublicationCRUD pcd = new PublicationCRUD();
//      // pcd.ajouterPublication(pub6);
//        pcd.ajouterPublication(pub1);

//partie archivage de publication
//        BasicConfigurator.configure();
//        logger.info("Info log message");
//        try {
//
//            JobDetail job = newJob(PublicationCRUD.class)
//                    .withIdentity("job1", "group1")
//                    .build();
//
//            Trigger trigger = TriggerBuilder.newTrigger()
//                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//                            .withIntervalInSeconds(10)
//                            .repeatForever())
//                    .build();
//
//            SchedulerFactory schFactory = new StdSchedulerFactory();
//            Scheduler sch = schFactory.getScheduler();
//            sch.start();
//            sch.scheduleJob(job, trigger);
//            //sch.shutdown();
//
//        } catch (SchedulerException se) {
//            se.printStackTrace();
//        }
       

        //pcd.supprimerPublication(7);
        //pcd.modifierPublication(8, "yosr", "sahnoun");
        // System.out.println(pcd.consulterPublicationArchive());
        //ajout de signals
//        Signale sig = new Signale(2, 55, 1);
//        SignaleCRUD scd = new SignaleCRUD();
//        scd.supprimerPubParSignale();
//
//        scd.ajouterSignale(sig);
//ajout de presse 
//        Presse press = new Presse("mosaique", false);
//        PresseCRUD prcd = new PresseCRUD();
        //prcd.ajouterPresse(press);
//System.out.println(prcd.afficherListePresse());
    //}
//     public static void configure() {
//    Logger root = Logger.getRootLogger();
//    root.addAppender(new ConsoleAppender(new PatternLayout("%r [%t] %p %c %x - %m%n")));
//}   
    
    
    //SendMail.sendMail("yosr.sahnoun1@esprit.tn","hellooo");

}
}