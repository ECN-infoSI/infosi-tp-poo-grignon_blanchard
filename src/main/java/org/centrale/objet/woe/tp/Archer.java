/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.woe.tp;

import java.util.Random;
import java.util.Date;

/**
 *
 * @author simon
 */
public class Archer extends Personnage{
    private int nbFleches;
    
    public Archer() {
        super();
        nbFleches = 3;
    }
    
    public Archer(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p, int nbFleches) {
        super(n, pV, dA, pPar, paAtt, paPar, dMax, p);
        this.nbFleches = nbFleches;
    }
    
    public Archer(Archer a) {
        super((Personnage) a);
        this.nbFleches = a.getNbFleches();
    }
    
    /**
     * Attaque à distance pour la classe Archer
     * @param c Créature à attaquer (peut être hors de portée)
     */
    public void combattre(Creature c) {
        Date date = new Date();
        Random rand = new Random(date.getTime());
        double dist = Point2D.distance(pos, c.pos);
        
        // Vérification que l'adversaire n'est pas hors de portée
        if (dist > 1 && dist <= distAttMax) {
            // Tirage pour décider si l'attaque réussi
            if (rand.nextInt() <= pageAtt && nbFleches > 0) {
                System.out.println("L'attaque a réussi !");
                
                c.prendreDegats(degAtt);
                nbFleches --;  
            } 
            else {
                System.out.println("L'attaque a échoué");
            }
        }
        else {
            System.out.println("La créature est hors de portée");
        }
    }
    
    /**
     * Affiche l'ensemble des statistiques du Personnage
     * @author simon
     */
    @Override
    public void affiche() {
        System.out.println(nom + " " + ptVie + " (" + pos.getX() + ", " + pos.getY() + ")");
        System.out.println("Nombre de Flèches : " + nbFleches);
        System.out.println("Att. : " + degAtt);
        System.out.println("Par. : " + ptPar);
        System.out.println("Pourcentage Att. : " + pageAtt);
        System.out.println("Pourcentage Par. : " + pagePar);
        System.out.println("Dist. Att. Max : " + distAttMax);
        
    }

    public int getNbFleches() {
        return nbFleches;
    }

    public void setNbFleches(int nbFleches) {
        this.nbFleches = nbFleches;
    }
}
