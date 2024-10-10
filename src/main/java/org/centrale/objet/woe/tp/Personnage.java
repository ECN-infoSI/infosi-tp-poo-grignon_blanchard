/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.woe.tp; 

/**
 * Super-classe représentant un Personnage
 * @author simon
 */
public class Personnage extends Creature{
    protected String nom;
    protected int distAttMax;
    protected int sexe; // 0 pour H, 1 pour F
 
    /**
     * Constructeur par défaut
     * @author simon
     */
    public Personnage() {
        super(); 
        nom = "John Doe";
        sexe = 0;
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
        sexe = 0;
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
     * @param sexe  Sexe du personnage
     */
    public Personnage(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p, int sexe) {
        super( pV, dA, pPar, paAtt, paPar, p); 
        nom = n;
        distAttMax = dMax;
        this.sexe = sexe;
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
        this.sexe = perso.getSexe();
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

    public int getSexe() {
        return sexe;
    }

    public void setSexe(int sexe) {
        this.sexe = sexe;
    }
}
