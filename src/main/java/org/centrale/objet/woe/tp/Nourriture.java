/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.woe.tp;

/**
 * Sous classe Objet permettant de stocker les objets qui ont des bonus/malus sur les caractéristiques des personnages temporairement
 * @author grigm
 */
public class Nourriture extends Objet implements Utilisable {
    private String nom; 
    private int dureeEffet; 
    private int degAtt; 
    private int ptPar; 
    private int pageAtt; 
    private int pagePar; 
    
    /**
     * Constructeur par défaut construit un steak 
     * @author grigm
     */
    public Nourriture(){ 
        super(); 
        nom = "café"; 
        dureeEffet = 10; 
        degAtt=10; 
        ptPar=0; 
        pageAtt=10; 
        pagePar = 0; 
    }
    
    /**
     * Constructeur manuel
     * @author grigm
     * @param n nom de la nourriture
     * @param duree nombre de tour que dure l'effet 
     * @param dA nombre de dégat malus ou bonus 
     * @param pPar nombre de point de parade malus ou bonus
     * @param paAtt nombre de point à ajouter au pourcentage attaque malus ou bonus
     * @param paPar nombre de point à ajouter au pourcentage parade malus ou bonus
     */
    public Nourriture(String n, int duree,  int dA, int pPar, int paAtt, int paPar) {
        super();
        nom=n; 
        dureeEffet = duree; 
        degAtt=dA;
        ptPar = pPar; 
        pageAtt = paAtt; 
        pagePar = paPar; 
    }
    
    /**
     * Affiche la nourriture avec son nom et ces bonus/malus
     * @author grigm
     */
    @Override
    public void affiche(){
        System.out.println(nom + " (" + pos.getX() + ", " + pos.getY() + ")");
        System.out.println("Duree effet : " + dureeEffet + " tour(s)");
        System.out.println("Effet Att. : " + degAtt);
        System.out.println("Effet Par. : " + ptPar);
        System.out.println("Effet Pourcentage Att. : " + pageAtt);
        System.out.println("Effet Pourcentage Par. : " + pagePar);
               
    }
    
    /**
     * méthode pour activer l'objet et modifier les caractéristiques du personnage
     * @author grigm
     * @param c représentant l'instance de Creature qui active l'objet
     */
    @Override
    public void utilise(Creature c) {
        //on met à jour les capacités de la créature 
        c.setDegAtt(c.degAtt+this.degAtt);
        c.setPtPar(c.ptPar+this.ptPar);
        c.setPageAtt(c.pageAtt+this.pageAtt);
        c.setPagePar(c.pagePar+this.pagePar);     
    }
    
     /**
     * méthode pour retirer l'effet au personnage
     * @author grigm
     * @param c représentant l'instance de Creature qui active l'objet
     */
    public void retireEffet(Creature c) {
        //on met à jour les capacités de la créature 
        c.setDegAtt(c.degAtt-this.degAtt);
        c.setPtPar(c.ptPar-this.ptPar);
        c.setPageAtt(c.pageAtt-this.pageAtt);
        c.setPagePar(c.pagePar-this.pagePar);             
    }
    
    /**
     * méthode pour afficher l'effet de l'objet au moment de son activation
     * @author grigm
     */
    public void afficheEffet(){ 
        System.out.println("Effet de la nourriture consommée : "); 
        System.out.println(nom);
        System.out.println("Effet Att. : " + degAtt);
        System.out.println("Effet Par. : " + ptPar);
        System.out.println("Effet Pourcentage Att. : " + pageAtt);
        System.out.println("Effet Pourcentage Par. : " + pagePar);
        System.out.println("Duree effet : " + dureeEffet + " tour(s)");
        
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

   
    @Override
    public int getDureeEffet() {
        return dureeEffet;
    }

    @Override
    public void setDureeEffet(int dureeEffet) {
        this.dureeEffet = dureeEffet;
    }

      
    
    
}
