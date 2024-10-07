/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.woe.tp;

/**
 * sous-classe d'Objet représentant les potions de soin
 * @author grigm
 */
public class PotionSoin extends Objet implements Utilisable {
    private int valeurPV; 
    
    /**
     * Constructeur par défaut
     * @author grigm
     */
    public PotionSoin(){
        super();
        this.valeurPV = 3; 
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

    @Override
    public void utilise() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
