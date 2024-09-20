/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.woe.tp;

import java.util.Date;
import java.util.Random;

/**
 * Super-classe représentant les créatures
 * @author grigm
 */
public class Creature {
    private int ptVie;
    private int degAtt;
    private int ptPar;
    private int pageAtt;
    private int pagePar;
    private Point2D pos;
    
    /**
     * Constructeur par défaut
     * @author grigm
     */
    public Creature() {
        ptVie = 100;
        degAtt = 5;
        ptPar = 10;
        pageAtt = 5;
        pagePar = 10;
        pos = new Point2D();
    }
    
    /**
     * Constructeur 
     * @author grigm
     * @param pV    Nombre de points de vie
     * @param dA    Nombre de points d'attaque
     * @param pPar  Nombre de points de parade
     * @param paAtt Page attaque
     * @param paPar Page parade
     * @param p     Position
     */
    public Creature( int pV, int dA, int pPar, int paAtt, int paPar, Point2D p) {
        ptVie = pV;
        degAtt = dA;
        ptPar = pPar;
        pageAtt = paAtt;
        pagePar = paPar;
        pos = p;
    }
    
    /**
     * Constructeur par copie d'un guerrier déjà existant
     * @author grigm
     * @param c Instance de Creature à copier
     */
    public Creature(Creature c) {
        this.ptVie = c.getPtVie();
        this.degAtt = c.getDegAtt();
        this.ptPar = c.getPtPar();
        this.pageAtt = c.getPageAtt();
        this.pagePar = c.getPagePar();
        this.pos = new Point2D(c.getPos());
    }

    /**
     * Affiche l'ensemble des statistiques de Creature
     * @author simon
     */
     public void affiche() {
        System.out.println("Creature(" + pos.getX() + ", " + pos.getY() + ")");
        System.out.println("Att. : " + degAtt);
        System.out.println("Par. : " + ptPar);
        System.out.println("Page Att. : " + pageAtt);
        System.out.println("Page Par. : " + pagePar);
   
    }
     
     /**
     * Déplace de manière aléatoire la Creature dans les 8 directions possibles d'un cran
     * @author grigm
     */
    public void deplace() {
        Date date1= new Date();
        Random rand = new Random(date1.getTime()); 
        this.pos.translate(rand.nextInt(2)-1, rand.nextInt(2)-1); // déplace d'une case adjacente
    }
    
    
    public int getPtVie() {
        return ptVie;
    }

    public int getDegAtt() {
        return degAtt;
    }

    public int getPtPar() {
        return ptPar;
    }

    public int getPageAtt() {
        return pageAtt;
    }

    public int getPagePar() {
        return pagePar;
    }

    public Point2D getPos() {
        return pos;
    }

    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }

    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
    }

    public void setPtPar(int ptPar) {
        this.ptPar = ptPar;
    }

    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }

    public void setPagePar(int pagePar) {
        this.pagePar = pagePar;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }
    
    
    
}
