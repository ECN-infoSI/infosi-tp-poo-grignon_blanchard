/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.woe.tp;
import java.util.Date;
import java.util.Random; 

/**
 *
 * @author grigm
 */
public class TestWoE{
   public static void main(String[] args) throws Exception {
       
       
       //Tests générer un monde à la composition aléatoire (max 10 instances de chaque sous classe) 
       Date date = new Date();
       Random rand = new Random(date.getTime()); 
      
       World monde = new World(rand.nextInt(10), rand.nextInt(10), rand.nextInt(10), rand.nextInt(10), rand.nextInt(10), rand.nextInt(10), rand.nextInt(10), 50);
       
       
       //monde.creationJoueur();
       //monde.joueur.perso.getPos().affiche();
       
       // Tests de la compostion du monde       
       //TestWoE.testCompositionCreatures(monde);
       
       //TestWoE.testCompositionObjets(monde);
       
       
       /*
       // Test du nombre de points de vie
       for (int n = 1; n <= 10000; n*=10){
           monde = new World(20*n, 20*n, 20*n, 20*n, 20*n, 0, 0, 50);
           
           TestWoE.testSommePtVieIterateur(monde);
       }
       */
       
       
       //Test Nuage Toxique déplacement + combat
       NuageToxique nunu = new NuageToxique(); 
       NuageToxique pluie = new NuageToxique(10); 
       nunu.affiche(); 
       pluie.affiche();
       
       Loup wolfie = new Loup();
       wolfie.affiche(); 
       System.out.println("Le loup est placé sous le nuage avec 100 pv"); 
       
       pluie.combattre((Creature)wolfie);
       wolfie.affiche();
       System.out.println("Le loup a perdu " + pluie.getNivToxique() + " pv"); 
       
       Point2D position = new Point2D(8,7); 
       pluie.deplacer(position, monde.getPresences());
       pluie.combattre((Creature)wolfie);
       wolfie.affiche();
       
       //Test Nourriture 
       Nourriture steak = new Nourriture(); 
       Nourriture burger = new Nourriture("burger", -5, 0, 0, -5); 
       steak.affiche();
       burger.affiche(); 
       
       
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
   
   /**
    * Calcule de la somme des points vies avec une boucle basée sur la taille
    * @param monde Monde des personnages à tester
    */
   private static void testSommePtVieIterateur(World monde) {
       int sommePV = 0;
       long tempsDeb, tempsFin;
       
       System.out.println("\n# TEST DU NOMBRE DE POINTS DE VIE TOTAL (" + monde.getNbCreatures() + ")");
       
       tempsDeb = System.nanoTime();   
       
       for (Creature c : monde.listCreatures) {
           sommePV += c.getPtVie();
       }
       
       tempsFin = System.nanoTime();
               
       System.out.println("Nombre totale des points de vie : " + sommePV);
       System.out.println("Execution en " + (tempsFin - tempsDeb) + " ns");
   }
   
   /**
    * Calcule de la somme des points vies avec une boucle basée sur la taille
    * @param monde Monde des personnages à tester
    */
   private static void testSommePtVieTaille(World monde) {
       int sommePV = 0;
       long tempsDeb, tempsFin;
       
       System.out.println("\n# TEST DU NOMBRE DE POINTS DE VIE TOTAL (" + monde.getNbCreatures() + ")");
       
       tempsDeb = System.nanoTime();
       
       for (int i = 0; i < monde.getNbCreatures(); i++) {
           sommePV += monde.listCreatures.get(i).getPtVie();
       }
       
       tempsFin = System.nanoTime();
               
       System.out.println("Nombre totale des points de vie : " + sommePV);
       System.out.println("Execution en " + (tempsFin - tempsDeb) + " ns");
   }
}
