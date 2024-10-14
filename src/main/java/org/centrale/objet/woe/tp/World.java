/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.woe.tp;

import java.util.Random; 
import java.util.Date;
import java.util.ArrayList; 
import java.util.Scanner;

/**
 * Classe représentant le monde à utiliser lors d'une partie
 * @author grigm
 */
public class World {
    public Joueur joueur;
    public ArrayList<Creature> listCreatures; 
    public ArrayList<Objet> listObjets; 
    public ArrayList<Utilisable> effets; 
    public ArrayList<Objet> listInventaire; 
    
    private int nbCreatures;
    private int nbObjets;
    
    private int dimension;
    private boolean[][] presences;
    
    private String player = "";

    /**
     * Constructeur par défaut qui crée un monde sans créature ni objet (les arraylists sont créées mais elles sont vides)
     */
    public World() { 
        dimension = 50;
        
        listCreatures = new ArrayList(); //notre liste de créature vide
        listObjets = new ArrayList(); //notre liste d'objet vide
        
        nbCreatures = 0;
        nbObjets = 0;
        
        presences = new boolean[dimension][dimension];
        
        effets= new ArrayList(); //liste d'utilisable vide
        listInventaire = new ArrayList(); //inventaire vide 
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
        
        presences = new boolean[dimension][dimension];
        
        effets = new ArrayList(); //liste d'utilisable vide
        listInventaire = new ArrayList(); //inventaire vide 
        
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
            presences[listCoor[i].getX()][listCoor[i].getY()] = true;
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
        // Affichage des statistiques du joueur
        System.out.println("\n# NOUVEAU TOUR DE JEU");
        joueur.perso.affiche();
        
        // Affichage de l'environnement à proximité du joueur
        afficheEnvironnementJoueur();
        
        // Affichage des actions possibles 
        Scanner scanner = new Scanner(System.in);
        String action;    
        boolean askFlag;
        
        do {
            System.out.println("# Actions possibles (attack/move/nothing):");
            action = scanner.nextLine();
            
            askFlag = false;  // Tant que action n'est pas validé, on suppose qu'il est valide
            
            switch (action) {
                case "attack":
                    break;
                    
                case "move":
                    joueur.choixDeplacement(presences);
                    break;
                    
                case "nothing":
                    break;
                    
                default:
                    askFlag = true;
            }
        }
        while (askFlag);
        
        
        // Action des créatures non-joueurs
        
        for (Creature c: listCreatures) {
            // Si la creature peut attaqué : attaque selon la distance au joueur
            if (c instanceof Guerrier && Point2D.distance(joueur.perso.getPos(), c.getPos()) <= 1.4) {
                ((Guerrier) c).combattre(joueur.perso);
            }            
            else if (c instanceof Archer && Point2D.distance(joueur.perso.getPos(), c.getPos()) <= ((Archer) c).getDistAttMax()) {
                ((Archer) c).combattre(joueur.perso);
            }
            else if (c instanceof Loup && Point2D.distance(joueur.perso.getPos(), c.getPos()) <= 1.4) {
                ((Loup) c).combattre(joueur.perso);
            }
            // Si joueur trop loin, ou creature neutre, deplacement
            else {
                c.deplacementAleatoire(presences);
            }   
        }
    }
    
