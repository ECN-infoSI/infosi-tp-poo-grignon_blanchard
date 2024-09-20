/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.woe.tp;
import java.util.Random; 

/**
 *
 * @author grigm
 */
public class World {
    public Archer robin; 
    public Paysan peon; 
    public Lapin bugs; 
    
    public World() { 
        robin = new Archer(); 
        peon = new Paysan(); 
        bugs = new Lapin(); 
        
    }
    
    public void creerMondeAlea(){ 
        Random rand = new Random(); 
        
        this.robin.setPos(new Point2D(rand.nextInt(100),rand.nextInt(100)));
        
        this.peon.setPos(new Point2D(rand.nextInt(100),rand.nextInt(100)));
        while (robin.getPos().getX()==peon.getPos().getX()|| robin.getPos().getY()==peon.getPos().getY()){
            this.peon.setPos(new Point2D(rand.nextInt(100),rand.nextInt(100)));
        }
        
        this.bugs.setPos(new Point2D(rand.nextInt(100),rand.nextInt(100)));
        
        while ((robin.getPos().getX()==bugs.getPos().getX() && robin.getPos().getY()==bugs.getPos().getY()) || (peon.getPos().getX()==bugs.getPos().getX() && peon.getPos().getY()==bugs.getPos().getY())){
            this.bugs.setPos(new Point2D(rand.nextInt(100),rand.nextInt(100)));
        }        
    }
}
