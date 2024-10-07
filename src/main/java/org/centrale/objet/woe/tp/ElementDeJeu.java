/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.woe.tp;

/**
 * SuperClasse ElementDeJeu regroupant les Creatures et les Objets
 * @author grigm
 */
public class ElementDeJeu {
    protected Point2D pos; 

    /**
     * Constructeur par défaut
     * @author grigm
     */
    public ElementDeJeu(){
        pos = new Point2D();  
    }
    
    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }
    
    
}
