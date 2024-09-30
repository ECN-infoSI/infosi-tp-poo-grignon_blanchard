/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.woe.tp;

import java.util.Random; 
import java.util.Date;
import java.util.ArrayList; 

/**
 * Classe représentant le monde à utiliser lors d'une partie
 * @author grigm
 */
public class World {
    public ArrayList<Creature> listCreatures; 
    public ArrayList<Objet> listObjets; 

    /**
     * Constructeur par défaut qui crée un monde sans créature ni objet (les arraylists sont créées mais elles sont vides)
     */
    public World() { 
        listCreatures = new ArrayList(); //notre liste de créature vide
        listObjets = new ArrayList(); //notre liste d'objet vide
        
    }
    
    
    /**
     * Constructeur par défaut avec une créature de chaque sous classe (2 archers et 2 lapins) et un objet de chaque sous classe
     * @param nbArcher 
     * @param nbPaysan 
     * @param nbGuerrier
     * @param nbLapin
     * @param nbLoup
     * @param nbEpee
     * @param nbPotionSoin
     */
    public World(int nbArcher, int nbPaysan, int nbGuerrier,int nbLapin, int nbLoup, int nbEpee, int nbPotionSoin) { 
        listCreatures = new ArrayList(); //notre liste de créature
        
        //On ajoute les Archers
        for (int i = 0; i < nbArcher; i++) {  
            listCreatures.add(new Archer()); 
        }
        
        //On ajoute les Paysans
        for (int i = 0; i < nbPaysan; i++) {  
            listCreatures.add(new Paysan()); 
        }
        
        //On ajoute les Guerriers
        for (int i = 0; i < nbGuerrier; i++) {  
            listCreatures.add(new Guerrier()); 
        }
        
        
        //On ajoute les Lapins
        for (int i = 0; i < nbLapin; i++) {  
            listCreatures.add(new Lapin()); 
        }
        
        //On ajoute les Loups
        for (int i = 0; i < nbLoup; i++) {  
            listCreatures.add(new Loup()); 
        }
        
        listObjets = new ArrayList(); //notre liste d'objet
        
        //On ajoute les Epees
        for (int i = 0; i < nbEpee; i++) {  
            listObjets.add(new Epee()); 
        }
        
        //On ajoute les Potions de Soin
        for (int i = 0; i < nbPotionSoin; i++) {  
            listObjets.add(new PotionSoin()); 
        }
        
        
        
               
       
        
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
        /*
        robin.setPos(new Point2D(listCoor[0], listCoor[1]));
        peon.setPos(new Point2D(listCoor[2], listCoor[3]));
        guillaumeT.setPos(new Point2D(listCoor[4], listCoor[5]));
        grosBill.setPos(new Point2D(listCoor[6], listCoor[7]));
        bugs1.setPos(new Point2D(listCoor[8], listCoor[9]));
        bugs2.setPos(new Point2D(listCoor[10], listCoor[11]));
        wolfie.setPos(new Point2D(listCoor[12], listCoor[13]));
        */
    }
    
    
    /**
     * Effectue un tour de jeu
     * @author simon
     */
    public void tourDeJeu() {
        
    }
    
    /**
     * Pour que le personnage utilise une potion de soin sur sa case
     * @author grigm
     * @param perso le Personnage qui vient de se déplacer
     */
    public void utilisePotion(Personnage perso) { 
        // si le tableau potion est vide 
        if (this.listObjets.isEmpty()){
            System.out.println("il n'y a pas de potion de soin dans ce monde");   
        }
        for(int i=0; i< this.listObjets.size(); i++){
            // on vérifie si l'objet fait bien partie de la classe PotionSoin
            if (this.listObjets.get(i) instanceof PotionSoin ){
                if (Point2D.distance(perso.pos, this.listObjets.get(i).getPos())==0){
                //ajout des points de vie au personnage 
                // pour appeler la méthode getValeur de Potion Soin on utilise((PotionSoin)this.listObjets.get(i))
                    perso.setPtVie(perso.getPtVie()+((PotionSoin)this.listObjets.get(i)).getValeurPV());
                    System.out.println("vous consommez la potion de soin, vous gagnez " +((PotionSoin)this.listObjets.get(i)).getValeurPV() + "PV");
                                        
                // la potion de soin est utilisée, on la retire du tableau potion
                this.listObjets.remove(i); 
                }
            }
        }
    }
}
