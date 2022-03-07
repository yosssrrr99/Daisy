/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.entities;

/**
 *
 * @author sarra
 */
public class Signale {
    private int idSignal;
    private int idClient;
    private int idPub;
    private int nbreSignal;
    
    public Signale() {
    } 

    public Signale(int idClient, int idPub, int nbreSignal) {
        this.idClient = idClient;
        this.idPub = idPub;
        this.nbreSignal = nbreSignal;
    }

    public int getIdSignal() {
        return idSignal;
    }

    public Signale(int idPub, int nbreSignal) {
        this.idPub = idPub;
        this.nbreSignal = nbreSignal;
    }


    public int getIdClient() {
        return idClient;
    }

    public int getIdPub() {
        return idPub;
    }

    public int getNbreSignal() {
        return nbreSignal;
    }

    public void setIdSignal(int idSignal) {
        this.idSignal = idSignal;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setIdPub(int idPub) {
        this.idPub = idPub;
    }

    public void setNbreSignal(int nbreSignal) {
        this.nbreSignal = nbreSignal;
    }

    @Override
    public String toString() {
        return "Signale{" + "idSignal=" + idSignal + ", idClient=" + idClient + ", idPub=" + idPub + ", nbreSignal=" + nbreSignal + '}';
    }

    
    
    
    
}
