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
        perso.setEstJoueur(true);
    }
    
    /**
     * Constructeur manuel
     * @param p Personnage du joueur
     */
    public Joueur(Personnage p) {
        this.perso = p;
    }
    
    public void choixDeplacement(boolean[][] presences) {
        
    }
}
