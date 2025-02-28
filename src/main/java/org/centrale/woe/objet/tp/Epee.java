/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.woe.objet.tp;

/**
 * sous-classe Objet représentant les épées 
 * @author grigm
 */
public class Epee extends Objet implements Utilisable{
    private int dureeEffet; 
    /**
     * Constructeur par défaut
     * @author grigm
     */
    public Epee(){
        super();
        dureeEffet = 10; 
    }
    
    /**
     * méthode pour activer l'objet et modifier les caractéristiques du personnage
     * on suppose que l'épée augmente l'attaque de 10 points
     * @author grigm
     * @param c représentant l'instance de Creature qui active l'objet
     */
    @Override
    public void utilise(Creature c) {
        // on ajoute 10 points d'attaque à la créature
        c.setDegAtt(c.degAtt+10); 
        
    }
    
    /**
     * méthode pour retirer l'effet au personnage
     * on suppose que l'épée augmente l'attaque de 10 points
     * @author grigm
     * @param c représentant l'instance de Creature qui active l'objet
     */
    public void retireEffet(Creature c) {
        // on retire 10 points d'attaque à la créature
        c.setDegAtt(c.degAtt-10); 
        
    }

    public int getDureeEffet() {
        return dureeEffet;
    }

    public void setDureeEffet(int dureeEffet) {
        this.dureeEffet = dureeEffet;
    }
    
    
    /**
     * méthode pour afficher l'effet de l'objet au moment de son activation
     * @author grigm
     */
    public void afficheEffet(){ 
        System.out.println("Epee + 10 ptAtt pendant 10 tours");
    }
    
}
