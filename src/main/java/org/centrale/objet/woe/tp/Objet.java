/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.woe.tp;

/**
 * Super-classe représentant les objets
 * @author grigm
 */
public class Objet {

    private Point2D pos; 
    
    /**
     * Constructeur par défaut
     * @author grigm
     */
    public Objet(){
        pos = new Point2D(); 
    }
    
    
    /**
     * méthode affiche la position de l'objet
     * @author grigm
     */
    public void affiche(){ 
        System.out.println("Objet(" + pos.getX() +","+pos.getY()+")");
    }

    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }
    
    
}
