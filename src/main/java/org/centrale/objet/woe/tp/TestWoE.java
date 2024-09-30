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
       World monde = new World(10, 7, 6, 2, 5, 4, 9, 50);
       
       for(Objet o : monde.listObjets) {
           o.affiche();
       }
       
       // Tests de la compostion du monde       
       TestWoE.testCompositionCreatures(monde);
       
       TestWoE.testCompositionObjets(monde);
       
       // Test du nombre de points de vie
       TestWoE.testSommePV(monde);
       
   }
   
   /**
    * Test la compostion d'un monde en affichange le nombre de créatures en fonction de leur type
    * @param monde Monde à tester
    * @author simon
    */
   private static void testCompositionCreatures(World monde) {
       int nbArcher = 0;
       int nbGuerrier = 0;
       int nbPaysan = 0;
       int nbLapin = 0;
       int nbLoup = 0;
       
       for (Creature c : monde.listCreatures) {
           if (c instanceof Archer) {
               nbArcher++;
           }
           
           if (c instanceof Guerrier) {
               nbGuerrier++;
           }
           
           if (c instanceof Paysan) {
               nbPaysan++;
           }
           
           if (c instanceof Lapin) {
               nbLapin++;
           }
           
           if (c instanceof Loup) {
               nbLoup++;
           }
       }
       
       System.out.println("\n# TEST DE LA RÉPARTITION DES CRÉATURES");
       
       System.out.println("Le monde est composé de :");
       System.out.println("\t" + nbArcher + " archers");
       System.out.println("\t" + nbGuerrier + " guerriers");
       System.out.println("\t" + nbPaysan + " paysans");
       System.out.println("\t" + nbLapin + " lapins");
       System.out.println("\t" + nbLoup + " loups");
   }
   
   /**
    * Test la compostion d'un monde en affichange le nombre d'objets en fonction de leur type
    * @param monde Monde à tester
    * @author simon
    */
   private static void testCompositionObjets(World monde) {
       int nbPotionSoin = 0;
       int nbEpee = 0;
       
       for (Objet o : monde.listObjets) {
           if (o instanceof PotionSoin) {
               nbPotionSoin++;
           }
           
           if (o instanceof Epee) {
               nbEpee++;
           }
       }
       
       System.out.println("\n# TEST DE LA RÉPARITION DES OBJETS");
       
       System.out.println("Le monde est composé de :");
       System.out.println("\t" + nbPotionSoin + " potions de soin");
       System.out.println("\t" + nbEpee + " épées");
   }
   
   private static void testSommePV(World monde) {
       int sommePV = 0;
       
       for (Creature c : monde.listCreatures) {
           sommePV += c.getPtVie();
       }
       
       System.out.println("\n# TEST DU NOMBRE DE POINTS DE VIE TOTAL");
               
       System.out.println("Nombre totale des points de vie : " + sommePV);
   }
}
