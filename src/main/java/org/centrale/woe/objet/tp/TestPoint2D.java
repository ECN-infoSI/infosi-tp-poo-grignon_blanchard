/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.woe.objet.tp;

/**
 * Test class Point2D et exceptions
 * @author grigm
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
        System.out.println(Point2D.distance(point1, point2));
        
        /*
        // NullPointerException
        Point2D point= null; 
        point.affiche(); 
        */
       
        /*
        // ArrayIndexOutOfBoundsException
        int tableau[] = new int[1]; 
        tableau[0]=1; 
        tableau[1]=1; 
        */
        
        /*
        // ArithmeticException
        int a = 0; 
        int b=5; 
        int c = b/a; 
        System.out.println(c); 
        */
        
        /*
        // ClassCastException
        Personnage x = new Personnage();
        System.out.println((Guerrier)x);
        */
        
        
        //NumberFormatException
        String prix = "45€"; 
        /*
        System.out.println(Integer.valueOf(prix));
        */
        
        try {
            int number = Integer.parseInt(prix);
            System.out.println(number);

            
        } catch (NumberFormatException e) {
            System.out.println("Chaîne de caractère non convertible en un entier");
        }

        
        /*
        //StackOverflowError
        boolean vrai = true; 
        while (vrai==true) {
            point2.translate(65, -7);
        }
        */

    }
}
