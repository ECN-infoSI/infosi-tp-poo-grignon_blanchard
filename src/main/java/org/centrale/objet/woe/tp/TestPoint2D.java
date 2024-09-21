/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.woe.tp;

/**
 *
 * @author simon
 */
public class TestPoint2D {
    public static void main(String[] args) {
        Point2D point1 = new Point2D();
        Point2D point2 = new Point2D(5, 6);
        Point2D point1bis = new Point2D(point1);  // Copie de point 1
        
        point1bis.affiche();
        
        point2.affiche();
        point2.translate(65, -7); // Translation du point
        point2.affiche();
        
        point1bis.setPosition(-1, 0);  // Repositionnement
        point1bis.affiche();
        
        System.out.println("Calcul de la distance entre point1 et point2");
        System.out.println(point1.distance(point2));
    }
}
