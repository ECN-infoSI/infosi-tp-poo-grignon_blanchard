/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.woe.tp;

/**
 *
 * @author grigm
 */
public class TestWoE{
   public static void main(String[] args) { 
       World monde = new World(); 
       monde.creerMondeAlea(); 
       
       monde.robin.affiche(); 
       System.out.println();
       
       monde.peon.affiche(); 
       System.out.println();
       
       monde.bugs.affiche(); 
       System.out.println();
       
       Point2D position = new Point2D(14,17); 
       Lapin roger = new Lapin(50, 50, 50, 10, 10, position); 
       
       System.out.println("Roger le lapin");
       roger.affiche(); 
       
       System.out.println("Roger se déplace d'une case en x");
       roger.deplace(); // utiliser une méthode de la super classe pour déplacer roger d'une case de x (+1)
       roger.affiche(); 
   }
   
}
