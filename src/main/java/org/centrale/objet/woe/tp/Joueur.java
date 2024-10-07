/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.woe.tp;

import java.util.Scanner;

/**
 * Classe représentant le joueur
 * @author simon
 */
public class Joueur {
    public Personnage perso;
    
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
    }
    
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
}
