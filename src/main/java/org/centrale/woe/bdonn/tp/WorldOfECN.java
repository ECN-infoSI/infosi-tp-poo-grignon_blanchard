/* --------------------------------------------------------------------------------
 * ECN Tools
 * 
 * Ecole Centrale Nantes - Septembre 2022
 * Equipe pédagogique Informatique et Mathématiques
 * JY Martin
 * -------------------------------------------------------------------------------- */

package org.centrale.woe.bdonn.tp;

import org.centrale.woe.objet.tp.World;
import java.util.Scanner;

/**
 *
 * @author ECN
 */
public class WorldOfECN {

    /**
     * main program
     * @param args  Paramètres
     * @throws java.lang.Exception Erreur si l'authentificaion echoue
     */
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        DatabaseTools database = new DatabaseTools();
        
        database.connect();
        
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
        
        String choix = "";
        boolean continueFlag = true;

        while (continueFlag) {
            System.out.println("# Créer ou Charger une partie, Supprimer un sauvegarde ? (create / load / delete)");
            choix = scan.nextLine();
            continueFlag = !"create".equals(choix) && !choix.equals("load") && !choix.equals("delete");
        }
        
        System.out.println("# Nom de la partie ?");
        String nomPartie = scan.nextLine();
        
        int dimension = 10;
        String nomSauvegarde;
        World monde;
        
        if (choix.equals("create")) {         
            nomSauvegarde = "Start";
        }
        else {
            System.out.println("# Nom de la sauvegarde ?");
            nomSauvegarde = scan.nextLine();
        }
        
        try {
            switch(choix) {
                case "load":
                    monde = database.readWorld(playerId, nomPartie, nomSauvegarde);
                    break;
                    
                case "create":
                    monde = new World(2, 2, 2, 2, 2, 1, 1, 2, dimension);
                    monde.creationJoueur();
                    
                    database.createPartie(playerId, nomPartie, dimension);
                    database.saveWorld(playerId, nomPartie, nomSauvegarde, monde);
                    break;
                    
                case "delete":
                    database.removeWorld(playerId, nomPartie, nomSauvegarde);
                    break;
            }
        }
        catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        
        database.disconnect();
    }
}
