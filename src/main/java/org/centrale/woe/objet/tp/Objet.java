/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.woe.objet.tp;

/**
 * Super-classe représentant les objets
 * @author grigm
 */
public class Objet extends ElementDeJeu{
    private boolean estdansinventaire; 
           
        
    /**
     * Constructeur par défaut
     * @author grigm
     */
    public Objet(){
        super(); 
        estdansinventaire =false; 
    }
    
    
    /**
     * méthode affiche la position de l'objet
     * @author grigm
     */
    public void affiche(){ 
        System.out.println(this.getClass().getSimpleName() + " Objet(" + this.pos.getX() +","+this.pos.getY()+")");
    }

    public boolean isEstdansinventaire() {
        return estdansinventaire;
    }

    public void setEstdansinventaire(boolean estdansinventaire) {
        this.estdansinventaire = estdansinventaire;
    }

    
    
    
}
