/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.woe.objet.tp;

/**
 *
 * @author simon
 */
public class Paysan extends Personnage {
    public Paysan() {
        super();
    }
    
    /**
     * Constructeur semi-manuel où seul le nom peut être défini
     * @param nom Nom du Paysan
     * @author simon
     */
    public Paysan(String nom) {
        super(nom);
    }
    
    public Paysan(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p, int sexe) {
        super(n, pV, dA, pPar, paAtt, paPar, dMax, p, sexe);
    }
    
    public Paysan(Paysan p) {
        super((Personnage) p);
    }
}