    /**
     * Affiche l'environnement à proximité du joueur
     * @author simon
     */
    public void afficheEnvironnementJoueur() {
        Point2D pos = joueur.perso.getPos();
        
        System.out.println("\t+-+-+-+-+-+");
        
        for (int i = -2; i <= 2; i++) {
            System.out.print("\t|");
            
            for (int j = -2; j <= 2; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("O|");
                }
                // Hors du plateau
                else if (pos.getX() + i < 0 || pos.getY() + j < 0 || pos.getX() + i >= dimension || pos.getY() + j >= dimension) {
                    System.out.print("#|");
                }
                else if (presences[pos.getX() + i][pos.getY() + j]) {
                    System.out.print("X|");
                }
                else {
                    System.out.print(" |");
                }
            }
            
            System.out.println("\n\t+-+-+-+-+-+");
        }
    }
      
    /**
     * Pour afficher l'inventaire
     * @author grigm
     */
    public void afficheInventaire() { 
        // s'il n'y a rien dans l'iventaire on le récise au joueur 
        if (this.listInventaire.isEmpty()){
            System.out.println("L'inventaire est vide"); 
        } else {
            for (int i=0; i < this.listInventaire.size(); i++){
               System.out.println("Objet " + i +":");
               listInventaire.get(i).affiche(); 
            }
        }
    }
    
    /**
     * Pour afficher la liste des utilisables en cours
     * @author grigm
     */
    public void afficheEffets() { 
        // s'il n'y a rien dans l'iventaire on le récise au joueur 
        if (this.effets.isEmpty()){
            System.out.println("Aucun effet n'est actif"); 
        } else {
            for (int i=0; i < this.effets.size(); i++){
               System.out.println("Objet " + i +":");
               effets.get(i).affiche(); 
               System.out.println("Durée restante : " +effets.get(i).getDureeEffet()); 
            }
        }
    }
    
    /**
     * Ajouter un objet dans l'inventaire
     * @author grigm
     * @param o Objet à ajouter dans l'inventaire
     */
    public void ajouterInventaire(Objet o) { 
        this.listInventaire.add(o);        
    }
    
    /**
     * Activer un objet à la position i dans l'inventaire, appliquer son effet et le placer dans la liste des effets
     * @author grigm
     * @param i indice de position de l'objet à activer dans l'inventaire 
     * @param c la créature qui utilise l'objet (souvent le joueur)
     */
    public void activerObjet(int i , Creature c) { 
        if (listInventaire.get(i) instanceof Utilisable){
            ((Utilisable)listInventaire.get(i)).utilise(c);      
            this.effets.add((Utilisable) listInventaire.get(i)); 
        } else{ 
            System.out.println("Cet objet n'est pas utilisable"); 
        }
    }
    
    /**
     * Réduire la durée de vie d'un objet d'un, s'il est arrivé à 0 l'objet et supprimé et l'effet annulé
     * @author grigm
     * @param c Créature qui a utilisé les objets et qui a l'effet
     */
    public void userObjet(Creature c) { 
        for (int i=0; i < this.effets.size(); i++){
            effets.get(i).setDureeEffet(effets.get(i).getDureeEffet()-1); 
        }
        for (int i=0; i < this.effets.size(); i++){
            if (effets.get(i).getDureeEffet()==0){
                if (effets.get(i) instanceof Epee){
                ((Epee)effets.get(i)).retireEffet(c);
                
                }else if (effets.get(i) instanceof Nourriture){ 
                    ((Nourriture)effets.get(i)).retireEffet(c);
                }
                System.out.println("Votre objet a casé"); 
                effets.get(i).affiche(); 
                effets.remove(i);                
            }
        }
    }

    
    /**
     * Procédure de création du personnage du joueur et placement sur une case libre
     * @author simon
     */
    public void creationJoueur() {
        // Creation du joueur et du personnage associé 
        joueur = new Joueur();
        
        // Placement du joueur sur une case libre
        Date date = new Date();
        Random rand = new Random(date.getTime()); 
        boolean freePositionFlag = false;
        
        while (!freePositionFlag) {
            freePositionFlag = true;
            
            joueur.perso.setPos(new Point2D(rand.nextInt(dimension), rand.nextInt(dimension)));
            
            for (int i = 0; i < nbCreatures - 1 && freePositionFlag; i++) {
                freePositionFlag = joueur.perso.getPos() != listCreatures.get(i).getPos();
            }
        }
        
        presences[joueur.perso.getPos().getX()][joueur.perso.getPos().getY()] = true;
    }
    
    /**
     * Ajout d'une créature non-joueur
     * @param c Créature à ajouter
     */
    public void addCreature(Creature c) {
        listCreatures.add(c);
        presences[c.getPos().getX()][c.getPos().getY()] = true;
        nbCreatures ++;
    }
    
    /**
     * Ajout d'un objet
     * @param o Objet à ajouter
     */
    public void addObjets(Objet o) {
        listObjets.add(o);
        nbObjets ++;
    }
    
    /**
     * Modifie le personnage du joueur en modifiant sa presence dans le monde
     * @param p Nouveau personnage
     */
    public void changePersonnageJoueur(Personnage p) {
        if (this.joueur != null && this.joueur.perso != null) {
            // S'il y avait déjà un personnage, on supprime sa présence
            presences[joueur.perso.getPos().getX()][joueur.perso.getPos().getY()] = false;
            this.joueur.perso = p;
        }
        else {
            this.joueur = new Joueur(p);
        }
        
        presences[joueur.perso.getPos().getX()][joueur.perso.getPos().getY()] = true;
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

    public boolean[][] getPresences() {
        return presences;
    }

    public void setPresences(boolean[][] presences) {
        this.presences = presences;
    }
    
    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public ArrayList<Creature> getListCreatures() {
        return listCreatures;
    }

    public void setListCreatures(ArrayList<Creature> listCreatures) {
        this.listCreatures = listCreatures;
    }

    public ArrayList<Objet> getListObjets() {
        return listObjets;
    }

    public void setListObjets(ArrayList<Objet> listObjets) {
        this.listObjets = listObjets;
    }
    
    
}
