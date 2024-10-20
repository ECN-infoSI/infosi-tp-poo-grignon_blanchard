/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.centrale.woe.objet.tp;

import java.util.ArrayList;

/**
 * Interface pour gérer les déplacements des créatures
 * @author simon
 */
public interface Deplacable {    
    /**
     * Déplacement aléatoire d'une créature
     * @param presences Tableau recensant les positions des créatures
     * @author simon
     */
    public void deplacementAleatoire(boolean[][] presences);
    
    /**
     * Déplacement manuel d'une créature
     * @param newPos    Nouvelle position (pas nécessairement licite)
     * @param presences Tableau recensant les positions des créatures
     * @param presencesObjet Tableau recensant les positions des objets 
     * @param listObjets    Liste des objets du monde
     * @param inventaire    inventaire du joueur 
     */
    public void deplacer(Point2D newPos, boolean[][] presences, boolean[][] presencesObjet, ArrayList<Objet> listObjets, ArrayList<Objet> inventaire) throws Exception;
}
