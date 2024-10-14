/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.woe.tp;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Classe représentant le joueur
 * @author simon
 */
public class Joueur {
    public Personnage perso;
    
    private ArrayList<Objet> inventaire;
    private ArrayList<Utilisable> effets;
    
    /**
     * Constructeur
     */
    public Joueur() {
        Scanner scanner = new Scanner(System.in);
        String nomClasse;
        
        boolean askFlag = true;
        
        do {
            System.out.println("# Choix de la classe du personnage (Guerrier/Archer) : ");
            nomClasse = scanner.nextLine();
            
            askFlag = false;  // Tant que nomClasse n'est pas validé, on suppose qu'il est valide
            
            switch (nomClasse) {
                case "Guerrier":
                    perso = new Guerrier();
                    break;
                    
                case "Archer":
                    perso = new Archer();
                    break;
                    
                default:
                    askFlag = true;
            }
        }
        while (askFlag);
        
        System.out.println("# Nom du personnage : ");
        perso.setNom(scanner.nextLine());
        perso.setEstJoueur(true);
        
        inventaire = new ArrayList();
    }
    
    /**
     * Constructeur manuel
     * @param p Personnage du joueur
     */
    public Joueur(Personnage p) {
        this.perso = p;
        this.inventaire = new ArrayList();
    }
    
    /**
     * Affichage du menu de déplacements
     * @param presences Présences des autres créatures
     * @param presencesObjet Présences des objets 
     * @return  la position choisie par le joueur
     */
    public Point2D choixDeplacement(boolean[][] presences, boolean[][] presencesObjet) {
        Scanner scanner = new Scanner(System.in);
        String direction;    
        boolean askFlag;
        Point2D newPos = new Point2D(perso.getPos());
        
        do {
            System.out.println("# Directions possibles (north/south/east/west):");
            direction = scanner.nextLine();
            
            askFlag = false;  // Tant que direction n'est pas validé, on suppose qu'il est valide
            
            switch (direction) {
                case "north":
                    newPos.translate(-1, 0);
                    break; 
   
                case "south":
                    newPos.translate(1, 0);
                    break;
  
                case "east":
                    newPos.translate(0, 1);
                    break;
                    
                case "west":
                    newPos.translate(0, -1);
                    break;
                    
                default:
                    askFlag = true;
            }
        }
        while (askFlag);
        return newPos;
    }
    
    /**
     * Affichage et choix de la cible à attaquer
     * @param listCreature Liste des créatures adverses
     * @return Indice de la créature à attaquer dans la liste
     */
    public int choixAttaque(ArrayList<Creature> listCreature) {
        Scanner scan = new Scanner(System.in);
        
        // Affichage de l'ensemble des créatures adverses
        
        System.out.println("# Liste des Créatures dans le monde : ");
        
        for (int i = 0; i < listCreature.size(); i++) {
            Creature tempCreature = listCreature.get(i);
            
            System.out.print("\t" + (i + 1) + ". " + tempCreature.getClass().getSimpleName() + " ");
            
            if (tempCreature instanceof Personnage) {
                System.out.print(((Personnage) tempCreature).getNom());
            }
            else {
                System.out.print("sauvage");
            }
            
            System.out.println(" : " + tempCreature.getPtVie() + "pv" + " (" + tempCreature.getPos().getX() + ", " + tempCreature.getPos().getY() + ")");
        }
        
        // Demande de la cible
        
        int indexCible = -1;
        
        while (indexCible == -1) {
            System.out.println("# N° de la créature à attaquer ?");
            
            try {
                indexCible = Integer.parseInt(scan.nextLine()) - 1;
            }
            catch (NumberFormatException e) {
                indexCible = -1;
            }
            
            // Vérification de la validité de l'indice
            if (indexCible < 0 || indexCible >= listCreature.size()) {
                indexCible = -1;
            }
        }
        
        return indexCible;
    }
    
    /**
     * Affichage et choix des objets dans l'inventaire
     * @return Indice de l'objet à utiliser
     */
    public int choixObjet() {
        Scanner scan = new Scanner(System.in);
        
        // Affichage des objets dans l'inventaire
        
        this.afficheInventaire();
        
        // Demande de la cible
        
        int indexCible = -1;
        
        while (indexCible == -1) {
            System.out.println("# N° de l'objet à utiliser ?");
            
            try {
                indexCible = Integer.parseInt(scan.nextLine()) - 1;
            }
            catch (NumberFormatException e) {
                indexCible = -1;
            }
            
            // Vérification de la validité de l'indice
            if (indexCible < 0 || indexCible >= inventaire.size()) {
                indexCible = -1;
            }
        }
        
        return indexCible;
    }
    
    /**
     * Pour afficher la liste des utilisables en cours
     * @author grigm
     */
    public void afficheEffets() { 
        // s'il n'y a rien dans la liste des effets on le précise au joueur 
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
     * Pour afficher l'inventaire du joueur
     * @author grigm
     */
    public void afficheInventaire() { 
        // s'il n'y a rien dans l'inventaire on le précise au joueur 
        if (this.inventaire.isEmpty()){
            System.out.println("Aucun objet ne se trouve dans l'inventaire"); 
        } else {
            System.out.println("# Liste des objets dans l'inventaire : ");
        
            for (int i = 0; i < inventaire.size(); i++) {
                Objet o = inventaire.get(i);

                System.out.println("\t" + (i + 1) + ". " + o.getClass().getSimpleName());
            }
        }
    }
    
    public ArrayList<Objet> getInventaire() {
        return inventaire;
    }
    
    /**
     * Ajout l'objet o à l'inventaire
     * @param o Objet à ajouter
     */
    public void addToInventaire(Objet o) {
        this.inventaire.add(o);
    }

    public void setInventaire(ArrayList<Objet> inventaire) {
        this.inventaire = inventaire;
    }

    public ArrayList<Utilisable> getEffets() {
        return effets;
    }
    
    /**
     * Ajouter un objet à la liste des objets en cours d'utilisation
     * @param o Objet à utiliser
     */
    public void addToEffets(Utilisable o) {
        this.effets.add(o);
    }

    public void setEffets(ArrayList<Utilisable> effets) {
        this.effets = effets;
    }
    
     /**
     * Activer un objet à la position i dans l'inventaire, appliquer son effet et le placer dans la liste des effets
     * On affiche l'effet qui est affecté au joueur puis on retire l'objet de l'inventaire
     * @author grigm
     * @param i indice de position de l'objet à activer dans l'inventaire 
     */
    public void activerObjet(int i) { 
        // on vérifie que l'objet implémente utilisable 
        if (this.inventaire.get(i) instanceof Utilisable){
            // on utilise l'objet sur le personnage joueur 
            ((Utilisable)inventaire.get(i)).utilise(this.perso);     
            //on ajoute l'objet dans la liste des effets en cours effets 
            this.addToEffets((Utilisable) inventaire.get(i)); 
            //on affiche l'effet de l'objet qu'on vient d'activer
            ((Utilisable)inventaire.get(i)).afficheEffet(); 
            //on retire l'objet de l'inventaire 
            this.inventaire.remove(i); 
        } else{ 
            // si l'objet n'est pas utilisable
            System.out.println("Cet objet n'est pas utilisable"); 
        }
    }
}
