/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.centrale.objet.woe.tp;

import java.lang.Math;

/**
 *
 * @author simon
 */
public class Point2D {
    private int x;
    private int y;
    
    public Point2D() {
        this.x = 8;
        this.y = 6;
    }
    
    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Point2D(Point2D pt) {
        this.x = pt.getX();
        this.y = pt.getY();
    }
    
    public void affiche() {
        System.out.println("[" + this.x + "; " + this.y + "]");
    }
    
    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
    
    /**
     * Calcul la distance euclidienne entre l'instance et une autre de Point2D
     * @param p Autre instance de Point2D dont la distance doit être calculée par rapport à l'instance
     * @return  Distance euclidienne entre this et p
     */
    public double distance(Point2D p) {
        return Math.sqrt((this.x - p.getX()) * (this.x - p.getX()) + (this.y - p.getY()) * (this.y - p.getY()));
    }
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
