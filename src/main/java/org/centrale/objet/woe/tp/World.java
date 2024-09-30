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
    
    private int nbCreatures;
    private int nbObjets;
    
    private int dimension;
    

    /**
     * Constructeur par défaut qui crée un monde sans créature ni objet (les arraylists sont créées mais elles sont vides)
     */
    public World() { 
        dimension = 50;
        
        listCreatures = new ArrayList(); //notre liste de créature vide
        listObjets = new ArrayList(); //notre liste d'objet vide
        
        nbCreatures = 0;
        nbObjets = 0;
    }
    
    
    /**
     * Constructeur par défaut avec une créature de chaque sous classe (2 archers et 2 lapins) et un objet de chaque sous classe
     * @param nbArcher      Nombre d'archers à placer
     * @param nbPaysan      Nombre de paysans à placer
     * @param nbGuerrier    Nombre de guerriers à placer
     * @param nbLapin       Nombre de lapins à placer
     * @param nbLoup        Nombre de loups à placer
     * @param nbEpee        Nombre d'épées à placer
     * @param nbPotionSoin  Nombre de potions de soin à placer
     * @param dimension     Taille du monde carré
     */
    public World(int nbArcher, int nbPaysan, int nbGuerrier,int nbLapin, int nbLoup, int nbEpee, int nbPotionSoin, int dimension) { 
        this.dimension = dimension;
        
        listCreatures = new ArrayList(); //notre liste de créature
        nbCreatures = nbArcher + nbPaysan + nbGuerrier + nbLapin + nbLoup;
        
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
        nbObjets = nbEpee + nbPotionSoin;
        
        //On ajoute les Epees
        for (int i = 0; i < nbEpee; i++) {  
            listObjets.add(new Epee()); 
        }
        
        //On ajoute les Potions de Soin
        for (int i = 0; i < nbPotionSoin; i++) {  
            listObjets.add(new PotionSoin()); 
        }
        
        this.creerMondeAlea();
    }
    
    /**
     * Constructeur aléatoire (évite que deux personnages apparaissent à la même position)
     * @author simon
     */
    public void creerMondeAlea() {
        Date date = new Date();
        Random rand = new Random(date.getTime()); 
        
        boolean flagAllUnique;  // Utiliser pour verifier que le couple de coordonées n'existe pas déjà
        Point2D[] listCoor = new Point2D[nbCreatures + nbObjets];
        
        // Placement des créatures
        for (int i = 0; i < nbCreatures; i++) {                      
            do {
                flagAllUnique = true;
                
                // Tirage d'un nouveau couple de coordonnées
                listCoor[i] = new Point2D(rand.nextInt(dimension), rand.nextInt(dimension));
                
                // Vérification que la paire n'existe pas déjà parmi les couples générés precedemment
                for (int j = 0; j < i && flagAllUnique; j++) {                    
                    flagAllUnique = listCoor[i] != listCoor[j];
                }
            }
            // On recommence l'opération tant que le couple existait déjà
            while (!flagAllUnique);
            
            listCreatures.get(i).setPos(listCoor[i]);
        }
        
        // Placement des objets en prenant en compte le placement des créatures
        for (int i = nbCreatures; i < nbCreatures + nbObjets; i++) {                      
            do {
                flagAllUnique = true;
                
                // Tirage d'un nouveau couple de coordonnées
                listCoor[i] = new Point2D(rand.nextInt(dimension), rand.nextInt(dimension));
                
                // Vérification que la paire n'existe pas déjà parmi les couples générés precedemment
                for (int j = 0; j < i && flagAllUnique; j++) {                                   
                    flagAllUnique = listCoor[i] != listCoor[j];
                }
            }
            // On recommence l'opération tant que le couple existait déjà
            while (!flagAllUnique);
            
            listObjets.get(i - nbCreatures).setPos(listCoor[i]);
        }
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
                if (perso.pos == this.listObjets.get(i).getPos()){
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

    public int getNbCreatures() {
        return nbCreatures;
    }

    public void setNbCreatures(int nbCreatures) {
        this.nbCreatures = nbCreatures;
    }

    public int getNbObjets() {
        return nbObjets;
    }

    public void setNbObjets(int nbObjets) {
        this.nbObjets = nbObjets;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }
}
