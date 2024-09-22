/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.woe.tp;

import java.util.Random; 
import java.util.Date;

/**
 * Classe représentant le monde à utiliser lors d'une partie
 * @author grigm
 */
public class World {
    public Archer robin; 
    public Paysan peon; 
    public Lapin bugs1;
    public Lapin bugs2;
    public Archer guillaumeT;
    public Guerrier grosBill;
    public Loup wolfie;
    
    /**
     * Constructeur par défaut
     */
    public World() { 
        // On renomme les personnages pour pouvoir les distinguer
        
        robin = new Archer(); 
        robin.setNom("Robin");
        
        peon = new Paysan(); 
        peon.setNom("Peon");
        
        guillaumeT = new Archer();
        guillaumeT.setNom("Guillaume T.");
        
        grosBill = new Guerrier();
        grosBill.setNom("Gros Bill");
        
        bugs1 = new Lapin(); 
        bugs2 = new Lapin();
        
        wolfie = new Loup();
    }
    
    /**
     * Constructeur aléatoire (évite que deux personnages apparaissent à la même position)
     * @author simon
     */
    public void creerMondeAlea() {
        Date date = new Date();
        Random rand = new Random(date.getTime()); 
        int[] listCoor = new int[14];
        boolean flagAllUnique;  // Utiliser pour verifier que le couple de coordonées n'existe pas déjà
        
        for (int i = 0; i < 7; i++) {                      
            do {
                flagAllUnique = true;
                
                // Tirage de deux entiers
                listCoor[2*i] = rand.nextInt(10);
                listCoor[2*i+1] = rand.nextInt(10);
                
                // Vérification que la paire n'existe pas déjà parmi les couples générés precedemment
                for (int j = 0; j < i && flagAllUnique; j++) {
                    flagAllUnique = listCoor[2*i] != listCoor[2*j] && listCoor[2*i+1] != listCoor[2*j+1];
                }
            }
            // On recommence l'opération tant que le couple existait déjà
            while (!flagAllUnique);
        }
        
        robin.setPos(new Point2D(listCoor[0], listCoor[1]));
        peon.setPos(new Point2D(listCoor[2], listCoor[3]));
        guillaumeT.setPos(new Point2D(listCoor[4], listCoor[5]));
        grosBill.setPos(new Point2D(listCoor[6], listCoor[7]));
        bugs1.setPos(new Point2D(listCoor[8], listCoor[9]));
        bugs2.setPos(new Point2D(listCoor[10], listCoor[11]));
        wolfie.setPos(new Point2D(listCoor[12], listCoor[13]));
    }
    
    
    /**
     * Effectue un tour de jeu
     * @author simon
     */
    public void tourDeJeu() {
        
    }
}
