/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.woe.objet.tp;

import java.util.Random;
import java.util.Date;

/**
 *
 * @author simon
 */
public class Archer extends Personnage implements Combattant {
    private int nbFleches;
    
    public Archer() {
        super("Archer Ennemi");
        nbFleches = 3;
        distAttMax = 10;
        degAtt = 5;
    }
    
    /**
     * Constructeur semi-manuel où seul le nom peut être défini
     * @param nom Nom de l'Archer
     * @author simon
     */
    public Archer(String nom) {
        super(nom);
        nbFleches = 3;
        distAttMax = 10;
        degAtt = 5;
    }
    
    public Archer(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p, int nbFleches, int sexe) {
        super(n, pV, dA, pPar, paAtt, paPar, dMax, p, sexe);
        this.nbFleches = nbFleches;
    }
    
    public Archer(Archer a) {
        super((Personnage) a);
        this.nbFleches = a.getNbFleches();
    }
    
    /**
     * Attaque à distance pour la classe Archer
     * @param c Créature à attaquer (peut être hors de portée)
     * @returns Flag si la cible est tuée dans l'attaque
     */
    public boolean combattre(Creature c) {
        Date date = new Date();
        Random rand = new Random(date.getTime());
        double dist = Point2D.distance(pos, c.pos);
        String nomAdversaire;
        boolean isKilled = false;
        
        // Détermination du nom de la créature adverse
        if (c instanceof Personnage) {
            nomAdversaire = ((Personnage) c).getNom();
        }
        else {
            nomAdversaire = c.getClass().getSimpleName() +  " sauvage";
        }
        
        System.out.print("# " + this.nom + " attaque " + nomAdversaire);
        
        // Vérification que l'adversaire n'est pas hors de portée
        if (dist > 1 && dist <= distAttMax) {
            // Tirage pour décider si l'attaque réussi
            int tirrageAtt = rand.nextInt(100) + 1;
            
            nbFleches --;  
            
            if (tirrageAtt <= pageAtt && nbFleches > 0) {
                System.out.println(" : L'attaque a réussi ! (" + tirrageAtt + ')');
                
                isKilled = c.prendreDegats(degAtt);
            } 
            else {
                System.out.println(" : L'attaque a échoué (" + tirrageAtt + ')');
            }
        }
        else {
            System.out.println(" : La créature est hors de portée");
        }
        
        return isKilled;
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
