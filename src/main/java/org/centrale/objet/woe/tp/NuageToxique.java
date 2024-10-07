/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.woe.tp;

import java.util.Date;
import java.util.Random;

/**
 * Sous classe Objet représentant un nuage toxique
 * @author grigm
 */
public class NuageToxique extends Objet implements Deplacable, Combattant{
    
    int nivToxique; //nombre dégat que prend le joueur s'il est sous le nuage toxique 
    
    /**
     * Constructeur par défaut (nivToxique =5)
     * @author grigm
     */
    public NuageToxique(){
        super(); 
        nivToxique = 5; 
    }
    
    /**
     * Constructeur imposer le nombre de dégat du nuage toxique 
     * @author grigm
     * @param nb nombre de dégat du nuage toxique 
     */
    public NuageToxique(int nb){
        super(); 
        nivToxique = nb; 
    }
    
    
    /**
     * méthode pour déplacer le nuage toxique sur une position définie 
     * @author grigm
     * @throws java.lang.Exception
     * @param newPos la position qu'on veut atteindre
     * @param presences représente le tableau du plateau de jeu avec des vrais quand la case est occupée
     */
    @Override
    public void deplacer(Point2D newPos, boolean[][] presences) throws Exception {
        // Déplacement à l'intérieur du monde
        if (newPos.getX() < presences.length && newPos.getY() < presences[0].length && 0 <= newPos.getX() && 0 <= newPos.getY()) {
            // Déplacement sur une case libre
            if (presences[newPos.getX()][newPos.getY()]) {
                // La cellule associée à la position précedente est indiquée comme libre
                presences[pos.getX()][pos.getY()] = false;

                // Déplacement du nuage
                pos = newPos;

                // La cellule associée à la nouvelle position est indiquée comme libre
                presences[pos.getX()][pos.getY()] = true;
            }
            else {
                throw new Exception("Mouvement vers une case occupée !");
            }
        }
        else {
            throw new Exception("Mouvement en dehors du monde !");
        }
    }
    
    
    /**
     * méthode pour déplacer le nuage toxique aléatoirement sur une case adjacente (diagonale possible)
     * @author grigm
     * @paramn presences représente le tableau du plateau de jeu avec des vrais quand la case est occupée
     */
    @Override
    public void deplacementAleatoire(boolean[][] presences) {
        Date date = new Date();
        Random rand = new Random(date.getTime());
        
        int dx, dy;
        
        // On génère un vecteur-déplacement jusqu'à qu'il soit valide
        do {
            dx = rand.nextInt(3) - 1;
            dy = rand.nextInt(3) - 1;
        }
        while (pos.getX() + dx < presences.length && pos.getY() + dy < presences[0].length // Déplacement à l'intérieur du monde (borne sup.)
                && 0 <= pos.getX() + dx && 0 <= pos.getY() + dy  // Déplacement à l'intérieur du monde (borne inf.) 
                && presences[pos.getX() + dx][pos.getY() + dy]);  // Déplacement sur une cellule libre
        
        // La cellule associée à la position précedente est indiquée comme libre
        presences[pos.getX()][pos.getY()] = false;
        
        // Déplacement du nuage toxique
        pos.translate(dx, dy);
        
        // La cellule associée à la nouvelle position est indiquée comme occupée
        presences[pos.getX()][pos.getY()] = true;
    }
    
    
    /**
     * Méthode pour permettre au nuage toxique d'attaquer les créatures (inflige autant de dégat que nivToxique)
     * @param c Créature à attaquer
     */
    @Override
    public void combattre(Creature c) {
                      
        // Vérification que la créature est en dessous du nuage
        if (this.pos.equals(c.pos)) {
            c.prendreDegats(nivToxique); 
        }
        else {
            System.out.println("La créature n'est pas sous le nuage toxique");
        }
    }
}
