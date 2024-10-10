/* --------------------------------------------------------------------------------
 * WoE Tools
 * 
 * Ecole Centrale Nantes - Septembre 2022
 * Equipe pédagogique Informatique et Mathématiques
 * JY Martin
 * -------------------------------------------------------------------------------- */
package org.centrale.bdonn.woe.tp;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

import org.centrale.objet.woe.tp.World;
import org.centrale.objet.woe.tp.Creature;
import org.centrale.objet.woe.tp.Personnage;
import org.centrale.objet.woe.tp.Archer;
import org.centrale.objet.woe.tp.Objet;
import org.centrale.objet.woe.tp.Point2D;

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
     * Ajout d'une partie dans la base de données
     * @param idJoueur  Identifiant du joueur
     * @param nomPartie Nom de la partie à créer
     * @param dimension Dimension du monde
     */
    public void createPartie(int idJoueur, String nomPartie, int dimension) {
        try {
           
            String query = "INSERT INTO Partie(nom, idJoueur, largeur, longueur) VALUES(?, ?, ?, ?);";
            PreparedStatement stmt = connection.prepareStatement(query);
            
            stmt.setString(1, nomPartie);
            stmt.setInt(2, idJoueur);
            stmt.setInt(3, dimension);
            stmt.setInt(4, dimension);
            
            stmt.execute();
            stmt.close();
            
        } catch(SQLException ex) {
            System.err.println("SQLException : " + ex.getMessage()) ;
        }
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
    public void saveWorld(Integer idJoueur, String nomPartie, String nomSauvegarde, World monde) throws Exception {
        // Obtention de l'identifiant de la partie
        int idPartie = this.getIdPartie(idJoueur, nomPartie);
        
        // Création de la sauvegarde
        this.createSauvegarde(idPartie, nomSauvegarde);
        
        // Sauvegarde du personnage du joueur
        saveCreature(monde.getJoueur().perso, idPartie, nomSauvegarde, true);
        
        // Sauvegarde des créatures non-joueur
        for (Creature c: monde.getListCreatures()) {
            saveCreature(c, idPartie, nomSauvegarde, false);
        }
        
        // Sauvegarde des objets
        for (Objet o: monde.getListObjets()) {
            saveObjet(o, idPartie, nomSauvegarde);
        }
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
     * Obtient la dimension d'un monde carré depuis les informations sur la partie
     * @param idJoueur  Identifiant du Joueur  
     * @param nomPartie Nom de la partie
     * @return          Dimension du monde
     */
    private int getWorldDimension(int idJoueur, String nomPartie) {
        int dimension = 0;
        
        try {
            String query = "SELECT largeur FROM Partie WHERE idJoueur = ? AND nom = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            
            stmt.setInt(1, idJoueur);
            stmt.setString(2, nomPartie);
            
            ResultSet res = stmt.executeQuery();
            
            if (res.next()) {
                dimension = res.getInt("largeur");
            }
               
            stmt.close();
            
        } catch(SQLException ex) {
            System.err.println("SQLException : " + ex.getMessage()) ;
        }
        
        return dimension;
    }  
    
    /**
     * Retourne l'ensemble des indices des instances de créatures d'une sauvegarde
     * @param idPartie      Identifiant de la partie
     * @param nomSauvegarde Nom de la sauvegarde
     * @return              Liste des indices des créatures de la sauvegarde          
     */
    private ArrayList<Integer> getCreaturesIndexes(int idPartie, String nomSauvegarde) {
        ArrayList<Integer> creaturesIndexes = new ArrayList<Integer>();
        
        try {
           
            String query = "SELECT id FROM InstanceCreature WHERE idPartie = ? AND nomSauvegarde = ?;";
            PreparedStatement stmt = connection.prepareStatement(query);
            
            stmt.setInt(1, idPartie);
            stmt.setString(2, nomSauvegarde);
            
            ResultSet res = stmt.executeQuery();
            
            if (res.next()) {
                creaturesIndexes.add(res.getInt("id"));
            }
               
            stmt.close();
            
        } catch(SQLException ex) {
            System.err.println("SQLException : " + ex.getMessage()) ;
        }
        
        return creaturesIndexes;
    }
    
    /**
     * Retourne l'ensemble des indices des instances des objets d'une sauvegarde
     * @param idPartie      Identifiant de la partie
     * @param nomSauvegarde Nom de la sauvegarde
     * @return              Liste des indices des objets de la sauvegarde          
     */
    private ArrayList<Integer> getObjetsIndexes(int idPartie, String nomSauvegarde) {
        ArrayList<Integer> objetsIndexes = new ArrayList<Integer>();
        
        try {
           
            String query = "SELECT id FROM PositionObjets WHERE idPartie = ? AND nomSauvegarde = ?;";
            PreparedStatement stmt = connection.prepareStatement(query);
            
            stmt.setInt(1, idPartie);
            stmt.setString(2, nomSauvegarde);
            
            ResultSet res = stmt.executeQuery();
            
            if (res.next()) {
                objetsIndexes.add(res.getInt("id"));
            }
               
            stmt.close();
            
        } catch(SQLException ex) {
            System.err.println("SQLException : " + ex.getMessage()) ;
        }
        
        return objetsIndexes;
    }  

    /**
     * get world from database
     * @param idJoueur
     * @param nomPartie
     * @param nomSauvegarde
     */
    public void readWorld(Integer idJoueur, String nomPartie, String nomSauvegarde) {
        // Creation d'une instance de World
        World monde = new World();
        monde.setDimension(getWorldDimension(idJoueur, nomPartie));
        
        // Ajout des créatures non-joueurs
        
        // Ajout des objets
        
        // Ajout du personnage joueur
    }
    
    /**
     * Processus pour lire une créature et retourner un objet de la sous classe correspondante
     * @author grigm
     * @param idCreature    Id de la créature dans la table instancecreature
     */
    private Creature readCreature(int idCreature){
        String query1 = "SELECT * FROM instancecreature WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query1);
            stmt.setInt(1, idCreature);
            ResultSet res = stmt.executeQuery();
            
            if (res.next()) {
                String nom= res.getString("nom");
                String typeClass= res.getString("type");
                int ptvie= res.getInt("ptvie");
                int ptatt= res.getInt("ptatt");
                int pageatt= res.getInt("pageatt");
                int ptpar= res.getInt("ptpar");
                int pagepar= res.getInt("pagepar");
                int distatt= res.getInt("distatt");
                int nbproj= res.getInt("nbproj");
                int x= res.getInt("x");
                int y= res.getInt("y"); 
                int sexe = res.getInt("sexe"); 
                boolean estJoueur = res.getBoolean("estJoueur");
            
                try {
                    
                    //On cree un objet Class
                    Class cl = Class.forName("org.centrale.objet.woe.tp." + typeClass);
                    //On cree les parametres du constructeur
                    Class[] types = new Class[]{};
                    //On recupere le constructeur avec les  paramètres
                    Constructor ct = cl.getConstructor(types);
                    //On instancie l’objet avec le constructeur surcharge !
                    Object o = ct.newInstance();
                    
                    //on crée un objet position 
                    Point2D position = new Point2D(x,y); 
                    ((Creature)o).setPos(position); 
                    
                    ((Creature)o).setDegAtt(ptatt); 
                    ((Creature)o).setPageAtt(pageatt); 
                    ((Creature)o).setPagePar(pagepar); 
                    ((Creature)o).setPtPar(ptpar); 
                    ((Creature)o).setPtVie(ptvie); 
                    
                    if (o instanceof Personnage){
                        ((Personnage)o).setNom(nom); 
                        ((Personnage)o).setSexe(sexe); 
                        ((Personnage)o).setDistAttMax(distatt);
                        ((Personnage)o).setEstJoueur(estJoueur);
                        if (o instanceof Archer){
                            ((Archer)o).setNbFleches(nbproj);
                        }
                    }
                    return (Creature)o; 
                } catch (Exception e) { 
                    System.out.println("Message erreur readCreature"); 
                    return null; 
                }
            }
            stmt.close();
            
        } catch(SQLException ex) {
            System.err.println("SQLException : " + ex.getMessage()) ;
            return null; 
        }
        return null; 
    }
    
    /**
     * Processus pour lire une créature et retourner un objet de la sous classe correspondante
     * @author grigm
     * @param idObjet    Id (unique) de l'objet dans la table positionobjet
     */
    private Objet readObjet(int idObjet){
        String query1 = "SELECT objet.type, positionobjet.x, positionobjet.y, positionobjet.dansinventaire FROM positionobjet JOIN objet on objet.id = positionobjet.idobjet WHERE positionobjet.id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query1);
            stmt.setInt(1, idObjet);
            ResultSet res = stmt.executeQuery();
            
            if (res.next()) {
                String typeClass= res.getString("type");
                int x= res.getInt("x");
                int y= res.getInt("y"); 
            
                try {
                 
                    //On cree un objet Class
                    Class cl = Class.forName("org.centrale.objet.woe.tp." + typeClass);
                    //On cree les parametres du constructeur
                    Class[] types = new Class[]{};
                    //On recupere le constructeur avec les  paramètres
                    Constructor ct = cl.getConstructor(types);
                    //On instancie l’objet avec le constructeur surcharge !
                    Object o = ct.newInstance();
                    
                    //on crée un objet position 
                    Point2D position = new Point2D(x,y); 
                    ((Objet)o).setPos(position); 
                    
                    return (Objet)o; 
                    
                } catch (Exception e) { 
                    System.out.println("Message erreur readObjet"); 
                    return null; 
                }
            }
            stmt.close();
            
        } catch(SQLException ex) {
            System.err.println("SQLException : " + ex.getMessage()) ;
            return null; 
        }
        return null; 
    }
}
