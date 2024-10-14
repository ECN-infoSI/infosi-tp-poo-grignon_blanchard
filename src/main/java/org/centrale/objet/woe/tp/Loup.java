package org.centrale.objet.woe.tp;
import java.util.Date;
import java.util.Random; 

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Monstre : Loup
 * @author simon
 */
public class Loup extends Monstre implements Combattant {
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
     * @author simon
     */
    public Loup() {
        super();
    }
    
    
    /**
     * Attaque au corps à corps pour la classe Loup
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
        
        System.out.print("# Loup sauvage attaque " + nomAdversaire);
        
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
