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
    
    /**
     * Attaque au corps à corps pour la classe Guerrier
     * @param c Créature à attaquer
     */
    public void combattre(Creature c) {
        Date date = new Date();
        Random rand = new Random(date.getTime());
        
        // Vérification que l'adversaire est sur une case adhacente
        // Comme sqrt(2) = 1.414, dist <= 1.4 permet de prendre en compte les erreurs dans le codage des flottants
        if (Point2D.distance(pos, c.pos) <= 1.4) {
            // Tirage pour décider si l'attaque réussi
            int tirrageAtt = rand.nextInt(100);
            
            if (tirrageAtt <= pageAtt) {
                System.out.println(tirrageAtt + " : L'attaque réussi !");
                
                // Tirage pour savoir si la créature adverse pare le coup
                int tirragePar = rand.nextInt(100);
                
                if (tirragePar <= c.pagePar) {
                    System.out.println(tirragePar + " : L'adversaire pare le coup !");
                    
                    c.prendreDegats(degAtt - c.ptPar);
                }
                else {    
                    System.out.println(tirragePar + " : L'adversaire ne pare pas le coup !");
                    
                    c.prendreDegats(degAtt);
                }
            } 
            else {
                System.out.println(tirrageAtt + " : L'attaque a échoué");
            }
        }
        else {
            System.out.println("La créature est hors de portée");
        }
    }
}
