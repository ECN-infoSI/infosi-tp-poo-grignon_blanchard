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
     * @author simon
     */
    public Loup() {
        super();
    }
    
    
    /**
     * methode combattre (mode de combat au corps à corps)
     * @author simon
     * @param c Creature attaqué
     */
    public void combattre(Creature c){ 
        Date date1= new Date();
        Random rand = new Random(date1.getTime()); 
        
        if (rand.nextInt(100) > this.pageAtt) { 
            System.out.println("Attaque ratée");
        }
        else { 
            System.out.println("Attaque réussie");
        }
        
    }
        
    
        
      
 
}
