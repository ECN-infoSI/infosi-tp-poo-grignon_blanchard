package org.centrale.objet.woe.tp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Monstre : Loup
 * @author simon
 */
public class Loup extends Monstre {
    /**
     * Constructeur manuel de Loup  
     * @param pV    Points de vie
     * @param dA    Attaque
     * @param pPar  Parade
     * @param paAtt Page Attaque
     * @param paPar Page Parade
     * @param p     Position
     */
    public Loup (int pV, int dA, int pPar, int paAtt, int paPar, Point2D p){
        super(pV, dA, pPar, paAtt, paPar,p); 
    }
    
    
    /**
     * Constructeur de copie
     * @param l Instance de loup à copier
     */
    public Loup(Loup l){ 
        super(l); 
    }
    
    
    /**
     * Constructeur par défaut
     */
    public Loup() {
        super();
    }
}
