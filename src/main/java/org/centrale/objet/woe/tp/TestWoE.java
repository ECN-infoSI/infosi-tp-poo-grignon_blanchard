/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.woe.tp;
import java.util.Date;
import java.util.Random; 
import java.util.Scanner;
import org.centrale.bdonn.woe.tp.DatabaseTools;

/**
 *
 * @author grigm
 */
public class TestWoE{
   public static void main(String[] args) throws Exception {
       Scanner scan = new Scanner(System.in);
        DatabaseTools database = new DatabaseTools();
        
        database.connect();
        
        // Authentification du Joueur
        
        System.out.println("# Nom du joueur ?");
        String nomJoueur = scan.nextLine();
        
        System.out.println("# Mot de Passe ?");
        String motDePasse = scan.nextLine();
        
        int playerId = -1;
        
        try {
            playerId = database.getPlayerID(nomJoueur, motDePasse);
        }
        catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        
        if (playerId == -1) {
            throw new Exception("Le couple nom/mot de passe n'est pas reconnu !");
        }
        
        // Choix de l'action pour commencer, continuer ou supprimer une partie
        
        String choix = "";
        boolean continueFlag = true;

        while (continueFlag) {
            System.out.println("# Créer ou Charger une partie, Supprimer un sauvegarde ? (create / load / delete)");
            choix = scan.nextLine();
            continueFlag = !"create".equals(choix) && !choix.equals("load") && !choix.equals("delete");
        }
        
        // Récupération des informations relatives à la sauvegarde
        
        System.out.println("# Nom de la partie ?");
        String nomPartie = scan.nextLine();
        
        int dimension = 5;
        String nomSauvegarde;
        World monde = new World();
        
        if (choix.equals("create")) {         
            nomSauvegarde = "Start";
        }
        else {
            System.out.println("# Nom de la sauvegarde ?");
            nomSauvegarde = scan.nextLine();
        }
        
        // Chargement de la partie
        
        boolean play = true;
        
        try {
            switch(choix) {
                case "load":
                    monde = database.readWorld(playerId, nomPartie, nomSauvegarde);
                    break;
                    
                case "create":
                    monde = new World(0, 0, 1, 1, 1, 1, 1, dimension);
                    monde.creationJoueur();
                    
                    database.createPartie(playerId, nomPartie, dimension);
                    database.saveWorld(playerId, nomPartie, nomSauvegarde, monde);
                    break;
                    
                case "delete":
                    database.removeWorld(playerId, nomPartie, nomSauvegarde);
                    play = false;
                    break;
            }
        }
        catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
            play = false;
        }
        
        // Phase de jeu
        String answer;
        
        while (play && monde.joueur.perso.getPtVie() > 0) {
            monde.tourDeJeu();
            
            System.out.println("# SAUVEGARDER ? (y/N)");
            answer = scan.nextLine();
            
            if (answer.equals("y") || answer.equals("yes") || answer.equals("Y")) {
                System.out.println("# NOM DE LA SAUVEGARDE ?");
                nomSauvegarde = scan.nextLine();
                
                database.saveWorld(playerId, nomPartie, nomSauvegarde, monde);
                
                System.out.println("# QUITTER ? (y/N)");   
                answer = scan.nextLine();
                
                play = !answer.equals("y") && !answer.equals("yes") && !answer.equals("Y");
            }
        }
        System.out.println("GAME OVER");
        database.disconnect();
   }
}
