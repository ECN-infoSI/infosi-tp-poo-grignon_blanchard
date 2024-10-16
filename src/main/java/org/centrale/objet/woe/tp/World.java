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
    private ArrayList<Creature> listCreatures; 
    private ArrayList<Objet> listObjets; 

    
    private int nbCreatures;
    private int nbObjets;
    
    private int dimension;
    private boolean[][] presences;
    private boolean[][] presencesObjet;
    
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
        presencesObjet = new boolean[dimension][dimension]; 
       
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
        presencesObjet = new boolean[dimension][dimension]; 
               
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
            presencesObjet[listCoor[i].getX()][listCoor[i].getY()] = true;
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
            System.out.println("# Actions possibles (attack/move/object/nothing):");
            action = scanner.nextLine();
            
            askFlag = false;  // Tant que action n'est pas validé, on suppose qu'il est valide
            
            switch (action) {
                case "attack":
                    if (joueur.perso instanceof Combattant) { 
                        int indiceCible = joueur.choixAttaque(listCreatures);
                        // Attaque et test si la cible est tuée pendant le combat : si oui, retirer la liste
                        if (((Combattant) joueur.perso).combattre(listCreatures.get(indiceCible))) {
                            listCreatures.remove(indiceCible);
                        }
                    }
                    break;
                    
                case "move":
                    Point2D newPos = new Point2D(joueur.choixDeplacement(presences, presencesObjet));
                                        
                    // Déplacement
                    try {
                        joueur.perso.deplacer(newPos, presences, presencesObjet, this.listObjets, joueur.getInventaire());
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                    
                case "object":
                    // si l'inventaire est vide, on le dit au joueur 
                    if (joueur.getInventaire().size()<1){ 
                        System.out.println ("L'inventaire est vide"); 
                    } else {
                    //on active l'objet choisi (ce qui l'utilise,le met dans la liste effets, affiche son effet et le retire de l'inventaire
                    joueur.activerObjet(joueur.choixObjet());
                    }
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
            // Si la créature est vivante            
            if (c.getPtVie() > 0) {
                // Si la creature peut attaqué : attaque selon la distance au joueur
                if (c instanceof Guerrier && Point2D.distance(joueur.perso.getPos(), c.getPos()) <= 1.4) {
                    ((Guerrier) c).combattre(joueur.perso);
                }            
                else if (c instanceof Archer && Point2D.distance(joueur.perso.getPos(), c.getPos()) <= ((Archer) c).getDistAttMax() && ((Archer) c).getNbFleches() > 0) {
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
                else if (presencesObjet[pos.getX() + i][pos.getY() + j]) {
                    System.out.print("&|");
                }
                else {
                    System.out.print(" |");
                }
            }
            
            System.out.println("\n\t+-+-+-+-+-+");
        }
    }

    
    /**
     * Réduire la durée de vie d'un objet d'un, s'il est arrivé à 0 l'objet et supprimé et l'effet annulé
     * @author grigm
     */
    public void userObjet() { 
        // pour chaque objet dans la liste des effets, on réduit leur durée de l'effet d'1 
        for (int i=0; i < joueur.getEffets().size(); i++){
            joueur.getEffets().get(i).setDureeEffet(joueur.getEffets().get(i).getDureeEffet()-1); 
        }
        // pour chaque objet, si la durée de l'effet est arrivée à 0 alors on retire l'effet et on supprime l'objet 
        for (int i=0; i < joueur.getEffets().size(); i++){
            if (joueur.getEffets().get(i).getDureeEffet()==0){
                // si c'est une épée on retire son effet
                if (joueur.getEffets().get(i) instanceof Epee){
                ((Epee)joueur.getEffets().get(i)).retireEffet((Creature) joueur.perso);
                
                // si c'est de la nourriture on retire son effet 
                }else if (joueur.getEffets().get(i) instanceof Nourriture){ 
                    ((Nourriture)joueur.getEffets().get(i)).retireEffet((Creature) joueur.perso);
                }
                // on annonce que l'objet a cassé et on le retire de la liste des effets en cours 
                System.out.println("Votre objet a cassé"); 
                joueur.getEffets().get(i).affiche(); 
                joueur.getEffets().remove(i);      
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
