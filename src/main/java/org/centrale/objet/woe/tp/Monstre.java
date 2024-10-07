/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.centrale.objet.woe.tp;

import java.util.Date;
import java.util.Random;


/**
 * Super-classe représentant les monstres 
 * @author grigm
 */
public abstract class Monstre extends Creature{
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
    
    public void deplacementAleatoire(boolean[][] presences) {
        Date date = new Date();
        Random rand = new Random(date.getTime());
        
        int dx, dy;
        
        // On génère un vecteur-déplacement jusqu'à qu'il soit valide
        do {
            dx = rand.nextInt(3) - 1;
            dy = rand.nextInt(3) - 1;
        }
        while (pos.getX() + dx < presences.length && pos.getY() + dy < presences[0].length // Déplacement à l'intérieur du monde (borne sup.)
                && 0 <= pos.getX() + dx && 0 <= pos.getY() + dy  // Déplacement à l'intérieur du monde (borne inf.) 
                && presences[pos.getX() + dx][pos.getY() + dy]);  // Déplacement sur une cellule libre
        
        // La cellule associée à la position précedente est indiquée comme libre
        presences[pos.getX()][pos.getY()] = false;
        
        // Déplacement de l'archer
        pos.translate(dx, dy);
        
        // La cellule associée à la nouvelle position est indiquée comme libre
        presences[pos.getX()][pos.getY()] = true;
    }
}
