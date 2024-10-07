/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.centrale.objet.woe.tp;

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
     */
    public void deplacer(Point2D newPos, boolean[][] presences) throws Exception;
}
