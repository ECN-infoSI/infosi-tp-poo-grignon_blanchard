/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.centrale.objet.woe.tp;

import java.util.Random;
import java.util.Date;

/**
 * Super-classe représentant les monstres 
 * @author grigm
 */
public class Monstre extends Creature{
    
    
    /**
     * Constructeur
     * @param pV Nombre de point de vie 
     * @param dA Nombre de point d'attaque
     * @param pPar Nombre de point de parade
     * @param paAtt page attage
     * @param paPar page parade 
     * @param p Postion
     */
    public Monstre(int pV, int dA, int pPar, int paAtt, int paPar, Point2D p){ 
        super( pV, dA, pPar, paAtt, paPar, p); 
       
    }
   
    /**
     * Constructeur par copie d'un Monstre déjà existant
     * @author grigm
     * @param m Instance de Monstre à copier
     */
    public Monstre(Monstre m){ 
        super((Creature) m);
        
    }
    
    
    /**
     * Constructeur par défaut
     * @author grigm
     */
    public Monstre() { 
        super();  
    }
    
    
    
}
