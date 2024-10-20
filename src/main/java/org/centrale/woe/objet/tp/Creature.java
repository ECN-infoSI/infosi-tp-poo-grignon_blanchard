/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.woe.objet.tp;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Super-classe représentant les créatures
 * @author grigm
 */
public abstract class Creature extends ElementDeJeu implements Deplacable {
    protected int ptVie;
    protected int degAtt;
    protected int ptPar;
    protected int pageAtt;
    protected int pagePar;
    
    
    /**
     * Constructeur par défaut
     * @author grigm
     */
    public Creature() {
        super(); 
        ptVie = 100;
        degAtt = 10;
        ptPar = 5;
        pageAtt = 80;
        pagePar = 25;
    }
    
    /**
     * Constructeur 
     * @author grigm
     * @param pV    Nombre de points de vie
     * @param dA    Nombre de points d'attaque
     * @param pPar  Nombre de points de parade
     * @param paAtt Pourcentage d'attaque
     * @param paPar Pourcentage de parade
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
        System.out.println("Creature " + ptVie + " (" + pos.getX() + ", " + pos.getY() + ")");
        System.out.println("Att. : " + degAtt);
        System.out.println("Par. : " + ptPar);
        System.out.println("Pourcentage Att. : " + pageAtt);
        System.out.println("Pourcentage Par. : " + pagePar);
   
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
    
    /**
     * Méthode permettant la gestion de la prise de dégats
     * @param degats Nombre de dégats à infliger (si négatif, rien n'est fait)
     */
    public boolean prendreDegats(int degats) {
        boolean killed = false;
        
        if (degats > 0) {
            ptVie -= degats;

            if (ptVie <= 0) {
                ptVie = 0;
                System.out.println("La créature est morte !");
                killed = true;
            }
        }
        
        return killed;
    }
    
    /**
     * Méthode permettant de déplacer une créature
     * @param newPos la position cible 
     * @param presences Tableau des présences des créatures 
     * @param presencesObjet Tableau des présences des Objets 
     * @param inventaire l'inventaire du joueur
     */
    public void deplacer(Point2D newPos, boolean[][] presences, boolean[][] presencesObjet, ArrayList<Objet> listObjets ,  ArrayList<Objet> inventaire) throws Exception {
        // Déplacement à l'intérieur du monde
        if (newPos.getX() < presences.length && newPos.getY() < presences[0].length && 0 <= newPos.getX() && 0 <= newPos.getY()) {
            // Déplacement sur une case libre
            if (!presences[newPos.getX()][newPos.getY()]) {
                // La cellule associée à la position précedente est indiquée comme libre
                presences[pos.getX()][pos.getY()] = false;

                // Déplacement de l'archer
                pos = newPos;

                // La cellule associée à la nouvelle position est indiquée comme libre
                presences[pos.getX()][pos.getY()] = true;
                
                // Si le personnage est un joueur et qu'il se trouve sur une case d'un objet alors il le ramasse et le met dans son inventaire
                if (((Personnage)this).isEstJoueur()){ 
                   if (presencesObjet[pos.getX()][pos.getY()]){
                       for (int i =0 ; i < listObjets.size(); i++){
                           //on cherche l'objet qui a la même position que le joueur
                           if (pos.getX()==listObjets.get(i).getPos().getX() && pos.getY()==listObjets.get(i).getPos().getY()){
                               //ajouter l'objet dans l'inventaire
                               inventaire.add(listObjets.get(i)); 
                                
                                //afficher le nouvel objet
                                System.out.println("Vous avez ramassé un objet :"); 
                                listObjets.get(i).affiche();
                                
                               //retirer l'objet de la liste des objets du monde 
                               listObjets.remove(i); 
                               
                               //retirer l'objet du tableau des presences Objet
                               presencesObjet[pos.getX()][pos.getY()]= false; 
                           }
                       }
                    } 
                }      
            }
            else {
                throw new Exception("Mouvement vers une case occupée !");
            }
        }
        else {
            throw new Exception("Mouvement en dehors du monde !");
        }
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
