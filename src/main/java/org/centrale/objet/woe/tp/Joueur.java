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
    private ArrayList<Objet> effets;
    
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
     */
    public void choixDeplacement(boolean[][] presences) {
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
        
        // Déplacement
        try {
            perso.deplacer(newPos, presences);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Affichage et choix de la cible à attaquer
     * @param listCreature
     * @return 
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

    public ArrayList<Objet> getEffets() {
        return effets;
    }
    
    /**
     * Ajouter un objet à la liste des objets en cours d'utilisation
     * @param o Objet à utiliser
     */
    public void addToEffets(Objet o) {
        this.effets.add(o);
    }

    public void setEffets(ArrayList<Objet> effets) {
        this.effets = effets;
    }
    
    
}
