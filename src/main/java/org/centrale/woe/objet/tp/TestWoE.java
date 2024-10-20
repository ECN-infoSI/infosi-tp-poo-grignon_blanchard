/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.woe.objet.tp;
import java.util.Scanner;
import org.centrale.woe.bdonn.tp.DatabaseTools;

/**
 *
 * @author grigm
 */
public class TestWoE{
   public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        DatabaseTools database = new DatabaseTools();
        
        // on demande si le joueur veut se connecter à la base de données 
        System.out.println("# Voulez-vous vous connecter à la base de données ? (y/N)");
        String choixconnection = scan.nextLine();
        
        //paramètres pour stocker les réponses 
        boolean connection = false; 
        boolean play = true;
        
        World monde = new World();
        int dimension = 5;
        
        //si on se connecte
        String nomSauvegarde;
        int playerId = -1;
        String nomPartie = "Defaut"; 
        
        if (choixconnection.equals("y") || choixconnection.equals("yes") || choixconnection.equals("Y")) {
            connection = true; 
            database.connect();
            
            // Authentification du Joueur
            System.out.println("# Nom du joueur ?");
            String nomJoueur = scan.nextLine();

            System.out.println("# Mot de Passe ?");
            String motDePasse = scan.nextLine();


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
            nomPartie = scan.nextLine();
         
            if (choix.equals("create")) {         
                nomSauvegarde = "Start";
            }
            else {
                System.out.println("# Nom de la sauvegarde ?");
                nomSauvegarde = scan.nextLine();
            }

            // Chargement de la partie
            try {
                switch(choix) {
                    case "load":
                        monde = database.readWorld(playerId, nomPartie, nomSauvegarde);
                        break;

                    case "create":
                        monde = new World(0, 0, 1, 1, 1, 1, 1, 2, dimension);
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
        }else {
            
            //on joue sans être connecté à la base de données et le monde est généré aléatoirement
            System.out.println("\n # Le monde est créé aléatoirement");
            System.out.println("Il comprend 1 guerrier, 1 loup, 1 lapin, 1 épée, 1 potion de soin et 2 cafés");
            System.out.println("Dans un monde 5*5");
            play = true; 
            monde = new World(0, 0, 1, 1, 1, 1, 1, 2, dimension);
            monde.creationJoueur();
            
        }

        // Phase de jeu
        String answer;
        
        // afficher la légende d'affichage
        System.out.println("Légende d'affichage de l'environnement :");
        System.out.println("# limite du monde");
        System.out.println("O joueur");
        System.out.println("X monstre");
        System.out.println("& object");
        
        while (play && monde.joueur.perso.getPtVie() > 0) {
            monde.tourDeJeu();
            
            System.out.println("\n\n# QUITTER ? (y/N)");   
            answer = scan.nextLine();
             
            if (connection){
                database.connect();
                
                System.out.println("# SAUVEGARDER ? (y/N)");
                String answerSauve = scan.nextLine();

                if (answerSauve.equals("y") || answerSauve.equals("yes") || answerSauve.equals("Y")) {
                    System.out.println("# NOM DE LA SAUVEGARDE ?");
                    nomSauvegarde = scan.nextLine();

                    database.saveWorld(playerId, nomPartie, nomSauvegarde, monde);
                }
                
                database.disconnect();
            }
            
            play = !answer.equals("y") && !answer.equals("yes") && !answer.equals("Y");
        }
        
        //afficher GAME OVER si le personnage joueur est mort
        if (monde.joueur.perso.getPtVie()<=0){
           System.out.println("GAME OVER"); 
        }   
   }
}
