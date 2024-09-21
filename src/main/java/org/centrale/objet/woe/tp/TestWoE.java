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
   }
   
}
