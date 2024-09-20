/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.woe.tp;
import java.util.Random; 

/**
 * Super-classe représentant un Personnage
 * @author simon
 */
public class Personnage {
    private String nom;
    private int ptVie;
    private int degAtt;
    private int ptPar;
    private int pageAtt;
    private int pagePar;
    private int distAttMax;
    private Point2D pos;
    
    /**
     * Constructeur par défaut
     * @author simon
     */
    public Personnage() {
        nom = "John Doe";
        ptVie = 100;
        degAtt = 5;
        ptPar = 10;
        pageAtt = 5;
        pagePar = 10;
        distAttMax = 2;
        pos = new Point2D();
    }
    
    /**
     * Constructeur
     * @param n     Nom du personnage
     * @param pV    Nombre de points de vie
     * @param dA    Nombre de points d'attaque
     * @param pPar  Nombre de points de parade
     * @param paAtt Page attaque
     * @param paPar Page parade
     * @param dMax  Distance d'attaque maximale
     * @param p     Position
     */
    public Personnage(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p) {
        nom = n;
        ptVie = pV;
        degAtt = dA;
        ptPar = pPar;
        pageAtt = paAtt;
        pagePar = paPar;
        distAttMax = dMax;
        pos = p;
    }
    
    
    /**
     * Constructeur par copie d'un Personnage déjà existant
     * @author simon
     * @param perso Instance de Personnage à copier
     */
    public Personnage(Personnage perso) {
        this.nom = perso.getNom();
        this.ptVie = perso.getPtVie();
        this.degAtt = perso.getDegAtt();
        this.ptPar = perso.getPtPar();
        this.pageAtt = perso.getPageAtt();
        this.pagePar = perso.getPagePar();
        this.distAttMax = perso.getDistAttMax();
        this.pos = perso.getPos();
    }
    
    
    /**
     * Déplace de manière aléatoire le Personnage dans les 8 directions possibles d'un cran
     * @author grigm
     */
    public void deplace() {
        Random rand = new Random(); 
        this.pos.translate(rand.nextInt(2)-1, rand.nextInt(2)-1); // déplace d'une case adjacente
    }
    
    
    /**
     * Affiche l'ensemble des statistiques du Personnage
     * @author simon
     */
    public void affiche() {
        System.out.println(nom + " (" + pos.getX() + ", " + pos.getY() + ")");
        System.out.println("Att. : " + degAtt);
        System.out.println("Par. : " + ptPar);
        System.out.println("Page Att. : " + pageAtt);
        System.out.println("Page Par. : " + pagePar);
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

    public int getPtVie() {
        return ptVie;
    }

    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }

    public int getDegAtt() {
        return degAtt;
    }

    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
    }

    public int getPtPar() {
        return ptPar;
    }

    public void setPtPar(int ptPar) {
        this.ptPar = ptPar;
    }

    public int getPageAtt() {
        return pageAtt;
    }

    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }

    public int getPagePar() {
        return pagePar;
    }

    public void setPagePar(int pagePar) {
        this.pagePar = pagePar;
    }

    public int getDistAttMax() {
        return distAttMax;
    }

    public void setDistAttMax(int distAttMax) {
        this.distAttMax = distAttMax;
    }

    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }
    
    
}
