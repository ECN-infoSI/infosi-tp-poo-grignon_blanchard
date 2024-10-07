/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.woe.tp; 

import java.util.Date;
import java.util.Random;

import java.lang.Exception;

/**
 * Super-classe représentant un Personnage
 * @author simon
 */
public abstract class Personnage extends Creature{
    protected String nom;
    protected int distAttMax;
 
    /**
     * Constructeur par défaut
     * @author simon
     */
    public Personnage() {
        super(); 
        nom = "John Doe";
        distAttMax = 10;
    }
    
    /**
     * Constructeur semi-manuel où seul le nom peut être défini
     * @param nom Nom du Personnage
     * @author simon
     */
    public Personnage(String nom) {
        super(); 
        this.nom = nom;
        distAttMax = 10;
    }
    
    /**
     * Constructeur
     * @param n     Nom du personnage
     * @param pV    Nombre de points de vie
     * @param dA    Nombre de points d'attaque
     * @param pPar  Nombre de points de parade
     * @param paAtt Pourcentage d'attaque
     * @param paPar Pourcentage de parade
     * @param dMax  Distance d'attaque maximale
     * @param p     Position
     */
    public Personnage(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p) {
        super( pV, dA, pPar, paAtt, paPar, p); 
        nom = n;
        distAttMax = dMax;
    }
    
    
    /**
     * Constructeur par copie d'un Personnage déjà existant
     * @author simon
     * @param perso Instance de Personnage à copier
     */
    public Personnage(Personnage perso) {
        super((Creature) perso);
        this.nom = perso.getNom();
        this.distAttMax = perso.getDistAttMax();
    }
    
       
    
    /**
     * Affiche l'ensemble des statistiques du Personnage
     * @author simon
     */
    @Override
    public void affiche() {
        System.out.println(nom + " " + ptVie + " (" + pos.getX() + ", " + pos.getY() + ")");
        System.out.println("Att. : " + degAtt);
        System.out.println("Par. : " + ptPar);
        System.out.println("Pourcentage Att. : " + pageAtt);
        System.out.println("Pourcentage Par. : " + pagePar);
        System.out.println("Dist. Att. Max : " + distAttMax);
        
    }
    
    public void deplacementAleatoire(boolean[][] presences) {
        Date date = new Date();
        Random rand = new Random(date.getTime());
        
        int dx, dy;
        
        // On génère un vecteur-déplacement jusqu'à qu'il soit valide
        do {
            dx = rand.nextInt(3) - 1;
            dy = rand.nextInt(3) - 1;
        }
        while (dx * dy != 0  // Pas de déplacement en diagonale
                && pos.getX() + dx < presences.length && pos.getY() + dy < presences[0].length // Déplacement à l'intérieur du monde (borne sup.)
                && 0 <= pos.getX() + dx && 0 <= pos.getY() + dy  // Déplacement à l'intérieur du monde (borne inf.)
                && presences[pos.getX() + dx][pos.getY() + dy]);  // Déplacement sur une cellule libre
        
        // La cellule associée à la position précedente est indiquée comme libre
        presences[pos.getX()][pos.getY()] = false;
        
        // Déplacement de l'archer
        pos.translate(dx, dy);
        
        // La cellule associée à la nouvelle position est indiquée comme libre
        presences[pos.getX()][pos.getY()] = true;
    }
    
    /**
     * Accesseur du nom de l'instance de Personnage
     * @return nom Nom de l'instance de Personnage
     */
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDistAttMax() {
        return distAttMax;
    }

    public void setDistAttMax(int distAttMax) {
        this.distAttMax = distAttMax;
    }
    
    
}
