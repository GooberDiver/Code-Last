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
import processing.core.PImage;

public class Animal extends Character {
    //Attribute
    private boolean flying; // Determines if animal is in flying state
    
    // Constructor
    public Animal(PApplet p, int x, int y, int speed, String name, String imagePath) {
        super(p, x, y, speed, name, imagePath); // Calls superclass
        flying = false; //Animal default state is not flying
    }
}