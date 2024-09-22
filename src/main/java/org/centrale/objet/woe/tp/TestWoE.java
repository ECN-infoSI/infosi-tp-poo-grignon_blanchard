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
       
       // Test placement aléatoire des personnages
       monde.robin.affiche(); 
       System.out.println();
       
       monde.guillaumeT.affiche(); 
       System.out.println();
       
       monde.peon.affiche(); 
       System.out.println();
       
       monde.grosBill.affiche(); 
       System.out.println();
       
       System.out.println("Bugs 1 :");
       monde.bugs1.affiche(); 
       System.out.println();
       
       System.out.println("Bugs 2 :");
       monde.bugs2.affiche(); 
       System.out.println();
       
       System.out.println("Wolfie :");
       monde.wolfie.affiche(); 
       System.out.println();
       
       
       // test Objet
       Objet objet = new Objet(); 
       objet.affiche();
       
       // Test attaque entre créature
       monde.grosBill.setPos(new Point2D(0, 0));
       monde.robin.setPos(new Point2D(10, 0));
       monde.wolfie.setPos(new Point2D(10, 1));
       
       monde.bugs1.setPos(new Point2D(1, 0));
       monde.bugs2.setPos(new Point2D(0, 10));
       
       System.out.println("# Attaque au corps à corps de deux créatures adjacentes");
       monde.wolfie.combattre(monde.robin);
       
       System.out.println("# Attaque au corps à corps hors de portée");
       monde.grosBill.combattre(monde.robin);
       
       System.out.println("# Attaque à distance dans la portée");
       monde.robin.combattre(monde.bugs1);
       
       System.out.println("# Attaque à distance hors de portée (trop proche)");
       monde.robin.combattre(monde.wolfie);
       
       System.out.println("# Attaque à distance hors de portée (trop loin)");
       monde.robin.combattre(monde.bugs2);
       
       // Affichage des statistiques des personnages après les combats
       monde.robin.affiche(); 
       System.out.println();
       
       monde.guillaumeT.affiche(); 
       System.out.println();
       
       monde.peon.affiche(); 
       System.out.println();
       
       monde.grosBill.affiche(); 
       System.out.println();
       
       System.out.println("Bugs 1 :");
       monde.bugs1.affiche(); 
       System.out.println();
       
       System.out.println("Bugs 2 :");
       monde.bugs2.affiche(); 
       System.out.println();
       
       System.out.println("Wolfie :");
       monde.wolfie.affiche(); 
       System.out.println();
   }
   
}
