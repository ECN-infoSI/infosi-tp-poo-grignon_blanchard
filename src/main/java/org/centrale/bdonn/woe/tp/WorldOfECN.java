/* --------------------------------------------------------------------------------
 * ECN Tools
 * 
 * Ecole Centrale Nantes - Septembre 2022
 * Equipe pédagogique Informatique et Mathématiques
 * JY Martin
 * -------------------------------------------------------------------------------- */

package org.centrale.bdonn.woe.tp;

import org.centrale.objet.woe.tp.Archer;
import org.centrale.objet.woe.tp.Creature;
import org.centrale.objet.woe.tp.Epee;
import org.centrale.objet.woe.tp.Guerrier;
import org.centrale.objet.woe.tp.Lapin;
import org.centrale.objet.woe.tp.Loup;
import org.centrale.objet.woe.tp.Objet;
import org.centrale.objet.woe.tp.Paysan;
import org.centrale.objet.woe.tp.PotionSoin;
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
        String nomSauvegarde = "INIT";
        int dimension = 10;
        
        // #########
        World world = new World(2, 2, 2, 2, 2, 1, 1, dimension);
        world.setPlayer(nomJoueur);
        world.creationJoueur();
        
        // Test phase
        DatabaseTools database = new DatabaseTools();

        database.connect();
        
        
        try {
            // Save world
            int playerId = database.getPlayerID(nomJoueur, motDePasse);
            database.createPartie(playerId, nomPartie, dimension);
            database.saveWorld(playerId, nomPartie, nomSauvegarde, world);
            
            // Retreive World
            World newMonde = database.readWorld(3, nomPartie, nomSauvegarde);
        }
        catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        
        database.disconnect();
    }
}
