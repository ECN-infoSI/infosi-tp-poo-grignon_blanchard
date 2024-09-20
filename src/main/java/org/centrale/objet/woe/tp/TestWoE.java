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
   }
   
}
