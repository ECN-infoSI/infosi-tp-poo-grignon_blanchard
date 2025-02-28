/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.woe.objet.tp;

/**
 * sous-classe d'Objet représentant les potions de soin
 * @author grigm
 */
public class PotionSoin extends Objet implements Utilisable {
    private int valeurPV; 
    private int dureeEffet; 
    
    /**
     * Constructeur par défaut
     * @author grigm
     */
    public PotionSoin(){
        super();
        this.valeurPV = 30; //par défaut +30 PV
        this.dureeEffet=1; 
    }
    
    /**
     * Constructeur avec choix de la valeur pv
     * @author grigm
     * @param pv point de vie ajoutés quand la potion est utilisée 
     */
    public PotionSoin(int pv){
        super();
        this.valeurPV = pv; 
        this.dureeEffet = 1; 
    }
    
    
    /**
     * Ascesseur pour récupérer le nombre de pv qu'apporte la potion de soin
     * @author grigm
     * @return valeurPV la valeur de la potion de soin en PV
     */
    public int getValeurPV() {
        return valeurPV;
    }

    public void setValeurPV(int valeurPV) {
        this.valeurPV = valeurPV;
    }

    public int getDureeEffet() {
        return dureeEffet;
    }

    @Override
    public void setDureeEffet(int dureeEffet) {
        this.dureeEffet = dureeEffet;
    }
    
    
    
    /**
     * méthode pour activer l'objet et modifier les caractéristiques du personnage
     * @author grigm
     * @param c représentant l'instance de Creature qui active l'objet
     */
    @Override
    public void utilise(Creature c) {
        //on met à jour les points de vie de la créature 
        c.setPtVie(c.ptVie+this.valeurPV);
        
    }
    
     /**
     * méthode pour afficher l'effet de l'objet au moment de son activation
     * @author grigm
     */
    public void afficheEffet(){ 
        System.out.println("Potion de Soin + " + this.valeurPV+ " ptVie");
    }
    
}
