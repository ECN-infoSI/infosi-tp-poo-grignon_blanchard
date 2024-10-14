/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.woe.tp;

import java.util.Date;
import java.util.Random;

/**
 * Sous-classe de Personnage représentant les guerriers
 * @author grigm
 */
public class Guerrier extends Personnage implements Combattant {
    
    /**
     * Constructeur par défaut
     * @author grigm
     */
    public Guerrier() {
        super();
    }
    
    /**
     * Constructeur semi-manuel où seul le nom peut être défini
     * @param nom Nom du Guerrier
     * @author simon
     */
    public Guerrier(String nom) {
        super(nom);
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
     * @param sexe  Sexe
     */
    public Guerrier(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p, int sexe) {
        super(n, pV, dA, pPar, paAtt, paPar, dMax, p, sexe);
    }
    
    /**
     * Constructeur par copie d'un guerrier déjà existant
     * @author grigm
     * @param g Instance de Guerrier à copier
     */
    public Guerrier(Guerrier g) {
        super((Personnage) g);
    }
    
    /**
     * Attaque au corps à corps pour la classe Guerrier
     * @param c Créature à attaquer
     * @returns Flag si la cible est tuée dans l'attaque
     */
    public boolean combattre(Creature c) {
        Date date = new Date();
        Random rand = new Random(date.getTime());
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
        
        // Vérification que l'adversaire est sur une case adhacente
        // Comme sqrt(2) = 1.414, dist <= 1.4 permet de prendre en compte les erreurs dans le codage des flottants
        if (Point2D.distance(pos, c.pos) <= 1.4) {
            // Tirage pour décider si l'attaque réussi
            int tirrageAtt = rand.nextInt(100) + 1;
            
            if (tirrageAtt <= pageAtt) {
               System.out.print(" : L'attaque a réussi (" + tirrageAtt + ')');
                
                // Tirage pour savoir si la créature adverse pare le coup
                int tirragePar = rand.nextInt(100) + 1;
                
                if (tirragePar <= c.pagePar) {
                    System.out.println(" mais l'adversaire pare le coup ! (" + tirragePar + ')');
                    
                    isKilled = c.prendreDegats(degAtt - c.ptPar);
                }
                else {    
                    System.out.println(" et l'adversaire ne pare pas le coup ! (" + tirragePar + ')');
                    
                    isKilled = c.prendreDegats(degAtt);
                }
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
}
