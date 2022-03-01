/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author 21653
 */
public class Etudiant {
    private int numinscri;
    private int idc;

    public Etudiant() {
    }

    public Etudiant(int NumInscri, int idC) {
        this.numinscri = NumInscri;
        this.idc= idC;
    }

    

    public int getNumInscri() {
        return numinscri;
    }

    public void setNumInscri(int NumInscri) {
        this.numinscri = NumInscri;
    }

    public int getIdC() {
        return idc;
    }

    public void setIdC(int idC) {
        this.idc = idC;
    }

    @Override
    public String toString() {
        return "Etudiant{" + "NumInscri=" + numinscri + ", idC=" + idc + '}';
    }
    

    
    
    
    
    
}
