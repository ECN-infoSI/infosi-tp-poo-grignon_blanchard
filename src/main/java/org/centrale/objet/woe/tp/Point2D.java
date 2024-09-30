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
     * Calcul la distance euclidienne entre deux instances de Point2D
     * @param p1 Instance de Point2D
     * @param p2 Instance de Point2D
     * @return   Distance euclidienne entre les points p1 et p2
     */
    public static double distance(Point2D p1, Point2D p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }
    
    /**
     * Test d'égalité entre deux points
     * @param p Autre point à tester
     * @return Valeur du test d'égalité
     * @author simon
     */
    public boolean equals(Point2D p) {
        return this.x == p.x && this.y == p.y;
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
