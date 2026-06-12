/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codelast;


/**
 *
 * @author 343084331
 */

// Importing
import processing.core.PApplet;

public class Human extends Character {
   // Attributes
    public boolean mounted; // Determine if character is riding an animal
    
    /**
     * Constructor for human class
     * @param p Sets up object to have visual
     * @param x horizontal coordinate
     * @param y vertical coordinate
     * @param speed move speed
     * @param name name of object
     * @param imagePath image of object loads from imagePath
     */
    public Human(PApplet p, int x, int y, int speed, String name, String imagePath) {
        super(p, x, y, speed, name, imagePath); // Calls superclass
        mounted = false; // Default state is not riding an animal
    }
}
