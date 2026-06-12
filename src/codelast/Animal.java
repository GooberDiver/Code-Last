/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codelast;

/**
 *
 * @author Goober
 */

// Importing
import processing.core.PApplet;

public class Animal extends Character {
    //Attribute
    private boolean flying; // Determines if animal is in flying state
    
    /**
     * Constructor for animal class
     * @param p Set up objects to have visual
     * @param x horizontal coordinate
     * @param y vertical coordinate
     * @param speed movement speed
     * @param name name of animal
     * @param imagePath image of object loads from imagePath
     */
    public Animal(PApplet p, int x, int y, int speed, String name, String imagePath) {
        super(p, x, y, speed, name, imagePath); // Calls superclass
        flying = false; //Animal default state is not flying
    }
}