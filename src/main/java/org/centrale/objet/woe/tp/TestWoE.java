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
       
          
      
       
       System.out.println("Robin se deplace 2 fois aleatoirement");
       monde.robin.deplace(); // utiliser une méthode de la super classe pour déplacer roger d'une case de x (+1)
       monde.robin.affiche(); 
       monde.robin.deplace(); // utiliser une méthode de la super classe pour déplacer roger d'une case de x (+1)
       monde.robin.affiche();
       
       System.out.println("Bugs se deplace 2 fois aleatoirement");
       monde.bugs.deplace(); // utiliser une méthode de la super classe pour déplacer  d'une case de x (+1)
       monde.bugs.affiche(); 
       monde.bugs.deplace(); // utiliser une méthode de la super classe pour déplacer roger d'une case de x (+1)
       monde.bugs.affiche();
       
       
       System.out.println("Robin se deplace 2 fois aleatoirement de nouveau");
       monde.robin.deplace(); // utiliser une méthode de la super classe pour déplacer roger d'une case de x (+1)
       monde.robin.affiche(); 
       monde.robin.deplace(); // utiliser une méthode de la super classe pour déplacer roger d'une case de x (+1)
       monde.robin.affiche();
       
       
   }
   
}
