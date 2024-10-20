/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.centrale.woe.objet.tp;

/**
 * Interface pour utiliser certains objets, nourriture, armes...
 * @author grigm
 */
public interface Utilisable {
       
    /**
     * Utilise l'objet en activant son effet
     * @author grigm
     * @param c représentant a créature qui active l'objet
     */
    public void utilise(Creature c);
    
    public void affiche(); 

    public int getDureeEffet();

    public void setDureeEffet(int duree);
    
    public void afficheEffet(); 
        
}
