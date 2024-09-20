/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.centrale.objet.woe.tp;

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
