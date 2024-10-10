/* --------------------------------------------------------------------------------
 * ECN Tools
 * 
 * Ecole Centrale Nantes - Septembre 2022
 * Equipe pédagogique Informatique et Mathématiques
 * JY Martin
 * -------------------------------------------------------------------------------- */

package org.centrale.bdonn.woe.tp;

import org.centrale.objet.woe.tp.World;

/**
 *
 * @author ECN
 */
public class WorldOfECN {

    /**
     * main program
     * @param args
     */
    public static void main(String[] args) {
        String nomJoueur = "Saegusa";
        String motDePasse = "Mayumi";
        String nomPartie = "Test Game 1";
        String nomSauvegarde = "Start";
        int dimension = 10;
        
        //
        
        World world = new World(2, 2, 2, 2, 2, 1, 1, dimension);
        world.setPlayer(nomJoueur);
        world.creationJoueur();
        
        // Test phase
        DatabaseTools database = new DatabaseTools();

        database.connect();
        
        // Save world
        try {
            Integer playerId = database.getPlayerID(nomJoueur, motDePasse);
            database.createPartie(playerId, nomPartie, dimension);
            database.saveWorld(playerId, nomPartie, nomSauvegarde, world);
        }
        catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        
        /*        
        // Retreive World
        database.readWorld(playerId, "Test Game 1", "Start", world);
        database.disconnect();
        */
    }
}
