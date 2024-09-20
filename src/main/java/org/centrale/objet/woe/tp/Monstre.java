/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.centrale.objet.woe.tp;

import java.util.Random;
import java.util.Date;

/**
 * Super-classe représentant les monstres 
 * @author grigm
 */
public class Monstre{
    private int ptVie; 
    private int degAtt; 
    private int ptPar; 
    private int pageAtt; 
    private int pagePar; 
    private Point2D pos; 
    
    
    /**
     * Constructeur
     * @param pV Nombre de point de vie 
     * @param dA Nombre de point d'attaque
     * @param pPar Nombre de point de parade
     * @param paAtt page attage
     * @param paPar page parade 
     * @param p Postion
     */
    public Monstre(int pV, int dA, int pPar, int paAtt, int paPar, Point2D p){ 
        ptVie = pV; 
        degAtt = dA; 
        ptPar = pPar; 
        pageAtt = paAtt; 
        pagePar = paPar; 
        pos= p ;  //on copie que la référence
       
    }
   
    /**
     * Constructeur par copie d'un Monstre déjà existant
     * @author grigm
     * @param m Instance de Monstre à copier
     */
    public Monstre(Monstre m){ 
        this.ptVie = m.getPtVie(); 
        this.degAtt = m.getDegAtt(); 
        this.ptPar = m.getPtPar(); 
        this.pageAtt = m.getPageAtt(); 
        this.pagePar = m.getPagePar(); 
        this.pos= m.getPos() ; 
        
    }
    
    
    /**
     * Constructeur par défaut
     * @author grigm
     */
    public Monstre() { 
        ptVie = 100; 
        degAtt = 100; 
        ptPar = 100; 
        pageAtt = 5; 
        pagePar = 10; 
        pos = new Point2D(); 
    }
    
    
    /**
     * Accesseur du nombre de point de vie du Monstre
     * @return ptVie Nombre de point de vie
     */
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

    public void setPtVie(int pv) {
        pv = ptVie;
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
    
    /**
     * Déplace de manière aléatoire le Monstre dans les 8 directions possibles d'un cran
     * @author grigm
     */
    public void deplace (){ 
        Date date1= new Date();
        /* on utilise getTime() pour avoir un seed différent dans le random*/
        Random rand = new Random(date1.getTime()); 
        this.pos.translate(rand.nextInt(2)-1, rand.nextInt(2)-1); //se déplace d'une case adjacente
    }
    
    /**
     * Affiche l'ensemble des statistiques du Monstre
     * @author grigm
     */
    public void affiche () {
        System.out.println("Monstre(" + pos.getX() + ", " + pos.getY() + ")");
        System.out.println("Att. : " + degAtt);
        System.out.println("Par. : " + ptPar);
        System.out.println("Page Att. : " + pageAtt);
        System.out.println("Page Par. : " + pagePar );
    }
    
    
    
}
