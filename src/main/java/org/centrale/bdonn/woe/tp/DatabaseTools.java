/* --------------------------------------------------------------------------------
 * WoE Tools
 * 
 * Ecole Centrale Nantes - Septembre 2022
 * Equipe pédagogique Informatique et Mathématiques
 * JY Martin
 * -------------------------------------------------------------------------------- */
package org.centrale.bdonn.woe.tp;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.centrale.objet.woe.tp.World;
import org.centrale.objet.woe.tp.Creature;
import org.centrale.objet.woe.tp.Personnage;
import org.centrale.objet.woe.tp.Archer;
import org.centrale.objet.woe.tp.Objet;

/**
 *
 * @author ECN
 */
public class DatabaseTools {

    private String login;
    private String password;
    private String url;
    private Connection connection;

    /**
     * Load infos
     */
    public DatabaseTools() {
        try {
            // Get Properties file
            ResourceBundle properties = ResourceBundle.getBundle(DatabaseTools.class.getPackage().getName() + ".database");

            // USE config parameters
            login = properties.getString("login");
            password = properties.getString("password");
            String server = properties.getString("server");
            String database = properties.getString("database");
            url = "jdbc:postgresql://" + server + "/" + database;

            // Mount driver
            Driver driver = DriverManager.getDriver(url);
            if (driver == null) {
                Class.forName("org.postgresql.Driver");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.connection = null;
    }

    /**
     * Get connection to the database
     */
    public void connect() {
        if (this.connection == null) {
            try {
                this.connection = DriverManager.getConnection(url, login, password);
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Disconnect from database
     */
    public void disconnect() {
        if (this.connection != null) {
            try {
                this.connection.close();
                this.connection = null;
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * get Player ID
     * @param nomJoueur  Pseudo
     * @param password   Mot de Passe
     * @return           Indice du Joueur
     * @throws Exception Si les identifiants de connexion ne fonctionne pas
     */
    public Integer getPlayerID(String nomJoueur, String password) throws Exception {
        int playerId = -1;
        
        try {
           
            String query = "SELECT id FROM Joueur WHERE pseudo = ? AND motdepasse = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            
            stmt.setString(1, nomJoueur);
            stmt.setString(2, password);
            
            ResultSet res = stmt.executeQuery();
            
            if (res.next()) {
                playerId = res.getInt("id");
            }
               
            stmt.close();
            
        } catch(SQLException ex) {
            System.err.println("SQLException : " + ex.getMessage()) ;
        }
        
        if (playerId == -1) {
            throw new Exception("Le couple nom / mot de passe n'existe pas !");
        }
        
        return playerId;
    }
    
    /**
     * Retourne l'indice d'une partie
     * @param idJoueur      Indice du Joueur
     * @param nomPartie     Nom de la partie
     * @return              Indice de la partie
     * @throws Exception    Si la partie n'existe pas     
     */
    private int getIdPartie(int idJoueur, String nomPartie) throws Exception {
        int idPartie = -1;
        
        try {
           
            String query = "SELECT id FROM Partie WHERE idJoueur = ? AND nom = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            
            stmt.setInt(1, idJoueur);
            stmt.setString(2, nomPartie);
            
            ResultSet res = stmt.executeQuery();
            
            if (res.next()) {
                idPartie = res.getInt("id");
            }
               
            stmt.close();
            
        } catch(SQLException ex) {
            System.err.println("SQLException : " + ex.getMessage()) ;
        }
        
        if (idPartie == -1) {
            throw new Exception("La partie cherchée n'existe pas !");
        }
        
        return idPartie;
    }
    
    /**
     * Crée une nouvelle sauvegarde avec les informations données
     * @param idPartie      Identifiant de la partie en cours
     * @param nomSauvegarde Nom de la nouvelle sauvegarde
     */
    private void createSauvegarde(int idPartie, String nomSauvegarde) {
        try {
           
            String query = "INSERT INTO Sauvegarde VALUES(?, ?, CURRENT_TIMESTAMP);";
            PreparedStatement stmt = connection.prepareStatement(query);
            
            stmt.setString(1, nomSauvegarde);
            stmt.setInt(2, idPartie);
            
            stmt.execute();
            stmt.close();
            
        } catch(SQLException ex) {
            System.err.println("SQLException : " + ex.getMessage()) ;
        }
    }

    /**
     * save world to database
     * @param idJoueur
     * @param nomPartie
     * @param nomSauvegarde
     * @param monde
     */
    public void saveWorld(Integer idJoueur, String nomPartie, String nomSauvegarde, World monde) {
        
    }
    
    /**
     * Processus de sauvegarde pour une creature
     * @author simon
     * @param c             Instance de la créature à sauvergarder
     * @param idPartie      Identifiant de la partie en cours
     * @param nomSauvegarde Nom de la sauvegarde
     * @param estJoueur     Test si la créature est controllée par le joueur
     */
    private void saveCreature(Creature c, int idPartie, String nomSauvegarde, boolean estJoueur) {
        String query1 = "INSERT INTO InstanceCreature(nomSauvegarde, idPartie, estJoueur, type, ptVie, ptAtt, pageAtt, ptPar, pagePar, x, y";
        String query2 = ") VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
        
        // Ajout de champs en plus en fonction de la sous-classe de creature
        if (c instanceof Personnage) {
            query1 += ", nom, distAtt, sexe";
            query2 += ", ?, ?, ?";
            
            if (c instanceof Archer) {
                query1 += ", nbProj";
                query2 += ", ?";
            }
        }
        
        try {
            PreparedStatement stmt = connection.prepareStatement(query1 + query2 + ");");
            
            stmt.setString(1, nomSauvegarde);
            stmt.setInt(2, idPartie);
            stmt.setBoolean(3, estJoueur);
            stmt.setString(4, c.getClass().getSimpleName());
            stmt.setInt(5, c.getPtVie());
            stmt.setInt(6, c.getDegAtt());
            stmt.setInt(7, c.getPageAtt());
            stmt.setInt(8, c.getPtPar());
            stmt.setInt(9, c.getPagePar());
            stmt.setInt(10, c.getPos().getX());
            stmt.setInt(11, c.getPos().getY());
            
            if (c instanceof Personnage) {
                stmt.setString(12, ((Personnage) c).getNom());
                stmt.setInt(13, ((Personnage) c).getDistAttMax());                
                stmt.setInt(14, ((Personnage) c).getSexe());
                
                if (c instanceof Archer) {
                    stmt.setInt(15, ((Archer) c).getNbFleches());
                }
            }
            
            stmt.execute();               
            stmt.close();
            
        } catch(SQLException ex) {
            System.err.println("SQLException : " + ex.getMessage()) ;
        }
    }
    
    /**
     * Processus de sauvegarde pour un objet
     * @author grigm
     * @param o             Instance d'objet à sauvergarder
     * @param idPartie      Identifiant de la partie en cours
     * @param nomSauvegarde Nom de la sauvegarde
     */
    private void saveObjet(Objet o, int idPartie, String nomSauvegarde){ 
        try {
            String query = "INSERT INTO positionobjet(idobjet, nomsauvegarde, idpartie, dansinventaire, x, y) VALUES (?,?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(query);


            String nomClasse = o.getClass().getSimpleName(); 

            try {
                String queryIdObjet = "SELECT id from objet Where type =?";
                PreparedStatement stmtIdObjet = connection.prepareStatement(queryIdObjet); 
                stmtIdObjet.setString(1,nomClasse);
                ResultSet resIdObjet = stmtIdObjet.executeQuery();
                if (resIdObjet.next()) {
                    int idJoueur =resIdObjet.getInt("id");
                    stmt.setInt(1,idJoueur);
                }
                stmtIdObjet.close(); 
            } catch(SQLException ex) {
                System.err.println("SQLException : " + ex.getMessage()) ;
            }

            stmt.setString(2, nomSauvegarde);
            stmt.setInt(3, idPartie);
            stmt.setBoolean(4, false);
            stmt.setInt(5,o.getPos().getX()); 
            stmt.setInt(6,o.getPos().getY()); 
            stmt.execute();
            
        } catch(SQLException ex) {
            System.err.println("SQLException : " + ex.getMessage()) ;
        }
    }

    /**
     * get world from database
     * @param idJoueur
     * @param nomPartie
     * @param nomSauvegarde
     * @param monde
     */
    public void readWorld(Integer idJoueur, String nomPartie, String nomSauvegarde, World monde) {

    }
}
