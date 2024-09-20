/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.woe.tp;

/**
 * Sous-classe de Personnage représentant les guerriers
 * @author grigm
 */
public class Guerrier extends Personnage {
    
    /**
     * Constructeur par défaut
     * @author grigm
     */
    public Guerrier() {
        super();
    }
    
    /**
     * Constructeur 
     * @author grigm
     * @param n     Nom du personnage 
     * @param pV    Nombre de points de vie
     * @param dA    Nombre de points d'attaque
     * @param pPar  Nombre de points de parade
     * @param paAtt Page attaque
     * @param paPar Page parade
     * @param dMax  Distance d'attaque maximale
     * @param p     Position
     */
    public Guerrier(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p) {
        super(n, pV, dA, pPar, paAtt, paPar, dMax, p);
    }
    
    /**
     * Constructeur par copie d'un guerrier déjà existant
     * @author grigm
     * @param g Instance de Guerrier à copier
     */
    public Guerrier(Guerrier g) {
        super((Personnage) g);
    }
    
}
